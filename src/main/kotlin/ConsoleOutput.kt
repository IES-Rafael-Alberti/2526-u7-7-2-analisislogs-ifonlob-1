package org.iesra

class ConsoleOutput : Output {
    override fun escribirDatos(contenido: String) = println(contenido)
}