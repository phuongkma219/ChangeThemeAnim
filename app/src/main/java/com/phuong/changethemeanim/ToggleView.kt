package com.phuong.changethemeanim

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter


class ToggleView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var iconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_sun)
    private var rotateAngle = 0f
    private var sizeDrawable = 56f
    private var animActiveSet = AnimatorSet()

    val onClickZone = View(context)

    init {
        setBackgroundColor(Color.WHITE)
        setupOnclickZone()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.rotate(rotateAngle, width / 2f, height / 2f)
        iconDrawable?.drawDrawableWithSize(canvas, sizeDrawable)
    }

    private fun setupOnclickZone() {
        val params = LayoutParams(56, 56)
        params.gravity = Gravity.CENTER
        onClickZone.layoutParams = params
        addView(onClickZone)
    }

    private fun Drawable.drawDrawableWithSize(canvas: Canvas, size: Float) {
        val radius = size / 2
        val centerX = width / 2f
        val centerY = width / 2f
        setBounds(
            (centerX - radius).toInt(),
            (centerY - radius).toInt(),
            (centerX + radius).toInt(),
            (centerY + radius).toInt()
        )
        draw(canvas)
    }

    fun onChangeBg(isLight : Boolean) {
        val scale = AnimationUtils.initAnimationFloat(
            values = floatArrayOf(56f, 0f),
            duration = 2000
        ) {
            sizeDrawable = it
            if(sizeDrawable <= 0f){
                iconDrawable = if (isLight){
                    ContextCompat.getDrawable(context, R.drawable.ic_moon)
                } else {
                    ContextCompat.getDrawable(context, R.drawable.ic_sun)
                }
            }
            invalidate()
        }

        val scale1 = AnimationUtils.initAnimationFloat(
            values = floatArrayOf(0f, 56f),
            duration = 2000
        ) {
            sizeDrawable = it
            invalidate()
        }

        val rotate = AnimationUtils.initAnimationFloat(
            values = floatArrayOf(0f, 360f),
            duration = 2000
        ) {
            rotateAngle = if (isLight){
                360f - it
            } else {
                it
            }
            invalidate()
        }

        animActiveSet.apply {
            playSequentially(scale, scale1)
            scale.addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    rotate.start()
                }
            })
            scale1.addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    rotate.start()
                }
            })
        }
        animActiveSet.start()
    }

}
@BindingAdapter("activeState")
fun ToggleView.setActiveButton(isLight: Boolean) = onChangeBg(isLight)