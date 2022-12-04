package dev.franckyi.aoc2022

fun main() {
    val lines = Utils.read(3).lines()
    println(part1(lines))
    println(part2(lines))
}

private fun part1(lines: List<String>) = lines.map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
        .map { (a, b) -> a.find { it in b }!!.code }
        .sumOf { getPriority(it) }

private fun part2(lines: List<String>) = lines.chunked(3)
        .map { (a, b, c) -> a.find { it in b && it in c }!!.code }
        .sumOf { getPriority(it) }

private fun getPriority(char: Int) = if (char > 90) char % 96 else char % 64 + 26