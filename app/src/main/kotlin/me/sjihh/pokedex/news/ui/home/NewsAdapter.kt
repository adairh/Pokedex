package me.sjihh.pokedex.news.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.sjihh.pokedex.R
import me.sjihh.pokedex.news.model.News
import me.sjihh.pokedex.news.repository.SharedViewModel
import java.text.SimpleDateFormat
import java.util.Locale

/**
 *
 * Lam Nguyen Huy Hoang
 *
 */

class NewsAdapter(
  var newsList: List<News>,
  private val context: Context,
  private val sharedViewModel: SharedViewModel
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.textViewName)
    private val dateTextView: TextView = itemView.findViewById(R.id.news_date)
    private val imageView: ImageView = itemView.findViewById(R.id.imageArticleView)
    private val shortContent: TextView = itemView.findViewById(R.id.shortContent)

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    fun bindView(news: News) {
      titleTextView.text = news.title

      // Convert Timestamp to Date and format it
      val date = news.date.toDate()
      val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      val formattedDate = dateFormat.format(date)
      dateTextView.text = formattedDate

      Glide.with(context)
        .load(news.thumbNail)
        .into(imageView)

      val text = Html.fromHtml(news.content, Html.FROM_HTML_MODE_LEGACY).toString()
      val maxWordCount = 30
      val words = text.split(" ")
      val shortContentText = if (words.size <= maxWordCount) {
        text
      } else {
        "${words.take(maxWordCount).joinToString(" ")}..."
      }

      shortContent.text = shortContentText

      itemView.setOnClickListener {
        sharedViewModel.select(news)
        it.findNavController().navigate(R.id.action_navigation_home_to_navigation_news_detail)
      }
    }

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val news = newsList[position]
    holder.bindView(news)
  }

  override fun getItemCount(): Int {
    return newsList.size
  }
}
