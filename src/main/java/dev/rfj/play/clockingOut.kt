package dev.rfj.play

import dev.rfj.canvas.Canvas
import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.tuple.Tuple.Companion.point
import dev.rfj.matrix.Matrix.Companion.rotationZ
import dev.rfj.output.plot
import dev.rfj.output.render
import kotlin.math.PI

fun main() {
    val canvas = Canvas(500, 500)
    canvas.setBackgroundColor(Tuple.color(1.0, 1.0, 1.0))

    var pixel = point(0.0, 2.0, 0.0)
    canvas.plot(center(pixel))

    for (i in 1..11) {
        pixel = pixel.transform(rotationZ(PI / 6)).asPoint()
        canvas.plot(center(pixel))
    }

    canvas.render()
}

fun center(pixel: Tuple): Point {
    return pixel.plus(point(2.5, 2.5, 0.0)).asPoint()
}
