package com.we.opengl3d

import kotlinx.coroutines.*
import org.junit.Test
import kotlin.concurrent.thread
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn

/**
 * @Author : dongfang
 * @Created Time : 2022-07-21  16:29
 * @Description:
 */
class ContinuationDemo {

//    @Test
//    fun main() = runBlocking {
//        println("start")
//        val result = testNoSuspendCoroutine()
////        val result = getLengthSuspend("Kotlin")
//        println(result)
//        println("end")
//    }


    @Test
    fun main1() = runBlocking {
        testStartCoroutine()
        Thread.sleep(2000L)
        launch (Dispatchers.IO){  }
    }


    fun main2() {
        val coroutineScope = CoroutineScope(Job())
//        val blok: suspend CoroutineScope.() -> Unit = {
//            delay(100)
//        }
//        coroutineScope.launch(block = blok)

        coroutineScope.launch {
            delay(100)
        }

        val job1 = Job()
        val job2 = Job()
        val job3 = Job()

        coroutineScope.cancel()
    }

    val block = suspend {
        println("Hello!")
        delay(1000L)
        println("World!")
        "Result"
    }

    private fun testStartCoroutine() {


        val continuation = object : Continuation<String> {
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<String>) {
                println("Result is: ${result.getOrNull()}")
            }
        }

        block.startCoroutine(continuation)
    }


//    suspend fun getLengthSuspend(text: String): Int = suspendCoroutine {
//        thread {
//            Thread.sleep(100)
//            it.resume(text.length)
//        }
//
//    }
//
//
//    suspend fun testNoSuspendCoroutine() = suspendCoroutineUninterceptedOrReturn<String> {
//
//        thread {
//            Thread.sleep(100)
//            it.resume("hello")
//        }
//        return@suspendCoroutineUninterceptedOrReturn kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
//    }

}