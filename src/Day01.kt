import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var total = 0
        input.forEach { string ->
            if (string.isEmpty()) {
                max = max(total, max)
                total = 0
            } else {
                total += string.toInt()
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val totals = mutableListOf<Int>()
        var total = 0
        input.forEach { string ->
            if (string.isEmpty()) {
                totals.add(total)
                total = 0
            } else {
                total += string.toInt()
            }
        }
        return totals.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))

}
