package com.ballomo.thelastavenger.ui.hero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ballomo.shared.domain.hero.ListHeroInformation
import com.ballomo.shared.domain.hero.LoadHeroInformation
import com.ballomo.thelastavenger.R
import com.ballomo.thelastavenger.databinding.ItemInformationHeroPostBinding

class PostHeroListAdapter: RecyclerView.Adapter<PostHeroListViewHolder>() {

    lateinit var heroList: ListHeroInformation

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHeroListViewHolder {
        val binding: ItemInformationHeroPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_information_hero_post,
            parent,
            false)

        return PostHeroListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::heroList.isInitialized) heroList.heroInformation.size else 0
    }

    override fun onBindViewHolder(holder: PostHeroListViewHolder, position: Int) {
        holder.bind(heroList.heroInformation[position])
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