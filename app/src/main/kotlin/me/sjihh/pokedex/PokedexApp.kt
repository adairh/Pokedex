package me.sjihh.pokedex

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class for the Pokedex app.
 * Responsible for initializing Firebase, configuring Dependency Injection (DI), and initializing Firestore.
 *
 * Note: This class is part of the News article from Firebase functionality.
 * Coder: Lam Nguyen Huy Hoang (ID: 21110028)
 */
@HiltAndroidApp
class PokedexApp : Application() {

  /**
   * Called when the application is starting.
   * Responsible for initializing Firebase, configuring DI, and initializing Firestore.
   */
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

  /**
   * Initializes Firestore to retrieve news articles.
   */
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

  /**
   * Configures Dependency Injection using Koin.
   */
  private fun configureDI() = startKoin {
    androidContext(this@PokedexApp)
  }
}
