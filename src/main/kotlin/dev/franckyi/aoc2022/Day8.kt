package dev.franckyi.aoc2022

fun main() {
    val rows = Utils.read(8).lines().map { it.toCharArray().map { it.digitToInt() } }
    val cols = rows.mapIndexed { y, row -> List(row.size) { x -> rows[x][y] } }
    println(part1(rows, cols))
    println(part2(rows, cols))
}

private fun part1(rows: List<List<Int>>, cols: List<List<Int>>) = rows.flatMapIndexed { y, row -> row.filterIndexed { x, value ->
    rows[y].take(x).all { it < value } ||
            rows[y].drop(x + 1).all { it < value } ||
            cols[x].take(y).all { it < value } ||
            cols[x].drop(y + 1).all { it < value }
} }.count()

private fun part2(rows: List<List<Int>>, cols: List<List<Int>>) = rows.flatMapIndexed { y, row -> row.mapIndexed { x, value ->
    rows[y].take(x).reversed().run { (takeWhile { it < value }.count() + 1).coerceAtMost(size) } *
            rows[y].drop(x + 1).run { (takeWhile { it < value }.count() + 1).coerceAtMost(size) } *
            cols[x].take(y).reversed().run { (takeWhile { it < value }.count() + 1).coerceAtMost(size) } *
            cols[x].drop(y + 1).run { (takeWhile { it < value }.count() + 1).coerceAtMost(size) }
} }.max()
