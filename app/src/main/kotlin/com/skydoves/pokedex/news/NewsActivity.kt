package com.skydoves.pokedex.news

import android.os.Bundle
import android.widget.TabHost
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.news.repository.NewsRepository
import com.skydoves.pokedex.news.repository.SharedViewModel
import com.skydoves.pokedex.news.ui.home.NewsAdapter
import com.skydoves.pokedex.utils.TabHostUtils

/**
 * Activity for displaying news articles.
 * Lam Nguyen Huy Hoang
 */
class NewsActivity : AppCompatActivity() {
  private val newsRepository = NewsRepository()
  private lateinit var newsAdapter: NewsAdapter
  private val sharedViewModel: SharedViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_news)

    val tabHost = findViewById<TabHost>(R.id.tabHost)
    TabHostUtils.setupTabHost(this, tabHost, 3)

    val recyclerView: RecyclerView = findViewById(R.id.recyclerViewNews)
    recyclerView.layoutManager = LinearLayoutManager(this)

    newsAdapter = NewsAdapter(emptyList(), this, sharedViewModel)
    recyclerView.adapter = newsAdapter

    newsRepository.getNews { newsList ->
      runOnUiThread {
        newsAdapter.newsList = newsList
        newsAdapter.notifyDataSetChanged()
      }
    }
  }
}
