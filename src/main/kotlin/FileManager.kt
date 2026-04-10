package org.iesra
import java.io.File
import java.io.FileNotFoundException

class FileManager(private val file : File) : Input{
    override fun obtenerDatos() : List<String>{
        return try{
            file.readLines()
        }
        catch(e : FileNotFoundException){
            println("Error: El archivo '$file' no se encuentra en el directorio de trabajo.")
            emptyList()
        }

    }
}