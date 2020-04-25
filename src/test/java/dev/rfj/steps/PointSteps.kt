package dev.rfj.steps

import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.store.TupleStore
import io.cucumber.java.en.Given

class PointSteps(
        private val tupleStore: TupleStore
) {

    @Given("{string} <- point\\({double}, {double}, {double})")
    fun createPointTuple(name: String, x: Double, y: Double, z: Double) {
        val pointTuple = Tuple.point(x, y, z)
        tupleStore.save(name, pointTuple)
    }
}