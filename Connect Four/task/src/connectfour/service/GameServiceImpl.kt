package connectfour.service

import connectfour.entity.CellType
import connectfour.entity.Game
import connectfour.entity.Player
import connectfour.exception.ColumnIsFullException
import connectfour.exception.IncorrectColumnNumberException

class GameServiceImpl(
    private val game: Game
) : GameService {
    override fun throwDisk(player: Player, column: Int) {
        if (column !in 1..game.board.columns) {
            throw IncorrectColumnNumberException("The column number is out of range (1 - ${game.board.columns})")
        }
        if (isColumnFull(column)) {
            throw ColumnIsFullException("Column $column is full")
        }

        for (n in 0 until game.board.rows) {
            if (game.board.data[n][column - 1] == CellType.Free) {
                game.board.data[n][column - 1] = player.cellType
                break
            }
        }
    }

    override fun getWinner(): Player? {
        // TODO: !!!
        return null
    }

    override fun isColumnFull(column: Int): Boolean {
        if (column !in 1..game.board.columns) {
            throw IncorrectColumnNumberException("The column number is out of range (1 - ${game.board.columns})")
        }

        for (n in 0 until game.board.rows) {
            if (game.board.data[n][column - 1] == CellType.Free) {
                return false
            }
        }
        return true
    }

    override fun isBoardFull(): Boolean {
        return game.board.data.all { rows ->
            rows.all { cell ->
                cell != CellType.Free
            }
        }
    }
}
