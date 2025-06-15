package com.ajrr.myrickmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajrr.myrickmorty.data.model.Episode
import com.ajrr.myrickmorty.data.repository.EpisodeRepository
import kotlinx.coroutines.launch

class EpisodeViewModel : ViewModel() {

    private val repository = EpisodeRepository()

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> get() = _episodes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loadEpisodes() {
        viewModelScope.launch {
            try {
                val result = repository.getEpisodes()
                _episodes.postValue(result)
            } catch (e: Exception) {
                _error.postValue("Error: ${e.localizedMessage}")
            }
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