package dev.rfj.steps

import dev.rfj.domain.Tuple
import io.cucumber.java.en.Then

class TupleArithmeticSteps {

    @Then("{tupleName} + {tupleName} = {tuple}")
    fun validateTupleAddition(addend: Tuple, augend: Tuple, result: Tuple) {
        println("Im here")
    }
}