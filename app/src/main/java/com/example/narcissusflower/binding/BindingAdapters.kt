package com.example.narcissusflower.binding

import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("imageFromUrl")
fun ImageView.bindFrom(url: String?) = Glide.with(this)
    .load(url)
    .transition(DrawableTransitionOptions.withCrossFade())
    .into(this)


@BindingAdapter("isGone")
fun View.isGone(result: Boolean = false) {
    visibility = if (result) View.GONE else View.VISIBLE
}

@BindingAdapter("isFabGone")
fun FloatingActionButton.isFabGone(isGone: Boolean = false) = if (isGone) hide() else show()

@BindingAdapter("renderHtml")
fun TextView.bindFromHtml(description: String?) {
    text = if (!description.isNullOrBlank()) {
        movementMethod = LinkMovementMethod.getInstance()
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    } else ""
}

@BindingAdapter("wateringText")
fun TextView.bindWateringInterval(wateringInterval: Int = 1) {
    text = if (wateringInterval > 1) "every $wateringInterval days" else "every day"
}