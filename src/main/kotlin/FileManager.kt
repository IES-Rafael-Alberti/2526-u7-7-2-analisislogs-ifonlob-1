package org.iesra
import java.io.File

class FileManager(private val fileName : String) : Input{

    override fun obtenerDatos() : List<String>{
        val file = File(fileName)
        return if (file.exists()) {
            file.readLines()
        } else {
            println("Error: El archivo '$fileName' no se encuentra en el directorio de trabajo.")
            emptyList()
        }
    }

    fun

}