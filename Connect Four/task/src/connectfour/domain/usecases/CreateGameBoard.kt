package connectfour.domain.usecases

import connectfour.domain.entity.GameBoard

interface CreateGameBoard {
    fun execute(request: Request): Response
    data class Request(val width: Int, val height: Int)
    data class Response(val result: Result<GameBoard>)
}
