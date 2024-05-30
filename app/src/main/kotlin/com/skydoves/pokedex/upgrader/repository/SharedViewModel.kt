package com.skydoves.pokedex.upgrader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skydoves.pokedex.upgrader.model.News

class SharedViewModel : ViewModel() {
    val selectedNews = MutableLiveData<News>()

    fun select(news: News) {
        selectedNews.value = news
    }

    fun getSelected(): LiveData<News> {
        return selectedNews
    }
}
