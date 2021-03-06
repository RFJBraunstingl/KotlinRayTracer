package dev.rfj.steps

import dev.rfj.domain.store.TupleStore
import dev.rfj.domain.tuple.Tuple
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.test.assertEquals

class ColorSteps(
        private val tupleStore: TupleStore
) {

    @Given("{string} <- color\\({double}, {double}, {double})")
    fun createColorTuple(name: String, red: Double, green: Double, blue: Double) {
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

    @Then("{tupleName} - {tupleName} = {color}")
    fun substractionResultsInColor(tuple1: Tuple, tuple2: Tuple, result: Tuple) {
        val actualResult = tuple1.minus(tuple2)
        assertEquals(result, actualResult)
    }

    @Then("{tupleName} * {tupleName} = {color}")
    fun multiplyColors(color1: Tuple, color2: Tuple, product: Tuple) {
        assertEquals(product, color1.multipliedBy(color2))
    }
}