package com.skydoves.pokedex.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.skydoves.pokedex.news.model.News

/**
 * [HomeViewModel] is a ViewModel responsible for managing and providing data
 * for the home screen UI components.
 */
class HomeViewModel : ViewModel() {

  // LiveData for the list of all news items
  private val listNews = MutableLiveData<List<News>>()

  // LiveData for the filtered news items based on search query
  private val filteredNews = MutableLiveData<List<News>>()

  /**
   * Initializes the ViewModel by fetching news data from Firebase.
   */
  init {
    fetchNews()
  }

  /**
   * Fetches news data from the Firebase Firestore database.
   */
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
        // Set the value of LiveData with the fetched news data
        listNews.value = newsList
        filteredNews.value = newsList
      }
      .addOnFailureListener { exception ->
        // Handle any errors
      }
  }

  /**
   * Returns the LiveData object containing the list of news items.
   *
   * @return LiveData<List<News>> representing the list of news items.
   */
  fun getListNews(): LiveData<List<News>> = filteredNews

  /**
   * Filters the list of news items based on the provided query string.
   *
   * @param query The search query string used to filter news items.
   */
  fun filterNews(query: String) {
    val allNews = listNews.value ?: return
    // Filter the list of news items based on the query string
    val filteredList = allNews.filter { it.title.contains(query, ignoreCase = true) }
    // Update the value of filteredNews LiveData with the filtered list
    filteredNews.value = filteredList
  }
}

