package org.jcs.engine.gfx

import javax.imageio.ImageIO


class SpriteSheet(path: String) {

    var width: Int
    var height: Int = 0
    var pixels: IntArray? = null

    init {
        val image = ImageIO.read(Thread.currentThread().contextClassLoader.getResourceAsStream(path))
        width = image.width
        height = image.height
        pixels = image.getRGB(0, 0, width, height, null, 0, width)
        for (i in pixels!!.indices)
            pixels!![i] = pixels!![i] and 0xFFFFFF
    }
}
