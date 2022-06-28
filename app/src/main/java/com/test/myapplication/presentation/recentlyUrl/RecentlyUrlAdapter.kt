package com.test.myapplication.presentation.recentlyUrl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.R
import com.test.myapplication.domain.model.ShortenUrlItem

class RecentlyUrlAdapter(val data: List<ShortenUrlItem>) :
    RecyclerView.Adapter<RecentlyUrlViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyUrlViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecentlyUrlViewHolder(
            layoutInflater.inflate(
                R.layout.item_view_recently_url,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecentlyUrlViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size
}