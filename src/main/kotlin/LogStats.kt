package org.iesra

class LogStats {
    fun lineasProcesadas(logs : List<LogEntry>) = logs.size
    fun lineasValidas(logs : List<LogEntry>) = logs.filter{linea -> linea.isValid}
    fun lineasInvalidas(logs : List<LogEntry>) = logs.filter{linea -> !linea.isValid}
    fun contadorInfo(logs : List<LogEntry>) = logs.count{linea -> linea.level == LogLevel.INFO}
    fun contadorWarning(logs : List<LogEntry>) = logs.count{linea -> linea.level == LogLevel.WARNING}
    fun contadorError(logs : List<LogEntry>) = logs.count{linea -> linea.level == LogLevel.ERROR}
    fun primeraFecha(logs : List<LogEntry>) = logs.minBy {linea-> linea.timestamp}
    fun ultimaFecha(logs : List<LogEntry>) = logs.maxBy {linea-> linea.timestamp}




}