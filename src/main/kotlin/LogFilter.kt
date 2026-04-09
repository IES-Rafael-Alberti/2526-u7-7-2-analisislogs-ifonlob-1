package org.iesra
import java.time.LocalDateTime

class LogFilter {
    fun filtrar(logs : List<LogEntry>, comienzo : LocalDateTime?, final : LocalDateTime?) : List<LogEntry>{
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
}