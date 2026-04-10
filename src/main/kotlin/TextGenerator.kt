package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TextGenerator(private val stats : LogStats, private val logs : List<LogEntry>, private val nombreArchivo : String, private val comienzo : LocalDateTime?, private val final : LocalDateTime?) {

    private val formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    private fun construirBaseInforme() : String{
        val informe = buildString {
            appendLine("INFORME DE LOGS")
            appendLine("===============")
            appendLine("Fichero analizado: $nombreArchivo")
            appendLine("Rango aplicado: ${calcularRango()}")
            appendLine("Niveles incluidos: ${nivelesIncluidos().joinToString(", ")}")
            appendLine()
            appendLine("Resumen:")
            appendLine("- Líneas procesadas: ${stats.lineasProcesadas(logs)}")
            appendLine("- Líneas válidas: ${stats.lineasValidas(logs).size}")
            appendLine("- Líneas inválidas: ${stats.lineasInvalidas(logs).size}")
            appendLine()
            appendLine("Conteo por nivel:")
            appendLine("- INFO: ${stats.contadorInfo(logs)}")
            appendLine("- WARNING: ${stats.contadorWarning(logs)}")
            appendLine("- ERROR: ${stats.contadorError(logs)}")
            appendLine()
            appendLine("Periodo detectado:")
            appendLine("- Primera entrada: ${LocalDateTime.parse(stats.primeraFecha(logs).toString(),formatoFecha)}")
            appendLine("- Última entrada: ${LocalDateTime.parse(stats.ultimaFecha(logs).toString(),formatoFecha)}")
        }
        return informe
    }

    fun generarEstadisticas() : String = construirBaseInforme()

    fun generarReporte() : String{
        val base = construirBaseInforme()
        val titulo = buildString{
            appendLine()
            appendLine("Líneas encontradas:")
        }
        val complemento = buildString{
            logs.forEach{log->
                appendLine(log)
        }
        }

        return base + titulo + complemento
    }
    fun calcularRango() : String{
        return if(comienzo == null){
            "$final y anteriores"
        }
        else if(final == null){
            "$comienzo y posteriores"
        }
        else{
            "$comienzo - $final"
        }
    }

    fun nivelesIncluidos() : Set<LogLevel>{
        val niveles : MutableList<LogLevel> = mutableListOf()
        if(stats.contadorInfo(logs) >= 1) {
            niveles.add(LogLevel.INFO)
        }
        if(stats.contadorError(logs) >= 1) {
            niveles.add(LogLevel.ERROR)
        }
        if(stats.contadorWarning(logs) >= 1) {
            niveles.add(LogLevel.WARNING)
        }
        return niveles.toSet()
    }
}