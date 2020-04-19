package dev.rfj.domain

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

        fun createPoint(
                x: Double,
                y: Double,
                z: Double
        ): Tuple = Tuple(x, y, z, 1.0)

        fun createVector(
                x: Double,
                y: Double,
                z: Double
        ): Tuple = Tuple(x, y, z, 0.0)
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
}