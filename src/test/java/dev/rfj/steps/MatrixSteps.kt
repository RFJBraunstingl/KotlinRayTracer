package dev.rfj.steps

import dev.rfj.domain.MatrixMap
import dev.rfj.domain.tuple.Tuple
import dev.rfj.matrix.Matrix
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import kotlin.math.exp
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue


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

    @Given("{name} ← transpose\\({matrixName})")
    fun givenTranspositionOfMatrix(
            name: String,
            matrix: Matrix
    ) {
        matrixMap[name] = matrix.transpose()
    }

    @Given("{name} ← submatrix\\({matrixName}, {int}, {int})")
    fun assignSubMatrix(
            name: String,
            matrix: Matrix,
            rowToRemove: Int,
            colToRemove: Int
    ) {
        matrixMap[name] = matrix.submatrix(rowToRemove, colToRemove)
    }

    @Given("{name} ← inverse\\({matrixName})")
    fun assignInversion(
            name: String,
            matrix: Matrix
    ) {
        matrixMap[name] = matrix.inverse()
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

    @Then("{matrixName} is the following {int}x{int} matrix:")
    fun validateMatrix(
            actual: Matrix,
            rows: Int,
            cols: Int,
            expected: Matrix
    ) {
        assertEquals(expected, actual)
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

    @Then("transpose\\({matrixName}) is the following matrix:")
    fun validateMatrixTransposition(
            input: Matrix,
            output: Matrix
    ) {
        assertEquals(output, input.transpose())
    }

    @Then("determinant\\({matrixName}) = {double}")
    fun validateDeterminantForMatrix(
            matrix: Matrix,
            determinant: Double
    ) {
        determinant.equalsWithDelta(matrix.determinant())
    }

    @Then("submatrix\\({matrixName}, {int}, {int}) is the following {int}x{int} matrix:")
    fun validateSubmatrix(
            input: Matrix,
            rowToRemove: Int,
            colToRemove: Int,
            expectedRows: Int,
            expectedColumns: Int,
            expected: Matrix
    ) {
        assertEquals(expected, input.submatrix(rowToRemove, colToRemove))
    }

    @Then("minor\\({matrixName}, {int}, {int}) = {double}")
    fun validateMinor(
            matrix: Matrix,
            row: Int,
            col: Int,
            expected: Double
    ) {
        expected.equalsWithDelta(matrix.minor(row, col))
    }

    @Then("cofactor\\({matrixName}, {int}, {int}) = {double}")
    fun validateCofactor(
            matrix: Matrix,
            row: Int,
            col: Int,
            expected: Double
    ) {
        expected.equalsWithDelta(matrix.cofactor(row, col))
    }

    @Then("{matrixName} is invertible")
    fun matrixIsInvertable(matrix: Matrix) {
        assertTrue { matrix.isInversable() }
    }

    @Then("{matrixName} is not invertible")
    fun matrixIsNotInvertable(matrix: Matrix) {
        assertFalse{ matrix.isInversable() }
    }

    @Then("{matrixName}[{int},{int}] = {intDivision}")
    fun valueOfMatrixEqualsIntDivision(
            matrix: Matrix,
            row: Int,
            col: Int,
            expected: Double
    ) {
        expected.equalsWithDelta(matrix.getValueAt(row, col))
    }

    @Then("inverse\\({matrixName}) is the following {int}x{int} matrix:")
    fun assertInverse(
            matrix: Matrix,
            expectedRows: Int,
            expectedColumns: Int,
            expected: Matrix
    ) {
        assertEquals(expected, matrix.inverse())
    }
}