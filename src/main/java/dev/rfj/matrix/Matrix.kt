package dev.rfj.matrix

import dev.rfj.domain.tuple.Tuple
import dev.rfj.util.equalsWithDelta
import javax.naming.OperationNotSupportedException
import kotlin.math.cos
import kotlin.math.sin

class Matrix(
        val numOfRows: Int,
        val numOfColumns: Int
) {

    private val values = createValueMatrix()

    companion object {

        fun identity4x4(): Matrix {
            /*
            | 1 | 0 | 0 | 0 |
            | 0 | 1 | 0 | 0 |
            | 0 | 0 | 1 | 0 |
            | 0 | 0 | 0 | 1 |
            */
            val identity = Matrix(4, 4)

            identity.setValueAt(0, 0, 1.0)
            identity.setValueAt(1, 1, 1.0)
            identity.setValueAt(2, 2, 1.0)
            identity.setValueAt(3, 3, 1.0)

            return identity
        }

        fun translation(x: Double, y: Double, z: Double): Matrix {
            val result = identity4x4()

            result.setValueAt(0, 3, x)
            result.setValueAt(1, 3, y)
            result.setValueAt(2, 3, z)

            return result
        }

        fun scaling(x: Double, y: Double, z: Double): Matrix {
            val result = identity4x4()

            result.setValueAt(0, 0, x)
            result.setValueAt(1, 1, y)
            result.setValueAt(2, 2, z)

            return result
        }

        fun rotationX(rotationInRad: Double): Matrix {
            val result = identity4x4()

            val sin = sin(rotationInRad)
            val cos = cos(rotationInRad)

            /*
             * [ 1   0    0   0 ]
             * [ 0  cos -sin  0 ]
             * [ 0  sin  cos  0 ]
             * [ 0   0    0   1 ]
             */

            result.setValueAt(1, 1, cos)
            result.setValueAt(1, 2, -sin)
            result.setValueAt(2, 1, sin)
            result.setValueAt(2, 2, cos)

            return result
        }

        fun rotationY(rotationInRad: Double): Matrix {
            val result = identity4x4()

            val sin = sin(rotationInRad)
            val cos = cos(rotationInRad)

            /*
             * [ cos  0  sin 0 ]
             * [  0   1   0  0 ]
             * [ -sin 0  cos 0 ]
             * [  0   0   0  1 ]
             */
            result.setValueAt(0, 0, cos)
            result.setValueAt(0, 2, sin)
            result.setValueAt(2, 0, -sin)
            result.setValueAt(2, 2, cos)

            return result
        }

        fun rotationZ(rotationInRad: Double): Matrix {
            val result = identity4x4()

            val sin = sin(rotationInRad)
            val cos = cos(rotationInRad)

            /*
             * [ cos -sin  0  0 ]
             * [ sin  cos  0  0 ]
             * [  0    0   1  0 ]
             * [  0    0   0  1 ]
             */
            result.setValueAt(0, 0, cos)
            result.setValueAt(0, 1, -sin)
            result.setValueAt(1, 0, sin)
            result.setValueAt(1, 1, cos)

            return result
        }
    }

    private fun createValueMatrix(): MutableList<MutableList<Double>> {
        val rows = mutableListOf<MutableList<Double>>()

        for (i in 0 until numOfRows) {
            val row = mutableListOf<Double>()

            for (j in 0 until numOfColumns)
                row.add(0.0)

            rows.add(row)
        }

        return rows
    }

    fun getValueAt(row: Int, col: Int): Double {
        return values[row][col]
    }

    fun setValueAt(row: Int, col: Int, value: Double) {
        values[row][col] = value
    }

    override fun toString(): String {
        return "Matrix(values=$values)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (numOfRows != other.numOfRows) return false
        if (numOfColumns != other.numOfColumns) return false

        for (row in 0 until numOfRows) {
            for (col in 0 until numOfColumns) {
                if (!getValueAt(row, col).equalsWithDelta(other.getValueAt(row, col)))
                    return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        var result = numOfRows
        result = 31 * result + numOfColumns
        result = 31 * result + values.hashCode()
        return result
    }

    fun multipliedBy(other: Matrix): Matrix {
        val result = Matrix(numOfRows, numOfColumns)

        for (row in 0 until numOfRows) {
            for (col in 0 until numOfColumns) {
                val value =
                        getValueAt(row, 0) * other.getValueAt(0, col) +
                                getValueAt(row, 1) * other.getValueAt(1, col) +
                                getValueAt(row, 2) * other.getValueAt(2, col) +
                                getValueAt(row, 3) * other.getValueAt(3, col)

                result.setValueAt(row, col, value)
            }
        }

        return result
    }

    fun multipliedBy(other: Tuple): Tuple {
        assert(numOfRows == 4) { "tuple multiplication is only supported for 4d matrices" }

        val newX =
                values[0][0] * other.x +
                        values[0][1] * other.y +
                        values[0][2] * other.z +
                        values[0][3] * other.w

        val newY =
                values[1][0] * other.x +
                        values[1][1] * other.y +
                        values[1][2] * other.z +
                        values[1][3] * other.w

        val newZ =
                values[2][0] * other.x +
                        values[2][1] * other.y +
                        values[2][2] * other.z +
                        values[2][3] * other.w

        val newW =
                values[3][0] * other.x +
                        values[3][1] * other.y +
                        values[3][2] * other.z +
                        values[3][3] * other.w

        return Tuple.create(
                newX,
                newY,
                newZ,
                newW
        )
    }

    fun transpose(): Matrix {
        val result = Matrix(numOfColumns, numOfRows)

        for (row in 0 until numOfRows) {
            for (col in 0 until numOfColumns) {
                val value = getValueAt(row, col)
                result.setValueAt(col, row, value)
            }
        }

        return result
    }

    fun determinant(): Double {
        if (is2x2Matrix())
            return values[0][0] * values[1][1] - values[0][1] * values[1][0]

        if (isSymmetricMatrix()) {
            // calculate the determinant using hte first row
            var determinant = 0.0
            for (col in 0 until numOfColumns) {
                determinant += values[0][col] * cofactor(0, col)
            }
            return determinant
        }

        throw UnsupportedOperationException("I do not know how to do that")
    }

    private fun isSymmetricMatrix(): Boolean = numOfRows == numOfColumns

    fun is2x2Matrix(): Boolean =
            numOfRows == 2 && numOfColumns == 2

    fun submatrix(rowToDrop: Int, colToDrop: Int): Matrix {
        val result = Matrix(
                numOfRows - 1,
                numOfColumns - 1
        )

        var newRow = 0
        var newCol = 0

        for (row in 0 until numOfRows) {
            if (row == rowToDrop)
                continue

            newCol = 0
            for (col in 0 until numOfColumns) {
                if (col == colToDrop)
                    continue

                val value = getValueAt(row, col)
                result.setValueAt(newRow, newCol, value)

                newCol++
            }

            newRow++
        }

        return result
    }

    fun minor(row: Int, col: Int): Double {
        return submatrix(row, col).determinant()
    }

    fun cofactor(row: Int, col: Int): Double {
        val cofactor = minor(row, col)

        // if row + col is an odd number, change sign
        if ((row + col) % 2 != 0)
            return cofactor * -1;

        return cofactor
    }

    fun isInversable() = determinant() != 0.0

    fun inverse(): Matrix {
        val determinant = determinant()

        if (determinant == 0.0)
            throw OperationNotSupportedException("This matrix is not inversable!")

        val result = Matrix(numOfRows, numOfColumns)

        for (row in 0 until numOfRows) {
            for (col in 0 until numOfColumns) {
                val c = cofactor(row, col)

                result.setValueAt(col, row, c / determinant)
            }
        }

        return result
    }
}
