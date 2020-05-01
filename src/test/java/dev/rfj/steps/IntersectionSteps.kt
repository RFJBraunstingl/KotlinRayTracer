package dev.rfj.steps

import dev.rfj.domain.IntersectionCollectionStore
import dev.rfj.domain.IntersectionMap
import dev.rfj.domain.intersection.Intersection
import dev.rfj.domain.intersection.IntersectionCollection
import dev.rfj.domain.shapes.Sphere
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IntersectionSteps(
        private val intersectionMap: IntersectionMap,
        private val intersectionCollectionStore: IntersectionCollectionStore
) {

    @Given("{name} ← intersection\\({double}, {sphereName})")
    fun constructIntersection(
            name: String,
            t: Double,
            sphere: Sphere
    ) {
        intersectionMap[name] = Intersection(t, sphere)
    }

    @When("{name} ← intersections\\({intersectionName}, {intersectionName})")
    fun aggregateIntersections(
            name: String,
            i1: Intersection,
            i2: Intersection
    ) {
        intersectionCollectionStore[name] = IntersectionCollection(i1, i2)
    }

    @When("{name} ← intersections\\({intersectionName}, {intersectionName}, {intersectionName}, {intersectionName})")
    fun aggregateIntersections(
            name: String,
            i1: Intersection,
            i2: Intersection,
            i3: Intersection,
            i4: Intersection
    ) {
        intersectionCollectionStore[name] = IntersectionCollection(i1, i2, i3, i4)
    }

    @When("{name} ← hit\\({intersectionCollectionName})")
    fun getHit(
            name: String,
            xs: IntersectionCollection
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

    @Then("{name} is nothing")
    fun validateHitDoesNotExist(
            name: String
    ) {
        assertFalse { intersectionMap.containsKey(name) }
    }
}