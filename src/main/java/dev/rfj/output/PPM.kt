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

    fun dumpToFile() {
        val file = createOutputFile()
        val writer = FileWriter(file)
        writer.use {
            lines.forEach { line ->
                writer.write(line)
                writer.write("\n")
            }
        }
    }

    private fun createOutputFile(): File {
        val file = File("./out/dmp.ppm")

        if (!file.exists())
            file.createNewFile()

        return file
    }
}
