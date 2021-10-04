package connectfour.service

import connectfour.entity.CellType
import connectfour.entity.Player

class PlayerFactoryImpl : PlayerFactory {
    override fun newPlayer(name: String, cellType: CellType): Player = Player(name, cellType)
}
