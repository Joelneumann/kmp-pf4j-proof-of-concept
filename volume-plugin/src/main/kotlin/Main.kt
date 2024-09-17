package de.joelneumann

import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper
import java.awt.Robot
import java.awt.event.KeyEvent

class VolumePlugin(wrapper: PluginWrapper): Plugin(wrapper){

    companion object {
        var volumeController: MediaControl? = MediaControlFactory.create()
    }

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
            println("The given volume is: $value and: ${volumeController?.getSystemVolume()}")
            volumeController?.setSystemVolume(value)
        }
    }

    @Extension
    class NextTrackAction : ActionExtension {
        override fun performAction(value: Int) {
            volumeController?.nextTrack()
        }
    }

}