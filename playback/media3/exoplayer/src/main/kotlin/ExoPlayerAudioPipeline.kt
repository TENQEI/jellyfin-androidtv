package org.jellyfin.playback.media3.exoplayer

import android.media.audiofx.LoudnessEnhancer
import timber.log.Timber

class ExoPlayerAudioPipeline {
	private var loudnessEnhancer: LoudnessEnhancer? = null
	var normalizationGain: Float? = null
		set(value) {
			Timber.d("Normalization gain changed to $value")
			field = value
			applyGain()
		}

	fun setAudioSessionId(audioSessionId: Int) {
		Timber.d("Audio session id changed to $audioSessionId")

		// Re-create loudness enhancer for normalization gain
		loudnessEnhancer?.release()
		loudnessEnhancer = runCatching { LoudnessEnhancer(audioSessionId) }
			.onFailure { Timber.w(it, "Failed to create LoudnessEnhancer") }
			.getOrNull()

		// Re-apply current normalization gain
		applyGain()
	}

	private fun applyGain() {
		if (loudnessEnhancer == null) {
			Timber.d("LoudnessEnhancer is not initialized")
			return
		}

		// LoudnessEnhancer can only boost audio, not attenuate it.
		// Only enable it when the normalization gain is positive; for negative or zero
		// gain the audio is already at or above the target loudness so leave it unchanged.
		val targetGain = normalizationGain
			?.takeIf { it > 0f }
			// Convert to millibels
			?.times(100f)
			// Round to integer
			?.toInt()

		Timber.d("Applying gain (targetGain=$targetGain)")
		loudnessEnhancer?.setEnabled(targetGain != null)
		loudnessEnhancer?.setTargetGain(targetGain ?: 0)
	}
}
