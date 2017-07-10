package org.jcs.engine.gfx


class Screen(val width: Int, val height: Int) {

    var pixels: IntArray = IntArray(width * height)

    fun clear(color: Int) {
        for (i in pixels.indices)
            pixels[i] = color
    }

    fun clear() {
        clear(0)
    }
}