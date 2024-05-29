package com.skydoves.pokedex.upgrader.ui.dashboard.evolution

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.databinding.ItemPokemonBinding
import com.skydoves.pokedex.upgrader.model.Pokemon

class EvolutionAdapter(
    private val context: Context
) : RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    private val list = arrayListOf<Pokemon>()

    fun setList(list: List<Pokemon>) {
        this.list.clear()
        this.list.addAll(list)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewBinding = ItemPokemonBinding.bind(itemView)

        fun bindView(item: Pokemon) {
            /*viewBinding.textViewName.text = item.name
            viewBinding.textViewID.text = item.id

            val color = PokemonColorUtil(itemView.context).getPokemonColor(item.typeofpokemon)
            viewBinding.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            item.typeofpokemon?.getOrNull(0).let { firstType ->
                viewBinding.textViewType3.text = firstType
                viewBinding.textViewType3.isVisible = firstType != null
            }

            item.typeofpokemon?.getOrNull(1).let { secondType ->
                viewBinding.textViewType2.text = secondType
                viewBinding.textViewType2.isVisible = secondType != null
            }

            item.typeofpokemon?.getOrNull(2).let { thirdType ->
                viewBinding.textViewType1.text = thirdType
                viewBinding.textViewType1.isVisible = thirdType != null
            }

            GlideApp.with(itemView.context)
                .load(item.imageurl)
                .placeholder(android.R.color.transparent)
                .into(viewBinding.imageView)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
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
