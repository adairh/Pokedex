package com.skydoves.pokedex.type

import android.os.Bundle
import android.widget.Button
import android.widget.TabHost
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.utils.TabHostUtils

class ElementActivity : AppCompatActivity() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: ElementAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_element)

    val tabHost = findViewById<TabHost>(R.id.tabHost)
    TabHostUtils.setupTabHost(this, tabHost, 2)

    recyclerView = findViewById(R.id.recyclerView)
    setupRecyclerView()

    val buttonViewDetail: Button = findViewById(R.id.buttonViewDetail)
    buttonViewDetail.setOnClickListener {
      val selectedElements = adapter.selectedItems.joinToString { it.name }
      Toast.makeText(this, "Selected Elements: $selectedElements", Toast.LENGTH_SHORT).show()
    }
  }

  private fun setupRecyclerView() {
    val elementList = listOf(
      ElementData(R.drawable.ic_type_normal, "Normal", R.color.gray_21),
      ElementData(R.drawable.ic_type_fighting, "Fighting", R.color.fighting),
      ElementData(R.drawable.ic_type_flying, "Flying", R.color.flying),
      ElementData(R.drawable.ic_type_poison, "Poison", R.color.poison),
      ElementData(R.drawable.ic_type_ground, "Ground", R.color.ground),
      ElementData(R.drawable.ic_type_rock, "Rock", R.color.rock),
      ElementData(R.drawable.ic_type_bug, "Bug", R.color.bug),
      ElementData(R.drawable.ic_type_ghost, "Ghost", R.color.ghost),
      ElementData(R.drawable.ic_type_steel, "Steel", R.color.steel),
      ElementData(R.drawable.ic_type_fire, "Fire", R.color.fire),
      ElementData(R.drawable.ic_type_water, "Water", R.color.water),
      ElementData(R.drawable.ic_type_grass, "Grass", R.color.grass),
      ElementData(R.drawable.ic_type_electric, "Electric", R.color.electric),
      ElementData(R.drawable.ic_type_psychic, "Psychic", R.color.psychic),
      ElementData(R.drawable.ic_type_ice, "Ice", R.color.ice),
      ElementData(R.drawable.ic_type_dragon, "Dragon", R.color.dragon),
      ElementData(R.drawable.ic_type_dark, "Dark", R.color.dark),
      ElementData(R.drawable.ic_type_fairy, "Fairy", R.color.fairy)
    )

    adapter = ElementAdapter(elementList)
    recyclerView.layoutManager = GridLayoutManager(this, 2)
    recyclerView.adapter = adapter
  }
}
