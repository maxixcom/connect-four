package connectfour.service

import connectfour.entity.Player

interface GameService {
    fun throwDisk(player: Player, column: Int)
    fun getWinner(): Player?
    fun isColumnFull(column: Int): Boolean
    fun isBoardFull(): Boolean
}
