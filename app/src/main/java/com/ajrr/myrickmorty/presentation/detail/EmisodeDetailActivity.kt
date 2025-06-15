package com.ajrr.myrickmorty.presentation.detail

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ajrr.myrickmorty.databinding.ActivityEpisodeDetailBinding
import com.bumptech.glide.Glide


class EpisodeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera los datos enviados por intent
        val name = intent.getStringExtra("episode_name")
        val code = intent.getStringExtra("episode_code")
        val date = intent.getStringExtra("episode_date")
        val characterUrls = intent.getStringArrayListExtra("episode_characters")

        // Muestra los datos del episodio
        binding.detailName.text = name
        binding.detailCode.text = code
        binding.detailDate.text = date

        // Log para depurar
        Log.d("EpisodeDetail", "Characters received: $characterUrls")

        // Convierte las URLs de personajes en URLs de imágenes
        val imageUrls = characterUrls?.mapNotNull { url ->
            val id = url.substringAfterLast("/").toIntOrNull()
            id?.let { "https://rickandmortyapi.com/api/character/avatar/$it.jpeg" }
        }

        // Muestra las imágenes
        imageUrls?.let { mostrarPersonajes(it) }
    }

    private fun mostrarPersonajes(urls: List<String>) {
        val container = binding.charactersContainer
        container.removeAllViews()

        urls.forEachIndexed { index, imageUrl ->
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(16, 0, 16, 0)
                layoutParams = params
            }

            val imageView = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(200, 200)
            }

            val numberText = TextView(this).apply {
                text = "${index + 1}"
                textSize = 16f
                gravity = android.view.Gravity.CENTER_HORIZONTAL
            }

            Glide.with(this)
                .load(imageUrl)
                .into(imageView)

            layout.addView(numberText)
            layout.addView(imageView)
            container.addView(layout)
        }
    }
}