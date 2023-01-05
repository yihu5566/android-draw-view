package com.we.opengl3d

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PointFEvaluator
import android.animation.PropertyValuesHolder
import android.graphics.PointF
import android.os.Bundle
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.animation.AnimatorSetCompat
import com.we.opengl3d.animator.ProgressAnimatorDemo
import com.we.opengl3d.animator.TypeEvaluatorAnimatorDemo
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_click).setOnClickListener {
            val view = findViewById<ImageView>(R.id.progress_animator_demo)

            val animator = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
            animator.interpolator = LinearInterpolator()
            val animator1 = ObjectAnimator.ofFloat(view, "translationX", 0f,-100f, 100f,0f)
            animator1.interpolator = DecelerateInterpolator()

            val animationSet = AnimatorSet()
            animationSet.playSequentially(animator,animator1)
            animationSet.duration=2000
            animationSet.start()
            //同时执行的动画
//            val valuesHolder = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f)
//            val valuesHolder1 = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f)
//            val valuesHolder2 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
//            val valuesHolder3 = PropertyValuesHolder.ofFloat("rotation", 0f, 359.9f)
//            val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
//                view,
//                valuesHolder,
//                valuesHolder1,
//                valuesHolder2,
//                valuesHolder3
//            )
//            objectAnimator.duration = 1000
//            objectAnimator.start()
        }

//        val animator: ObjectAnimator = ObjectAnimator.ofObject(
//            view, "progress",
//            PointFEvaluator(), PointF(0f,0f),PointF(1f,1f)
//        )
//        // 执行动画
//        animator.start()

//        findViewById<TextView>(R.id.tv_click).setOnClickListener {
//            val intent1 = Intent()
//            val name = ComponentName("com.we.westarry", "com.we.westarry.ui.activity.LoginActivity")
//            intent1.component = name
//            intent1.putExtra("data", "传递的数据")
//            startActivity(intent1)
//        }
//        GlobalScope.launch {
////            dd()
////            doWhat('s')
////            channelTest()
////            flowTest()
//
////            cancelJob()
//            testCoroutine()
//        }
    }

    suspend fun testCoroutine() {
        println("start")
        val user = getUserInfo()
        println(user)
        val friendList = getFriendList(user)
        println(friendList)
        val feedList = getFeedList(friendList)
        println(feedList)
    }

    suspend fun getUserInfo(): String {
        withContext(Dispatchers.IO) {
            delay(1000L)
        }
        return "BoyCoder"
    }

    suspend fun noSuspendGetUserInfo(): String {
        return "Jack";
    }

    suspend fun getFriendList(user: String): String {
        withContext(Dispatchers.IO) {
            delay(1000L)
        }
        return "Tom, Jack"
    }

    suspend fun getFeedList(list: String): String {
        withContext(Dispatchers.IO) {
            delay(1000L)
        }
        return "{FeedList..}"
    }


    fun cancelJob() = runBlocking {


//        launch {
//            try {
//                delay(100L)
//                1 / 0 // 故意制造异常
//            } catch (e: ArithmeticException) {
//                println("Catch: $e")
//            }
//        }
//
//
//        delay(500L)
//        println("End")


        val scope = CoroutineScope(SupervisorJob())
        val deferred = scope.async {
            delay(100L)
            1 / 0
        }
        delay(100)
        println("End")
    }


    fun flowTest() = runBlocking {
        val mutex = Mutex()
        var i = 0
        val mutableListOf = mutableListOf<Job>()
        repeat(10) {
            val launch = launch(Dispatchers.Default) {
                repeat(100) {
//
//                    mutex.lock()
//                    i++
//                    发生异常将解锁不会被调用，一直处于挂起状态
//                    mutex.unlock()
//
                    mutex.withLock {
                        i++
                    }
                }
            }

            mutableListOf.add(launch)
        }
        mutableListOf.joinAll()
        println("end=====$i")

    }


    /**
     *
     */
    fun jobTest() = runBlocking {
        val job = launch {

        }
        val job1 = launch {

        }

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->

        }

        launch(CoroutineName("lll") + coroutineExceptionHandler) {


        }


    }


    suspend fun doWhat(str: Char) {

        withContext(Dispatchers.Main) {


        }
    }


    fun dd() = runBlocking {

        launch {

        }
        println(Thread.currentThread().name)
        println("00000")

        val result = async {
            println("--111--")
            delay(100)
            println("--222--")
            return@async "dddd"
        }
        println("--333--${result.await()}")


    }


    val channel = Channel<Int>(capacity = 4, onBufferOverflow = BufferOverflow.DROP_LATEST) {
        println("失败数据$it")
    }


    fun channelTest() = runBlocking {

        launch {
            channel.send(1)
            channel.send(11)
            channel.send(122)
            channel.send(1111)
            channel.send(111222)

        }

        val channel1 = produce {
            for (i in 0..10) {
                send("消息$i---")
            }
        }

        launch {

//            for (i in channel1) {
//                println("======$i=======")
//            }
            channel1.consumeEach {
                println("======$it=======")
            }
//            channel.receive()
//            channel.cancel()
        }

        println("end")
    }
}