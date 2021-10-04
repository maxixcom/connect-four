package connectfour.service

import connectfour.entity.CellType
import connectfour.entity.Player

interface PlayerFactory {
    fun newPlayer(name: String, cellType: CellType): Player
}
