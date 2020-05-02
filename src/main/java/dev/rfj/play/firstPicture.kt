package dev.rfj.play

import dev.rfj.canvas.Canvas
import dev.rfj.domain.Ray
import dev.rfj.domain.shapes.ShapeFactory
import dev.rfj.domain.shapes.Sphere
import dev.rfj.domain.tuple.Tuple
import dev.rfj.output.render

const val SIZE_IN_PX = 500

const val MIN_COORD = -2.5
const val MAX_COORD = 2.5
val COORD_DELTA = MAX_COORD - MIN_COORD

fun main() {
    val canvas = Canvas(SIZE_IN_PX, SIZE_IN_PX)
    val colorOfHits = Tuple.color(1.0, 0.0, 0.0)

    val target = ShapeFactory.createUnitSphere() // -1.0..1.0 on x and y axis

    val zCoord = -1.0
    val direction = Tuple.vector(0.0, 0.0, 1.0)

    for (y in 0..SIZE_IN_PX) {

        val yCoord = pxToCoord(y)

        for (x in 0..SIZE_IN_PX) {
            val xCoord = pxToCoord(x)
            val origin = Tuple.point(xCoord, yCoord, zCoord)

            val ray = Ray(origin, direction)

            if (rayHitsSphere(ray, target))
                canvas.setPixelAt(x, y, colorOfHits)
        }
    }

    canvas.render()
}

fun rayHitsSphere(ray: Ray, target: Sphere): Boolean = target.intersections(ray).isNotEmpty()

fun pxToCoord(px: Int): Double = px.toDouble() / SIZE_IN_PX * COORD_DELTA + MIN_COORD
