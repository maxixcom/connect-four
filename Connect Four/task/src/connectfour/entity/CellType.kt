package connectfour.entity

enum class CellType(val char: Char) {
    Free(' '),
    Player1('o'),
    Player2('*'),
}
