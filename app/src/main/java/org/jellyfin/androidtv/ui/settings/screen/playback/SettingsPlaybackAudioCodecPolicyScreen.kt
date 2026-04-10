package org.jellyfin.androidtv.ui.settings.screen.playback

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import org.jellyfin.androidtv.R
import org.jellyfin.androidtv.preference.UserPreferences
import org.jellyfin.androidtv.preference.constant.AudioCodecPolicy
import org.jellyfin.androidtv.ui.base.Text
import org.jellyfin.androidtv.ui.base.form.RadioButton
import org.jellyfin.androidtv.ui.base.list.ListButton
import org.jellyfin.androidtv.ui.base.list.ListSection
import org.jellyfin.androidtv.ui.navigation.LocalRouter
import org.jellyfin.androidtv.ui.settings.compat.rememberPreference
import org.jellyfin.androidtv.ui.settings.composable.SettingsColumn
import org.jellyfin.preference.Preference
import org.koin.compose.koinInject

/**
 * A reusable settings screen for selecting the [AudioCodecPolicy] for a specific audio codec.
 *
 * @param titleRes String resource id for the screen heading (e.g. "TrueHD codec policy").
 * @param preference The [UserPreferences] preference key for this codec.
 */
@Composable
fun SettingsPlaybackAudioCodecPolicyScreen(
	titleRes: Int,
	preference: Preference<AudioCodecPolicy>,
) {
	val router = LocalRouter.current
	val userPreferences = koinInject<UserPreferences>()
	var currentPolicy by rememberPreference(userPreferences, preference)

	SettingsColumn {
		item {
			ListSection(
				overlineContent = { Text(stringResource(R.string.pref_playback_advanced).uppercase()) },
				headingContent = { Text(stringResource(titleRes)) },
			)
		}

		items(AudioCodecPolicy.entries) { entry ->
			ListButton(
				headingContent = { Text(stringResource(entry.nameRes)) },
				trailingContent = { RadioButton(checked = currentPolicy == entry) },
				onClick = {
					currentPolicy = entry
					router.back()
				}
			)
		}
	}
}
