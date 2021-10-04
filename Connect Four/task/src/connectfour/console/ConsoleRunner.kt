package connectfour.console

import connectfour.ConsoleApplication
import connectfour.repository.GameRepository
import connectfour.service.GameServiceFactory
import connectfour.service.PlayerFactory

class ConsoleRunner {
    private val playerFactory: PlayerFactory = ConsoleApplication.playerFactory
    private val gameServiceFactory: GameServiceFactory = ConsoleApplication.gameServiceFactory
    private val gameRepository: GameRepository = ConsoleApplication.gameRepository

    fun run() {
        printWelcomeMessage()
        val (playerName1, playerName2) = readPlayerNames()
        var (rows, columns) = readBoardsDimensions()

        val idGame = gameRepository.create(
            player1 = playerFactory.newPlayer(playerName1),
            player2 = playerFactory.newPlayer(playerName1),
            rows = rows,
            columns = columns,
        )

        val game = gameRepository.findById(idGame) ?: throw Exception("Game not found exception")
        val gameService = gameServiceFactory.newServiceForGame(game)

        game.printStartMessage()
        game.board.print()
    }
}
