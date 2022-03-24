package uz.usoft.quizapp.presentation.adapters.levels

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            if (absoluteAdapterPosition != 0) {
                startRoad.visibility = getInvisible()
            }
            if (absoluteAdapterPosition == 0) {
                textLevelLine.visibility = getInvisible()
            }
            if (absoluteAdapterPosition % 2 == 0) {

                getInvisible().let {
                    textLevelStroke.visibility = it
                    imageLock2.visibility = it
                    textLevelDouble.visibility = it
                    textLevelDoubleLine.visibility = it
                }
                bind.textLevel.text = value.id.toString()
//                loadLevel(itemView)
            } else {
                getInvisible().let {
//                    textLevelDoubleLine.visibility = it
                    textLevelLine.visibility = it
                    textLevel.visibility = it
                    textLevelDoubleStroke.visibility = it
                    imageLock.visibility = it
                }

//                loadLevelDouble(itemView)
                bind.textLevelDouble.text = value.id.toString()
            }
        }
    }

    private fun getInvisible() = View.INVISIBLE

    private fun loadLevel(view: View) {
        var textLevel=view.findViewById<TextView>(R.id.textLevel)
        val valueAnimator = ValueAnimator.ofInt(0, 75)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener {
            textLevel.height = it.values.toString().toInt()
            textLevel.width = it.values.toString().toInt()
        }
        valueAnimator.start()
    }

    private fun loadLevelDouble(view: View) {
        var textLevelDouble=view.findViewById<TextView>(R.id.textLevelDouble)
        val valueAnimator = ValueAnimator.ofInt(0, 75)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener {
            textLevelDouble.height = it.values.toString().toInt()
            textLevelDouble.width = it.values.toString().toInt()
        }
        valueAnimator.start()
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        val view: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_lelvel, parent, false)
        return HistoryVH(view)
    }


    fun setListener(f: (Int) -> Unit) {
        itemListener = f
    }

}