package dev.franckyi.aoc2022

fun main() {
    val pairs = Utils.read(4).lines()
        .map { it.split(',')
            .map { it.split('-')
                .map { it.toInt() } }
            .map { (a, b) -> a to b } }
        .map { (a, b) -> a to b }
    println(part1(pairs))
    println(part2(pairs))
}

private fun part1(pairs: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Int = pairs.count { (a, b) -> a.first <= b.first && a.second >= b.second || b.first <= a.first && b.second >= a.second }

private fun part2(pairs: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Int = pairs.count { (a, b) -> a.first <= b.first && a.second >= b.first || b.first <= a.first && b.second >= a.first }
