package com.we.androiddemo.mvidemo

import com.airbnb.mvrx.MavericksViewModel

/**
 * @Author : dongfang
 * @Created Time : 2023-07-25  15:58
 * @Description:
 */
class CounterViewModel(initialState: CounterState) :
    MavericksViewModel<CounterState>(initialState) {
    fun incrementCount() = setState { copy(count = count + 1) }
}