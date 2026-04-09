package org.iesra
import java.time.LocalDateTime

class LogFilter {
    fun filtrar(logs : List<LogEntry>, comienzo : LocalDateTime?, final : LocalDateTime?) : List<LogEntry>{
        if (comienzo == null){
            return logs.filter{log ->
                log.timestamp < final
            }
        }
        else if(final == null){
            return logs.filter{log ->
                log.timestamp > comienzo
            }
        }
        else{
            return logs.filter{log ->
                log.timestamp > comienzo && log.timestamp < final
            }
        }
    }
}