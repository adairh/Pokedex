package me.sjihh.pokedex.news.ui.newsdetail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import me.sjihh.pokedex.R
import me.sjihh.pokedex.databinding.FragmentNewsDetailBinding
import me.sjihh.pokedex.news.repository.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Lam Nguyen Huy Hoang
 */
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

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    sharedViewModel.selectedNews.observe(viewLifecycleOwner, Observer { news ->
      // Update UI with the selected news data
      viewBinding?.apply {
        txtNewsTitle.text = news.title
        newsDate.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(news.date.toDate())
        newsContent.text = Html.fromHtml(news.content, Html.FROM_HTML_MODE_LEGACY).toString()


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
