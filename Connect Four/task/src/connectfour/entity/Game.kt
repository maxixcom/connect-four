package connectfour.entity

data class Game(
    val id: Int,
    val player1: Player,
    val player2: Player,
    val board: Board,
)
