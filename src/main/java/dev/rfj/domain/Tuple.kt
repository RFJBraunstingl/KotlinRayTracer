package dev.rfj.domain

data class Tuple(
        val x: Double,
        val y: Double,
        val z: Double,
        val w: Double
) {

    fun isVector(): Boolean = (w == 0.0)

    fun isPoint(): Boolean = !isVector()
}