package org.iesra
import java.time.LocalDateTime
import kotlin.collections.filter

class LogFilter {
    fun filtrarPorFechas(logs : List<LogEntry>, comienzo : LocalDateTime?, final : LocalDateTime?) : List<LogEntry>{
        return if (comienzo == null && final == null) {
            logs
        }
        else if(comienzo == null){
            logs.filter{log->
                log.timestamp?.let { timestamp-> !timestamp.isAfter(final) } ?: false
            }
        }
        else if(final == null){
            logs.filter{log->
                log.timestamp?.let { timestamp-> !timestamp.isBefore(comienzo) } ?: false
            }
        }
        else {
            logs.filter { it.timestamp?.let { timestamp-> !timestamp.isBefore(comienzo) && !timestamp.isAfter(final) } ?: false }
        }
    }

    fun filtrarPorNiveles(logs : List<LogEntry>, levels : List<LogLevel>?) : List<LogEntry>{
        return if(levels.isNullOrEmpty()){
            logs
        }
        else{
            logs.filter{it.level in levels}
        }
    }

    fun filtrarLineasValidad



}