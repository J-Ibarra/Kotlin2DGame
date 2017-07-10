package org.jcs.engine

interface IGameLogic {
    fun init()
    fun tick()
    fun render()
    fun oneSecond(ups: Int, fps: Int)
    fun destroy()
}