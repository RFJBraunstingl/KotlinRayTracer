package dev.rfj.paramtypes

import dev.rfj.matrix.Matrix4x4
import io.cucumber.core.api.TypeRegistry
import io.cucumber.core.api.TypeRegistryConfigurer
import io.cucumber.datatable.DataTableType

class MatrixRegistryConfigurer: TypeRegistryConfigurer {

    override fun configureTypeRegistry(registry: TypeRegistry) {
        registry.defineDataTableType(
                DataTableType(Matrix4x4::class.java, Matrix4x4Transformer())
        )
    }
}