package dev.rfj.steps

import dev.rfj.domain.Tuple
import dev.rfj.domain.store.TupleStore
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TupleSteps(
        private val tupleStore: TupleStore
) {

    @Given("{string} <- tuple\\({double}, {double}, {double}, {double})")
    fun createTuple(name: String, x: Double, y: Double, z: Double, w: Double) {
        tupleStore.save(name, Tuple.create(x, y, z, w))
    }

    @Then("{tupleName}.x = {double}")
    fun validateXValueOfTupleWithName(tuple: Tuple, x: Double) {
        assertEquals(x, tuple.x)
    }

    @Then("{tupleName}.y = {double}")
    fun validateYValueOfTupleWithName(tuple: Tuple, y: Double) {
        assertEquals(y, tuple.y)
    }

    @Then("{tupleName}.z = {double}")
    fun validateZValueOfTupleWithName(tuple: Tuple, z: Double) {
        assertEquals(z, tuple.z)
    }

    @Then("{tupleName}.w = {double}")
    fun validateWValueOfTupleWithName(tuple: Tuple, w: Double) {
        assertEquals(w, tuple.w)
    }

    @Then("{tupleName} is a {string}")
    fun isA(tuple: Tuple, type: String) {
        when (type) {
            "point" -> assertTrue { tuple.isPoint() }
            "vector" -> assertTrue { tuple.isVector() }
            else -> throw RuntimeException("I do not know how to verify this")
        }
    }

    @Then("{tupleName} is not a {string}")
    fun isNotA(tuple: Tuple, type: String) {
        when (type) {
            "point" -> assertFalse { tuple.isPoint() }
            "vector" -> assertFalse { tuple.isVector() }
            else -> throw RuntimeException("I do not know how to verify this")
        }
    }

    @Then("{tupleName} = {tuple}")
    fun verifyTuple(actual: Tuple, expected: Tuple) {
        assertEquals(expected, actual)
    }

    @Then("-{tupleName} = {tuple}")
    fun verifyNegatedTuple(toNegate: Tuple, expected: Tuple) {

    }
}