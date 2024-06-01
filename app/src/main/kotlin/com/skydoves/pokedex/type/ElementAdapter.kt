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
/**
 * Adapter for displaying ElementData items in a RecyclerView.
 * Allows selecting up to 2 items.
 * @coder Nguyen Dang Khoa
 */
class ElementAdapter(private val elementList: List<ElementData>) : RecyclerView.Adapter<ElementAdapter.ViewHolder>() {

  // Set to keep track of selected items
  val selectedItems = mutableSetOf<ElementData>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    // Inflates the item layout and creates a new ViewHolder
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_element, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val element = elementList[position]
    holder.bind(element)

    // Handles item click events
    holder.itemView.setOnClickListener {
      if (selectedItems.contains(element)) {
        // If the item is already selected, deselect it and flip the view
        selectedItems.remove(element)
        flipView(holder.itemView.context, holder.frontView, holder.backView, false)
      } else if (selectedItems.size < 2) {
        // If less than 2 items are selected, select the clicked item and flip the view
        selectedItems.add(element)
        flipView(holder.itemView.context, holder.frontView, holder.backView, true)
      } else {
        // If 2 items are already selected, show a toast indicating the limit
        Toast.makeText(holder.itemView.context, "You can select up to 2 items only", Toast.LENGTH_SHORT).show()
      }
      // Notify the adapter that the item has changed
      notifyItemChanged(position)
    }
  }

  override fun getItemCount() = elementList.size

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Views within the item layout
    val frontView: View = itemView.findViewById(R.id.layoutElementContainerFront)
    val backView: View = itemView.findViewById(R.id.layoutElementContainerBack)

    // Binds data to views
    fun bind(element: ElementData) {
      val iconImageView = itemView.findViewById<ImageView>(R.id.imageViewElementIcon)
      val nameTextView = itemView.findViewById<TextView>(R.id.textViewElementName)

      // Set icon and name
      iconImageView.setImageResource(element.iconResId)
      nameTextView.text = element.name

      // Get context
      val context = itemView.context
      // Check if the item is selected
      val isSelected = selectedItems.contains(element)
      // Create rounded drawable
      val drawable = createRoundedDrawable(context, element.backgroundColorResId, isSelected)

      // Set background drawable for both front and back views
      frontView.background = drawable
      backView.background = drawable
    }
  }
}
