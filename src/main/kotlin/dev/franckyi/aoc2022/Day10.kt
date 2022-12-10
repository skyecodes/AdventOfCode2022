package dev.franckyi.aoc2022

fun main() {
    val program = Utils.read(10).lines()
    println(part1(program))
    part2(program)
}

private fun part1(program: List<String>): Int {
    var signal = 0
    var x = 1
    var cycles = 0
    var nextCycle = 20
    program.forEach {
        val line = it.split(" ")
        if (line[0] == "noop") cycles++
        else {
            cycles += 2
            if (cycles >= nextCycle) {
                signal += x * nextCycle
                nextCycle += 40
            }
            x += line[1].toInt()
        }
    }
    return signal
}

private fun part2(program: List<String>) {
    val pixels = Array(6) { CharArray(40) { '.' } }
    var lineIdx = 0
    var x = 1
    var nextAdd: Int? = null
    repeat(240) { cycle ->
        val row = cycle / 40
        val col = cycle % 40
        if (col in x - 1..x + 1) pixels[row][col] = '#'
        nextAdd?.let {
            x += it
            nextAdd = null
        } ?: run {
            val line = program[lineIdx++].split(" ")
            if (line[0] == "addx") nextAdd = line[1].toInt()
        }
    }
    pixels.forEach { println(it.joinToString("")) }
}