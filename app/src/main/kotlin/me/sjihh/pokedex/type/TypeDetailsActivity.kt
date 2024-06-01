/**
 * An activity for displaying details about Pokémon types, including strengths and weaknesses.
 */
package me.sjihh.pokedex.type

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import me.sjihh.pokedex.R
import me.sjihh.pokedex.ui.createRoundedDrawable
import me.sjihh.pokedex.utils.TypesInit.Companion.types

/**
 * A class representing an activity for displaying details about Pokémon types, including strengths and weaknesses.
 * Coded by Nguyen Dang Khoa (ID: 21110045).
 */
class TypeDetailsActivity : AppCompatActivity() {
  private var weakList: List<String>? = null
  private var strongList: List<String>? = null
  private var resistantList: List<String>? = null
  private var vulnerableList: List<String>? = null
  private var immuneList: List<String>? = null
  private var typess: List<String>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_type_details)

    // Retrieve data from the intent
    weakList = intent.getStringArrayListExtra("weakList")
    strongList = intent.getStringArrayListExtra("strongList")
    resistantList = intent.getStringArrayListExtra("resistantList")
    vulnerableList = intent.getStringArrayListExtra("vulnerableList")
    immuneList = intent.getStringArrayListExtra("immuneList")
    typess = intent.getStringArrayListExtra("Type")

    // Populate the views with the data
    populateViews()
  }

  /**
   * Populates the views with Pokémon type details.
   */
  private fun populateViews() {
    // Update the header and subtitle
    var str = ""
    for (t in typess!!)
      str += ("$t ")

    findViewById<TextView>(R.id.type_details_header).text = str+"Details"
    findViewById<TextView>(R.id.type_details_subtitle).text = "Strengths and Weaknesses"

    // Populate the list views
    populateListView(R.id.very_weak_against_card, R.id.card_title, "Very Weak Against", weakList)
    populateListView(R.id.weak_against_card, R.id.card_title, "Weak Against", strongList)
    populateListView(R.id.resistant_against_card, R.id.card_title, "Strong Against", resistantList)
    populateListView(R.id.very_resistant_against_card, R.id.card_title, "Very Strong Against", vulnerableList)
    populateListView(R.id.immune_against_card, R.id.card_title, "Immune Against", immuneList)
  }

  /**
   * Populates a list view with Pokémon type details.
   * @param parentViewId The id of the parent view.
   * @param titleViewId The id of the title view.
   * @param title The title of the list.
   * @param list The list of Pokémon types.
   */
  private fun populateListView(parentViewId: Int, titleViewId: Int, title: String, list: List<String>?) {
    val parent = findViewById<LinearLayout>(parentViewId)
    val titleView = parent.findViewById<TextView>(titleViewId)
    titleView.text = title

    val listContainer = parent.findViewById<LinearLayout>(R.id.type_relations_list)
    listContainer.removeAllViews()

    list?.forEach { typeName ->
      val view = layoutInflater.inflate(R.layout.poke_type_small_card, listContainer, false)
      view.findViewById<TextView>(R.id.type_name).text = typeName

      // Set the type color and icon
      val typeData = types.find { it.name == typeName }
      typeData?.let { typeDetails ->
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.cornerRadius = 50f
        drawable.setColor(ContextCompat.getColor(this, typeDetails.backgroundColorResId))
        view.background = drawable

        view.findViewById<ImageView>(R.id.type_image).setImageResource(typeDetails.iconResId)
      }

      listContainer.addView(view)
    }
  }
}
