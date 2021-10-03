package connectfour.service

import connectfour.entity.Player

class PlayerFactoryImpl : PlayerFactory {
    override fun newPlayer(name: String): Player = Player(name)
}
