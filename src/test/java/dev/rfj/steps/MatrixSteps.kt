package dev.rfj.steps

import dev.rfj.domain.MatrixMap
import dev.rfj.matrix.Matrix4x4
import dev.rfj.util.equalsWithDelta
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then


class MatrixSteps(
        private val matrixMap: MatrixMap = MatrixMap()
) {

    @Given("the following 4x4 matrix {name}:")
    fun theFollowing4x4MatrixM(name: String, matrix4x4: Matrix4x4) {
        matrixMap[name] = matrix4x4
    }

    @Then("{matrixName}[{int},{int}] = {double}")
    fun validateMatrixValue(matrix4x4: Matrix4x4, row: Int, col: Int, value: Double) {
        val valueFromMatrix = matrix4x4.getValueAt(row, col)
        value.equalsWithDelta(valueFromMatrix)
    }
}