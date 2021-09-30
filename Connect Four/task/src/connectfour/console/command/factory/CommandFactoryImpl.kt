package connectfour.console.command.factory

import connectfour.console.command.CommandCreate

class CommandFactoryImpl : CommandFactory {

    override fun parseCreate(input: String): CommandCreate? {
        return "^\\s*(?<width>\\d)\\s*x\\s*(?<height>\\d)\\s*$".toRegex(RegexOption.IGNORE_CASE).matchEntire(input)
            ?.let {
                CommandCreate(
                    width = it.groups["width"]!!.value.toInt(),
                    height = it.groups["height"]!!.value.toInt(),
                )
            }
    }
}
