package connectfour.entity

data class Player(
    val name: String,
    val cellType: CellType,
    var score: Int = 0,
)
