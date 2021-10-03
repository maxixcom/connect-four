package connectfour

import connectfour.console.extensions.print
import connectfour.entity.Board

fun main() {
    printWelcomeMessage()
    println("First player's name:")
    val player1 = readLine()!!
    println("Second player's name:")
    val player2 = readLine()!!

    var rows: Int = 6
    var columns: Int = 7

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

    println("$player1 VS $player2")
    println("$rows X $columns board")

    val board = Board(
        rows = rows,
        columns = columns
    )

//    val board = List(rows) {
//        MutableList(columns) {
//            ' '
//        }
//    }
    board.print()
}

fun printWelcomeMessage() {
    println("Connect Four")
}
