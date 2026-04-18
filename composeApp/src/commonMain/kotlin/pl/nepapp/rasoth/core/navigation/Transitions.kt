package pl.nepapp.rasoth.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith

private const val FIVE_PERCENT = 0.05f
private val SlightlyRight = { width: Int -> (width * FIVE_PERCENT).toInt() }
private val SlightlyLeft = { width: Int -> 0 - (width * FIVE_PERCENT).toInt() }

val navigationSlideTransitionSpecPop =
    (
            slideInHorizontally(
                tween(),
                SlightlyLeft,
            ) + fadeIn()
            ) togetherWith
            slideOutHorizontally(tween(), SlightlyRight) + fadeOut()


val navigationSlideTransitionSpec =
    (slideInHorizontally(tween(), SlightlyRight) + fadeIn()) togetherWith
            slideOutHorizontally(tween(), SlightlyLeft) + fadeOut()

val navigationFadeTransitionSpecPop =
    (
            fadeIn(
                animationSpec = tween()
            )
            ) togetherWith
            fadeOut(
                animationSpec = tween()
            )

val navigationFadeTransitionSpec =
    (
            fadeIn(
                animationSpec = tween()
            )
            ) togetherWith
            fadeOut(
                animationSpec = tween()
            )
