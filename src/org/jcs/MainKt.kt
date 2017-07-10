package org.jcs

import org.jcs.engine.GameLoop
import org.jcs.engine.IGameLogic
import org.jcs.engine.gfx.Screen
import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.awt.image.DataBufferInt
import javax.swing.JFrame


class MainKt : Canvas(), IGameLogic {

    val SCALE = 3
    val WIDTH = 640 / SCALE
    val HEIGHT = 480 / SCALE

    private val image: BufferedImage = BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)
    private val pixels: IntArray = (image.raster.dataBuffer as DataBufferInt).data

    private var screen: Screen? = null

    override fun init() {
        screen = Screen(WIDTH, HEIGHT)
    }

    var t = 0
    override fun tick() {
        t++
        screen!!.clear(t)
    }

    override fun render() {
        val bs = bufferStrategy
        if (bs == null) {
            createBufferStrategy(3)
            requestFocus()
            return
        }

        for (y in 0..HEIGHT - 1) {
            for (x in 0..WIDTH - 1) {
                pixels[x + y * WIDTH] = screen!!.pixels[x + y * screen!!.width]
            }
        }

        val g = bs.drawGraphics

        g.drawImage(image, 0, 0, width, height, null)
        g.dispose()
        bs.show()
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
}
