/*
 * DetailActivity is responsible for displaying detailed information about a Pokemon.
 * It binds the Pokemon data to the layout and sets up the ViewModel to manage the UI state.
 * This class also handles transitions using TransformationLayout.
 *
 * Coders: Nguyen Dang Khoa (ID: 21110045) and Lam Nguyen Huy Hoang (ID: 21110028)
 */
package me.sjihh.pokedex.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import me.sjihh.pokedex.R
import me.sjihh.pokedex.core.model.Pokemon
import me.sjihh.pokedex.databinding.ActivityDetailBinding
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationCompat.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

  @Inject
  internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

  @get:VisibleForTesting
  internal val viewModel: DetailViewModel by viewModels {
    DetailViewModel.provideFactory(detailViewModelFactory, pokemon.name)
  }

  private val pokemon: Pokemon by bundleNonNull(EXTRA_POKEMON)

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationEndContainerApplyParams(this)
    super.onCreate(savedInstanceState)
    binding.pokemon = pokemon
    binding.vm = viewModel
  }

  companion object {
    /**
     * Key for passing Pokemon object as extra to DetailActivity.
     */
    @VisibleForTesting
    internal const val EXTRA_POKEMON = "EXTRA_POKEMON"

    /**
     * Starts DetailActivity with the provided Pokemon object and applies a transformation transition.
     *
     * @param transformationLayout The TransformationLayout used for transition animation.
     * @param pokemon The Pokemon object to display details for.
     */
    fun startActivity(transformationLayout: TransformationLayout, pokemon: Pokemon) =
      transformationLayout.context.intentOf<DetailActivity> {
        putExtra(EXTRA_POKEMON to pokemon)
        TransformationCompat.startActivity(transformationLayout, intent)
      }
  }
}
