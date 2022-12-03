import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int =
            input.fold(Pair(0, 0)) { (max, total), line ->
                if (line.isEmpty())
                    Pair(max(total, max), 0)
                else
                    Pair(max, total + line.toInt())
            }.first

    fun part2(input: List<String>): Int =
            input.fold(Pair(listOf(0, 0, 0), 0)) { (maxThree, total), line ->
                if (line.isEmpty())
                    Pair((maxThree + total).sortedDescending().take(3), 0)
                else
                    Pair(maxThree, total + line.toInt())
            }.first.sum()

    val input = readInput("Day01")

    val part1 = part1(input)
    println(part1)
    check(part1 == 69693)

    val part2 = part2(input)
    println(part2)
    check(part2 == 200945)
}
