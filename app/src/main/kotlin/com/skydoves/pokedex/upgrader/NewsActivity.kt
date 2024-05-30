package com.skydoves.pokedex.upgrader

import android.os.Bundle
import android.widget.TabHost
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.upgrader.repository.NewsRepository
import com.skydoves.pokedex.upgrader.repository.SharedViewModel
import com.skydoves.pokedex.upgrader.ui.home.NewsAdapter
import com.skydoves.pokedex.utils.TabHostUtils

class NewsActivity : AppCompatActivity() {
  private val newsRepository = NewsRepository()
  private lateinit var newsAdapter: NewsAdapter
  private val sharedViewModel: SharedViewModel by viewModels() // Use viewModels() to create the ViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_news)

    val tabHost = findViewById<TabHost>(R.id.tabHost)
    TabHostUtils.setupTabHost(this, tabHost, 3)

    val recyclerView: RecyclerView = findViewById(R.id.recyclerViewNews)
    recyclerView.layoutManager = LinearLayoutManager(this)

    // Initialize newsAdapter here
    newsAdapter = NewsAdapter(emptyList(), this, sharedViewModel) // Pass the sharedViewModel

    recyclerView.adapter = newsAdapter

    newsRepository.getNews { newsList ->
      // Now that newsAdapter is initialized, update its data
      newsAdapter.newsList = newsList
      newsAdapter.notifyDataSetChanged()
    }

  }
}
