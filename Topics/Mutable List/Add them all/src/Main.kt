fun solution(first: List<Int>, second: List<Int>): MutableList<Int> {
    val list = first.toMutableList()
    list.addAll(second)
    return list
}
