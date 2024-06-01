package com.skydoves.pokedex.news.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken

/**
 * Lam Nguyen Huy Hoang
 * Converts a list of strings to a JSON string and vice versa.
 */
class ListStringConverter {
  private val gson = Gson()
  private val type: Type = object : TypeToken<List<String>>() {}.type

  /**
   * Converts a JSON string to a list of strings.
   *
   * @param json The JSON string representing the list of strings.
   * @return The list of strings.
   */
  @TypeConverter
  fun fromString(json: String?): List<String> {
    return gson.fromJson(json, type)
  }

  /**
   * Converts a list of strings to a JSON string.
   *
   * @param list The list of strings to be converted.
   * @return The JSON string representing the list of strings.
   */
  @TypeConverter
  fun fromList(list: List<String?>?): String {
    return gson.toJson(list, type)
  }
}
