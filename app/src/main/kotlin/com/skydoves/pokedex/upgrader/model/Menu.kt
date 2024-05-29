package com.skydoves.pokedex.upgrader.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class Menu(
    val id: Int,
    @StringRes val name: Int,
    @ColorRes val color: Int
)
