package dev.rfj.paramtypes

import dev.rfj.domain.Tuple
import dev.rfj.domain.store.TupleStore
import io.cucumber.java.ParameterType

class CustomGherkinTypes(
        private val tupleStore: TupleStore
) {

    @ParameterType("([a-zA-Z0-9]+)")
    fun tupleName(name: String): Tuple = tupleStore.findByName(name)

    @ParameterType("tuple\\((-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+)\\)")
    fun tuple(x: String, y: String, z: String, w: String): Tuple {
        return Tuple.create(
                x.toDouble(),
                y.toDouble(),
                z.toDouble(),
                w.toDouble()
        )
    }

    @ParameterType("vector\\((-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+)\\)")
    fun vector(x: String, y: String, z: String): Tuple {
        return Tuple.createVector(
                x.toDouble(),
                y.toDouble(),
                z.toDouble()
        )
    }

    @ParameterType("point\\((-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+)\\)")
    fun point(x: String, y: String, z: String): Tuple {
        return Tuple.createPoint(
                x.toDouble(),
                y.toDouble(),
                z.toDouble()
        )
    }
}