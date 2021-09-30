fun main() {
    val xs = MutableList(5) { it + 1 }
    val ys = MutableList(5) { it + 1 }
    repeat(3) {
        val (x, y) = readLine()!!
            .split(" ")
            .take(2)
            .map(String::toInt)
        xs[x - 1] = 0
        ys[y - 1] = 0
    }
    println(xs.filter { it != 0 }.joinToString(" "))
    println(ys.filter { it != 0 }.joinToString(" "))
}
