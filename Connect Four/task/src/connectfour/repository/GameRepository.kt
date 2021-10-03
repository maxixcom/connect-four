package connectfour.repository

import connectfour.entity.Game
import connectfour.entity.Player

interface GameRepository {
    fun create(player1: Player, player2: Player, rows: Int, columns: Int): Int
    fun findById(id: Int): Game?
}
