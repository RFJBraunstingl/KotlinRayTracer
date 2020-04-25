package dev.rfj.canvas

import dev.rfj.domain.tuple.Color
import dev.rfj.domain.tuple.Tuple
import dev.rfj.output.PPM
import dev.rfj.output.canvasToPPM

class Canvas(
        val width: Int,
        val height: Int) {

    private val grid = createPixelGrid()

    private fun createPixelGrid(): Array<Array<Color>> {
        var result = arrayOf<Array<Color>>()

        for (i in 0 until height) {
            var row = arrayOf<Color>()

            for (j in 0 until width)
                row += Tuple.color(0.0, 0.0, 0.0)

            result += row
        }

        return result
    }

    fun eachPixel(op: (it: Color) -> Unit) {
        eachRow { row ->
            row.forEach(op)
        }
    }

    fun eachRow(op: (row: Array<Color>) -> Unit) {
        grid.forEach(op)
    }

    fun setPixelAt(x: Int, y: Int, color: Color) {
        grid[y][x] = color
    }

    fun getPixelAt(x: Int, y: Int): Color {
        return grid[y][x]
    }

    fun toPPM(): PPM {
        return canvasToPPM(this)
    }
}