package uz.usoft.quizapp.presentation.adapters.levels

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import render.animations.Bounce
import render.animations.Flip
import render.animations.Render
import uz.usoft.quizapp.R
import uz.usoft.quizapp.app.App
import uz.usoft.quizapp.data.others.AnswerPassedData
import uz.usoft.quizapp.data.others.StaticValues
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import uz.usoft.quizapp.databinding.ItemCategoryBinding
import uz.usoft.quizapp.utils.scope
import kotlin.random.Random


class CategoryQuestionsAdapter :
    ListAdapter<QuestionAnswers, CategoryQuestionsAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((QuestionAnswers) -> Unit)? = null
    private val myRandomValues = Random.nextInt(0, 19)
    var list = ArrayList<AnswerPassedData>()


    object MyDifUtils : DiffUtil.ItemCallback<QuestionAnswers>() {
        override fun areItemsTheSame(
            oldItem: QuestionAnswers,
            newItem: QuestionAnswers
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: QuestionAnswers,
            newItem: QuestionAnswers
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemCategoryBinding::bind)

        init {

        }

        fun load() = bind.scope {
            val value = getItem(absoluteAdapterPosition) as QuestionAnswers


            if (value.state == 1) {
                bind.imageCategory.visibility = View.VISIBLE
            } else {
                bind.imageCategory.visibility = View.INVISIBLE
            }
            imageCategory.setOnClickListener {
//                searchPassageCategory(itemId.toInt())
                if (value.state == 1) {
                    itemListener?.invoke(getItem(absoluteAdapterPosition))
                }

            }


            val list = StaticValues.counter
            if (list.size != 0) {
                for (i in 0 until list.size) {
                    if (value.position == list[i].id) {
                        itemView.isEnabled = false
                        val render = Render(App.instance)
                        render.setAnimation(Flip().InY(imageCategory))
                        render.start()
                        if (list[i].correct) {
                            imageCategory.setImageResource(R.drawable.ic_correct)
                        } else {
                            imageCategory.setImageResource(R.drawable.ic_wrong)
                        }
                        return@scope
                    }else
                    {
                        if (value.position != list[i].id && (value.position - 1 == list[i].id || value.position + 1 == list[i].id)) {
//                        Glide.with(imageCategory.context)
//                            .load(value.questionData.categoryPhoto)
//                            .into(imageCategory)
                            imageCategory.setImageResource(R.drawable.bg_answer_false)
                            imageCategory.isEnabled = true
                        } else
                        {

                            if ((value.position != list[i].id) && (value.position - 5 == list[i].id || value.position + 5 == list[i].id)) {

//                        Glide.with(imageCategory.context)
//                            .load(value.questionData.categoryPhoto)
//                            .into(imageCategory)
                                imageCategory.setImageResource(R.drawable.bg_answer_false)
                                imageCategory.isEnabled = true
                                return@scope
                            }
                            else {
                                imageCategory.setImageResource(R.drawable.play_bt)
                                imageCategory.isEnabled = false
                                return@scope
                            }                        }
                    }



                }

            } else {
                Glide.with(imageCategory.context)
                    .load(value.questionData.categoryPhoto)
                    .into(imageCategory)
            }

//            }

        }
    }
/*

  if (value.state == 1 && list.size > i) {

                        if (value.position != list[i].id && (value.position - 1 == list[i].id || value.position + 1 == list[i].id)) {

                            Glide.with(imageCategory.context)
                                .load(value.questionData.categoryPhoto)
                                .into(imageCategory)

                            itemView.isEnabled = true

                        } else if ((value.position != list[i].id) && (value.position - 5 == list[i].id || value.position + 5 == list[i].id)) {

                            Glide.with(imageCategory.context)
                                .load(value.questionData.categoryPhoto)
                                .into(imageCategory)

                            itemView.isEnabled = true

                        } else {
                            imageCategory.setImageResource(R.drawable.play_bt)
                            itemView.isEnabled = false
                        }
                        if (value.position == list[i].id) {
                            itemView.isEnabled = true
                            val render = Render(App.instance)
                            render.setAnimation(Flip().InY(imageCategory))
                            render.start()
                            if (list[i].correct) {

                                imageCategory.setImageResource(R.drawable.ic_correct)

                            } else {
                                imageCategory.setImageResource(R.drawable.ic_wrong)

                            }
                            return@scope

                        }
                    }
* */
//    private fun searchPassageCategory(clickId: Int): Boolean {
//        if (clickId ==) {
//
//        }
//    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )


    fun setListener(f: (QuestionAnswers) -> Unit) {
        itemListener = f
    }

}