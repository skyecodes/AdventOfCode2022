package dev.franckyi.aoc2022

fun main() {
    val input = Utils.read(2).lines()
    println(compute(input, listOf("B X", "C Y", "A Z", "A X", "B Y", "C Z", "C X", "A Y", "B Z")))
    println(compute(input, listOf("B X", "C X", "A X", "A Y", "B Y", "C Y", "C Z", "A Z", "B Z")))
}

private fun compute(input: List<String>, scores: List<String>): Int = input.sumOf { scores.indexOf(it) + 1 }
