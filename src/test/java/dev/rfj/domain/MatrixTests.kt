package dev.rfj.domain

import dev.rfj.domain.tuple.Tuple
import dev.rfj.matrix.Matrix
import org.junit.Test
import kotlin.math.PI
import kotlin.test.assertEquals

class MatrixTests {

    @Test
    fun testMatrixChaining() {
        val point = Tuple.point(1.0, 0.0, 1.0)
        val matrix =
                Matrix.identity4x4()
                        .rotateX(PI / 2)
                        .scale(5.0, 5.0, 5.0)
                        .translate(10.0, 5.0, 7.0)
        val actual = point.transform(matrix)
        val expected = Tuple.point(15.0, 0.0, 7.0)

        assertEquals(expected, actual)
    }
}