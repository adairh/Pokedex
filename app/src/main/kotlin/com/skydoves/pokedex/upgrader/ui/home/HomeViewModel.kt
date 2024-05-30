package com.skydoves.pokedex.upgrader.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.skydoves.pokedex.R
import com.skydoves.pokedex.upgrader.model.Menu
import com.skydoves.pokedex.upgrader.model.News

class HomeViewModel : ViewModel() {

    private val listMenu = MutableLiveData<List<Menu>>()
    private val listNews = MutableLiveData<List<News>>()

    fun getListMenu(): LiveData<List<Menu>> {
        listMenu.value = listOf(
            Menu(id = 1, name = R.string.menu_item_1, color = R.color.lightTeal),
            Menu(id = 1, name = R.string.menu_item_2, color = R.color.lightRed),
            Menu(id = 1, name = R.string.menu_item_3, color = R.color.lightBlue),
            Menu(id = 1, name = R.string.menu_item_4, color = R.color.lightYellow),
            Menu(id = 1, name = R.string.menu_item_5, color = R.color.lightPurple),
            Menu(id = 1, name = R.string.menu_item_6, color = R.color.lightBrown)
        )
        return listMenu
    }

  fun getListNews(): LiveData<List<News>> {

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
      }
      .addOnFailureListener { exception ->
        // Handle any errors
      }
    return listNews
    }
}
