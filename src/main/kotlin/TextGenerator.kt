package org.iesra

import java.time.LocalDateTime

class TextGenerator(private val stats : LogStats = LogStats(), private val logs : List<LogEntry>, private val fileName : String, private val comienzo : LocalDateTime?, private val final : LocalDateTime?) {
    fun generarEstadisticas(rango : String = calcularRango()) : String{
        val estadisticas = buildString {
            appendLine("INFORME DE LOGS")
            appendLine("===============")
            appendLine("Fichero analizado: $fileName")
            appendLine("Rango aplicado: ${calcularRango()}")
            appendLine("Niveles incluidos: ${nivelesIncluidos()}")
            appendLine()
            appendLine("Resumen:")
            appendLine("- Líneas procesadas: ${stats.lineasProcesadas(logs)}")
            appendLine("- Líneas válidas: ${stats.lineasValidas(logs)}")
            appendLine("- Líneas inválidas: ${stats.lineasInvalidas(logs)}")
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
        return estadisticas
    }

    fun generarReporte(rango : String = calcularRango()) : String{
        val reporte = buildString {
            appendLine("INFORME DE LOGS")
            appendLine("===============")
            appendLine("Fichero analizado: $fileName")
            appendLine("Rango aplicado: ${calcularRango()}")
            appendLine("Niveles incluidos: ${nivelesIncluidos()}")
            appendLine()
            appendLine("Resumen:")
            appendLine("- Líneas procesadas: ${stats.lineasProcesadas(logs)}")
            appendLine("- Líneas válidas: ${stats.lineasValidas(logs)}")
            appendLine("- Líneas inválidas: ${stats.lineasInvalidas(logs)}")
            appendLine()
            appendLine("Conteo por nivel:")
            appendLine("- INFO: ${stats.contadorInfo(logs)}")
            appendLine("- WARNING: ${stats.contadorWarning(logs)}")
            appendLine("- ERROR: ${stats.contadorError(logs)}")
            appendLine()
            appendLine("Periodo detectado:")
            appendLine("- Primera entrada: ${stats.primeraFecha(logs)}")
            appendLine("- Primera entrada: ${stats.ultimaFecha(logs)}")
            appendLine()
            appendLine("Entradas encontradas:")
        }

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