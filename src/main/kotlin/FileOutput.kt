package org.iesra
import java.io.File

class FileOutput(private val file : File) : Output{
    override fun escribirDatos(contenido: String) {
        try {
            file.writeText(contenido)
            println("Informe guardado con éxito en: ${file.absolutePath}")
        } catch (e: Exception) {
            println("Error: No se pudo escribir el fichero de salida: ${e.message}")
        }
    }
}