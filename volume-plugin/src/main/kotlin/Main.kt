package de.joelneumann

import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper

class VolumePlugin(wrapper: PluginWrapper): Plugin(wrapper){

    override fun start() {
        super.start()
        println("VolumePlugin has started")
    }

    override fun stop() {
        super.stop()
        println("VolumePlugin has stopped")
    }

    @Extension
    class VolumeAction : ActionExtension {
        override fun performAction(value: Int) {
            println("The given volume is: $value")
        }

    }

}