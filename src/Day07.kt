sealed class File {
    abstract fun size(): Int
}

class TextFile(private val size: Int) : File() {
    override fun size(): Int = size
}

class Dir(private val parent: Dir?) : File() {
    private val children: MutableMap<String, File> = mutableMapOf()

    override fun size(): Int = children.values.sumOf { it.size() }

    fun parent() = parent!!

    fun childDir(name: String): Dir = children.getOrPut(name) { Dir(this) } as Dir

    fun childFile(name: String, size: String) {
        children[name] = TextFile(size.toInt())
    }

    fun allDirs(): List<Dir> = children.values.filterIsInstance<Dir>().flatMap { it.allDirs() } + this
}

fun main() {
    fun parse(input: List<String>): Dir {
        val top = Dir(null)
        input.fold(top) { current, line ->
            if (line.startsWith("$")) {
                if (line.substring(2).startsWith("cd ")) {
                    when (val dest = line.substring(5)) {
                        "/" -> top
                        ".." -> current.parent()
                        else -> current.childDir(dest)
                    }
                } else current
            } else {
                if (line.startsWith("dir ")) {
                    current.childDir(line.substring(4))
                } else {
                    val info = line.split(" ")
                    current.childFile(info[1], info[0])
                }
                current
            }
        }
        return top
    }

    fun part1(input: List<String>): Int {
        val top = parse(input)
        return top.allDirs().filter { it.size() < 100000 }.sumOf { it.size() }
    }

    fun part2(input: List<String>): Int {
        val top = parse(input)
        val free = 70000000 - top.size()
        return top.allDirs().filter { free + it.size() > 30000000 }.minBy { it.size() }.size()
    }

    val input = readInput("Day07")

    val part1 = part1(input)
    println(part1)
    check(part1 == 1423358)

    val part2 = part2(input)
    println(part2)
    check(part2 == 545729)
}
