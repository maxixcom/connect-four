package connectfour.console.command.factory

import connectfour.console.command.CommandCreate

interface CommandFactory {
    fun parseCreate(input: String): CommandCreate?
}
