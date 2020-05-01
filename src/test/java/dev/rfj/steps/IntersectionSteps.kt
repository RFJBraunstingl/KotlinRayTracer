package dev.rfj.steps

import dev.rfj.domain.intersection.Intersection
import dev.rfj.domain.IntersectionMap
import dev.rfj.domain.intersection.IntersectionCollection
import dev.rfj.domain.shapes.Sphere
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class IntersectionSteps(
        private val intersectionMap: IntersectionMap
) {

    private lateinit var xs: IntersectionCollection
    private lateinit var hit: Optional<Intersection>

    @Given("{name} ← intersection\\({double}, {sphereName})")
    fun constructIntersection(
            name: String,
            t: Double,
            sphere: Sphere
    ) {
        intersectionMap[name] = Intersection(t, sphere)
    }

    @When("xs ← intersections\\({intersectionName}, {intersectionName})")
    fun aggregateIntersections(
            i1: Intersection,
            i2: Intersection
    ) {
        xs = IntersectionCollection(i1, i2)
    }

    @When("{name} ← hit(xs)")
    fun getHit(
            name: String
    ) {
        val hit = xs.hit()
        if (hit.isPresent)
            intersectionMap[name] = hit.get()
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

    @Then("{intersectionName} = {intersectionName}")
    fun validateIntersectionEquality(
            i1: Intersection,
            i2: Intersection
    ) {
        assertEquals(i1, i2)
    }
}