package com.rainbowt.traveltaipei.ui.base

/**
 * @Author RainBowT
 */
interface CRUD<B> {
    operator fun set(position: Int, data: B)

    fun remove(pos: Int)

    fun setData(dataList: MutableList<B>?)

    fun add(position: Int, data: B)

    fun clear()
}