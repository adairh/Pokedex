package com.skydoves.pokedex.utils

import android.content.Context
import android.content.Intent
import android.widget.TabHost
import com.skydoves.pokedex.R
import com.skydoves.pokedex.type.ElementActivity
import com.skydoves.pokedex.type.activity.CameraActivity
import com.skydoves.pokedex.ui.main.MainActivity
import com.skydoves.pokedex.upgrader.NewsActivity

object TabHostUtils {
  fun setupTabHost(context: Context, tabHost: TabHost, current: Int) {
    tabHost.setup()

    var list = listOf("Dex", "Cam", "Type", "News")

    val tab1 = tabHost.newTabSpec(list[0])
    tab1.setContent(R.id.dex)
    tab1.setIndicator(list[0])
    tabHost.addTab(tab1)

    val tab2 = tabHost.newTabSpec(list[1])
    tab2.setContent(R.id.profile)
    tab2.setIndicator(list[1])
    tabHost.addTab(tab2)

    val tab3 = tabHost.newTabSpec(list[2])
    tab3.setContent(R.id.element)
    tab3.setIndicator(list[2])
    tabHost.addTab(tab3)

    tabHost.currentTab = current

    val tab4 = tabHost.newTabSpec(list[3])
    tab4.setContent(R.id.news)
    tab4.setIndicator(list[3])
    tabHost.addTab(tab4)

    tabHost.currentTab = current


    tabHost.setOnTabChangedListener { tabId ->
      println(tabId)
      when (tabId) {
        list[0] -> {
          // Start DexActivity
          context.startActivity(Intent(context, MainActivity::class.java))
        }
        list[1] -> {
          // Start ProfileActivity
          context.startActivity(Intent(context, CameraActivity::class.java))
        }
        list[2] -> {
          // Start ElementActivity
          context.startActivity(Intent(context, ElementActivity::class.java))
        }
        list[3] -> {
          // Start ElementActivity
          context.startActivity(Intent(context, NewsActivity::class.java))
        }
      }
    }
  }
}