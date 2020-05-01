package dev.rfj.domain.shapes

import dev.rfj.domain.intersection.Intersection
import dev.rfj.domain.Ray
import dev.rfj.domain.tuple.Point
import kotlin.math.sqrt

data class Sphere(
        val radius: Double,
        val position: Point
) {
    fun intersections(ray: Ray): List<Intersection> {
        val sphereToRay = ray.origin.minus(position)

        val a = ray.direction.dotProduct(ray.direction)
        val b = ray.direction.dotProduct(sphereToRay) * 2
        val c = sphereToRay.dotProduct(sphereToRay) - 1

        // if discriminant is negative, the ray misses the sphere
        val discriminant = (b * b) - 4 * a * c
        if (discriminant < 0)
            return emptyList()

        // else - calculate intersections
        val discSqrt = sqrt(discriminant)
        val t1 = (-b - discSqrt) / (2 * a)
        val t2 = (-b + discSqrt) / (2 * a)

        return listOf(
                Intersection(t1, this),
                Intersection(t2, this)
        )
    }
}
