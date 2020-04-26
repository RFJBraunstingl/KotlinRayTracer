package dev.rfj.steps

import dev.rfj.domain.MatrixMap
import dev.rfj.matrix.Matrix
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class MatrixSteps(
        private val matrixMap: MatrixMap = MatrixMap()
) {

    @Given("the following {int}x{int} matrix {name}:")
    fun theFollowingMatrix(
            numOfRows: Int,
            numOfColumns: Int,
            name: String,
            matrix: Matrix) {
        matrixMap[name] = matrix
    }

    @Given("the following matrix {name}:")
    fun theFollowingMatrix(
            name: String,
            matrix: Matrix
    ) {
        matrixMap[name] = matrix
    }

    @Then("{matrixName}[{int},{int}] = {double}")
    fun validateMatrixValue(matrix: Matrix, row: Int, col: Int, value: Double) {
        val valueFromMatrix = matrix.getValueAt(row, col)
        value.equalsWithDelta(valueFromMatrix)
    }

    @Then("{matrixName} = {matrixName}")
    fun validateMatrixEquality(
            matrix1: Matrix,
            matrix2: Matrix
    ) {
        assertEquals(matrix1, matrix2)
    }

    @Then("{matrixName} != {matrixName}")
    fun validateMatrixInequality(
            matrix1: Matrix,
            matrix2: Matrix
    ) {
        assertNotEquals(matrix1, matrix2)
    }
}