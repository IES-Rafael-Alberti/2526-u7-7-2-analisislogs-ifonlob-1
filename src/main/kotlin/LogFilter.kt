package org.iesra
import java.time.LocalDateTime

class LogFilter {
    fun filtrar(logs : List<LogEntry>, comienzo : LocalDateTime?, final : LocalDateTime?) : List<LogEntry>{
        return if (comienzo == null){
            logs.filter{log ->
                log.timestamp < final
            }
        }
        else if(final == null){
            logs.filter{log ->
                log.timestamp > comienzo
            }
        }
        else{
            logs.filter{log ->
                log.timestamp > comienzo && log.timestamp < final
            }
        }
    }
}