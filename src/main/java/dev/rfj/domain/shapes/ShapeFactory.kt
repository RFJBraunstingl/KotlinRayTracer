package dev.rfj.domain.shapes

import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple
import dev.rfj.matrix.Matrix

class ShapeFactory {

    companion object {

        fun createUnitSphere(): Sphere {
            return createSphereWithRadiusAt(
                    1.0,
                    Tuple.point(0.0, 0.0, 0.0)
            )
        }

        private fun createSphereWithRadiusAt(radius: Double, position: Point): Sphere {
            return Sphere(
                    radius,
                    position,
                    Matrix.identity4x4()
            )
        }
    }
}