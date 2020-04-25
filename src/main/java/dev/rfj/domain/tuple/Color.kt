package dev.rfj.domain.tuple

data class Color(
        val red: Double,
        val green: Double,
        val blue: Double
): Tuple(red, green, blue, 0.0)