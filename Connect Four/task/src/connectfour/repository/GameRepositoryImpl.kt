package connectfour.repository

import connectfour.entity.Board
import connectfour.entity.Game
import connectfour.entity.Player

object GameRepositoryImpl : GameRepository {
    private val games: MutableList<Game> = mutableListOf()

    override fun create(player1: Player, player2: Player, rows: Int, columns: Int): Int {
        val game = Game(
            id = games.size,
            player1 = player1,
            player2 = player2,
            board = Board(
                rows = rows,
                columns = columns,
            )
        )
        games.add(game)
        return game.id
    }

    override fun findById(id: Int): Game? = this.games.firstOrNull { it.id == id }
}
