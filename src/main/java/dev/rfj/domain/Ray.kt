package dev.rfj.domain

import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Vector
import dev.rfj.matrix.Matrix

data class Ray(
        val origin: Point,
        val direction: Vector
) {
    fun positionAt(t: Double): Point {
        val distance = direction.multipliedBy(t)
        return origin.plus(distance).asPoint()
    }

    fun transform(transformation: Matrix): Ray {
        return Ray(
                origin = transformation.multipliedBy(origin).asPoint(),
                direction = transformation.multipliedBy(direction).asVector()
        )
    }
}
