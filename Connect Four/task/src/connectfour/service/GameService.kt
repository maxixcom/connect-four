package connectfour.service

import connectfour.entity.Coordinates
import connectfour.entity.Player

interface GameService {
    fun throwDisk(player: Player, column: Int): Coordinates?
    fun isTheDiskWon(coordinates: Coordinates): Boolean
    fun isColumnFull(column: Int): Boolean
    fun isBoardFull(): Boolean
}
