package dev.rfj.steps

import dev.rfj.domain.Tuple
import dev.rfj.domain.store.TupleStore
import io.cucumber.java.en.Given

class VectorSteps(
        private val tupleStore: TupleStore
) {

    @Given("{string} <- vector\\({double}, {double}, {double})")
    fun createVectorTuple(name: String, x: Double, y: Double, z: Double) {
        val vectorTuple = Tuple.createVector(x, y, z)
        tupleStore.save(name, vectorTuple)
    }
}