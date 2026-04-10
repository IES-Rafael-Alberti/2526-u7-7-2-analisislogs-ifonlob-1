package org.iesra
import java.io.File
import java.time.LocalDateTime

class LogProcessor (private val parser : LogParser = LogParser(), private val filter : LogFilter = LogFilter(), private val stats : LogStats = LogStats()){
    fun ejecutarProceso(lineasBrutas: List<String>, nombreArchivo: String, fechaInicio: LocalDateTime?, fechaFin: LocalDateTime?, soloEstadisticas: Boolean, salida : Output, niveles : List<LogLevel>?, ignorarInvalidos : Boolean){
        val generador : TextGenerator

        val logsParseados = parser.parsear(lineasBrutas)
        val logsFiltradosFecha = filter.filtrarPorFechas(logsParseados, fechaInicio, fechaFin)
        val logsFiltradosNiveles = filter.filtrarPorNiveles(logsParseados,niveles)

        val logsFiltradosFinal = logsFiltradosFecha.intersect(logsFiltradosNiveles).toList()

        if(ignorarInvalidos){
            generador = TextGenerator(stats, logsFiltradosFinal.filter{it.isValid}, nombreArchivo, fechaInicio, fechaFin)
        }
        else{
            generador = TextGenerator(stats, logsFiltradosFinal, nombreArchivo, fechaInicio, fechaFin)
        }

        val contenidoFinal = if (soloEstadisticas) {
            generador.generarEstadisticas()
        }
        else {
            generador.generarReporte()
        }

        salida.escribirDatos(contenidoFinal)
    }
}