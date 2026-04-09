package org.iesra

import java.time.LocalDateTime

class TextGenerator(private val stats : LogStats, private val logs : List<LogEntry>, private val nombreArchivo : String, private val comienzo : LocalDateTime?, private val final : LocalDateTime?) {
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
            appendLine("- Primera entrada: ${stats.primeraFecha(logs)}")
            appendLine("- Primera entrada: ${stats.ultimaFecha(logs)}")
        }
        return informe
    }

    fun generarEstadisticas() : String = construirBaseInforme()

    fun generarReporte() : String{
        val base = construirBaseInforme()
        val complemento = buildString{
            appendLine()
            appendLine("Líneas encontradas:")
        }
        val reporte = base + complemento
        logs.forEach{log->
             reporte + "$log\n"
        }


        return reporte
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