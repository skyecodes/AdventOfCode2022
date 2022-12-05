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
    instructions.forEach { (0 until it.first).forEach { _ -> stacks[it.third - 1].add(stacks[it.second - 1].removeLast()) } }
    return stacks.map { it.last() }.joinToString("")
}

private fun part2(stacks: MutableList<ArrayDeque<Char>>, instructions: List<Triple<Int, Int, Int>>): String {
    instructions.forEach {
        stacks[it.third - 1].addAll(stacks[it.second - 1].takeLast(it.first))
        repeat(it.first) { _ -> stacks[it.second - 1].removeLast() }
    }
    return stacks.map { it.last() }.joinToString("")
}

private fun readStacks(lines: List<String>, rows: Int, cols: Int): MutableList<ArrayDeque<Char>> {
    val stacks: MutableList<ArrayDeque<Char>> = mutableListOf()
    (0 until cols).forEach { _ -> stacks.add(ArrayDeque()) }
    lines.take(rows).reversed().forEach { it.toList().chunked(4).forEachIndexed { index, chars -> if (chars[1].isUpperCase()) stacks[index].add(chars[1]) } }
    return stacks
}