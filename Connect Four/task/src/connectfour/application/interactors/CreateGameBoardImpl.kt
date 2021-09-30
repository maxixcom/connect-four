package connectfour.application.interactors

import connectfour.domain.usecases.CreateGameBoard
import connectfour.persistence.GameBoardRegistry

class CreateGameBoardImpl(
    private val gameBoardRegistry: GameBoardRegistry
) : CreateGameBoard {
    override fun execute(request: CreateGameBoard.Request): CreateGameBoard.Response {
        val result = kotlin.runCatching {
            with(request) {
                gameBoardRegistry.create(width, height)
                gameBoardRegistry.get()!!
            }
        }
        return CreateGameBoard.Response(result)
    }
}
