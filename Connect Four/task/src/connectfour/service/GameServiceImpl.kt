package connectfour.service

import connectfour.entity.Game
import connectfour.entity.Player

class GameServiceImpl(
    private val game: Game
) : GameService {
    override fun throwDisk(player: Player, column: Int) {
        TODO("Not yet implemented")
    }

    override fun getWinner(): Player? {
        TODO("Not yet implemented")
    }

    override fun isColumnFull(column: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBoardFull(): Boolean {
        TODO("Not yet implemented")
    }
}
