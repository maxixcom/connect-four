package connectfour.console

import connectfour.ConsoleApplication
import connectfour.entity.CellType
import connectfour.exception.ColumnIsFullException
import connectfour.exception.IncorrectColumnNumberException
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
            player1 = playerFactory.newPlayer(playerName1, CellType.Player1),
            player2 = playerFactory.newPlayer(playerName2, CellType.Player2),
            rows = rows,
            columns = columns,
        )

        val game = gameRepository.findById(idGame) ?: throw Exception("Game not found exception")
        val gameService = gameServiceFactory.newServiceForGame(game)

        game.printStartMessage()

        var turn = 0
        mainloop@ while (true) {
            val currentPlayer = if (turn % 2 == 0) game.player1 else game.player2
            game.board.print()

            playerinput@ while (true) {
                val inputCommand = readPlayersInput(currentPlayer)
                if (inputCommand is InputCommand.End) {
                    break@mainloop
                }
                try {
                    val inputColumn = (inputCommand as InputCommand.Column).column
                    val coordinates = gameService.throwDisk(currentPlayer, inputColumn)
                    if (coordinates != null && gameService.isTheDiskWon(coordinates)) {
                        game.board.print()
                        printPlayerWon(currentPlayer)
                        break@mainloop
                    }
                    break@playerinput
                } catch (e: ColumnIsFullException) {
                    println(e.message)
                } catch (e: IncorrectColumnNumberException) {
                    println(e.message)
                } catch (e: Exception) {
                    println("Unknown error: ${e.message}")
                    break@mainloop
                }
            }

            if (gameService.isBoardFull()) {
                game.board.print()
                printDrawMessage()
                break
            }

            turn++
        }

        printGameOverMessage()
    }
}
