package org.iesra

class LogCounter {
    fun contar(logs : List<LogEntry>) : Map<LogLevel, Int?>{
        val contadorNiveles = mutableMapOf<LogLevel, Int?>()
        logs.forEach{log ->
            if(log.level != null) {
                contadorNiveles[log.level] = contadorNiveles[log.level]?.plus(1)
            }
        }
        return contadorNiveles
    }
}