fun main() {
    when (readLine()!!.toInt()) {
        in -14..12, in 15..16, in 19..Int.MAX_VALUE -> println("True")
        else -> println("False")
    }
}
