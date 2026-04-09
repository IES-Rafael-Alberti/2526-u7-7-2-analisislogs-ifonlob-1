package org.iesra
import java.io.File

class FileOutput(private val ruta : String) : Output{
    override fun escribirDatos(contenido: String) {
        val file = File(ruta)
        try {
            file.writeText(contenido)
            println("Informe guardado con éxito en: ${file.absolutePath}")
        } catch (e: Exception) {
            println("Error: No se pudo escribir el fichero de salida: ${e.message}")
        }
    }
}