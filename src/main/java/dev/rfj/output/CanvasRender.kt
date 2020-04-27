package dev.rfj.output

import dev.rfj.canvas.Canvas
import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple

fun Canvas.render() {
    canvasToPPM(this).dumpToFile()
}

fun Canvas.plot(point: Point) {
    this.plot(point, 100.0)
}

fun Canvas.plot(point: Point, scale: Double) {
    val color = Tuple.color(0.0, 0.0, 1.0)
    val x = (point.x * scale).toInt()
    var y = (point.y * scale).toInt()

    if (x < 1 ||
            x > this.width ||
            y < 1 ||
            y > this.height)
        return

    // invert y
    y = this.height - y

    // - 1 taking care of zero index
    this.setPixelAt(x - 1, y - 1, color)
}