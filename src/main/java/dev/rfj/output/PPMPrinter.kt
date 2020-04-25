package dev.rfj.output

import java.io.File
import java.io.FileWriter

fun PPM.dumpToFile() {

    fun createOutputFile(): File {
        val file = File("./out/dmp.ppm")

        if (!file.exists())
            file.createNewFile()

        return file
    }

    val file = createOutputFile()
    val writer = FileWriter(file)
    writer.use {
        this.lines().forEach { line ->
            writer.write(line)
            writer.write("\n")
        }
    }
}