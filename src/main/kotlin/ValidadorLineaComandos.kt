package org.iesra

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ValidadorLineaComandos : CliktCommand(name="logtool",help="Procesa ficheros de logs."){
    private val formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val input by option("-i", "--input", help = "Fichero de log de entrada. Obligatorio.").file(mustExist = true, canBeDir = false).required()
    val from by option("-f", "--from", help = "Fecha inicial (YYYY-MM-DD HH:MM:SS)").convert { LocalDateTime.parse(it,formatoFecha) }
    val to by option("-t", "--to", help = "Fecha final (YYYY-MM-DD HH:MM:SS)").convert { LocalDateTime.parse(it,formatoFecha)}
    val level by option("-l", "--level", help = "Niveles (INFO, WARNING, ERROR)").choice("INFO", "WARNING", "ERROR", ignoreCase = true).split(",")
    val statsOnly by option("-s", "--stats", help = "Muestra únicamente las estadísticas.").flag()
    val report by option("-r", "--report", help = "Genera un informe completo (comportamiento por defecto).").flag(default = true)
    val ignoreInvalid by option("--ignore-invalid", help = "Ignora líneas mal formadas.").flag()
    val outputFichero by option("-o", "--output", help = "Guarda la salida en un fichero.").file()
    val outputConsola by option("-p", "--stdout", help = "Muestra la salida por consola.").flag()

    override fun run() {
        if (outputFichero == null && !outputConsola) {
            println("Error de uso: Debe indicarse una salida (--stdout o --output <fichero>).")
            return
        }



        val procesador = LogProcessor()

        procesador.ejecutarProceso()
    }
}