package com.skydoves.pokedex.news.ui.home

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.databinding.ItemMenuBinding
import com.skydoves.pokedex.news.model.Menu
import com.skydoves.pokedex.news.utils.PokemonColorUtil


/**
 * [MenuAdapter] is a RecyclerView adapter that binds a list of [Menu] items to the UI.
 * Each item represents a menu option displayed in the app's menu screen.
 *
 * Lam Nguyen Huy Hoang
 *
 * @property list The list of menu items to be displayed.
 * @property context The context in which the adapter is used.
 */
class MenuAdapter(
  private val list: List<Menu>,
  private val context: Context
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

  /**
   * [ViewHolder] holds references to the views for a single item in the RecyclerView.
   * It binds the data from a [Menu] item to the UI.
   *
   * @param itemView The view for a single item in the RecyclerView.
   */
  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewBinding = ItemMenuBinding.bind(itemView)

    /**
     * Binds the data from a [Menu] item to the UI.
     *
     * @param item The menu item to be displayed.
     */
    fun bindView(item: Menu) {
      // Set the name of the menu item
      viewBinding.textViewName.text = itemView.context.getString(item.name)

      // Set the background color of the menu item
      val color = PokemonColorUtil(itemView.context).convertColor(item.color)
      viewBinding.relativeLayoutBackground.background.colorFilter =
        PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
  }

  /**
   * Inflates the layout for a single item in the RecyclerView.
   *
   * @param parent The parent view group.
   * @param viewType The type of view to be inflated.
   * @return A [ViewHolder] instance representing a single item view.
   */
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
    return ViewHolder(view)
  }

  /**
   * Binds the data of a [Menu] item to the UI of a [ViewHolder].
   *
   * @param holder The ViewHolder instance to bind the data to.
   * @param position The position of the item in the list.
   */
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = list[position]
    holder.bindView(item)
  }

  /**
   * Returns the total number of items in the list.
   *
   * @return The total number of items in the list.
   */
  override fun getItemCount(): Int {
    return list.size
  }
}
