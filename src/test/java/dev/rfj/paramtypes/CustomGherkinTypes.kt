package dev.rfj.paramtypes

import dev.rfj.canvas.Canvas
import dev.rfj.domain.MatrixMap
import dev.rfj.domain.tuple.Tuple
import dev.rfj.domain.store.TupleStore
import dev.rfj.domain.tuple.Color
import dev.rfj.matrix.Matrix4x4
import dev.rfj.steps.canvas.CanvasMap
import io.cucumber.java.ParameterType
import kotlin.test.fail

class CustomGherkinTypes(
        private val tupleStore: TupleStore,
        private val canvasMap: CanvasMap,
        private val matrixMap: MatrixMap
) {

    @ParameterType("([a-zA-Z0-9]+)")
    fun tupleName(name: String): Tuple = tupleStore.findByName(name)

    @ParameterType("([a-zA-Z0-9]+)")
    fun colorName(name: String): Color {
        val color = tupleStore.findByName(name)
        if (color !is Color) fail("$name was referenced as Color but is not a color")
        return color
    }

    @ParameterType("([a-zA-Z0-9]+)")
    fun canvasName(name: String): Canvas {
        return canvasMap[name] ?: fail("Could not find Canvas with name $name!")
    }

    @ParameterType("([a-zA-Z0-9]+)")
    fun matrixName(name: String): Matrix4x4 {
        return matrixMap[name] ?: fail("Could not find matrix with name $name!")
    }

    @ParameterType("([a-zA-Z0-9]+)")
    fun name(name: String): String = name


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
        return Tuple.vector(
                x.toDouble(),
                y.toDouble(),
                z.toDouble()
        )
    }

    @ParameterType("point\\((-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+)\\)")
    fun point(x: String, y: String, z: String): Tuple {
        return Tuple.point(
                x.toDouble(),
                y.toDouble(),
                z.toDouble()
        )
    }

    @ParameterType("color\\((-?\\d+\\.\\d+), (-?\\d+\\.\\d+), (-?\\d+\\.\\d+)\\)")
    fun color(red: String, green: String, blue: String): Color {
        return Tuple.color(
                red.toDouble(),
                green.toDouble(),
                blue.toDouble()
        )
    }
}