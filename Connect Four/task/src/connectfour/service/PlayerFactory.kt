package connectfour.service

import connectfour.entity.Player

interface PlayerFactory {
    fun newPlayer(name: String): Player
}
