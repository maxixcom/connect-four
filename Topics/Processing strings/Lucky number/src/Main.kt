fun main() {
    val number = readLine()!!
    val list = number.foldIndexed(mutableListOf(0, 0)) { index, acc, c ->
        if (index < number.length / 2) {
            acc[0] += c.digitToInt()
        } else {
            acc[1] += c.digitToInt()
        }
        acc
    }

    println(
        if (list[0] == list[1]) {
            "YES"
        } else {
            "NO"
        }
    )
}
