fun priority(char: Char): Int =
        if (char.isUpperCase()) char - 'A' + 27 else if (char.isLowerCase()) char - 'a' + 1 else throw RuntimeException()

fun main() {
    fun part1(input: List<String>): Int =
            input.sumOf { line ->
                val first = line.take(line.length / 2).toSet().toList()
                val second = line.takeLast(line.length / 2).toSet().toList()
                val doubles = (first + second)
                        .groupBy { it }
                        .filter { it.value.size > 1 }
                        .map { it.key }
                priority(doubles[0])
            }

    fun part2(input: List<String>): Int =
            input.fold(Pair(0, listOf<String>())) { (sum, list), line ->
                if (list.size < 2) {
                    Pair(sum, list + line)
                } else {
                    val triples = (list[0].toSet().toList() + list[1].toSet().toList() + line.toSet().toList())
                            .groupBy { it }
                            .filter { it.value.size == 3 }
                            .map { it.key }
                    Pair(sum + priority(triples[0]), listOf())
                }
            }.first

    val input = readInput("Day03")

    println(part1(input))
    check(part1(input) == 8105)

    println(part2(input))
    check(part2(input) == 2363)
}
