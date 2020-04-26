package dev.rfj.domain.tuple

import dev.rfj.util.equalsWithDelta
import kotlin.math.sqrt

open class Tuple(
        open val x: Double,
        open val y: Double,
        open val z: Double,
        open val w: Double
) {

    companion object {

        val ZERO_VECTOR = vector(0.0, 0.0, 0.0)

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
        ): Point = Point(x, y, z)

        fun vector(
                x: Double,
                y: Double,
                z: Double
        ): Vector = Vector(x, y, z)

        fun color(
                red: Double,
                green: Double,
                blue: Double
        ): Color = Color(red, green, blue)
    }

    fun isVector(): Boolean = (w == 0.0)

    fun isPoint(): Boolean = (w == 1.0)

    fun isColor(): Boolean = this is Color

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

    fun multipliedBy(other: Tuple): Tuple {
        return create(
                x * other.x,
                y * other.y,
                z * other.z,
                w * other.w
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

    fun asPoint(): Point {
        return point(
                x,
                y,
                z
        )
    }

    fun asVector(): Vector {
        return vector(
                x,
                y,
                z
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other !is Tuple)
            return false

        if (!x.equalsWithDelta(other.x)) return false
        if (!y.equalsWithDelta(other.y)) return false
        if (!z.equalsWithDelta(other.z)) return false
        if (!w.equalsWithDelta(other.w)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        result = 31 * result + w.hashCode()
        return result
    }

    override fun toString(): String {
        return "Tuple(x=$x, y=$y, z=$z, w=$w)"
    }
}