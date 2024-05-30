package com.skydoves.pokedex.news.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.pokedex.R
import com.skydoves.pokedex.databinding.FragmentHomeBinding
import com.skydoves.pokedex.news.repository.SharedViewModel
import com.skydoves.pokedex.news.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

  private val homeViewModel: HomeViewModel by viewModel()
  private val sharedViewModel: SharedViewModel by sharedViewModel()
  private var viewBinding: FragmentHomeBinding? = null
  private lateinit var searchEditText: EditText

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)

    searchEditText = view.findViewById(R.id.searchEditText)
    searchEditText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val searchQuery = s.toString()
        homeViewModel.filterNews(searchQuery)
      }

      override fun afterTextChanged(s: Editable?) {}
    })

    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.red)

    viewBinding = FragmentHomeBinding.bind(view)

    viewBinding?.recyclerViewNews?.layoutManager = LinearLayoutManager(context)
    viewBinding?.recyclerViewNews?.addItemDecoration(
      DividerItemDecoration(
        context,
        DividerItemDecoration.VERTICAL
      )
    )

    homeViewModel.getListNews().observe(viewLifecycleOwner, Observer { newsList ->
      viewBinding?.recyclerViewNews?.adapter = NewsAdapter(newsList, view.context, sharedViewModel)
    })
  }

  override fun onDestroyView() {
    viewBinding = null
    super.onDestroyView()
  }
}
