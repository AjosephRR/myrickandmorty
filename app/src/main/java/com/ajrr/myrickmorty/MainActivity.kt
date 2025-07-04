package com.ajrr.myrickmorty

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajrr.myrickmorty.auth.LoginActivity
import com.ajrr.myrickmorty.data.model.Episode
import com.ajrr.myrickmorty.data.remote.RetrofitInstance
import com.ajrr.myrickmorty.databinding.ActivityMainBinding
import com.ajrr.myrickmorty.presentation.EpisodeAdapter
import com.ajrr.myrickmorty.presentation.detail.EpisodeDetailActivity
import com.ajrr.myrickmorty.viewmodel.EpisodeViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: EpisodeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.episodeRecyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        viewModel.loadEpisodes()

        binding.searchEditText.addTextChangedListener { editable ->
            val query = editable.toString().trim()
            if (query.isNotEmpty()){
                viewModel.searchEpisodes(query)
            }else{
                viewModel.loadEpisodes()
            }
        }

        binding.logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        viewModel.episodes.observe(this){ episodes ->
            binding.episodeRecyclerView.adapter = EpisodeAdapter(episodes){ episode ->
                val intent = Intent(this, EpisodeDetailActivity::class.java)
                intent.putExtra("episode_name", episode.name)
                intent.putExtra("episode_code", episode.episode)
                intent.putExtra("episode_date", episode.air_date)
                startActivity(intent)
            }

        }



    }

}