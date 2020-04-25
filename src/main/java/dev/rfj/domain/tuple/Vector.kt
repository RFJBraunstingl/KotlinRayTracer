package dev.rfj.domain.tuple

class Vector(
        override val x: Double,
        override val y: Double,
        override val z: Double
): Tuple(x, y, z, 0.0)