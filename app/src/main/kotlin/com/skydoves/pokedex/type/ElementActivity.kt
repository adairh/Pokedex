package com.skydoves.pokedex.type

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TabHost
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.pokedex.R
import com.skydoves.pokedex.utils.TabHostUtils
import com.skydoves.pokedex.utils.TypesInit.Companion.types
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.tensorflow.lite.DataType

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
      val selectedElements = adapter.selectedItems

      if (selectedElements.size <= 0) {
        Toast.makeText(this, "You have to select at least 1 items.", Toast.LENGTH_SHORT).show()
      }
      else {
        val weakList = mutableListOf<String>()
        val strongList = mutableListOf<String>()
        val resistantList = mutableListOf<String>()
        val vulnerableList = mutableListOf<String>()
        val immuneList = mutableListOf<String>()

        for (element in selectedElements) {
          for (we in element.weak) {
            weakList.add(we.name)
          }

          for (se in element.strong) {
            strongList.add(se.name)
          }

          for (re in element.resistant) {
            resistantList.add(re.type.name)
          }

          for (ve in element.vulnerable) {
            vulnerableList.add(ve.type.name)
          }

          for (ie in element.immune) {
            immuneList.add(ie.type.name)
          }


          /*println("WEAK")
        for (we in element.weak)
          println("Weak: " + we.name)

        println("STRONG")
        for (se in element.strong)
          println("Strong: " + se.name)

        println("RESISTANT")
        for (re in element.resistant)
          println("Resistant: " + re.type.name)


        println("VULNERABLE")
        for (ve in element.vulnerable)
          println("Vulnerable: " + ve.type.name)

        println("IMMUNE")
        for (ve in element.immune)
          println("Immune: " + ve.type.name)*/
        }

        val intent = Intent(this@ElementActivity, TypeDetailsActivity::class.java)
        intent.putStringArrayListExtra("weakList", ArrayList(weakList))
        intent.putStringArrayListExtra("strongList", ArrayList(strongList))
        intent.putStringArrayListExtra("resistantList", ArrayList(resistantList))
        intent.putStringArrayListExtra("vulnerableList", ArrayList(vulnerableList))
        intent.putStringArrayListExtra("immuneList", ArrayList(immuneList))

        val list = mutableListOf<String>()
        for (i in selectedElements)
          list.add(i.name)
        intent.putStringArrayListExtra("Type", ArrayList(list))

        startActivity(intent)
      }

      //Toast.makeText(this, "Additional operations performed.", Toast.LENGTH_SHORT).show()
    }

  }


  private fun setupRecyclerView() {


    adapter = ElementAdapter(types)
    recyclerView.layoutManager = GridLayoutManager(this, 2)
    recyclerView.adapter = adapter
  }

}
