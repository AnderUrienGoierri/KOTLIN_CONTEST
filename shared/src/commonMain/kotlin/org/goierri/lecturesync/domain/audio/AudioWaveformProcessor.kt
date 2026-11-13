package org.goierri.lecturesync.domain.audio

import kotlin.math.log10
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Real-time audio waveform and volume processor for multiplatform visualizers.
 */
object AudioWaveformProcessor {

    /**
     * Calculates the Root Mean Square (RMS) amplitude normalized between 0.0 and 1.0.
     */
    fun calculateRmsAmplitude(pcmBytes: ByteArray): Double {
        if (pcmBytes.isEmpty()) return 0.0

        var sumSquare = 0.0
        val sampleCount = pcmBytes.size / 2

        for (i in 0 until sampleCount) {
            val idx = i * 2
            val low = pcmBytes[idx].toInt() and 0xFF
            val high = pcmBytes[idx + 1].toInt()
            val sample = (high shl 8) or low
            val normalizedSample = sample / 32768.0
            sumSquare += normalizedSample * normalizedSample
        }

        val rms = sqrt(sumSquare / sampleCount)
        return min(1.0, max(0.0, rms))
    }

    /**
     * Converts normalized RMS amplitude to a bar height in pixels (e.g. for JavaFX or Compose).
     */
    fun amplitudeToBarHeight(amplitude: Double, maxHeightPx: Double = 48.0, minHeightPx: Double = 6.0): Double {
        val clamped = min(1.0, max(0.0, amplitude))
        return minHeightPx + (clamped * (maxHeightPx - minHeightPx))
    }
}
