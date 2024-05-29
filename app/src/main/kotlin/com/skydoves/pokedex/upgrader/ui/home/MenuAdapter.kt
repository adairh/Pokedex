package com.skydoves.pokedex.upgrader.ui.home

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.databinding.ItemMenuBinding
import com.skydoves.pokedex.upgrader.model.Menu
import com.skydoves.pokedex.upgrader.utils.PokemonColorUtil

class MenuAdapter(
    private val list: List<Menu>,
    private val context: Context
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemMenuBinding.bind(itemView)
        fun bindView(item: Menu) {
            viewBinding.textViewName.text = itemView.context.getString(item.name)

            val color = PokemonColorUtil(itemView.context).convertColor(item.color)
            viewBinding.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_navigation_home_to_navigation_pokedex)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
