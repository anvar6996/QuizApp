package uz.usoft.quizapp.presentation.adapters.levels

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.response.category.Answer
import uz.usoft.quizapp.databinding.ItemAnswersBinding
import uz.usoft.quizapp.databinding.ItemCategoryBinding
import uz.usoft.quizapp.utils.scope


class AnswersAdapter : ListAdapter<Answer, AnswersAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((Answer) -> Unit)? = null
    var Arr1 = arrayOf(
        0, 1, 1, 1, 0,
        0, 1, 0, 1, 0,
        1, 1, 1, 1, 1,
        1, 0, 0, 0, 1,
        1, 1, 1, 1, 1,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0
    )

    object MyDifUtils : DiffUtil.ItemCallback<Answer>() {
        override fun areItemsTheSame(
            oldItem: Answer,
            newItem: Answer
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Answer,
            newItem: Answer
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemAnswersBinding::bind)

        init {
            itemView.setOnClickListener {
                itemListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun load() = bind.scope {
            val value = getItem(absoluteAdapterPosition) as Answer
            bind.answerText.setText(value.answer.uz)
            bind.answerCount.setText("${absoluteAdapterPosition + 1}")
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH = HistoryVH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_answers, parent, false)
    )


    fun setListener(f: (Answer) -> Unit) {
        itemListener = f
    }

}