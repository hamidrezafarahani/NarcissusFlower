package com.example.narcissusflower.ui.plantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.narcissusflower.data.local.entities.Plant
import com.example.narcissusflower.databinding.ListItemPlantingBinding

class PlantAdapter(
    private val op: (Plant) -> Unit
) : ListAdapter<Plant, PlantAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ListItemPlantingBinding.inflate(layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(plant)
    }

    inner class ViewHolder(
        private val binding: ListItemPlantingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val plant = getItem(absoluteAdapterPosition)
                op(plant)
            }
        }

        fun bind(plant: Plant) = with(binding) {
            this.plant = plant
            executePendingBindings()
        }
    }

    companion object {

        private val DiffCallback = object : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem.plantId == newItem.plantId
            }

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem == newItem
            }
        }
    }
}