package uz.usoft.quizapp.presentation.screens

import android.animation.ValueAnimator
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.others.AnswerPassedData
import uz.usoft.quizapp.data.others.StaticValues
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import uz.usoft.quizapp.databinding.ScreenQuestionBinding
import uz.usoft.quizapp.presentation.viewmodels.questions.QuestionsScreenViewModel
import uz.usoft.quizapp.presentation.viewmodelsimpl.questions.QuestionsScreenViewModelImpl
import uz.usoft.quizapp.utils.scope
import kotlin.random.Random


@AndroidEntryPoint
class QuestionScreen : Fragment(R.layout.screen_question) {
    private val bind by viewBinding(ScreenQuestionBinding::bind)
    private var doubleChanseController = false
    private var countAnswerClick = 0
    private var clickAnswerId: Int = -1
    private var correct: Boolean = false
    private val viewModel: QuestionsScreenViewModel by viewModels<QuestionsScreenViewModelImpl>()
    private var mRewardedAd: RewardedAd? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        loadReward()
        arguments?.let {
            val value = StaticValues.questionAnswers
//            showToast(value.toString())
            val data = value.questionData
            questionText.text = data.descriptionRu
            questionText.movementMethod = ScrollingMovementMethod()
            Glide.with(questionsImage.context)
                .load(value.photos[0].path)
                .override(300, 200)
                .into(questionsImage)
            bind.answerText1.text = value.answers[0].answerTranslateData.aRu
            bind.answerText2.text = value.answers[1].answerTranslateData.aRu
            bind.answerText3.text = value.answers[2].answerTranslateData.aRu
            bind.answerText4.text = value.answers[3].answerTranslateData.aRu

            answerText1.setOnClickListener {
                answerClick(value.answers[0].correct, bgAnswerLine1, answerCount1, answerText1)
                searchCorrectAnswer(value)
            }
            answerText2.setOnClickListener {
                answerClick(value.answers[1].correct, bgAnswerLine2, answerCount2, answerText2)
                searchCorrectAnswer(value)
            }

            answerText3.setOnClickListener {
                answerClick(value.answers[2].correct, bgAnswerLine3, answerCount3, answerText3)
                searchCorrectAnswer(value)
            }
            answerText4.setOnClickListener {
                answerClick(value.answers[3].correct, bgAnswerLine4, answerCount4, answerText4)
                searchCorrectAnswer(value)

            }

            bind.fiftyFifty.setOnClickListener {
                fiftyFifty(value)
            }

            bind.reverseContext.setOnClickListener {
                correctAnswer(value)
            }
        }
        bind.doubleChanse.setOnClickListener {
            doubleChanse()
        }

