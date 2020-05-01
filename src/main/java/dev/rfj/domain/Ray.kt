package dev.rfj.domain

import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Vector

data class Ray(
        val origin: Point,
        val direction: Vector
) {
    fun positionAt(t: Double): Point {
        val distance = direction.multipliedBy(t)
        return origin.plus(distance).asPoint()
    }
}
