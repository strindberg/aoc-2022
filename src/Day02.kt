import Result.DRAW
import Result.LOSS
import Result.WIN

sealed interface Move {
    val points: Int
    fun beats(other: Move): Result
    fun askedFor(result: Result): Move
}

enum class Result { WIN, DRAW, LOSS }

object Rock : Move {
    override val points = 1

    override fun beats(other: Move) =
            when (other) {
                is Scissor -> WIN
                is Rock -> DRAW
                else -> LOSS
            }

    override fun askedFor(result: Result) =
            when (result) {
                WIN -> Paper
                DRAW -> Rock
                LOSS -> Scissor
            }
}

object Paper : Move {
    override val points = 2

    override fun beats(other: Move) = when (other) {
        is Rock -> WIN
        is Paper -> DRAW
        else -> LOSS
    }

    override fun askedFor(result: Result) =
            when (result) {
                WIN -> Scissor
                DRAW -> Paper
                LOSS -> Rock
            }
}

object Scissor : Move {
    override val points = 3
    override fun beats(other: Move) = when (other) {
        is Paper -> WIN
        is Scissor -> DRAW
        else -> LOSS
    }

    override fun askedFor(result: Result) =
            when (result) {
                WIN -> Rock
                DRAW -> Scissor
                LOSS -> Paper
            }
}

fun getFirstMove(first: Char) =
        if (first == 'A') Rock else if (first == 'B') Paper else if (first == 'C') Scissor else throw RuntimeException()

fun getSecondMove(b: Char) =
        if (b == 'X') Rock else if (b == 'Y') Paper else if (b == 'Z') Scissor else throw RuntimeException()

fun getSecondResult(second: Char) =
        if (second == 'X') LOSS else if (second == 'Y') DRAW else if (second == 'Z') WIN else throw RuntimeException()

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val first = getFirstMove(line[0])
            val second = getSecondMove(line[2])
            when (second.beats(first)) {
                WIN -> second.points + 6
                DRAW -> second.points + 3
                LOSS -> second.points
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val first = getFirstMove(line[0])
            val result = getSecondResult(line[2])
            val second = first.askedFor(result)
            when (result) {
                WIN -> second.points + 6
                DRAW -> second.points + 3
                LOSS -> second.points
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01")
    // check(part1(testInput) == 1)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))

}
