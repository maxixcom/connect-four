import kotlin.math.pow

fun main() {
    // write your code here
    var amount = 1000
    var years = 10
    var percent = 5

    val param = readLine()!!
    val value = readLine()!!.toInt()

    when (param) {
        "amount" -> amount = value
        "years" -> years = value
        "percent" -> percent = value
    }

    val k = amount * (1 + percent / 100.0).pow(years)
    println(k.toInt())
}
