package dev.rfj

import dev.rfj.canvas.Canvas
import dev.rfj.domain.tuple.Tuple
import dev.rfj.output.canvasToPPM

fun main() {
    val canvas = Canvas(500, 500)

    canvas.setBackgroundColor(Tuple.color(0.0, 0.0, 1.0))

    canvasToPPM(canvas).dumpToFile()
}