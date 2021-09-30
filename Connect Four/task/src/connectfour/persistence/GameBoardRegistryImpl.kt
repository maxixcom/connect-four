package connectfour.persistence

import connectfour.domain.entity.GameBoard

class GameBoardRegistryImpl : GameBoardRegistry {
    private var instance: GameBoard? = null

    override fun create(width: Int, height: Int) {
        this.instance = GameBoard(width, height)
    }

    override fun get() = instance
}
