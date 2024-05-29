package com.skydoves.pokedex.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.TabHost
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.pokedex.R
import com.skydoves.pokedex.databinding.ActivityMainBinding
import com.skydoves.pokedex.type.activity.CameraActivity
import com.skydoves.pokedex.utils.TabHostUtils
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  @get:VisibleForTesting
  internal val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding {
      adapter = PokemonAdapter()
      vm = viewModel
    }

    val tabHost = findViewById<TabHost>(R.id.tabHost)
    TabHostUtils.setupTabHost(this, tabHost, 0)
  }
}
