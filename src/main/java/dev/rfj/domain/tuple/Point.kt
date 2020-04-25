package dev.rfj.domain.tuple

class Point(
        override val x: Double,
        override val y: Double,
        override val z: Double
): Tuple(x, y, z, 1.0)