package com.ajrr.myrickmorty.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajrr.myrickmorty.data.model.Episode
import com.ajrr.myrickmorty.databinding.ItemEpisodeBinding


class EpisodeAdapter(
    private val episodes: List<Episode>,
    private val onItemClick: (Episode) -> Unit
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode) {
            binding.episodeName.text = episode.name
            binding.episodeCode.text = episode.episode
            binding.episodeDate.text = episode.air_date

            binding.root.setOnClickListener {
                onItemClick(episode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size
}
