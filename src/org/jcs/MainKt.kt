package org.jcs

import org.jcs.engine.GameLoop
import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Dimension
import javax.swing.JFrame


class MainKt : Canvas(), IGameLogic {

    val SCALE = 3
    val WIDTH = 640 / SCALE
    val HEIGHT = 480 / SCALE

    override fun init() {
        println("init")
    }

    override fun tick() {
//        println("tick")
    }

    override fun render() {
//        println("render")
    }

    override fun oneSecond(ups: Int, fps: Int) {
        println("ups: $ups, fps: $fps")
    }

    override fun destroy() {
        println("destroy")
    }

}

fun main(args: Array<String>) {
    val game = MainKt()

    val gameLoop = GameLoop(game)

    val dimension: Dimension = Dimension(game.WIDTH * game.SCALE, game.HEIGHT * game.SCALE)
    game.preferredSize = dimension
    game.minimumSize = dimension
    game.maximumSize = dimension

    val frame: JFrame = JFrame("Kotlin Game")
    frame.layout = BorderLayout()
    frame.add(game, BorderLayout.CENTER)
    frame.pack()
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setLocationRelativeTo(null)
    frame.isResizable = false
    frame.isVisible = true

    gameLoop.start()
    Thread.sleep(10000)
    gameLoop.stop()
}