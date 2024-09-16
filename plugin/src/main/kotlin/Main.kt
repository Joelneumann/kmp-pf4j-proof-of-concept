package de.joelneumann

import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper

class MyPlugin(wrapper: PluginWrapper): Plugin(wrapper){

    override fun start() {
        super.start()
        println("This test has started")
    }

    override fun stop() {
        super.stop()
        println("This test has stopped")
    }

    @Extension
    class FriendlyGreeting : GreetingExtension {
        override fun greet(name: String): String {
            return "Hello, $name! Welcome to PF4J with Kotlin!"
        }
    }

}