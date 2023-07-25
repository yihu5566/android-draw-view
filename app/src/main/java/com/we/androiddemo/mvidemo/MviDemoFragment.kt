package com.we.androiddemo.mvidemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.we.androiddemo.R
import com.we.androiddemo.databinding.FragmentMviDemoBinding
import com.we.androiddemo.utils.viewbinding.viewBinding

/**
 * @Author : dongfang
 * @Created Time : 2023-07-25  15:44
 * @Description:
 */
class MviDemoFragment : Fragment(R.layout.fragment_mvi_demo), MavericksView {
    private val viewModel: CounterViewModel by fragmentViewModel()
    private val binding: FragmentMviDemoBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.counterText.setOnClickListener {
            viewModel.incrementCount()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        binding.counterText.text = "Count: ${state.count}"
    }
}