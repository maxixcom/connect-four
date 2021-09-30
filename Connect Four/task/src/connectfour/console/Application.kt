package connectfour.console

import connectfour.application.interactors.CreateGameBoardImpl
import connectfour.domain.usecases.CreateGameBoard
import connectfour.persistence.GameBoardRegistry
import connectfour.persistence.GameBoardRegistryImpl

object Application {
    private val gameBoardRegistry: GameBoardRegistry = GameBoardRegistryImpl()

    val useCaseCreateGameBoard: CreateGameBoard = CreateGameBoardImpl(
        gameBoardRegistry = gameBoardRegistry
    )
}
