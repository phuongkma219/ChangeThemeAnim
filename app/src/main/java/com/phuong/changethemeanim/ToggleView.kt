package com.phuong.changethemeanim

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat


class ToggleView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs),
    View.OnClickListener {
    private var iconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_sun)
    var touchType = -1
    init {
        setBackgroundColor(Color.WHITE)
        setOnClickListener(this)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        iconDrawable?.drawDrawableWithSize(canvas,56f)
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

    override fun onClick(v: View?) {
        Log.d("kkk", "onClick: ")
    }

}