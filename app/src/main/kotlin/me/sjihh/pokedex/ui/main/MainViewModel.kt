package me.sjihh.pokedex.ui.main

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import me.sjihh.pokedex.core.model.Pokemon
import me.sjihh.pokedex.core.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for the main screen of the Pokedex app.
 * Handles fetching and displaying the list of Pokemon.
 *
 * Note: This class is part of the Pokedex and view poke detail functionality.
 * Coded by: Nguyen Dang Khoa (ID: 21110045)
 */
@HiltViewModel
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository,
) : BindingViewModel() {

  /**
   * Indicates whether data is being loaded.
   */
  @get:Bindable
  var isLoading: Boolean by bindingProperty(false)
    private set

  /**
   * Message to display in a toast.
   */
  @get:Bindable
  var toastMessage: String? by bindingProperty(null)
    private set

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  private val pokemonListFlow = pokemonFetchingIndex.flatMapLatest { page ->
    mainRepository.fetchPokemonList(
      page = page,
      onStart = { isLoading = true },
      onComplete = { isLoading = false },
      onError = { toastMessage = it },
    )
  }

  /**
   * List of Pokemon to be displayed on the screen.
   */
  @get:Bindable
  val pokemonList: List<Pokemon> by pokemonListFlow.asBindingProperty(viewModelScope, emptyList())

  init {
    Timber.d("init MainViewModel")
  }

  /**
   * Fetches the next page of Pokemon list.
   */
  @MainThread
  fun fetchNextPokemonList() {
    if (!isLoading) {
      pokemonFetchingIndex.value++
    }
  }
}
