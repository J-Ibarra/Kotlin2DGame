package org.jcs

interface IGameLogic {
    fun init()
    fun tick()
    fun render()
    fun oneSecond(ups: Int, fps: Int)
    fun destroy()
}