package com.ballomo.thelastavenger.ui.hero.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ballomo.shared.data.NetworkState
import com.ballomo.shared.data.entity.hero.Results
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.databinding.ItemInformationHeroPostBinding
import com.ballomo.thelastavenger.ui.hero.all.PostHeroListViewHolder
import com.ballomo.thelastavenger.ui.hero.model.LoadHeroInformation

class PostHeroPageListAdapter(
    private val retryCallback:() -> Unit
): PagedListAdapter<Results, RecyclerView.ViewHolder>(DiffCallback) {
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.item_information_hero_post -> {
                val binding: ItemInformationHeroPostBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_information_hero_post,
                    parent,
                    false)
                PostHeroListViewHolder(binding)
            }

            R.layout.network_state_layout -> NetWorkStateViewHolder.create(
                parent,
                retryCallback)

            else -> throw IllegalArgumentException("unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            R.layout.item_information_hero_post -> {
                val result = getItem(position)

                val loadHeroInformation = result?.let {
                    return@let LoadHeroInformation(
                        it.name ?: "",
                        it.image ?: ""
                    )
                }

                loadHeroInformation?.let {
                    (holder as PostHeroListViewHolder).bind(it)
                }
            }

            R.layout.network_state_layout -> (holder as NetWorkStateViewHolder).bindTo(networkState)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == (itemCount - 1)) {
            R.layout.network_state_layout
        }else {
            R.layout.item_information_hero_post
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow():Boolean = networkState != null && networkState != NetworkState.LOADED

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean =
                oldItem == newItem
        }
    }
}