fun main() {
    fun createStacks(input: List<String>): Array<ArrayDeque<Char>> =
            Array(9) { ArrayDeque<Char>() }.apply {
                input.forEach { line ->
                    if (line.contains("[")) {
                        for ((index, char) in line.withIndex()) {
                            if (char == '[') {
                                this[index / 4].addLast(line[index + 1])
                            }
                        }
                    } else {
                        return this
                    }
                }
            }

    fun interpret(line: String): Triple<Int, Int, Int> =
            line.split(" ").let {
                Triple(it[1].toInt(), it[3].toInt() - 1, it[5].toInt() - 1)
            }

    fun moveCrates1(input: List<String>, stacks: Array<ArrayDeque<Char>>): String {
        input.forEach { line ->
            if (line.startsWith("move")) {
                val (amount, from, to) = interpret(line)
                for (i in 0 until amount) {
                    stacks[to].addFirst(stacks[from].removeFirst())
                }
            }
        }
        return String(stacks.map { it.first() }.toCharArray())
    }

    fun moveCrates2(input: List<String>, stacks: Array<ArrayDeque<Char>>): String {
        input.forEach { line ->
            if (line.startsWith("move")) {
                val (amount, from, to) = interpret(line)
                mutableListOf<Char>().let { temp ->
                    for (i in 0 until amount) {
                        temp.add(stacks[from].removeFirst())
                    }
                    for (element in temp.reversed()) {
                        stacks[to].addFirst(element)
                    }
                }
            }
        }
        return String(stacks.map { it.first() }.toCharArray())
    }

    fun part1(input: List<String>): String = moveCrates1(input, createStacks(input))

    fun part2(input: List<String>): String = moveCrates2(input, createStacks(input))

    val input = readInput("Day05")

    println(part1(input))
    check(part1(input) == "QPJPLMNNR")

    println(part2(input))
    check(part2(input) == "BQDNWJPVJ")
}
