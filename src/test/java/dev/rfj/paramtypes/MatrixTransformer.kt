package dev.rfj.paramtypes

import dev.rfj.matrix.Matrix
import io.cucumber.datatable.DataTable
import io.cucumber.datatable.TableTransformer

class MatrixTransformer: TableTransformer<Matrix> {

    override fun transform(dataTable: DataTable): Matrix {
        val listOfListOfStrings = dataTable.asLists()
        val numOfRows = listOfListOfStrings.size
        val numOfColumns = listOfListOfStrings[0].size

        val result = Matrix(numOfRows, numOfColumns)

        for (row in 0 until numOfRows) {
            val rowAsStrings = listOfListOfStrings[row]

            for (col in 0 until numOfColumns) {
                val value = rowAsStrings[col].toDouble()
                result.setValueAt(row, col, value)
            }
        }

        return result
    }
}
