package com.skydoves.pokedex.news.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.GsonBuilder
import com.skydoves.pokedex.news.model.News
import com.skydoves.pokedex.news.utils.TimestampDeserializer
import java.security.Timestamp

class NewsRepository {
  private val db = FirebaseFirestore.getInstance()
  private val gson = GsonBuilder().registerTypeAdapter(Timestamp::class.java, TimestampDeserializer()).create()

  fun getNews(callback: (List<News>) -> Unit) {
    db.collection("news")
      .get()
      .addOnSuccessListener { querySnapshot ->
        val newsList = querySnapshot.documents.mapNotNull { document ->
          val json = document.data?.let { gson.toJson(it) }
          gson.fromJson(json, News::class.java)
        }
        callback(newsList)
      }
      .addOnFailureListener {
        // Handle any errors
      }
  }
}
