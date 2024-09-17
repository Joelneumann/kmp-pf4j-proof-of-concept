package de.joelneumann

import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper

class PrintPlugin(wrapper: PluginWrapper): Plugin(wrapper){

    override fun start() {
        super.start()
        println("PrintPlugin has started")
    }

    override fun stop() {
        super.stop()
        println("PrintPlugin has stopped")
    }

    @Extension
    class PrintAction : ActionExtension {
        override fun performAction(value: Int) {
            println("The given number is: $value")
        }

    }

}