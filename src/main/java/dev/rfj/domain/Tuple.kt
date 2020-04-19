package dev.rfj.domain

data class Tuple(
        val x: Double,
        val y: Double,
        val z: Double,
        val w: Double
) {

    companion object {
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
}