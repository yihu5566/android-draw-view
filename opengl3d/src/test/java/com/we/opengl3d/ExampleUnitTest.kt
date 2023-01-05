package com.we.opengl3d

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

//        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() = runBlocking {
        println("message--->${Thread.currentThread().name}")
        launch {
            println(Thread.currentThread().name)
            delay(100)
        }
        Thread.sleep(1000)

        repeat(6){
            println("000000")
        }
    }


    @Test
    fun testAsync(){




    }

}