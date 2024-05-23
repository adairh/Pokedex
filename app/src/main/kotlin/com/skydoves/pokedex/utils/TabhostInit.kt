package com.skydoves.pokedex.utils

import android.content.Context
import android.content.Intent
import android.widget.TabHost
import com.skydoves.pokedex.R
import com.skydoves.pokedex.type.activity.CameraActivity
import com.skydoves.pokedex.ui.main.MainActivity

object TabHostUtils {
  fun setupTabHost(context: Context, tabHost: TabHost, current: Int) {
    tabHost.setup()

    val tab1 = tabHost.newTabSpec("Dex")
    tab1.setContent(R.id.dex)
    tab1.setIndicator("Dex")
    tabHost.addTab(tab1)

    val tab2 = tabHost.newTabSpec("Camera")
    tab2.setContent(R.id.profile)
    tab2.setIndicator("Camera")
    tabHost.addTab(tab2)

    val tab3 = tabHost.newTabSpec("Elements")
    tab3.setContent(R.id.element)
    tab3.setIndicator("Elements")
    tabHost.addTab(tab3)

    tabHost.currentTab = current

    tabHost.setOnTabChangedListener { tabId ->
      println(tabId)
      when (tabId) {
        "Dex" -> {
          // Start DexActivity
          context.startActivity(Intent(context, MainActivity::class.java))
        }
        "Camera" -> {
          // Start ProfileActivity
          context.startActivity(Intent(context, CameraActivity::class.java))
        }
        R.id.element.toString() -> {
          // Start ElementActivity
          // context.startActivity(Intent(context, ElementActivity::class.java))
        }
      }
    }
  }
}