package com.example.dragonballapp.domain.model

import androidx.annotation.DrawableRes
import com.example.dragonballapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.gokuvegeta,
        title = "Welcome to the Dragon Ball Universe",
        description = "Find awesome dragon ball characters, their powers ability and much more"
    )

    object Second : OnBoardingPage(
        image = R.drawable.dragon,
        title = "Let's Start...",
        description = "Enjoy the characters and share them with your friend."

    )

}
