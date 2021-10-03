package connectfour.entity

data class Board(
    val rows: Int,
    val columns: Int
) {
    val data: List<MutableList<CellType>> = List(rows) {
        MutableList(columns) {
            CellType.Free
        }
    }
}
