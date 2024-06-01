package me.sjihh.pokedex.ui.main

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import me.sjihh.pokedex.R
import me.sjihh.pokedex.core.model.Pokemon
import me.sjihh.pokedex.databinding.ItemPokemonBinding
import me.sjihh.pokedex.ui.details.DetailActivity

/**
 * Adapter for displaying Pokemon items in a RecyclerView.
 * Handles item click events to navigate to the detail screen.
 *
 * Note: This class is part of the Pokedex and view poke detail functionality.
 * Coders: Nguyen Dang Khoa (ID: 21110045) and Lam Nguyen Huy Hoang (ID: 21110028)
 */
class PokemonAdapter : BindingListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(diffUtil) {

  private var onClickedAt = 0L

  /**
   * Creates a ViewHolder by inflating the item_pokemon layout.
   */
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
    val binding = parent.binding<ItemPokemonBinding>(R.layout.item_pokemon)
    return PokemonViewHolder(binding)
  }

  /**
   * Binds data to the ViewHolder.
   */
  override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) =
    holder.bindPokemon(getItem(position))

  /**
   * ViewHolder for displaying Pokemon items.
   */
  inner class PokemonViewHolder constructor(
    private val binding: ItemPokemonBinding,
  ) : RecyclerView.ViewHolder(binding.root) {

    init {
      binding.root.setOnClickListener {
        val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
          ?: return@setOnClickListener
        val currentClickedAt = SystemClock.elapsedRealtime()
        if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
          DetailActivity.startActivity(binding.transformationLayout, getItem(position))
          onClickedAt = currentClickedAt
        }
      }
    }

    /**
     * Binds a Pokemon item to the ViewHolder.
     */
    fun bindPokemon(pokemon: Pokemon) {
      binding.pokemon = pokemon
      binding.executePendingBindings()
    }
  }

  companion object {
    /**
     * DiffUtil callback for calculating the difference between two lists of Pokemon.
     */
    private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {

      override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        oldItem.name == newItem.name

      override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
        oldItem == newItem
    }
  }
}
