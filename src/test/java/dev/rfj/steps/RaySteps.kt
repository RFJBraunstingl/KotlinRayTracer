package dev.rfj.steps

import dev.rfj.domain.Ray
import dev.rfj.domain.RayMap
import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.tuple.Vector
import dev.rfj.matrix.Matrix
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals

class RaySteps(
        private val rayMap: RayMap
) {

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

    @When("{name} ← transform\\({rayName}, {matrixName})")
    fun transformRay(
            nameOfTransformedRay: String,
            input: Ray,
            transformation: Matrix
    ) {
        rayMap[nameOfTransformedRay] = input.transform(transformation)
    }

    @Then("{rayName}.origin = {point}")
    fun validatePoint(ray: Ray, expected: Point) = validateOrigin(ray, expected)

    @Then("{rayName}.origin = {tupleName}")
    fun validateOrigin(
            ray: Ray,
            origin: Tuple
    ) {
        assertEquals(origin, ray.origin)
    }

    @Then("{rayName}.direction = {vector}")
    fun validateDirectionVector(ray: Ray, direction: Vector) = validateDirection(ray, direction)

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