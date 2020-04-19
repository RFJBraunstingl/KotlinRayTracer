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

    @Then("{string}.x = {double}")
    fun validateXValueOfTupleWithName(name: String, x: Double) {
        val tuple = tupleStore.findByName(name)
        assertEquals(x, tuple.x)
    }

    @Then("{string}.y = {double}")
    fun validateYValueOfTupleWithName(name: String, y: Double) {
        val tuple = tupleStore.findByName(name)
        assertEquals(y, tuple.y)
    }

    @Then("{string}.z = {double}")
    fun validateZValueOfTupleWithName(name: String, z: Double) {
        val tuple = tupleStore.findByName(name)
        assertEquals(z, tuple.z)
    }

    @Then("{string}.w = {double}")
    fun validateWValueOfTupleWithName(name: String, w: Double) {
        val tuple = tupleStore.findByName(name)
        assertEquals(w, tuple.w)
    }

    @Then("{string} is a {string}")
    fun isA(name: String, type: String) {
        val tuple = tupleStore.findByName(name)

        when (type) {
            "point" -> assertTrue { tuple.isPoint() }
            "vector" -> assertTrue { tuple.isVector() }
            else -> throw RuntimeException("I do not know how to verify this")
        }
    }

    @Then("{string} is not a {string}")
    fun isNotA(name: String, type: String) {
        val tuple = tupleStore.findByName(name)

        when (type) {
            "point" -> assertFalse { tuple.isPoint() }
            "vector" -> assertFalse { tuple.isVector() }
            else -> throw RuntimeException("I do not know how to verify this")
        }
    }

    @Then("{string} = tuple\\({double}, {double}, {double}, {double})")
    fun verifyTuple(name: String, x: Double, y: Double, z: Double, w: Double) {
        assertEquals(Tuple.create(x, y, z, w), tupleStore.findByName(name))
    }
}