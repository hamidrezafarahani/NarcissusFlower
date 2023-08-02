package com.example.narcissusflower.ui.garden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.narcissusflower.data.local.entities.PlantAndGardenPlantings
import com.example.narcissusflower.databinding.ListItemGardenPlantingBinding

class GardenPlantingAdapter(
    private val op: (PlantAndGardenPlantings) -> Unit
) : ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ListItemGardenPlantingBinding.inflate(layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plantAndGardenPlantings = getItem(position)
        holder.bind(plantAndGardenPlantings)
    }

    inner class ViewHolder(
        private val binding: ListItemGardenPlantingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val plantAndGardenPlantings = getItem(absoluteAdapterPosition)
                op(plantAndGardenPlantings)
            }
        }

        fun bind(plantings: PlantAndGardenPlantings) = with(binding) {
            viewModel = PlantAndGardenViewModel(plantings)
            executePendingBindings()
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
            override fun areItemsTheSame(
                oldItem: PlantAndGardenPlantings,
                newItem: PlantAndGardenPlantings
            ): Boolean {
                return oldItem.plant.plantId == newItem.plant.plantId
            }

            override fun areContentsTheSame(
                oldItem: PlantAndGardenPlantings,
                newItem: PlantAndGardenPlantings
            ): Boolean {
                return oldItem.plant == newItem.plant
            }
        }
    }
}