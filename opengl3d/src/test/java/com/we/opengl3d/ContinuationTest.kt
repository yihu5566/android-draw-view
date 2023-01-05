package com.we.opengl3d

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * @Author : dongfang
 * @Created Time : 2022-07-21  15:54
 * @Description:
 */
class ContinuationTest {

    fun main() = runBlocking {
        testCoroutine()
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
}