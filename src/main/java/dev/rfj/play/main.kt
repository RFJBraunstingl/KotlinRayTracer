package dev.rfj.play

import dev.rfj.canvas.Canvas
import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple
import dev.rfj.output.canvasToPPM
import dev.rfj.output.dumpToFile
import dev.rfj.output.plot
import dev.rfj.output.render

fun main() {
    val canvas = Canvas(1000, 500)
    canvas.setBackgroundColor(Tuple.color(0.0, 0.0, 0.0))

    runProjectileSimulation(canvas)

    canvas.render()
}

private fun runProjectileSimulation(canvas: Canvas) {
    /*
     * should be between
     * x => 0 .. 12
     * y => 0 .. 5
     * => canvas with
     * width = 1000
     * height = 500
     */
    val initialPosition = Tuple.point(0.0, 1.0, 0.0)
    val initialVelocity = Tuple.vector(0.5, 0.7, 0.0).normalize().asVector()
    var projectile = Projectile(initialPosition, initialVelocity)

    val gravity = Tuple.vector(0.0, -0.1, 0.0)
    val wind = Tuple.vector(0.01, 0.0, 0.0)
    val env = Environment(gravity, wind)

    do {
        println(projectile)
        plotPositionOnCanvas(projectile.position, canvas)
        projectile = tick(env, projectile)
    } while (projectile.position.y > 0)
}

fun plotPositionOnCanvas(position: Point, canvas: Canvas) {
    canvas.plot(position)
}
