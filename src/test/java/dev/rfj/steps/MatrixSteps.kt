package dev.rfj.steps

import dev.rfj.domain.MatrixMap
import dev.rfj.domain.tuple.Tuple
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

    @Then("{matrixName} * {matrixName} is the following {int}x{int} matrix:")
    fun validateMatrixMultiplication(
            matrix1: Matrix,
            matrix2: Matrix,
            numOfRowsOfProduct: Int,
            numOfColumnsOfProduct: Int,
            product: Matrix
    ) {
        assertEquals(product, matrix1.multipliedBy(matrix2))
    }

    @Then("{matrixName} * {matrixName} = {matrixName}")
    fun validateMatrixMultiplication(
            matrix1: Matrix,
            matrix2: Matrix,
            product: Matrix
    ) {
        assertEquals(product, matrix1.multipliedBy(matrix2))
    }

    @Then("{matrixName} * {tupleName} = {tuple}")
    fun validateMatrixTupleMultiplication(
            matrix: Matrix,
            tuple: Tuple,
            product: Tuple
    ) {
        assertEquals(product, matrix.multipliedBy(tuple))
    }

/*    @Then("{matrixName} * identity_matrix = {matrixName}")
    fun validateIdentityMatrixMultiplication(
            input: Matrix,
            output: Matrix
    ) {
        val identityMatrix = Matrix.identity4x4()
        assertEquals(output, input.multipliedBy(identityMatrix))
    }*/
}