package org.iesra
import java.io.File

class FileManager(private val file : File) : Input{
    override fun obtenerDatos() : List<String>{
        return if (file.exists()) {
            file.readLines()
        } else {
            println("Error: El archivo '$file' no se encuentra en el directorio de trabajo.")
            emptyList()
        }
    }
}