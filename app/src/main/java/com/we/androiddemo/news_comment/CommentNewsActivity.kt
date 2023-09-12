package com.we.androiddemo.news_comment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.we.androiddemo.R
import com.we.androiddemo.news_comment.logic.impl.FoldReducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @Author : dongfang
 * @Created Time : 2023-09-12  14:25
 * @Description:
 */
class CommentNewsActivity : AppCompatActivity() {
    lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_news)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        commentAdapter = CommentAdapter {

            lifecycleScope.launchWhenResumed {
                val newList = withContext(Dispatchers.IO) {
                    reduce.invoke(commentAdapter.currentList)
                }
                val firstSubmit = commentAdapter.itemCount == 1
                commentAdapter.submitList(newList) {
                    // 这里是为了处理submitList后，列表滑动位置不对的问题
                    if (firstSubmit) {
                        recyclerView.scrollToPosition(0)
                    } else if (this@CommentAdapter is FoldReducer) {
                        val index = commentAdapter.currentList.indexOf(this@CommentAdapter.folding)
                        recyclerView.scrollToPosition(index)
                    }
                }

            }
        }
        recyclerView.adapter = commentAdapter


    }
}
