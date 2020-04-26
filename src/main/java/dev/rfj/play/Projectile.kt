package dev.rfj.play

import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Vector

data class Projectile(
        val position: Point,
        val velocity: Vector)