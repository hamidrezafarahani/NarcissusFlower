package com.example.narcissusflower.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun ImageView.bindFrom(url: String) = Glide.with(this)
    .load(url)
    .transition(DrawableTransitionOptions.withCrossFade())
    .into(this)


@BindingAdapter("isGone")
fun View.isGone(result: Boolean = false) {
    visibility = if (result) View.GONE else View.VISIBLE
}
