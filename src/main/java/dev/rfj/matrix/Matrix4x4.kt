package dev.rfj.matrix

class Matrix4x4 {

    private val values = createValueMatrix()

    private fun createValueMatrix(): MutableList<MutableList<Double>> {
        val rows = mutableListOf<MutableList<Double>>()

        for (i in 0..3) {
            val row = mutableListOf<Double>()

            for (j in 0..3)
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
        return "Matrix4x4(values=$values)"
    }
}
