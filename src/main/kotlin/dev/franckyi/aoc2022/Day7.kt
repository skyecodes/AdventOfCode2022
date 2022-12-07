package dev.franckyi.aoc2022

fun main() {
    val input = Utils.read(7).lines()
    val root = parseDir(input)
    println(part1(root))
    println(part2(root))
}

private fun parseDir(input: List<String>): Directory {
    val root = Directory()
    var index = 1 // first line is always "cd /", so we can ignore it and setup root directory
    val currentDirStack = ArrayDeque<Directory>()
    currentDirStack.add(root)
    while (index < input.size) {
        val line = input[index++].split(" ")
        if (line[1] == "cd") {
            line[2].split("/").forEach {
                if (it == "..") currentDirStack.removeLast()
                else currentDirStack.add(currentDirStack.last().directories[it]!!)
            }
        } else if (line[1] == "ls") {
            val currentDir = currentDirStack.last()
            while (index < input.size && !input[index].startsWith("$")) {
                val (size, name) = input[index++].split(" ")
                if (size == "dir") currentDir.directories[name] = Directory()
                else currentDir.files[name] = size.toInt()
            }
        }
    }
    return root
}

private fun part1(root: Directory): Int = root.allDirs.map { it.size }.filter { it < 100000 }.sum()

private fun part2(root: Directory): Int {
    val needed = 30000000 - (70000000 - root.size)
    return root.allDirs.map { it.size }.filter { it >= needed }.min()
}

private class Directory {
    val directories = mutableMapOf<String, Directory>()
    val files = mutableMapOf<String, Int>()
    val size: Int get() = directories.values.sumOf { it.size } + files.values.sum()
    val allDirs: List<Directory> get() = buildList {
        add(this@Directory)
        addAll(directories.values.flatMap { it.allDirs })
    }
}