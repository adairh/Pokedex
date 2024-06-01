package com.skydoves.pokedex.news.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

/**
 *
 * Lam Nguyen Huy Hoang
 * Some Menu detail
 */

data class Menu(
    val id: Int,
    @StringRes val name: Int,
    @ColorRes val color: Int
)
