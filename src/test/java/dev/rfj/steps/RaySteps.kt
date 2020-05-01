package dev.rfj.steps

import dev.rfj.domain.Ray
import dev.rfj.domain.RayMap
import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.tuple.Vector
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.test.assertEquals

class RaySteps(
        private val rayMap: RayMap
) {

    /* GIVEN */

    @Given("{name} ← ray\\({tupleName}, {tupleName})")
    fun constructRay(
            name: String,
            origin: Tuple,
            direction: Tuple
    ) {
        rayMap[name] = Ray(origin.asPoint(), direction.asVector())
    }

    @Given("{name} ← ray\\({point}, {vector})")
    fun constructRayWithTuples(
            name: String,
            origin: Point,
            direction: Vector
    ) {
        rayMap[name] = Ray(origin, direction)
    }


    /* THEN */

    @Then("{rayName}.origin = {tupleName}")
    fun validateOrigin(
            ray: Ray,
            origin: Tuple
    ) {
        assertEquals(origin, ray.origin)
    }

    @Then("{rayName}.direction = {tupleName}")
    fun validateDirection(
            ray: Ray,
            direction: Tuple
    ) {
        assertEquals(direction, ray.direction)
    }

    @Then("position\\({rayName}, {double}) = {point}")
    fun validatePosition(
            ray: Ray,
            t: Double,
            expected: Point
    ) {
        assertEquals(expected, ray.positionAt(t))
    }
}