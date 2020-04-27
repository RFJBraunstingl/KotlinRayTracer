package dev.rfj.steps

import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.store.TupleStore
import dev.rfj.domain.tuple.Point
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.test.assertEquals

class PointSteps(
        private val tupleStore: TupleStore
) {

    @Given("{string} <- point\\({double}, {double}, {double})")
    fun createPointTuple(name: String, x: Double, y: Double, z: Double) {
        val pointTuple = Tuple.point(x, y, z)
        tupleStore.save(name, pointTuple)
    }

    @Then("{tupleName} = {point}")
    fun validatePoint(
            tuple: Tuple,
            point: Point
    ) = assertEquals(point, tuple)
}