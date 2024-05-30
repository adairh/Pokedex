package com.skydoves.pokedex.news.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
  val id: String = "",
  val title: String = "",
  val content: String = "",
  val date: Timestamp = Timestamp.now(),
  val headingImageUrl: String = "",
  val thumbNail: String = "",
) : Parcelable
