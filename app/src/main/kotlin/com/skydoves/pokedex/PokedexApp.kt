package com.skydoves.pokedex

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore
import com.skydoves.pokedex.news.di.appComponent
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
@HiltAndroidApp
class PokedexApp : Application() {
  override fun onCreate() {
    super.onCreate()

    // Initialize Firebase
    FirebaseApp.initializeApp(this)

    // Check if Firebase is initialized
    if (FirebaseApp.getApps(this).isEmpty()) {
      throw IllegalStateException("FirebaseApp initialization failed.")
    }

    // Now configure DI and Firestore
    configureDI()
    initializeFirestore()
  }

  private fun initializeFirestore() {
    val db = Firebase.firestore
    db.collection("news")
      .get()
      .addOnSuccessListener { result ->
        for (document in result) {
          println("${document.id} => ${document.data}")
        }
      }
      .addOnFailureListener { exception ->
        println("Error getting documents. $exception")
      }
  }

  private fun configureDI() = startKoin {
    androidContext(this@PokedexApp)
    modules(appComponent)
  }
}
