package uz.usoft.quizapp.presentation.adapters.achivements

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.usoft.quizapp.R
import uz.usoft.quizapp.data.response.StatisticData
import uz.usoft.quizapp.data.response.category.Data
import uz.usoft.quizapp.databinding.ItemAchivementsBinding
import uz.usoft.quizapp.databinding.ItemCategoryBinding
import uz.usoft.quizapp.utils.scope


class AchivementsAdapter : ListAdapter<StatisticData, AchivementsAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((StatisticData) -> Unit)? = null
    var Arr1 = arrayOf(
        0, 1, 1, 1, 0,
        0, 1, 0, 1, 0,
        1, 1, 1, 1, 1,
        1, 0, 0, 0, 1,
        1, 1, 1, 1, 1,
        0, 1, 0, 1, 0,
        0, 1, 1, 1, 0
    )

    object MyDifUtils : DiffUtil.ItemCallback<StatisticData>() {
        override fun areItemsTheSame(
            oldItem: StatisticData,
            newItem: StatisticData
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: StatisticData,
            newItem: StatisticData
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemAchivementsBinding::bind)


        fun load() = bind.scope {
            val value = getItem(absoluteAdapterPosition) as StatisticData
            bind.progress.progress = value.progress.toFloat()
            bind.iconCategory.setImageResource(value.imageView)
            bind.nameText.text = value.name
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_achivements, parent, false)
        )


    fun setListener(f: (StatisticData) -> Unit) {
        itemListener = f
    }

}