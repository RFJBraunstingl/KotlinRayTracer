package dev.rfj.matrix

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
}