        viewModel.liveDataScreenClose.observe(viewLifecycleOwner, screenCloseObserver)
    }

    private fun answerClick(
        controller: Boolean,
        imageView: ImageView,
        textView: TextView,
        textAnswer: TextView
    ) {
        countAnswerClick++
        if (countAnswerClick == 2) {
            doubleChanseController = false
        }
        if (controller) {
            StaticValues.counter.add(
                AnswerPassedData(
                    StaticValues.questionAnswers.position, true
                )
            )
            imageView.setImageResource(R.drawable.bg_answer_correct)
            bind.animate.visibility = View.VISIBLE
            bind.animate.playAnimation()
            val value = bind.starsCount.text.toString().toInt()
            loadStars(value, value + 40)
            viewModel.screenClose("1")

        } else {
            if (!doubleChanseController) {
                var data = AnswerPassedData(
                    StaticValues.questionAnswers.position, false
                )
                if (StaticValues.counter.contains(data)) {
                    StaticValues.counter.add(data)
                }

                imageView.setImageResource(R.drawable.bg_answer_false)
                bind.failAnimate.visibility = View.INVISIBLE

                fullScreen()
                bind.failAnimate.playAnimation()
                if (mRewardedAd != null) {
                    mRewardedAd?.show(requireActivity()) {
                    }
                }
            }
        }
        textView.visibility = View.INVISIBLE
        textAnswer.setTextColor(resources.getColor(R.color.realWhite))
    }

    private fun loadStars(startCount: Int, finalCount: Int) {
        val valueAnimator = ValueAnimator.ofInt(startCount, finalCount)
        valueAnimator.duration = 1000
        valueAnimator.addUpdateListener {
            bind.starsCount.text = it.animatedValue.toString()
        }
        valueAnimator.start()
    }

    private fun searchCorrectAnswer(data: QuestionAnswers) = bind.scope {
        if (doubleChanseController) {
            return@scope
        }
        clickAnswerId = data.questionData.questionDataId
        for (i in 0 until data.answers.size) {
            if (data.answers[i].correct) {
                if (i == 0) {
                    loadCorrectAnswer(bgAnswerLine1, answerCount1, answerText1)
                }
                if (i == 1) {
                    loadCorrectAnswer(bgAnswerLine2, answerCount2, answerText2)
                }
                if (i == 2) {
                    loadCorrectAnswer(bgAnswerLine3, answerCount3, answerText3)
                }
                if (i == 3) {
                    loadCorrectAnswer(bgAnswerLine4, answerCount4, answerText4)
                }
                return@scope
            }
        }
    }

    private fun loadCorrectAnswer(
        imageView: ImageView,
        textView: TextView,
        textAnswer: TextView
    ) {
        correct = true
        imageView.setImageResource(R.drawable.bg_answer_correct)
        textView.visibility = View.INVISIBLE
        textAnswer.setTextColor(resources.getColor(R.color.realWhite))
    }

    private fun loadReward() {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(
            requireContext(),
            "ca-app-pub-3940256099942544/5224354917",
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    mRewardedAd = rewardedAd
                }
            })
    }


    private fun fullScreen() {

        mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdShowedFullScreenContent() {

            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {

            }

            override fun onAdDismissedFullScreenContent() {
                mRewardedAd = null
                findNavController().popBackStack()
            }
        }
    }

    private fun doubleChanse() {
        doubleChanseController = true
    }

    private fun fiftyFifty(data: QuestionAnswers) = bind.scope {
        var countFiftyFifty = 0
        for (i in 0 until data.answers.size) {
            if (countFiftyFifty == 2) {
                return@scope
            }
            if (!data.answers[i].correct) {

                getInvisible().let {
                    if (i == 0) {
                        if (bgAnswerLine1.visibility == getVisible()) {
                            answerText1.visibility = it
                            answerCount1.visibility = it
                            bgAnswerLine1.visibility = it
                            progressLine1.visibility = it
                        }
                    }
                    if (i == 1) {
                        if (bgAnswerLine2.visibility == getVisible()) {
                            answerText2.visibility = it
                            answerCount2.visibility = it
                            bgAnswerLine2.visibility = it
                            progressLine2.visibility = it
                        }

                    }
                    if (i == 2) {
                        if (bgAnswerLine3.visibility == getVisible()) {

                            progressLine3.visibility = it
                            answerText3.visibility = it
                            answerCount3.visibility = it
                            bgAnswerLine3.visibility = it
                        }
                    }
                    if (i == 3) {
                        if (bgAnswerLine4.visibility == getVisible()) {
                            progressLine4.visibility = it
                            answerText4.visibility = it
                            answerCount4.visibility = it
                            bgAnswerLine4.visibility = it
                        }
                    }
                }
                countFiftyFifty += 1

            }

        }
    }

    private fun correctAnswer(data: QuestionAnswers) = bind.scope {
        for (i in 0 until data.answers.size) {
            if (!data.answers[i].correct) {
                val myRandomValues = Random.nextInt(15, 20)
                getVisible().let { visible ->
                    getInvisible().let { invisible ->

                        if (i == 0) {
                            if (progressLine1.visibility != invisible)
                                progressLine1.visibility = visible
                            progressLine1.progress = myRandomValues.toFloat()
                        }
                        if (i == 1) {
                            if (progressLine2.visibility != invisible) {
                                progressLine2.visibility = visible
                                progressLine2.progress = myRandomValues.toFloat()
                            }
                        }
                        if (i == 2) {
                            if (progressLine3.visibility != invisible) {
                                progressLine3.visibility = visible
                                progressLine3.progress = myRandomValues.toFloat()
                            }
                        }
                        if (i == 3) {
                            if (progressLine4.visibility != invisible) {
                                progressLine4.visibility = visible
                                progressLine4.progress = myRandomValues.toFloat()

                            }
                        }
                    }

                }

            }
            if (data.answers[i].correct) {
                val myRandomValues = Random.nextInt(40, 99)
                getVisible().let {
                    if (i == 0) {
                        progressLine1.visibility = it
                        progressLine1.progress = myRandomValues.toFloat()
                    }
                    if (i == 1) {
                        progressLine2.visibility = it
                        progressLine2.progress = myRandomValues.toFloat()
                    }
                    if (i == 2) {
                        progressLine3.visibility = it
                        progressLine3.progress = myRandomValues.toFloat()
                    }
                    if (i == 3) {
                        progressLine4.visibility = it
                        progressLine4.progress = myRandomValues.toFloat()
                    }
                }
            }
        }

    }


    private fun getGone() = View.GONE
    private fun getVisible() = View.VISIBLE
    private fun getInvisible() = View.INVISIBLE


    private val screenCloseObserver = Observer<Unit>
    {
        findNavController().popBackStack()
    }

}


