package connectfour.persistence

import connectfour.domain.entity.GameBoard

interface GameBoardRegistry {
    fun create(width: Int, height: Int)
    fun get(): GameBoard?
}
