package connectfour.console

import connectfour.ConsoleApplication
import connectfour.entity.CellType
import connectfour.entity.Game
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

        val numberOfGames = readNumberOfGames()

        val player1 = playerFactory.newPlayer(playerName1, CellType.Player1)
        val player2 = playerFactory.newPlayer(playerName2, CellType.Player2)

        printStartMessage(
            player1 = player1,
            player2 = player2,
            rows = rows,
            columns = columns,
            totalGames = numberOfGames
        )

        val games = (1..numberOfGames).map {
            gameRepository.create(
                player1 = player1,
                player2 = player2,
                rows = rows,
                columns = columns,
            )
        }
        for (idGame in games) {
            val game = gameRepository.findById(idGame) ?: throw Exception("Game not found exception")
            if (numberOfGames > 1) {
                printGameTitle(game.id)
            }
            if (!playGame(game)) {
                break
            }
            if (numberOfGames > 1) {
                printScore(player1, player2)
            }
        }

        printGameOverMessage()
    }

    /**
     * Play the game
     *
     * @return true if we can proceed to the next game, otherwise false (user wants to end the streak)
     */
    private fun playGame(game: Game): Boolean {
        val gameService = gameServiceFactory.newServiceForGame(game)

        var turn = game.id + 1
        while (true) {
            val currentPlayer = if (turn % 2 == 0) game.player1 else game.player2
            game.board.print()

            playerinput@ while (true) {
                val inputCommand = readPlayersInput(currentPlayer)
                if (inputCommand is InputCommand.End) {
                    return false
                }
                try {
                    val inputColumn = (inputCommand as InputCommand.Column).column
                    val coordinates = gameService.throwDisk(currentPlayer, inputColumn)
                    if (coordinates != null && gameService.isTheDiskWon(coordinates)) {
                        game.board.print()
                        currentPlayer.score += 2
                        printPlayerWon(currentPlayer)
                        return true
                    }
                    break@playerinput
                } catch (e: ColumnIsFullException) {
                    println(e.message)
                } catch (e: IncorrectColumnNumberException) {
                    println(e.message)
                } catch (e: Exception) {
                    println("Unknown error: ${e.message}")
                    return false
                }
            }

            if (gameService.isBoardFull()) {
                game.board.print()
                game.player1.score += 1
                game.player2.score += 1
                printDrawMessage()
                return true
            }

            turn++
        }
        return true
    }
}
