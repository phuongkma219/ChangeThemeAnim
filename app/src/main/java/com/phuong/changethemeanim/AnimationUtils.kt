package com.phuong.changethemeanim

import android.animation.ValueAnimator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

object AnimationUtils {

    const val DURATION_SHORT = 200L
    const val DURATION_MEDIUM = 300L
    const val DURATION_LONG = 400L

    fun initAnimationFloat(
        vararg values: Float,
        duration: Long = DURATION_MEDIUM,
        interpolator: Interpolator = LinearInterpolator(),
        onUpdate: (value: Float) -> Unit
    ): ValueAnimator = ValueAnimator.ofFloat(*values).apply {
        this.interpolator = interpolator
        this.duration = duration
        addUpdateListener {
            val value = it.animatedValue as Float
            onUpdate(value)
        }
    }

    fun initAnimationInt(
        vararg values: Int,
        duration: Long = DURATION_MEDIUM,
        interpolator: Interpolator = LinearInterpolator(),
        onUpdate: (value: Int) -> Unit
    ): ValueAnimator = ValueAnimator.ofInt(*values).apply {
        this.interpolator = interpolator
        this.duration = duration
        addUpdateListener {
            val value = it.animatedValue as Int
            onUpdate(value)
        }
    }

}
