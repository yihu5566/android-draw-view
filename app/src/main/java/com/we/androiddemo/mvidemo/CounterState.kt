package com.we.androiddemo.mvidemo

import com.airbnb.mvrx.MavericksState

/**
 * @Author : dongfang
 * @Created Time : 2023-07-25  15:55
 * @Description:
 */
data class CounterState(val count: Int = 0):MavericksState
