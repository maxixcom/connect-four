package connectfour.domain.exceptions

class InvalidBoardRowException(message: String = "Board rows should be from 5 to 9") : Exception(message)
