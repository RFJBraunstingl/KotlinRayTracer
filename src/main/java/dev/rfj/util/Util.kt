package dev.rfj.util

import kotlin.math.abs

fun Double.equalsWithDelta(other: Double, delta: Double) = abs(this - other) < delta

fun Double.equalsWithDelta(other: Double) = equalsWithDelta(other, 0.00001)