package com.skydoves.pokedex.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.skydoves.pokedex.news.model.News

class HomeViewModel : ViewModel() {

  private val listNews = MutableLiveData<List<News>>()
  private val filteredNews = MutableLiveData<List<News>>()

  init {
    fetchNews()
  }

  private fun fetchNews() {
    val db = Firebase.firestore
    db.collection("news")
      .get()
      .addOnSuccessListener { documents ->
        val newsList = mutableListOf<News>()
        for (document in documents) {
          val news = document.toObject(News::class.java)
          newsList.add(news)
        }
        listNews.value = newsList
        filteredNews.value = newsList
      }
      .addOnFailureListener { exception ->
        // Handle any errors
      }
  }

  fun getListNews(): LiveData<List<News>> = filteredNews

  fun filterNews(query: String) {
    val allNews = listNews.value ?: return
    val filteredList = allNews.filter { it.title.contains(query, ignoreCase = true) }
    filteredNews.value = filteredList
  }
}
