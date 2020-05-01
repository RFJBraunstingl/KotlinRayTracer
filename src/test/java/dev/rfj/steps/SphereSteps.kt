package dev.rfj.steps

import dev.rfj.domain.IntersectionCollectionStore
import dev.rfj.domain.IntersectionMap
import dev.rfj.domain.intersection.Intersection
import dev.rfj.domain.Ray
import dev.rfj.domain.SphereMap
import dev.rfj.domain.intersection.IntersectionCollection
import dev.rfj.domain.shapes.ShapeFactory
import dev.rfj.domain.shapes.Sphere
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals

class SphereSteps(
        private val sphereMap : SphereMap,
        private val intersectionMap: IntersectionMap,
        private val intersectionCollectionStore : IntersectionCollectionStore
) {

    /* GIVEN */

    @Given("{name} ← create_unit_sphere")
    fun constructUnitSphere(
            name: String
    ) {
        sphereMap[name] = ShapeFactory.createUnitSphere()
    }

    @Given("^([a-z]+) ← sphere\\(\\)$")
    fun constructUnitSphereFunction(
            name: String
    ) {
        return constructUnitSphere(name)
    }


    /* WHEN */

    @When("{name} ← intersect\\({sphereName}, {rayName})")
    fun calculateIntersection(
            name: String,
            sphere: Sphere,
            ray: Ray
    ) {
        intersectionCollectionStore[name] = IntersectionCollection(sphere.intersections(ray))
    }


    /* THEN */

    @Then("{intersectionCollectionName}.count = {int}")
    fun validateIntersectionCount(
            intersections: IntersectionCollection,
            count: Int
    ) {
        assertEquals(count, intersections.size)
    }

    @Then("{intersectionCollectionName}[{int}].t = {double}")
    fun validateIntersectionAtIndex(
            intersections: IntersectionCollection,
            index: Int,
            expected: Double
    ) {
        expected.equalsWithDelta(intersections[index].t)
    }

    @Then("{intersectionCollectionName}[{int}].object = {sphereName}")
    fun validateIntersectionObject(
            intersections: IntersectionCollection,
            index: Int,
            sphere: Sphere
    ) {
        assertEquals(sphere, intersections[index].obj)
    }
}