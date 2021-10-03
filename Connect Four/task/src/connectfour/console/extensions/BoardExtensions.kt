package connectfour.console.extensions

import connectfour.entity.Board

fun Board.print() {
    println(
        (1..this.columns).joinToString(
            separator = " ",
            prefix = " "
        )
    )
    this.data.reversed().forEach { row ->
        println(
            row.map { it.char }.joinToString(
                separator = "║",
                prefix = "║",
                postfix = "║"
            )
        )
    }
    val bottom = List(this.columns) { '═' }
    println(
        bottom.joinToString(
            separator = "╩",
            prefix = "╚",
            postfix = "╝"
        )
    )
}
