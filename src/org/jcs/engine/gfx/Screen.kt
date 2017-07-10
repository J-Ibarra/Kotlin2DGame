package org.jcs.engine.gfx


class Screen(val width: Int, val height: Int, val sheet: SpriteSheet) {

    var pixels: IntArray = IntArray(width * height)

    fun render(xp: Int, yp: Int, sprite: Int) {
        val xSprite = sprite % 32
        val ySprite = sprite / 32
        val sOffset = xSprite * 8 + ySprite * 8 * sheet.width

        for (y in 0..7) {
            for (x in 0..7) {
                pixels[x + xp + (y + yp) * width] = sheet.pixels!![x + y * sheet.width + sOffset]
            }
        }
    }

    fun clear(color: Int) {
        for (i in pixels.indices)
            pixels[i] = color
    }

    fun clear() {
        clear(0)
    }
}