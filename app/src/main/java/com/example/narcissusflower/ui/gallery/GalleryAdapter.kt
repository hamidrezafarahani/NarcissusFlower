package com.example.narcissusflower.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.narcissusflower.data.remote.dtos.UnSplashPhoto
import com.example.narcissusflower.databinding.ListItemPhotoBinding

class GalleryAdapter(
    private val owner: LifecycleOwner,
    private val op: (UnSplashPhoto) -> Unit
) : PagingDataAdapter<UnSplashPhoto, GalleryAdapter.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = getItem(position)
        photo?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ListItemPhotoBinding.inflate(layout, parent, false)
        return ViewHolder(binding)
    }


    inner class ViewHolder(
        private val binding: ListItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.lifecycleOwner = owner
            itemView.setOnClickListener {
                val photo = getItem(absoluteAdapterPosition)
                photo?.let(op)
            }
        }

        fun bind(photo: UnSplashPhoto) = with(binding) {
            this.photo = photo
            executePendingBindings()
        }
    }

    companion object {

        private val DiffCallback = object : DiffUtil.ItemCallback<UnSplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnSplashPhoto, newItem: UnSplashPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnSplashPhoto,
                newItem: UnSplashPhoto
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}