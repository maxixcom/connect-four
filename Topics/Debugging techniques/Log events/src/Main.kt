fun String?.capitalize(): String? {
    println("Before: $this")
    val capitalized = when {
        isNullOrBlank() -> this
        length == 1 -> toUpperCase()
        else -> Character.toUpperCase(this[0]).toString() + substring(1)
    }
    println("After: $capitalized")
    return capitalized
}