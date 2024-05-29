package com.skydoves.pokedex.type

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.ui.createRoundedDrawable
import com.skydoves.pokedex.ui.flipView

class ElementAdapter(private val elementList: List<ElementData>) : RecyclerView.Adapter<ElementAdapter.ViewHolder>() {

  val selectedItems = mutableSetOf<ElementData>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_element, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val element = elementList[position]
    holder.bind(element)

    holder.itemView.setOnClickListener {
      if (selectedItems.contains(element)) {
        selectedItems.remove(element)
        flipView(holder.itemView.context, holder.frontView, holder.backView, false)
      } else if (selectedItems.size < 2) {
        selectedItems.add(element)
        flipView(holder.itemView.context, holder.frontView, holder.backView, true)
      } else {
        Toast.makeText(holder.itemView.context, "You can select up to 2 items only", Toast.LENGTH_SHORT).show()
      }
      notifyItemChanged(position)
    }
  }

  override fun getItemCount() = elementList.size

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val frontView: View = itemView.findViewById(R.id.layoutElementContainerFront)
    val backView: View = itemView.findViewById(R.id.layoutElementContainerBack)

    fun bind(element: ElementData) {
      val iconImageView = itemView.findViewById<ImageView>(R.id.imageViewElementIcon)
      val nameTextView = itemView.findViewById<TextView>(R.id.textViewElementName)

      iconImageView.setImageResource(element.iconResId)
      nameTextView.text = element.name

      val context = itemView.context
      val isSelected = selectedItems.contains(element)
      val drawable = createRoundedDrawable(context, element.backgroundColorResId, isSelected)

      frontView.background = drawable
      backView.background = drawable
    }
  }
}

