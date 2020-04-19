package dev.rfj.steps

import dev.rfj.domain.Tuple
import io.cucumber.java.en.Then
import kotlin.test.assertEquals

class TupleArithmeticSteps {

    @Then("{tupleName} + {tupleName} = {tuple}")
    fun validateTupleAddition(addend: Tuple, augend: Tuple, result: Tuple) {
        assertEquals(result, addend.plus(augend))
    }
}