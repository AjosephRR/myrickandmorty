package com.ajrr.myrickmorty.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajrr.myrickmorty.databinding.ActivityEpisodeDetailBinding

class EpisodeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera los datos enviados
        val name = intent.getStringExtra("episode_name")
        val code = intent.getStringExtra("episode_code")
        val date = intent.getStringExtra("episode_date")

        // Muestra los datos
        binding.detailName.text = name
        binding.detailCode.text = code
        binding.detailDate.text = date
    }
}