package dev.franckyi.aoc2022

fun main() {
    var monkeys = readMonkeys()
    println(run(monkeys, 20, 3))
    monkeys = readMonkeys()
    println(run(monkeys, 10000, 1))
}

private fun readMonkeys() = Utils.read(11).lines().chunked(7).map { lines ->
    val items = lines[1].substring(18).split(", ").map { it.toLong() }.toMutableList()
    val operation: (Long, Long) -> Long = lines[2].substring(23).split(" ").let { (op, other) ->
        val otherVal = if (other == "old") null else other.toLong()
        val oper: (Long, Long) -> Long = if (op == "+") Long::plus else Long::times
        { old, modulus -> oper(old, otherVal ?: old) % modulus }
    }
    val testVal = lines[3].substring(21).toLong()
    val ifTrue = lines[4].substring(29).toInt()
    val ifFalse = lines[5].substring(30).toInt()
    Monkey(items, operation, testVal, ifTrue, ifFalse)
}

private fun run(monkeys: List<Monkey>, amount: Int, divide: Long): Long {
    val modulus = monkeys.fold(1L) { acc, monkey -> acc * monkey.testVal }
    repeat(amount) {
        monkeys.forEach { monkey ->
            monkey.items.forEach {
                monkey.inspected++
                val newVal = monkey.operation(it, modulus) / divide
                val dest = if (newVal % monkey.testVal == 0L) monkey.ifTrue else monkey.ifFalse
                monkeys[dest].items.add(newVal)
            }
            monkey.items.clear()
        }
    }
    return monkeys.map { it.inspected }.sortedDescending().take(2).reduce(Long::times)
}

private data class Monkey(val items: MutableList<Long>, val operation: (Long, Long) -> Long,
                          val testVal: Long, val ifTrue: Int, val ifFalse: Int, var inspected: Long = 0)
