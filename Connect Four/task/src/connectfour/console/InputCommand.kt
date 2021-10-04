package connectfour.console

sealed interface InputCommand {
    class Column(val column: Int) : InputCommand
    object End : InputCommand
}
