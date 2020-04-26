package dev.rfj.paramtypes

import dev.rfj.matrix.Matrix4x4
import io.cucumber.datatable.DataTable
import io.cucumber.datatable.TableTransformer

class Matrix4x4Transformer: TableTransformer<Matrix4x4> {

    override fun transform(dataTable: DataTable): Matrix4x4 {
        val result = Matrix4x4()
        val listOfListOfStrings = dataTable.asLists()

        for (row in 0 until listOfListOfStrings.size) {
            val rowAsStrings = listOfListOfStrings[row]

            for (col in 0 until rowAsStrings.size) {
                val value = rowAsStrings[col].toDouble()
                result.setValueAt(row, col, value)
            }
        }

        return result
    }
}
