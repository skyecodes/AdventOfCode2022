package dev.franckyi.aoc2022

import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val path = Utils.read(9).lines()
        .map { it.split(' ') }
        .map { (direction, distance) -> Move(direction, distance.toInt()) }
    println(part1(path))
    println(part2(path))
}

private fun part1(path: List<Move>): Int {
    val tailPos = mutableSetOf<Pos>()
    val head = Pos()
    val tail = Pos()
    path.forEach { (direction, distance) ->
        repeat(distance) {
            head.move(direction)
            tail.follow(head)
            tailPos.add(tail.copy())
        }
    }
    return tailPos.size
}

private fun part2(path: List<Move>): Int {
    val tailPos = mutableSetOf<Pos>()
    val knots = buildList { repeat(10) { add(Pos()) } }
    path.forEach { (direction, distance) ->
        repeat(distance) {
            knots.forEachIndexed { index, pos ->
                if (index == 0) pos.move(direction)
                else {
                    pos.follow(knots[index - 1])
                    if (index == knots.size - 1) tailPos.add(pos.copy())
                }
            }
        }
    }
    return tailPos.size
}

private data class Move(val direction: String, val distance: Int)

private data class Pos(var x: Int = 0, var y: Int = 0) {
    fun move(direction: String) = when (direction) {
        "U" -> y++
        "D" -> y--
        "L" -> x--
        else -> x++
    }

    fun follow(head: Pos) {
        val dx = head.x - x
        val dy = head.y - y
        when {
            abs(dx) < 2 && abs(dy) < 2 -> return
            dx == 0 -> y += dy.sign
            dy == 0 -> x += dx.sign
            else -> {
                x += dx.sign
                y += dy.sign
            }
        }
    }
}