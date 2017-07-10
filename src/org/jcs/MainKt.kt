package org.jcs

import org.jcs.engine.GameLoop

class MainKt : IGameLogic {

    override fun init() {
        println("init")
    }

    override fun tick() {
        println("tick")
    }

    override fun render() {
        println("render")
    }

    override fun oneSecond() {
        println("oneSecond")
    }

    override fun destroy() {
        println("destroy")
    }

}

fun main(args: Array<String>) {
    val game = MainKt()

    val gameLoop = GameLoop(game)

    gameLoop.start()
    Thread.sleep(20)
    gameLoop.stop()
}