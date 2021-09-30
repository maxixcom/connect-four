package connectfour.console.controller

import connectfour.console.command.CommandCreate
import connectfour.domain.usecases.CreateGameBoard

class GameControllerImpl(
    private val createGameBoard: CreateGameBoard
) : GameController {
    override fun createGameBoard(command: CommandCreate) {
        val response = createGameBoard.execute(
            CreateGameBoard.Request(
                width = command.width,
                height = command.height
            )
        )
        response.result.onFailure {
            println()
        }
    }
}
