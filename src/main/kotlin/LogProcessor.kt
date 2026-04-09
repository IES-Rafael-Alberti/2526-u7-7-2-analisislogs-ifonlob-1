package org.iesra
import java.time.LocalDateTime

class LogProcessor (private val parser : LogParser = LogParser(), private val filter : LogFilter = LogFilter(), private val stats : LogStats = LogStats()){
    fun ejecutarProceso(lineasBrutas: List<String>, nombreArchivo: String, fechaInicio: LocalDateTime?, fechaFin: LocalDateTime?, soloEstadisticas: Boolean, salida: Output){
        val logsParseados = parser.parsear(lineasBrutas)
        val logsFiltrados = filter.filtrar(logsParseados, fechaInicio, fechaFin)
        val generador = TextGenerator(stats, logsFiltrados, nombreArchivo, fechaInicio, fechaFin)
        val contenidoFinal = if (soloEstadisticas) {
            generador.generarEstadisticas()
        } else {
            generador.generarReporte()
        }
        salida.escribirDatos(contenidoFinal)
    }
}