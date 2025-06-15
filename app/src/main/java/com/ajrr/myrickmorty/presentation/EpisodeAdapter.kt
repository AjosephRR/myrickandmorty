package com.ajrr.myrickmorty.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajrr.myrickmorty.data.model.Episode
import com.ajrr.myrickmorty.databinding.ItemEpisodeBinding
import java.text.SimpleDateFormat
import java.util.Locale


class EpisodeAdapter(
    private val episodes: List<Episode>,
    private val onItemClick: (Episode) -> Unit
) : RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode) {
            binding.episodeName.text = episode.name
            binding.episodeCode.text = episode.episode
            binding.episodeDate.text = formatDate(episode.air_date)

            val characterNames = episode.characters.take(3).joinToString(", ") {
                it.substringAfterLast("/")
            }
            binding.episodeCharacters.text = "Personajes: $characterNames"


            binding.root.setOnClickListener {
                onItemClick(episode)
            }
        }

        private fun formatDate(dateStr: String): String {
            return try {
                val parser = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
                val date = parser.parse(dateStr)
                val formatter = SimpleDateFormat("dd MMM yyyy", Locale("es", "MX"))
                formatter.format(date)
            } catch (e: Exception) {
                dateStr
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
        holder.itemView.translationX = -300f
        holder.itemView.alpha = 0f
        holder.itemView.animate()
            .translationX(0f)
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    override fun getItemCount(): Int = episodes.size
}
