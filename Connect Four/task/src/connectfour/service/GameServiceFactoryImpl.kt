package connectfour.service

import connectfour.entity.Game

class GameServiceFactoryImpl : GameServiceFactory {
    override fun newServiceForGame(game: Game): GameService {
        return GameServiceImpl(game)
    }
}
