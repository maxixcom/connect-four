package connectfour.service

import connectfour.entity.CellType
import connectfour.entity.Coordinates
import connectfour.entity.Game
import connectfour.entity.Player
import connectfour.exception.ColumnIsFullException
import connectfour.exception.IncorrectColumnNumberException

class GameServiceImpl(
    private val game: Game
) : GameService {
    override fun throwDisk(player: Player, column: Int): Coordinates? {
        if (column !in 1..game.board.columns) {
            throw IncorrectColumnNumberException("The column number is out of range (1 - ${game.board.columns})")
        }
        if (isColumnFull(column)) {
            throw ColumnIsFullException("Column $column is full")
        }

        for (n in 0 until game.board.rows) {
            if (game.board.data[n][column - 1] == CellType.Free) {
                game.board.data[n][column - 1] = player.cellType
                return Coordinates(n, column - 1)
            }
        }
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

    override fun isTheDiskWon(coordinates: Coordinates): Boolean =
        isHorizontalLineWon(coordinates) || isVerticalLineWon(coordinates) ||
            isLeftDiagonalWon(coordinates) || isRightDiagonalWon(coordinates)

    private fun isRightDiagonalWon(coordinates: Coordinates): Boolean {
        val boardData = game.board.data

        var num = 0
        var row = coordinates.row
        var column = coordinates.column
        val playerType = boardData[row][column]
        // ->v
        while ((row < game.board.rows) && (column >= 0) && (boardData[row][column] == playerType)) {
            num++
            row++
            column--
        }
        // <-^
        row = coordinates.row - 1
        column = coordinates.column + 1
        while ((row >= 0) && (column < game.board.columns) && (boardData[row][column] == playerType)) {
            num++
            row--
            column++
        }
        if (num >= 4) {
            return true
        }
        return false
    }

    private fun isLeftDiagonalWon(coordinates: Coordinates): Boolean {
        val boardData = game.board.data

        var num = 0
        var row = coordinates.row
        var column = coordinates.column
        val playerType = boardData[row][column]
        // <-v
        while ((row >= 0) && (column >= 0) && (boardData[row][column] == playerType)) {
            num++
            row--
            column--
        }
        // ->^
        row = coordinates.row + 1
        column = coordinates.column + 1
        while ((row < game.board.rows) && (column < game.board.columns) && (boardData[row][column] == playerType)) {
            num++
            row++
            column++
        }
        if (num >= 4) {
            return true
        }
        return false
    }

    private fun isVerticalLineWon(coordinates: Coordinates): Boolean {
        val boardData = game.board.data

        var num = 0
        var row = coordinates.row
        val column = coordinates.column
        val playerType = boardData[row][column]
        // v
        while ((row >= 0) && (boardData[row][column] == playerType)) {
            num++
            row--
        }
        // ^
        row = coordinates.row + 1
        while ((row < game.board.rows) && (boardData[row][column] == playerType)) {
            num++
            row++
        }
        if (num >= 4) {
            return true
        }
        return false
    }

    private fun isHorizontalLineWon(coordinates: Coordinates): Boolean {
        val boardData = game.board.data

        var num = 0
        val row = coordinates.row
        var column = coordinates.column
        val playerType = boardData[row][column]
        // <-
        while ((column >= 0) && (boardData[row][column] == playerType)) {
            num++
            column--
        }
        // ->
        column = coordinates.column + 1
        while ((column < game.board.columns) && (boardData[row][column] == playerType)) {
            num++
            column++
        }
        if (num >= 4) {
            return true
        }
        return false
    }
}
