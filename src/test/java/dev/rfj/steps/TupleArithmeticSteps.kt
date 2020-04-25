package dev.rfj.steps

import dev.rfj.domain.Tuple
import dev.rfj.domain.store.TupleStore
import io.cucumber.java.en.Then
import org.junit.Assert
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TupleArithmeticSteps(
        private val tupleStore: TupleStore
) {

    @Then("{tupleName} + {tupleName} = {tuple}")
    fun validateTupleAddition(addend: Tuple, augend: Tuple, result: Tuple) {
        assertEquals(result, addend.plus(augend))
    }

    @Then("{tupleName} - {tupleName} = {vector}")
    fun substractionResultsInVector(tuple1: Tuple, tuple2: Tuple, result: Tuple) {
        val actualResult = tuple1.minus(tuple2)
        assertTrue { actualResult.isVector() }
        assertEquals(result, actualResult)
    }

    @Then("{tupleName} - {tupleName} = {point}")
    fun substractionResultsInPoint(tuple1: Tuple, tuple2: Tuple, result: Tuple) {
        val actualResult = tuple1.minus(tuple2)
        assertTrue { actualResult.isPoint() }
        assertEquals(result, actualResult)
    }

    @Then("{tupleName} * {double} = {tuple}")
    fun verifyScalarMultiplication(tuple: Tuple, scalar: Double, expected: Tuple) {
        assertEquals(expected, tuple.multipliedBy(scalar));
    }

    @Then("^([a-zA-Z0-9]+) \\/ ([0-9]+\\.[0-9]+) = tuple\\((-?[0-9]+\\.[0-9]+), (-?[0-9]+\\.[0-9]+), (-?[0-9]+\\.[0-9]+), (-?[0-9]+\\.[0-9]+)\\)$")
    fun aTuple(tupleName: String, scalar: Double, x: String, y: String, z: String, w: String) {
        val actual = tupleStore.findByName(tupleName)
        val expected = Tuple.create(
                x.toDouble(),
                y.toDouble(),
                z.toDouble(),
                w.toDouble()
        )

        assertEquals(expected, actual.dividedBy(scalar))
    }

    @Then("magnitude\\({tupleName}) = {double}")
    fun verifyMagnitudeCalculation(tuple: Tuple, magnitude: Double) {
        val delta = 0.00001
        Assert.assertEquals(magnitude, tuple.magnitude(), delta)
    }

    @Then("normalize\\({tupleName}) = {vector}")
    fun verifyNormal(vector: Tuple, normal: Tuple) {
        assertEquals(normal, vector.normalize())
    }
}