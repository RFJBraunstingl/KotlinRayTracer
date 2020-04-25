package dev.rfj.steps

import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.store.TupleStore
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ColorSteps(
        private val tupleStore: TupleStore
) {

    @Given("{string} <- color\\({double}, {double}, {double})")
    fun createVectorTuple(name: String, red: Double, green: Double, blue: Double) {
        val colorTuple = Tuple.color(red, green, blue)
        tupleStore.save(name, colorTuple)
    }

    @Then("{tupleName}.red = {double}")
    fun validateRedValue(color: Tuple, red: Double) {
        assertEquals(red, color.x)
    }

    @Then("{tupleName}.green = {double}")
    fun validateGreenValue(color: Tuple, green: Double) {
        assertEquals(green, color.y)
    }

    @Then("{tupleName}.blue = {double}")
    fun validateBlueValue(color: Tuple, blue: Double) {
        assertEquals(blue, color.z)
    }

    @Then("{tupleName} + {tupleName} = {color}")
    fun additionOfColors(tuple1: Tuple, tuple2: Tuple, result: Tuple) {
        val actualResult = tuple1.plus(tuple2)
        assertEquals(result, actualResult)
    }
}