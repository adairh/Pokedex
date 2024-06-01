package me.sjihh.pokedex.news.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 *
 * Lam Nguyen Huy Hoang
 * Generation Items
 */

class Generation(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val image: Int
) {
    override fun toString() = "$id$title$image"
}
