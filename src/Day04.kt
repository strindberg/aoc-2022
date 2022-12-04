data class RangePair(val start1: Int, val end1: Int, val start2: Int, val end2: Int)

fun ranges(line: String): RangePair {
    val pair1 = line.split(",")[0].split("-")
    val pair2 = line.split(",")[1].split("-")
    return RangePair(pair1[0].toInt(), pair1[1].toInt(), pair2[0].toInt(), pair2[1].toInt())
}

fun main() {
    fun part1(input: List<String>): Int =
            input.fold(0) { count, line ->
                val rs = ranges(line)
                if ((rs.start1 <= rs.start2 && rs.end1 >= rs.end2) || ((rs.start2 <= rs.start1 && rs.end2 >= rs.end1)))
                    count + 1
                else
                    count
            }

    fun part2(input: List<String>): Int =
            input.fold(0) { count, line ->
                val rs = ranges(line)
                if (rs.end1 < rs.start2 || rs.start1 > rs.end2)
                    count
                else
                    count + 1
            }

    val input = readInput("Day04")

    println(part1(input))
    check(part1(input) == 509)

    println(part2(input))
    check(part2(input) == 870)
}
