package com.we.androiddemo.slidingconflict

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.we.androiddemo.R
import com.we.androiddemo.databinding.FragmentMyBinding
import com.we.androiddemo.databinding.ItemFragmentTextBinding

/**
 * @Author : dongfang
 * @Created Time : 2023-07-19  09:32
 * @Description:
 */
class MyFragment : Fragment() {
    lateinit var binding: FragmentMyBinding
    val listOf: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repeat(120) {
            listOf.add("我是条目---$it")
        }
        Log.d("tag", "数据长度${listOf.size}")
        binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rlv.layoutManager = LinearLayoutManager(requireContext())
        val recyclerAdapter = RecyclerAdapter(requireContext(), listOf)
        binding.rlv.adapter = recyclerAdapter
    }

    class RecyclerAdapter(val mContext: Context, val dataList: MutableList<String>) :
        Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(ItemFragmentTextBinding.inflate(LayoutInflater.from(mContext)).root)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.text).text = dataList[position]
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}