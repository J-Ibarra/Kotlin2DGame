package org.jcs.engine


class GameLoop(val gameLogic: IGameLogic) : Runnable {
    private var running: Boolean = false

    override fun run() {

        this.gameLogic.init()

        val nsPerTicks = 1000000000.0 / 60

        var delta = 0.0
        var frames = 0
        var ticks = 0

        var lastTime = System.nanoTime()
        var lastTimer = System.currentTimeMillis()

        while (this.running) {
            val now = System.nanoTime()
            delta += (now - lastTime) / nsPerTicks
            lastTime = now

            var shouldRender = false

            while (delta >= 1.0) {
                this.gameLogic.tick()
                ticks++
                delta -= 1.0
                shouldRender = true
            }

            Thread.sleep(10)
            Thread.yield()

            if (shouldRender) {
                this.gameLogic.render()
                frames++
            }

            if (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000
                this.gameLogic.oneSecond(ticks, frames)
                frames = 0
                ticks = 0
            }
        }

        this.gameLogic.destroy()

    }

    @Synchronized fun start() {
        this.running = true
        Thread(this, "Jcs Game").start()
    }

    @Synchronized fun stop() {
        this.running = false
        Thread.yield()
    }
}