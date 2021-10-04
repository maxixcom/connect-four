package connectfour.console

import connectfour.entity.Board
import connectfour.entity.Game
import connectfour.entity.Player

fun printWelcomeMessage() {
    println("Connect Four")
}

fun readPlayerNames(): Pair<String, String> {
    println("First player's name:")
    val player1 = readLine()!!
    println("Second player's name:")
    val player2 = readLine()!!

    return player1 to player2
}

fun readBoardsDimensions(): Pair<Int, Int> {
    var rows = 6
    var columns = 7

    while (true) {
        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        try {
            readLine()?.let { input ->
                if (input.isEmpty()) {
                    return@let
                }
                val regex = "^\\s*(\\d+)\\s*x\\s*(\\d+)\\s*$".toRegex(RegexOption.IGNORE_CASE)
                regex.matchEntire(input)?.let {
                    rows = it.groups[1]!!.value.toInt()
                    columns = it.groups[2]!!.value.toInt()
                    if (rows !in (5..9)) {
                        throw IllegalArgumentException("Board rows should be from 5 to 9")
                    }
                    if (columns !in (5..9)) {
                        throw IllegalArgumentException("Board columns should be from 5 to 9")
                    }
                } ?: throw IllegalArgumentException("Invalid input")
            }
            break
        } catch (e: Exception) {
            println(e.message)
        }
    }

    return rows to columns
}

fun Board.print() {
    println(
        (1..this.columns).joinToString(
            separator = " ",
            prefix = " "
        )
    )
    this.data.reversed().forEach { row ->
        println(
            row.map { it.char }.joinToString(
                separator = "║",
                prefix = "║",
                postfix = "║"
            )
        )
    }
    val bottom = List(this.columns) { '═' }
    println(
        bottom.joinToString(
            separator = "╩",
            prefix = "╚",
            postfix = "╝"
        )
    )
}

fun Game.printStartMessage() {
    println("${player1.name} VS ${player2.name}")
    println("${board.rows} X ${board.columns} board")
}

fun readPlayersInput(player: Player): InputCommand {
    while (true) {
        try {
            println("${player.name}'s turn:")
            return when (val input = readLine()) {
                "end" -> InputCommand.End
                else -> InputCommand.Column(input?.toInt() ?: throw NumberFormatException())
            }
        } catch (e: Exception) {
            println("Incorrect column number")
        }
    }
}

fun printGameOverMessage() {
    println("Game over!")
}
