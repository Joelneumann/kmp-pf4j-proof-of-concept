package de.joelneumann

import org.pf4j.ExtensionPoint

interface GreetingExtension : ExtensionPoint {
    fun greet(name: String): String
}