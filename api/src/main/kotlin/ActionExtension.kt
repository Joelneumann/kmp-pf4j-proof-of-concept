package de.joelneumann

import org.pf4j.ExtensionPoint

interface ActionExtension: ExtensionPoint {

    fun performAction(value: Int)
}