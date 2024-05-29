package com.skydoves.pokedex.upgrader.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skydoves.pokedex.R

class SearchFragment : Fragment() {

  private lateinit var searchEditText: EditText

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_search, container, false)

    searchEditText = view.findViewById(R.id.searchEditText)
    searchEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Handle text changes here
        val searchQuery = s.toString()
        println("Search query: $searchQuery")
        // You can perform search operations with the searchQuery here
      }

      override fun afterTextChanged(s: Editable?) {}
    })

    return view
  }
}
