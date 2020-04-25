package dev.rfj.output

import java.io.File
import java.io.FileWriter

class PPM(
        val widthInPx: Int,
        val heightInPx: Int
) {

    companion object {

        /*
         * the magic number which identifies the ppm format
         * we are using
         */
        const val MAGIC_NUMBER = "P3"

        /*
         * colors will be represented by
         * an integer value from 0 to 255
         * (inclusive)
         */
        const val MAX_COLOR_VAL = 255

    }

    fun appendLine(line: String) {
        lines += line
    }

    private var lines = arrayOf<String>()

    init {
        /* initialize header */
        lines += MAGIC_NUMBER
        lines += "$widthInPx $heightInPx"
        lines += "$MAX_COLOR_VAL"
    }

    fun lines(): Array<String> = lines.copyOf()
}
