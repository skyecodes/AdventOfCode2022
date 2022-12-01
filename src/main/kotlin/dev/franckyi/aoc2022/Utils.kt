package dev.franckyi.aoc2022

object Utils {
    fun read(day: Int): String = Utils::class.java.getResource("/input/Day$day.txt")!!.readText()
}