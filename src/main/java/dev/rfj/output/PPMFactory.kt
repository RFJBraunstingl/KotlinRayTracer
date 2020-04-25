package dev.rfj.output

import dev.rfj.canvas.Canvas
import kotlin.math.roundToInt

fun canvasToPPM(canvas: Canvas): PPM {

    fun doubleToColorVal(colorVal: Double): Int {
        var toInt = (colorVal * PPM.MAX_COLOR_VAL).roundToInt()

        if (toInt < 0)
            toInt = 0

        if (toInt > 255)
            toInt = 255

        return toInt
    }

    fun appendColorValue(line: String, ppm: PPM, colorVal: Double): String {
        // if adding pixel would make line exceed 70 characters rotate early
        var tmp = line
        if (tmp.length > 66) {
            tmp = tmp.dropLast(1) // remove last space
            ppm.appendLine(tmp)
            tmp = ""
        }

        tmp += doubleToColorVal(colorVal)
        tmp += " "
        return tmp
    }

    val ppm = PPM(canvas.width, canvas.height)

    canvas.eachRow { row ->
        var line = ""

        row.forEach { px ->
            line = appendColorValue(line, ppm, px.red)
            line = appendColorValue(line, ppm, px.green)
            line = appendColorValue(line, ppm, px.blue)
        }

        line = line.dropLast(1) // remove last space
        ppm.appendLine(line)
    }

    // end ppm file with a new line
    ppm.appendLine("")

    return ppm
}
