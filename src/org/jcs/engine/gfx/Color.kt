package org.jcs.engine.gfx


object Color {

    var colors = IntArray(6 * 6 * 6)

    init {
        var pp = 0
        for (r in 0..5) {
            for (g in 0..5) {
                for (b in 0..5) {
                    val rr = r * 255 / 5
                    val gg = g * 255 / 5
                    val bb = b * 255 / 5
                    val mid = (rr * 30 + gg * 59 + bb * 11) / 100

                    val r1 = (rr + mid * 1) / 2 * 230 / 255 + 10
                    val g1 = (gg + mid * 1) / 2 * 230 / 255 + 10
                    val b1 = (bb + mid * 1) / 2 * 230 / 255 + 10
                    colors[pp++] = r1 shl 16 or (g1 shl 8) or b1
                }
            }
        }
    }

    operator fun get(a: Int, b: Int, c: Int, d: Int): Int {
        return (get(d) shl 24) + (get(c) shl 16) + (get(b) shl 8) + get(a)
    }

    operator fun get(d: Int): Int {
        if (d < 0)
            return 255
        val r = d / 100 % 10
        val g = d / 10 % 10
        val b = d / 1 % 10
        return r * 36 + g * 6 + b
    }

}