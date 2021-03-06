package com.ballomo.thelastavenger.ui.hero.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.databinding.ItemInformationHeroPostBinding
import com.ballomo.thelastavenger.ui.hero.model.ListHeroInformation
import com.ballomo.thelastavenger.ui.hero.model.LoadHeroInformation

class PostHeroListAdapter: RecyclerView.Adapter<PostHeroListViewHolder>() {

    var heroList: ListHeroInformation? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHeroListViewHolder {
        val binding: ItemInformationHeroPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_information_hero_post,
            parent,
            false)

        return PostHeroListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return heroList?.let {
            return@let if (it.heroInformation.isNotEmpty()) it.heroInformation.size else 0
        } ?: 0
    }

    override fun onBindViewHolder(holder: PostHeroListViewHolder, position: Int) {
        heroList?.let {
            holder.bind(it.heroInformation[position])
        }
    }

    fun updateListHeroInformation(listHeroInformation: ListHeroInformation) {
        this.heroList = listHeroInformation
        notifyDataSetChanged()
    }
}

class PostHeroListViewHolder(private val binding:ItemInformationHeroPostBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(heroPost: LoadHeroInformation) {
        binding.heroInformation = heroPost
        binding.executePendingBindings()
    }
}