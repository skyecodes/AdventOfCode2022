package dev.franckyi.aoc2022

fun main() {
    val lines = Utils.read(5).lines()
    val rows = lines.indexOfFirst { it.isEmpty() } - 1
    val cols = (lines[rows].length + 2) / 4
    val instructions = lines.drop(rows + 2)
        .map { it.split(" ") }
        .map { Triple(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
    var stacks = readStacks(lines, rows, cols)
    println(part1(stacks, instructions))
    stacks = readStacks(lines, rows, cols)
    println(part2(stacks, instructions))
}

private fun part1(stacks: MutableList<ArrayDeque<Char>>, instructions: List<Triple<Int, Int, Int>>): String {
    instructions.forEach { (amount, from, to) -> repeat(amount) { stacks[to - 1].add(stacks[from - 1].removeLast()) } }
    return stacks.map { it.last() }.joinToString("")
}

private fun part2(stacks: MutableList<ArrayDeque<Char>>, instructions: List<Triple<Int, Int, Int>>): String {
    instructions.forEach { (amount, from, to) ->
        stacks[to - 1].addAll(stacks[from - 1].takeLast(amount))
        repeat(amount) { _ -> stacks[from - 1].removeLast() }
    }
    return stacks.map { it.last() }.joinToString("")
}

private fun readStacks(lines: List<String>, rows: Int, cols: Int): MutableList<ArrayDeque<Char>> = mutableListOf<ArrayDeque<Char>>().apply {
    repeat(cols) { add(ArrayDeque()) }
    lines.take(rows).reversed().forEach { it.toList().chunked(4).forEachIndexed { index, chars -> if (chars[1].isUpperCase()) this[index].add(chars[1]) } }
}
