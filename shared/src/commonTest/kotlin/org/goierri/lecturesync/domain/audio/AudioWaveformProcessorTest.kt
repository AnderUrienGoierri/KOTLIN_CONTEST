package org.goierri.lecturesync.domain.audio

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AudioWaveformProcessorTest {

    @Test
    fun testEmptyPcmYieldsZeroAmplitude() {
        val amp = AudioWaveformProcessor.calculateRmsAmplitude(byteArrayOf())
        assertEquals(0.0, amp)
    }

    @Test
    fun testBarHeightClamping() {
        val minHeight = AudioWaveformProcessor.amplitudeToBarHeight(0.0, maxHeightPx = 48.0, minHeightPx = 6.0)
        assertEquals(6.0, minHeight)

        val maxHeight = AudioWaveformProcessor.amplitudeToBarHeight(1.0, maxHeightPx = 48.0, minHeightPx = 6.0)
        assertEquals(48.0, maxHeight)

        val midHeight = AudioWaveformProcessor.amplitudeToBarHeight(0.5, maxHeightPx = 48.0, minHeightPx = 6.0)
        assertTrue(midHeight in 6.0..48.0)
    }
}
