package dev.rfj.steps

import dev.rfj.domain.Intersection
import dev.rfj.domain.IntersectionMap
import dev.rfj.domain.shapes.Sphere
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class IntersectionSteps(
        private val intersectionMap: IntersectionMap
) {

    @Given("{name} ← intersection\\({double}, {sphereName})")
    fun constructIntersection(
            name: String,
            t: Double,
            sphere: Sphere
    ) {
        intersectionMap[name] = Intersection(t, sphere)
    }

    @Then("{intersectionName}.t = {double}")
    fun validateT(
            intersection: Intersection,
            expectedT: Double
    ) {
        assertTrue { expectedT.equalsWithDelta(intersection.t) }
    }

    @Then("{intersectionName}.object = {sphereName}")
    fun validateObject(
            intersection: Intersection,
            obj: Sphere
    ) {
        assertEquals(obj, intersection.obj)
    }
}