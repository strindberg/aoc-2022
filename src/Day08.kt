import kotlin.math.max

fun main() {

    fun parse(input: List<String>) = input.map { line -> line.toList().map { it.digitToInt() } }

    fun outerVisible(map: List<List<Int>>, row: List<Int>, rowIndex: Int, colIndex: Int) =
        rowIndex == 0 || colIndex == 0 || rowIndex == map.lastIndex || colIndex == row.lastIndex

    fun leftVisible(row: List<Int>, colIndex: Int, value: Int) =
        row.subList(0, colIndex).all { it < value }

    fun rightVisible(row: List<Int>, colIndex: Int, value: Int) =
        row.subList(colIndex + 1, row.size).all { it < value }

    fun upVisible(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) =
        map.subList(0, rowIndex).all { it[colIndex] < value }

    fun downVisible(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) =
        map.subList(rowIndex + 1, map.size).all { it[colIndex] < value }

    fun outerScore(map: List<List<Int>>, row: List<Int>, rowIndex: Int, colIndex: Int) =
        if (rowIndex == 0 || colIndex == 0 || rowIndex == map.lastIndex || colIndex == row.lastIndex) 0 else 1

    fun horizontalScore(row: List<Int>, value: Int) =
        row.fold(Pair(0, false)) { (count, found), newValue ->
            if (found) Pair(count, true)
            else if (newValue >= value) Pair(count + 1, true)
            else Pair(count + 1, false)
        }.first

    fun leftScore(row: List<Int>, colIndex: Int, value: Int) =
        horizontalScore(row.subList(0, colIndex).reversed(), value)

    fun rightScore(row: List<Int>, colIndex: Int, value: Int) =
        horizontalScore(row.subList(colIndex + 1, row.size), value)

    fun verticalScore(map: List<List<Int>>, colIndex: Int, value: Int) =
        map.fold(Pair(0, false)) { (count, found), list ->
            if (found) Pair(count, true)
            else if (list[colIndex] >= value) Pair(count + 1, true)
            else Pair(count + 1, false)
        }.first

    fun upScore(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) =
        verticalScore(map.subList(0, rowIndex).reversed(), colIndex, value)

    fun downScore(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) =
        verticalScore(map.subList(rowIndex + 1, map.size), colIndex, value)

    fun part1(map: List<List<Int>>) =
        map.foldIndexed(0) { rowIndex, count, row ->
            row.foldIndexed(count) { colIndex, rowCount, value ->
                if (outerVisible(map, row, rowIndex, colIndex) ||
                    leftVisible(row, colIndex, value) ||
                    rightVisible(row, colIndex, value) ||
                    upVisible(map, rowIndex, colIndex, value) ||
                    downVisible(map, rowIndex, colIndex, value)
                ) rowCount + 1 else rowCount
            }
        }

    fun part2(map: List<List<Int>>) =
        map.foldIndexed(0) { rowIndex, max, row ->
            row.foldIndexed(max) { colIndex, rMax, value ->
                max(
                    rMax,
                    outerScore(map, row, rowIndex, colIndex) *
                            leftScore(row, colIndex, value) *
                            rightScore(row, colIndex, value) *
                            upScore(map, rowIndex, colIndex, value) *
                            downScore(map, rowIndex, colIndex, value)
                )
            }
        }

    val map = parse(readInput("Day08"))

    val part1 = part1(map)
    println(part1)
    check(part1 == 1715)

    val part2 = part2(map)
    println(part2)
    check(part2 == 374400)
}
