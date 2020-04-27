package dev.rfj.steps

import dev.rfj.domain.MatrixMap
import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.tuple.Vector
import dev.rfj.matrix.Matrix
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.test.assertEquals


class TransformationSteps(
        private val matrixMap: MatrixMap
) {

    @Given("{name} ← translation\\({double}, {double}, {double})")
    fun givenTranslation(
            name: String,
            translationX: Double,
            translationY: Double,
            translationZ: Double) {
        matrixMap[name] = Matrix.translation(
                translationX,
                translationY,
                translationZ
        )
    }

    @Given("{name} ← scaling\\({double}, {double}, {double})")
    fun givenScaling(
            name: String,
            x: Double,
            y: Double,
            z: Double
    ) {
        matrixMap[name] = Matrix.scaling(
                x,
                y,
                z
        )
    }

    @Then("{matrixName} * {tupleName} = {point}")
    fun validateMatrixPointMultiplication(
            matrix: Matrix,
            tuple: Tuple,
            expected: Point
    ) {
        assertEquals(expected, matrix.multipliedBy(tuple))
    }

    @Then("{matrixName} * {tupleName} = {vector}")
    fun validateMatrixVectorMultiplication(
            matrix: Matrix,
            tuple: Tuple,
            expected: Vector
    ) {
        assertEquals(expected, matrix.multipliedBy(tuple))
    }
}