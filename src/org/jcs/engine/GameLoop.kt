package org.jcs.engine

import org.jcs.IGameLogic


class GameLoop(val gameLogic: IGameLogic) : Runnable {
    private var running: Boolean = false

    override fun run() {

        this.gameLogic.init()

        while (this.running) {

            this.gameLogic.tick()

            this.gameLogic.render()

            Thread.sleep(10)
            Thread.yield()

            this.gameLogic.oneSecond()
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