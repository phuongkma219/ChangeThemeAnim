package com.phuong.changethemeanim

import android.animation.Animator
import android.os.Bundle
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import com.phuong.changethemeanim.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewOnClick()
    }


    private fun setViewOnClick() {
        binding.image.setOnClickListener {
            toggleDarkMode()

            // Chuyển đổi giữa chủ đề sáng và tối

            // Lấy tọa độ trung tâm của ImageView
            val x = (it.x + it.width / 2).toInt()
            val y = (it.y + it.height / 2).toInt()

            // Tính toán bán kính tối đa cho hiệu ứng lan toả
            val displayMetrics = resources.displayMetrics
            val centerX = displayMetrics.widthPixels / 2
            val centerY = displayMetrics.heightPixels

            // Tính toán bán kính tối đa cho hiệu ứng lan toả
            val endRadius = Math.hypot(centerX.toDouble(), centerY.toDouble()).toFloat()            // Tạo và chạy hiệu ứng lan toả
            val animator = ViewAnimationUtils.createCircularReveal(window.decorView, x, y, 0f, endRadius.toFloat())
            animator.duration = 500 // Thời lượng của animation (500 milliseconds)
            val animationListener = object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator) {

                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
                override fun onAnimationStart(animation: Animator) {}
            }
            animator.addListener(animationListener)
            animator.start()
        }
    }
    private fun toggleDarkMode() {
        val currentNightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            android.content.res.Configuration.UI_MODE_NIGHT_NO -> {
                // Chuyển sang chủ đề tối
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            android.content.res.Configuration.UI_MODE_NIGHT_YES -> {
                // Chuyển sang chủ đề sáng
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        recreate() // Tải lại Activity để áp dụng chủ đề mới
    }


    companion object {
        val ROOT_ID = ViewCompat.generateViewId()
        private const val TAG = "MainActivity"
    }

}