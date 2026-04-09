package org.jellyfin.androidtv.preference.constant

import org.jellyfin.androidtv.R
import org.jellyfin.preference.PreferenceEnum

enum class PreferredAudioCodecs(
	override val nameRes: Int,
) : PreferenceEnum {
	/**
	 * Prefer AAC as the transcoding audio codec
	 */
	AAC(R.string.pref_audio_aac),

	/**
	 * Prefer AC3 (Dolby Digital) as the transcoding audio codec
	 */
	AC3(R.string.pref_audio_ac3),

	/**
	 * Prefer MP3 as the transcoding audio codec
	 */
	MP3(R.string.pref_audio_mp3),

	/**
	 * No preferred codec; let the server decide
	 */
	NONE(R.string.pref_audio_none),
}
