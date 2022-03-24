package uz.usoft.quizapp.presentation.screens

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import uz.usoft.quizapp.data.response.category.Data
import uz.usoft.quizapp.databinding.ScreenQuestionBinding
import uz.usoft.quizapp.presentation.adapters.levels.AnswersAdapter
import uz.usoft.quizapp.presentation.viewmodels.questions.QuestionsScreenViewModel
import uz.usoft.quizapp.presentation.viewmodelsimpl.questions.QuestionsScreenViewModelImpl
import uz.usoft.quizapp.utils.scope


@AndroidEntryPoint
class QuestionScreen : Fragment(R.layout.screen_question) {
    private val bind by viewBinding(ScreenQuestionBinding::bind)
    private val adapterAnswer = AnswersAdapter()
    private var doubleChanseController = false
    private var countAnswerClick = 0
    private val viewModel: QuestionsScreenViewModel by viewModels<QuestionsScreenViewModelImpl>()
    private var mRewardedAd: RewardedAd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        loadReward()
        arguments?.let {
            val data = it.getSerializable("value") as Data
            questionText.setText(data.description?.uz.toString())
            adapterAnswer.submitList(data.answers)
            Glide.with(questionsImage.context)
                .load(data.photos?.get(1)?.path)
                .override(300, 200)
                .into(questionsImage)
            bind.answerText1.text = data.answers[0].answer.uz
            bind.answerText2.text = data.answers[1].answer.uz
            bind.answerText3.text = data.answers[2].answer.uz
            bind.answerText4.text = data.answers[3].answer.uz

            bgAnswerLine1.setOnClickListener {
                answerClick(data.answers[0].correct, bgAnswerLine1, answerCount1, answerText1)
                searchCorrectAnswer(data)
            }
            bgAnswerLine2.setOnClickListener {
                answerClick(data.answers[1].correct, bgAnswerLine2, answerCount2, answerText2)
                searchCorrectAnswer(data)

            }

            bgAnswerLine3.setOnClickListener {
                answerClick(data.answers[2].correct, bgAnswerLine3, answerCount3, answerText3)
                searchCorrectAnswer(data)
            }
            bgAnswerLine4.setOnClickListener {
                answerClick(data.answers[3].correct, bgAnswerLine4, answerCount4, answerText4)
                searchCorrectAnswer(data)

            }

            bind.fiftyFifty.setOnClickListener {
                fiftyFifty(data)
            }
        }
        bind.doubleChanse.setOnClickListener {
            doubleChanse()
        }

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
            imageView.setImageResource(R.drawable.bg_answer_correct)
            bind.animate.visibility = View.VISIBLE
            bind.animate.playAnimation()
            val value = bind.starsCount.text.toString().toInt()
            loadStars(value, value + 40)
        } else {
            if (!doubleChanseController) {
                fullScreen()
                bind.failAnimate.visibility = View.INVISIBLE
                bind.failAnimate.playAnimation()
                if (mRewardedAd != null) {
                    mRewardedAd?.show(requireActivity()) {

                    }
                }
            }
            imageView.setImageResource(R.drawable.bg_answer_false)
        }

        textView.visibility = View.INVISIBLE
        textAnswer.setTextColor(resources.getColor(R.color.realWhite))
    }

    private fun loadStars(startCount: Int, finalCount: Int) {
        val valueAnimator = ValueAnimator.ofInt(startCount, finalCount)
        valueAnimator.duration = 1500
        valueAnimator.addUpdateListener {
            bind.starsCount.text = it.animatedValue.toString()
        }
        valueAnimator.start()
    }

    private fun searchCorrectAnswer(data: Data) = bind.scope {
        if (doubleChanseController) {
            return@scope
        }
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

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {

            }

            override fun onAdDismissedFullScreenContent() {
                mRewardedAd = null
            }
        }
    }

    private fun doubleChanse() {
        doubleChanseController = true
    }

    private fun fiftyFifty(data: Data) = bind.scope {
        var countFiftyFifty = 0
        for (i in 0 until data.answers.size) {
            if (countFiftyFifty == 2) {
                return@scope
            }
            if (!data.answers[i].correct) {

                getInvisible().let {
                    if (i == 0) {
                        answerText1.visibility = it
                        answerCount1.visibility = it
                        bgAnswerLine1.visibility = it
                    }
                    if (i == 1) {
                        answerText2.visibility = it
                        answerCount2.visibility = it
                        bgAnswerLine2.visibility = it
                    }
                    if (i == 2) {
                        answerText3.visibility = it
                        answerCount3.visibility = it
                        bgAnswerLine3.visibility = it
                    }
                    if (i == 3) {
                        answerText4.visibility = it
                        answerCount4.visibility = it
                        bgAnswerLine4.visibility = it
                    }
                }
                countFiftyFifty += 1

            }

        }
    }

    private fun correctAnswer() {

    }

    private fun nextQuestions() {

    }

    private fun getGone() = View.GONE
    private fun getVisible() = View.VISIBLE
    private fun getInvisible() = View.INVISIBLE

}