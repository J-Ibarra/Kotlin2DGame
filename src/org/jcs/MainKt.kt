package org.jcs

import org.jcs.engine.GameLoop
import org.jcs.engine.IGameLogic
import org.jcs.engine.gfx.Color
import org.jcs.engine.gfx.Screen
import org.jcs.engine.gfx.SpriteSheet
import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
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
    private var sheet: SpriteSheet? = null

    override fun init() {
        sheet = SpriteSheet("SpriteSheet.png")
        screen = Screen(WIDTH, HEIGHT, sheet!!)
    }

    override fun tick() {

    }

    override fun render() {
        val bs = bufferStrategy
        if (bs == null) {
            createBufferStrategy(3)
            requestFocus()
            return
        }

        screen!!.clear(Color[555])

        screen!!.render(8, 8, 31, Color[5, 500, 50, 550])

        screen!!.render(50, 100, 29, Color[555, 0, 0, 50], 0)
        screen!!.render(58, 100, 29, Color[555, 0, 0, 50], 1)
        screen!!.render(66, 100, 30, Color[555, 0, 0, 50], 0)
        screen!!.render(74, 100, 30, Color[555, 0, 0, 50], 2)

        for (y in 0..HEIGHT - 1) {
            for (x in 0..WIDTH - 1) {
                val cc = screen!!.pixels[x + y * screen!!.width]
                if (cc < 6 * 6 * 6)
                    pixels[x + y * WIDTH] = Color.colors[cc]
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
    frame.isAlwaysOnTop = true
    frame.isResizable = false
    frame.isVisible = true


    frame.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent) {
            gameLoop.stop()
            Thread.sleep(500)
            kotlin.system.exitProcess(0)
        }
    })

    gameLoop.start()
}
