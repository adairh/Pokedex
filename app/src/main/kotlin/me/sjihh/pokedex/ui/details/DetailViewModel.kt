package me.sjihh.pokedex.ui.details

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import me.sjihh.pokedex.core.model.PokemonInfo
import me.sjihh.pokedex.core.repository.DetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
/**
*
* Coders: Nguyen Dang Khoa (ID: 21110045) and Lam Nguyen Huy Hoang (ID: 21110028)
*
*
*/

class DetailViewModel @AssistedInject constructor(
  detailRepository: DetailRepository,
  @Assisted private val pokemonName: String,
) : BindingViewModel() {

  @get:Bindable
  var isLoading: Boolean by bindingProperty(true)
    private set

  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set

  private val pokemonInfoFlow: Flow<PokemonInfo?> = detailRepository.fetchPokemonInfo(
    name = pokemonName,
    onComplete = { isLoading = false },
    onError = { toastMessage = it },
  )

  @get:Bindable
  val pokemonInfo: PokemonInfo? by pokemonInfoFlow.asBindingProperty(viewModelScope, null)

  init {
    Timber.d("init DetailViewModel")
  }

  /**
   * AssistedFactory interface for creating instances of DetailViewModel.
   */
  @dagger.assisted.AssistedFactory
  interface AssistedFactory {
    fun create(pokemonName: String): DetailViewModel
  }

  companion object {
    /**
     * Provides a factory to create instances of DetailViewModel.
     *
     * @param assistedFactory The assisted factory for creating instances of DetailViewModel.
     * @param pokemonName The name of the Pokemon for which details are to be displayed.
     * @return The ViewModelProvider.Factory instance for DetailViewModel.
     */
    fun provideFactory(
      assistedFactory: AssistedFactory,
      pokemonName: String,
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(pokemonName) as T
      }
    }
  }
}
