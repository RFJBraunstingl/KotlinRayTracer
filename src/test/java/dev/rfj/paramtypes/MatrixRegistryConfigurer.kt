package dev.rfj.paramtypes

import dev.rfj.matrix.Matrix
import io.cucumber.core.api.TypeRegistry
import io.cucumber.core.api.TypeRegistryConfigurer
import io.cucumber.datatable.DataTableType

class MatrixRegistryConfigurer: TypeRegistryConfigurer {

    override fun configureTypeRegistry(registry: TypeRegistry) {
        registry.defineDataTableType(
                DataTableType(Matrix::class.java, MatrixTransformer())
        )
    }
}