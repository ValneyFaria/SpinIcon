package com.valneyfaria.spinicon

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.valneyfaria.spinicon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        binding.buttonTest2.setOnClickListener {
            rotate()
        }
    }

    private fun rotate() {
        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true

        Log.i("Main", clicked.toString())

        val from = if (clicked) 180.0f else 0.0f
        val to = if (clicked) 0.0f else 180.0f

        clicked = !clicked
        val animRotate = RotateAnimation(
            from, to,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )

        animRotate.duration = 1000
        animRotate.fillAfter = true
        animSet.addAnimation(animRotate)

        binding.iconThumb2.startAnimation(animSet)
    }

    companion object {
        private const val ROTATE_FROM = 0.0f
        private const val ROTATE_TO = 180.0f
        private const val ROTATE_FROM_1 = 0.0f
        private const val ROTATE_TO_1 = 180.0f // 3.141592654f * 32.0f;
        private var clicked = false
    }

    private fun rotate2() {
        val r = RotateAnimation(
            ROTATE_FROM,
            ROTATE_TO,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        r.duration = 2.toLong() * 500
        r.repeatCount = 0
        binding.iconThumb.startAnimation(r)
        binding.iconThumb.setColorFilter(R.color.colorThumbPressed)
    }

    private fun rotate3() {

        val angle = 30.0F
        val b = AppCompatResources.getDrawable(applicationContext, R.drawable.ic_chevron_right)

        val rotatedDrawable = b?.let { getRotateDrawable(it, angle) }
        val favicon = findViewById<View>(R.id.icon_thumb_2) as ImageView

        val buttonTest = findViewById<View>(R.id.button_test) as Button

        buttonTest.setOnClickListener {
            val r = RotateAnimation(
                ROTATE_FROM_1,
                ROTATE_TO_1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F
            )
            r.duration = 2.toLong() * 500
            r.repeatCount = 0
            favicon.startAnimation(r)

            binding.iconThumb.setImageDrawable(rotatedDrawable)
        }
    }

    private fun getRotateDrawable(d: Drawable, angle: Float): Drawable {
        val arD = arrayOf(d)
        return object : LayerDrawable(arD) {
            override fun draw(canvas: Canvas) {
                canvas.save()
                canvas.rotate(
                    angle, (d.bounds.width() / 2).toFloat(),
                    (d.bounds.height() / 2).toFloat()
                )
                super.draw(canvas)
                canvas.restore()
            }
        }
    }
}
