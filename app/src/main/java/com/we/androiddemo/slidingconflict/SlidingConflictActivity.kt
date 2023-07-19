package com.we.androiddemo.slidingconflict

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.we.androiddemo.R
import com.we.androiddemo.databinding.ActivitySllidingConflictBinding

/**
 * @Author : dongfang
 * @Created Time : 2023-07-19  09:17
 * @Description:
 */
class SlidingConflictActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slliding_conflict)
    }

    override fun onResume() {
        super.onResume()
        Log.d("tag", "数据长度")
        val pagerAdapter = MyViewPagerAdapter(this)
        findViewById<ViewPager2>(R.id.vp2).adapter = pagerAdapter
    }

    private inner class MyViewPagerAdapter(
        fragmentActivity: FragmentActivity,
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 4
        }

        override fun createFragment(position: Int): Fragment {
            return MyFragment()
        }
    }
}