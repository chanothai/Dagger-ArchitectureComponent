package com.ballomo.thelastavenger.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ballomo.shared.util.getParentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val daggerActivity:AppCompatActivity? = view.getParentActivity()
    if (daggerActivity != null && visibility != null) {
        visibility.observe(daggerActivity, Observer {value->
            view.visibility = value?:View.VISIBLE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: String?) {
    text?.let {
        view.text = it
    }
}

@BindingAdapter(value = ["imageUrl", "circleCrop", "drawable"], requireAll = false)
fun setImage(view: ImageView, url: String?,isCircleCrop: Boolean, drawable: Drawable?) {

    val option = when(isCircleCrop) {
        true , drawable != null -> RequestOptions().apply {
            this.circleCrop()
            this.placeholder(drawable)
            return@apply
        }
        false, drawable != null -> RequestOptions().apply {
            this.centerCrop()
            this.placeholder(drawable)
        }
    }

    url?.let {
        Glide.with(view.context)
            .load(it)
            .apply(option)
            .into(view)
    }
}

@BindingAdapter("adapterHero")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}