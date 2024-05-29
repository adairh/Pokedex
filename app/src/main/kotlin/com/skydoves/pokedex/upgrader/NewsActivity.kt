package com.skydoves.pokedex.upgrader

import android.os.Bundle
import android.widget.TabHost
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.pokedex.R
import com.skydoves.pokedex.utils.TabHostUtils

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

      val tabHost = findViewById<TabHost>(R.id.tabHost)
      TabHostUtils.setupTabHost(this, tabHost, 3)
    }
}
