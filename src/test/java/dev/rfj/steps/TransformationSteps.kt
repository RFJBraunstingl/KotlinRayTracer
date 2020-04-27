package dev.rfj.steps

import dev.rfj.domain.MatrixMap
import dev.rfj.domain.store.TupleStore
import dev.rfj.domain.tuple.Point
import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.tuple.Vector
import dev.rfj.matrix.Matrix
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import kotlin.test.assertEquals


class TransformationSteps(
        private val matrixMap: MatrixMap,
        private val tupleStore: TupleStore
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

    @Given("{name} ← rotation_x\\({double})")
    fun givenRotationX(
            name: String,
            rotationX: Double
    ) {
        matrixMap[name] = Matrix.rotationX(rotationX)
    }

    @Given("{name} ← rotation_y\\({double})")
    fun givenRotationY(
            name: String,
            rotationY: Double
    ) {
        matrixMap[name] = Matrix.rotationY(rotationY)
    }

    @Given("{name} ← rotation_z\\({double})")
    fun givenRotationZ(
            name: String,
            rotationZ: Double
    ) {
        matrixMap[name] = Matrix.rotationZ(rotationZ)
    }

    @Given("{name} ← shearing\\({double}, {double}, {double}, {double}, {double}, {double})")
    fun givenShearing(
            name: String,
            xy: Double,
            xz: Double,
            yx: Double,
            yz: Double,
            zx: Double,
            zy: Double
    ) {
        matrixMap[name] = Matrix.shearing(
                xy, xz, yx, yz, zx, zy
        )
    }

    @When("{name} ← {matrixName} * {tupleName}")
    fun multiplyMatrixByTuple(
            name: String,
            matrix: Matrix,
            tuple: Tuple
    ) {
        val result = matrix.multipliedBy(tuple)
        tupleStore.save(name, result)
    }

    @When("{name} ← {matrixName} * {matrixName} * {matrixName}")
    fun chainMatrices(
            name: String,
            matrix1: Matrix,
            matrix2: Matrix,
            matrix3: Matrix
    ) {
        matrixMap[name] = matrix1.multipliedBy(matrix2).multipliedBy(matrix3)
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