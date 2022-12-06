fun main() {
    fun distinct(chars: List<Char>): Boolean {
        for ((index, c) in chars.withIndex()) {
            if (index + 1 <= chars.size) {
                for (d in chars.subList(index + 1, chars.size)) {
                    if (c == d) return false
                }
            }
        }
        return true
    }

    fun signature(input: String, length: Int): Int =
            input.fold(Triple(0, listOf<Char>(), false)) { (index, list, found), char ->
                if (list.size < length - 1) {
                    Triple(index + 1, list + char, false)
                } else if (found) {
                    Triple(index, list, true)
                } else {
                    val newList = list.takeLast(length - 1) + char
                    if (distinct(newList)) {
                        Triple(index + 1, newList, true)
                    } else {
                        Triple(index + 1, newList, false)
                    }
                }
            }.first

    val input = readInput("Day06")[0]

    println(signature(input, 4))
    check(signature(input, 4) == 1909)

    println(signature(input, 14))
    check(signature(input, 14) == 3380)
}
