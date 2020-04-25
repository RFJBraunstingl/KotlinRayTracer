package dev.rfj.domain

import dev.rfj.domain.store.TupleStore
import dev.rfj.domain.store.exception.TupleNotFoundException
import dev.rfj.domain.tuple.Tuple
import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_TUPLE_NAME = "test"
val TEST_TUPLE: Tuple = Tuple(1.0, 1.0, 1.0, 1.0)

class TupleStoreTest {

    @Test
    fun testTupleStorage() {
        val tupleStore = TupleStore()
        tupleStore.save(TEST_TUPLE_NAME, TEST_TUPLE)
        assertEquals(1, tupleStore.size())
    }

    @Test
    fun testTupleRetrieval() {
        val tupleStore = TupleStore()
        tupleStore.save(TEST_TUPLE_NAME, TEST_TUPLE)
        assertEquals(TEST_TUPLE, tupleStore.findByName("test"))
    }

    @Test(expected = TupleNotFoundException::class)
    fun testTupleNotFound() {
        val tupleStore = TupleStore()
        tupleStore.findByName("not a tuple")
    }
}