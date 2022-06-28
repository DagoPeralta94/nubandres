package com.test.myapplication.presentation.recentlyUrl

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.databinding.ItemViewRecentlyUrlBinding
import com.test.myapplication.domain.model.ShortenUrlItem

class RecentlyUrlViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemViewRecentlyUrlBinding.bind(view)

    fun bind(shorten: ShortenUrlItem) {
        with(binding) {
            textViewAliasDefinition.text = shorten.alias
            textViewUrlCompleteDefinition.text = shorten.info.urlComplete
            textViewUrlShortenDefinition.text = shorten.info.urlShorten
        }
    }
}