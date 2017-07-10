package org.jcs.engine.gfx


class Screen(val width: Int, val height: Int, val sheet: SpriteSheet) {

    val BIT_MIRROR_X = 0x01
    val BIT_MIRROR_Y = 0x02

    var pixels: IntArray = IntArray(width * height)

    fun render(xp: Int, yp: Int, sprite: Int, colors: Int, mirror: Int) {

        val mirrorX = mirror and BIT_MIRROR_X > 0
        val mirrorY = mirror and BIT_MIRROR_Y > 0

        val xSprite = sprite % 32
        val ySprite = sprite / 32
        val sOffset = xSprite * 8 + ySprite * 8 * sheet.width

        for (y in 0..7) {
            if (y + yp < 0 || y + yp >= height)
                continue

            val ys = if (mirrorY) y else 7 - y

            for (x in 0..7) {
                if (x + xp < 0 || x + xp >= width)
                    continue

                val xs = if (mirrorX) x else 7 - x

                val col = colors shr sheet.pixels!![xs + ys * sheet.width + sOffset] * 8 and 255
                if (col < 255)
                    pixels[x + xp + (y + yp) * width] = col
            }
        }
    }

    fun render(xp: Int, yp: Int, sprite: Int, colors: Int) {
        render(xp, yp, sprite, colors, 0)
    }

    fun clear(color: Int) {
        for (i in pixels.indices)
            pixels[i] = color
    }

    fun clear() {
        clear(0)
    }
}