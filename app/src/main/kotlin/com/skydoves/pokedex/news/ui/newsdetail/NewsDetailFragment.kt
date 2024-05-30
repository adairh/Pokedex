package com.skydoves.pokedex.news.ui.newsdetail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.skydoves.pokedex.R
import com.skydoves.pokedex.databinding.FragmentNewsDetailBinding
import com.skydoves.pokedex.news.repository.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class NewsDetailFragment : Fragment() {

  private val sharedViewModel: SharedViewModel by sharedViewModel()
  private var viewBinding: FragmentNewsDetailBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
    viewBinding = FragmentNewsDetailBinding.bind(view)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    sharedViewModel.selectedNews.observe(viewLifecycleOwner, Observer { news ->
      // Update UI with the selected news data
      viewBinding?.apply {
        txtNewsTitle.text = news.title
        newsDate.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(news.date.toDate())
        newsContent.text = Html.fromHtml(news.content).toString()


        Glide.with(this@NewsDetailFragment)
          .load(news.headingImageUrl)
          .into(headingImg)
      }
    })
  }

  override fun onDestroyView() {
    viewBinding = null
    super.onDestroyView()
  }
}
