package dev.rfj.steps

import dev.rfj.domain.Tuple
import io.cucumber.java.en.Then
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TupleArithmeticSteps {

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
}