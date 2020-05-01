package dev.rfj.steps

import dev.rfj.domain.Intersection
import dev.rfj.domain.Ray
import dev.rfj.domain.SphereMap
import dev.rfj.domain.shapes.ShapeFactory
import dev.rfj.domain.shapes.Sphere
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals

class SphereSteps(
        private val sphereMap : SphereMap
) {

    private var intersections = listOf<Intersection>()


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

    @When("xs ← intersect\\({sphereName}, {rayName})")
    fun calculateIntersection(
            sphere: Sphere,
            ray: Ray
    ) {
        intersections = sphere.intersections(ray)
    }


    /* THEN */

    @Then("xs.count = {int}")
    fun validateIntersectionCount(
            count: Int
    ) {
        assertEquals(count, intersections.size)
    }

    @Then("xs[{int}].t = {double}")
    fun validateIntersectionAtIndex(
            index: Int,
            expected: Double
    ) {
        expected.equalsWithDelta(intersections[index].t)
    }

    @Then("xs[{int}].object = {sphereName}")
    fun validateIntersectionObject(
            index: Int,
            sphere: Sphere
    ) {
        assertEquals(sphere, intersections[index].obj)
    }
}