package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogParser {
    private val formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun parsear(lineasBrutas: List<String>): List<LogEntry> {
        return lineasBrutas.filter { it.isNotBlank() }.map { linea ->
            try {
                val fechaTexto = linea.substringAfter("[").substringBefore("]")
                val trasFecha = linea.substringAfter("] ").trim()
                val nivelTexto = trasFecha.substringBefore(" ")
                val mensaje = trasFecha.substringAfter(" ")

                LogEntry(
                    timestamp = LocalDateTime.parse(fechaTexto, formateador),
                    level = LogLevel.valueOf(nivelTexto.uppercase()),
                    message = mensaje,
                    isValid = true
                )
            } catch (e: Exception) {
                LogEntry(
                    timestamp = null,
                    level = null,
                    message = linea,
                    isValid = false
                )
            }
        }
    }
}