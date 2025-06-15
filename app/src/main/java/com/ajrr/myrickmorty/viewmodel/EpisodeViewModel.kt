package com.ajrr.myrickmorty.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajrr.myrickmorty.data.model.Episode
import com.ajrr.myrickmorty.data.repository.EpisodeRepository
import kotlinx.coroutines.launch

class EpisodeViewModel (application: Application) : AndroidViewModel(application) {


    private val repository = EpisodeRepository(application.applicationContext)

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> get() = _episodes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun loadEpisodes(query: String? = null) {
        viewModelScope.launch {
            -loading.value = true
                val data = repository.getEpisodes(name = query)
                _episodes.value = data
            _loading.value = false
        }
    }

    fun searchEpisodes(query: String){
        viewModelScope.launch {
            try {
                val result = repository.getEpisodes(name = query)
                _episodes.postValue(result)
            }catch (e: Exception){
                _error.postValue("No se encontraron episodios con es enombre")

            }
        }
    }
}