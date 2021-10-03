package connectfour.service

import connectfour.entity.Game

interface GameServiceFactory {
    fun newServiceForGame(game: Game): GameService
}
