package connectfour.entity

enum class CellType(val char: Char) {
    Free(' '),
    Player1('x'),
    Player2('o'),
}
