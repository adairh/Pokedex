package com.skydoves.pokedex.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.pokedex.R
import com.skydoves.pokedex.core.model.Pokemon
import com.skydoves.pokedex.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * PKMDActivity displays details of a specific Pokemon.
 * Responsible for initializing and managing the UI components and interactions related to Pokemon details.
 *
 * Note: This class is part of the Pokedex and view poke detail functionality.
 * Coders: Nguyen Dang Khoa (ID: 21110045) and Lam Nguyen Huy Hoang (ID: 21110028)
 */
@AndroidEntryPoint
class PKMDActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

  @Inject
  internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

  @get:VisibleForTesting
  internal val viewModel: DetailViewModel by viewModels {
    DetailViewModel.provideFactory(detailViewModelFactory, pokemon.name)
  }

  private val pokemon: Pokemon by bundleNonNull(EXTRA_POKEMON)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.pokemon = pokemon
    binding.vm = viewModel
  }

  companion object {
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

    /**
     * Starts PKMDActivity to display details of the provided Pokemon.
     *
     * @param context The context from which the activity is started.
     * @param pokemon The Pokemon object whose details are to be displayed.
     */
    fun startActivity(context: Context, pokemon: Pokemon) {
      val intent = Intent(context, PKMDActivity::class.java)
      intent.putExtra(EXTRA_POKEMON, pokemon)
      context.startActivity(intent)
    }
  }
}
