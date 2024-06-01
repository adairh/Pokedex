package me.sjihh.pokedex.news.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.sjihh.pokedex.news.model.News

/**
 *
 * Lam Nguyen Huy Hoang
 */


class SharedViewModel : ViewModel() {
    val selectedNews = MutableLiveData<News>()

    fun select(news: News) {
        selectedNews.value = news
    }

    fun getSelected(): LiveData<News> {
        return selectedNews
    }
}
