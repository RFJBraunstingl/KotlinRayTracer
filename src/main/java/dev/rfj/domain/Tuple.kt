package dev.rfj.domain

import kotlin.math.sqrt

data class Tuple(
        val x: Double,
        val y: Double,
        val z: Double,
        val w: Double
) {

    companion object {

        val ZERO_VECTOR = Tuple(0.0, 0.0, 0.0, 0.0)

        fun create(
                x: Double,
                y: Double,
                z: Double,
                w: Double
        ): Tuple = Tuple(x, y, z, w)

        fun point(
                x: Double,
                y: Double,
                z: Double
        ): Tuple = Tuple(x, y, z, 1.0)

        fun vector(
                x: Double,
                y: Double,
                z: Double
        ): Tuple = Tuple(x, y, z, 0.0)

        fun color(
                red: Double,
                green: Double,
                blue: Double
        ): Tuple = Tuple(red, green, blue, -1.0)
    }

    fun isVector(): Boolean = (w == 0.0)

    fun isPoint(): Boolean = !isVector()

    fun plus(add: Tuple) =
            create(
                    x + add.x,
                    y + add.y,
                    z + add.z,
                    w + add.w
            )

    fun minus(sub: Tuple) =
            create(
                    x - sub.x,
                    y - sub.y,
                    z - sub.z,
                    w - sub.w
            )

    operator fun unaryMinus(): Tuple {
        return create(-x, -y, -z, -w)
    }

    fun multipliedBy(scalar: Double): Tuple {
        return create(
                x * scalar,
                y * scalar,
                z * scalar,
                w * scalar
        )
    }

    fun dividedBy(scalar: Double): Tuple {
        return create(
                x / scalar,
                y / scalar,
                z / scalar,
                w / scalar
        )
    }

    fun magnitude(): Double {
        val sumOfCubes = x * x + y * y + z * z + w * w
        return sqrt(sumOfCubes)
    }

    fun normalize(): Tuple {
        val magnitude = magnitude()
        return dividedBy(magnitude)
    }

    fun dotProduct(other: Tuple): Double {
        return x * other.x +
                y * other.y +
                z * other.z +
                w * other.w
    }

    fun crossProduct3d(other: Tuple): Tuple {
        return vector(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        )
    }
}