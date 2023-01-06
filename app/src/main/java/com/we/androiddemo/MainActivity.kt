package com.we.androiddemo

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cameraTransform = findViewById<CameraTransform>(R.id.cam_view)
        
        val bottomFlipAnimator = ObjectAnimator.ofFloat(cameraTransform, "bottomFlip", 60f)
        bottomFlipAnimator.startDelay = 1000
        bottomFlipAnimator.duration = 1000
        val topFlipAnimator = ObjectAnimator.ofFloat(cameraTransform, "topFlip", -60f)
        topFlipAnimator.startDelay = 1000
        topFlipAnimator.duration = 1000

        val rotationFlipAnimator = ObjectAnimator.ofFloat(cameraTransform, "rotationFlip", 270f)
        rotationFlipAnimator.startDelay = 200
        rotationFlipAnimator.duration = 1000

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(bottomFlipAnimator, rotationFlipAnimator,topFlipAnimator)
        animatorSet.start()


    }
}