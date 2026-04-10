package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class LogEntry(
    val timestamp: LocalDateTime?,
    val level: LogLevel?,
    val message: String,
    val isValid: Boolean = true
){
    private val formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    override fun toString(): String = "[${timestamp?.format(formatoFecha)}] $level $message"

}
