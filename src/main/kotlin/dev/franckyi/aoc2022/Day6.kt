package dev.franckyi.aoc2022

fun main() {
    val message = Utils.read(6)
    println(findMarker(message, 4))
    println(findMarker(message, 14))
}

private fun findMarker(message: String, packetSize: Int): Int = (packetSize..message.length).find { message.substring(it - packetSize, it).toSet().size == packetSize } ?: 0
