package me.sjihh.pokedex.news.ui.home

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
import me.sjihh.pokedex.R
import me.sjihh.pokedex.databinding.FragmentHomeBinding
import me.sjihh.pokedex.news.repository.SharedViewModel
import me.sjihh.pokedex.news.utils.PokemonColorUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * [HomeFragment] displays a list of news articles and provides a search functionality
 * to filter the articles based on user input.
 */
class HomeFragment : Fragment() {

  // ViewModels for managing data and communication between components
  private val homeViewModel: HomeViewModel by viewModel()
  private val sharedViewModel: SharedViewModel by sharedViewModel()

  // ViewBinding for accessing UI elements
  private var viewBinding: FragmentHomeBinding? = null

  // EditText for search input
  private lateinit var searchEditText: EditText

  /**
   * Creates and returns the view hierarchy associated with the fragment.
   */
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)

    // Initialize the search EditText and add a TextWatcher for filtering news
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

  /**
   * Initializes the UI components and observes LiveData to update the UI.
   */
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // Set the status bar color based on a predefined color resource
    activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.red)

    viewBinding = FragmentHomeBinding.bind(view)

    // Set up RecyclerView with LinearLayoutManager and DividerItemDecoration
    viewBinding?.recyclerViewNews?.layoutManager = LinearLayoutManager(context)
    viewBinding?.recyclerViewNews?.addItemDecoration(
      DividerItemDecoration(
        context,
        DividerItemDecoration.VERTICAL
      )
    )

    // Observe the list of news articles and update the RecyclerView adapter
    homeViewModel.getListNews().observe(viewLifecycleOwner, Observer { newsList ->
      viewBinding?.recyclerViewNews?.adapter = NewsAdapter(newsList, view.context, sharedViewModel)
    })
  }

  /**
   * Cleans up references to the viewBinding when the fragment's view is destroyed.
   */
  override fun onDestroyView() {
    viewBinding = null
    super.onDestroyView()
  }
}
