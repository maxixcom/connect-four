package connectfour

import connectfour.repository.GameRepository
import connectfour.repository.GameRepositoryImpl
import connectfour.service.GameServiceFactory
import connectfour.service.GameServiceFactoryImpl
import connectfour.service.PlayerFactory
import connectfour.service.PlayerFactoryImpl

object ConsoleApplication {
    val playerFactory: PlayerFactory = PlayerFactoryImpl()
    val gameRepository: GameRepository = GameRepositoryImpl
    val gameServiceFactory: GameServiceFactory = GameServiceFactoryImpl()
}
