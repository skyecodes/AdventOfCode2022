package dev.franckyi.aoc2022

fun main() {
    val values = Utils.read(1).split("\n\n").map { it.split("\n").sumOf { it.toInt() } }
    println(part1(values))
    println(part2(values))
}

private fun part1(values: List<Int>): Int = values.max()

private fun part2(values: List<Int>): Int = values.sortedDescending().take(3).sum()
