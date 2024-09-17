package de.joelneumann

object MediaControlFactory {
    fun create(): MediaControl {
        val osName = System.getProperty("os.name").lowercase()

        return when {
            osName.contains("win") -> MediaControlWindows()
            osName.contains("mac") -> MediaControlMac()
            else -> throw UnsupportedOperationException("Unsupported OS: $osName")
        }
    }
}

interface MediaControl {
    fun setSystemVolume(volume: Int)
    fun getSystemVolume(): Int

    fun nextTrack()
}

// Windows implementation
class MediaControlWindows : MediaControl {
    override fun setSystemVolume(volume: Int) {
        // JNA code or Windows-specific logic
        println("Setting Windows system volume to $volume")
    }

    override fun getSystemVolume(): Int {
        // JNA code or Windows-specific logic
        return 50 // Placeholder value
    }

    override fun nextTrack() {
        println("Setting Windows next Track")
    }
}

// macOS implementation
class MediaControlMac : MediaControl {
    override fun setSystemVolume(volume: Int) {
        if (volume < 0 || volume > 100) return
        val command = "set volume output volume $volume"
        Runtime.getRuntime().exec(arrayOf("osascript", "-e", command))
    }

    override fun getSystemVolume(): Int {
        val command = arrayOf("osascript", "-e", "output volume of (get volume settings)")
        val process = Runtime.getRuntime().exec(command)
        return process.inputStream.bufferedReader().readText().trim().toInt()
    }

    override fun nextTrack() {
        val command = "tell application \"Spotify\" to next track"
        Runtime.getRuntime().exec(arrayOf("osascript", "-e", command))
    }
}