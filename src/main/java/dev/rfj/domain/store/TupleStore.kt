package dev.rfj.domain.store

import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.store.exception.TupleNotFoundException

class TupleStore {

    private val tupleMap = HashMap<String, Tuple>()

    fun save(test: String, tuple: Tuple) {
        tupleMap[test] = tuple
    }

    fun size(): Int {
        return tupleMap.size
    }

    fun findByName(name: String): Tuple {
        val tuple: Tuple? = tupleMap[name]
        if (tuple is Tuple)
            return tuple

        throw TupleNotFoundException(name)
    }
}
