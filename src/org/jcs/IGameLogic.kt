package org.jcs

interface IGameLogic {
    fun init()
    fun tick()
    fun render()
    fun oneSecond()
    fun destroy()
}