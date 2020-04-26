package dev.rfj.matrix

import dev.rfj.util.equalsWithDelta

class Matrix(
        val numOfRows: Int,
        val numOfColumns: Int
) {

    private val values = createValueMatrix()

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
}
