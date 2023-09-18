package com.we.androiddemo

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.we.androiddemo.chainlinkdemo.AbsDialog
import com.we.androiddemo.chainlinkdemo.AdDialog
import com.we.androiddemo.chainlinkdemo.CommentDialog
import com.we.androiddemo.chainlinkdemo.PolicyDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cameraTransform = findViewById<CameraTransform>(R.id.cam_view)

        val bottomFlipAnimator = ObjectAnimator.ofFloat(cameraTransform, "bottomFlip", 60f)
        bottomFlipAnimator.startDelay = 1000
        bottomFlipAnimator.duration = 1000
        val topFlipAnimator = ObjectAnimator.ofFloat(cameraTransform, "topFlip", -60f)
        topFlipAnimator.startDelay = 200
        topFlipAnimator.duration = 1000

        val rotationFlipAnimator = ObjectAnimator.ofFloat(cameraTransform, "rotationFlip", 270f)
        rotationFlipAnimator.startDelay = 200
        rotationFlipAnimator.duration = 1000

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(bottomFlipAnimator, rotationFlipAnimator, topFlipAnimator)
        animatorSet.start()
        //模拟打开app
        val dialogs = mutableListOf<AbsDialog>()
        dialogs.add(PolicyDialog(this))
        dialogs.add(CommentDialog(this))
        dialogs.add(AdDialog(this))
        //根据优先级排序
        dialogs.sortBy { it.getPriority() }
        //创建链条
        for (i in 0 until dialogs.size - 1) {
            dialogs[i].setNextDialog(dialogs[i + 1])
        }
        cameraTransform.setOnClickListener {
//            startActivity(Intent(this, CommentNewsActivity::class.java))
            dialogs[0].showDialog()
        }

    }
}