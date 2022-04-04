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
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.roomdata.entity.QuestionData
import uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
import uz.usoft.quizapp.databinding.ItemCategoryBinding
import uz.usoft.quizapp.utils.scope


class CategoryQuestionsAdapter :
    ListAdapter<QuestionAnswers, CategoryQuestionsAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((QuestionAnswers) -> Unit)? = null
    var Arr1 = arrayOf(
        0, 1, 1, 1, 0,
        0, 1, 0, 1, 0,
        1, 1, 1, 1, 1,
        1, 0, 0, 0, 1,
        1, 1, 1, 1, 1,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0
    )

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
            itemView.setOnClickListener {
//                val value = getItem(absoluteAdapterPosition) as _root_ide_package_.uz.usoft.quizapp.data.roomdata.realationdata.QuestionAnswers
//                if (value.stateShow == 1) {
                itemListener?.invoke(getItem(absoluteAdapterPosition))
//                }

            }
        }

        fun load() = bind.scope {
            val value = getItem(absoluteAdapterPosition) as QuestionAnswers

//            if (value.stateShow == 1) {
            Glide.with(imageCategory.context)
                .load(value.questionData.category.photo)
                .override(300, 200)
                .into(imageCategory)
            bind.imageCategory.visibility = View.VISIBLE
//            } else {
//                bind.imageCategory.visibility = View.INVISIBLE
//            }
        }
    }

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