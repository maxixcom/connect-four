package connectfour.console.controller

import connectfour.console.command.CommandCreate

interface GameController {
    fun createGameBoard(command: CommandCreate)
}
