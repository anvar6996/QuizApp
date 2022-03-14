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
import uz.usoft.quizapp.data.response.level.Data
import uz.usoft.quizapp.databinding.ItemCategoryBinding
import uz.usoft.quizapp.utils.scope


class CategoryQuestionsAdapter :
    ListAdapter<Data, CategoryQuestionsAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((Int) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemCategoryBinding::bind)

        init {
            itemView.setOnClickListener {
                itemListener?.invoke(absoluteAdapterPosition)
            }
        }

        fun load() = bind.scope {
            val value = getItem(absoluteAdapterPosition) as Data
            Glide.with(imageCategory.context)
                .load(value.category.photo)
                .override(300, 200)
                .into(imageCategory)
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )


    fun setListener(f: (Int) -> Unit) {
        itemListener = f
    }

}