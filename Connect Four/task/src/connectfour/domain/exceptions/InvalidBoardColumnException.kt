package connectfour.domain.exceptions

class InvalidBoardColumnException(message: String = "Board columns should be from 5 to 9") : Exception(message)
