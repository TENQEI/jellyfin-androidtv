package org.jellyfin.androidtv.preference.constant

import org.jellyfin.androidtv.R
import org.jellyfin.preference.PreferenceEnum

/**
 * Policy controlling how a specific audio codec is handled during playback.
 * The client modifies the device profile sent to the server so that the server
 * can make the appropriate transcoding decision.
 */
enum class AudioCodecPolicy(
	override val nameRes: Int,
) : PreferenceEnum {
	/**
	 * Follow default behavior: allow the codec for direct play without any channel restriction.
	 */
	AUTO(R.string.audio_codec_policy_auto),

	/**
	 * Always allow this codec for direct play regardless of channel count.
	 */
	ALWAYS_ALLOW(R.string.audio_codec_policy_always_allow),

	/**
	 * Always force the server to transcode this codec (equivalent to disabling the codec).
	 */
	ALWAYS_TRANSCODE(R.string.audio_codec_policy_always_transcode),

	/**
	 * Allow direct play only when the audio is stereo (≤2 channels). If the stream is
	 * multichannel, instruct the server to transcode it (e.g. to AC3).
	 */
	TRANSCODE_IF_MULTICHANNEL(R.string.audio_codec_policy_transcode_if_multichannel),
}
