package com.skydoves.pokedex.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.pokedex.ui.main.MainViewModel
import com.skydoves.whatif.whatIfNotNullAs

object RecyclerViewBinding {

  /**
   * Binds a RecyclerView adapter to a RecyclerView view.
   * Sets the state restoration policy to PREVENT_WHEN_EMPTY.
   *
   * Note: This function is part of the Pokedex and view poke detail functionality.
   * Coder: Nguyen Dang Khoa (ID: 21110045)
   */
  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter.apply {
      stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
  }

  /**
   * Submits a list of items to a RecyclerView adapter that supports binding.
   * Uses the whatIfNotNullAs extension function to safely cast the adapter.
   *
   * Note: This function is part of the Pokedex and view poke detail functionality.
   * Coder: Nguyen Dang Khoa (ID: 21110045)
   */
  @JvmStatic
  @BindingAdapter("submitList")
  fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
    view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
      adapter.submitList(itemList)
    }
  }

  /**
   * Adds pagination support to a RecyclerView for loading more Pokemon data.
   * Configures a RecyclerViewPaginator with a threshold and binds it to the view model's loading state and fetch function.
   *
   * Note: This function is part of the Pokedex and view poke detail functionality.
   * Coder: Nguyen Dang Khoa (ID: 21110045)
   */
  @JvmStatic
  @BindingAdapter("paginationPokemonList")
  fun paginationPokemonList(view: RecyclerView, viewModel: MainViewModel) {
    RecyclerViewPaginator(
      recyclerView = view,
      isLoading = { viewModel.isLoading },
      loadMore = { viewModel.fetchNextPokemonList() },
      onLast = { false },
    ).run {
      threshold = 8
    }
  }
}
