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
import uz.usoft.quizapp.data.response.map.MapLevelsResponseItem
import uz.usoft.quizapp.databinding.ItemLelvelBinding
import uz.usoft.quizapp.utils.scope


class MapLevelsAdapter :
    ListAdapter<MapLevelsResponseItem, MapLevelsAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((Int) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<MapLevelsResponseItem>() {
        override fun areItemsTheSame(
            oldItem: MapLevelsResponseItem,
            newItem: MapLevelsResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MapLevelsResponseItem,
            newItem: MapLevelsResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemLelvelBinding::bind)

        init {
            itemView.setOnClickListener {
                itemListener?.invoke(getItem(absoluteAdapterPosition).id)
            }
        }

        fun load() = bind.scope {
            val value = getItem(absoluteAdapterPosition) as MapLevelsResponseItem
            bind.textLevel.text = value.id.toString()
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {

        holder.load()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_lelvel, parent, false)
        view.translationX = 150f
        return HistoryVH(view)
//        HistoryVH(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_lelvel, parent, false)
//        )
    }


    fun setListener(f: (Int) -> Unit) {
        itemListener = f
    }

}