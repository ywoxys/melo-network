package org.github.ywoxys.kernel.base.account.preferences;

import java.util.EnumMap;
import java.util.Map;

public class PreferencesPlayer {

    private final Map<Preferences, Boolean> preferences= new EnumMap<>(Preferences.class);

    public PreferencesPlayer() {
        for (Preferences preference : Preferences.values()) {
            preferences.put(preference, false);
        }
    }

    public void setPreference(Preferences preference, Boolean isActive) {
        preferences.put(preference, isActive);
    }

    public boolean isPreferenceActive(Preferences preference) {
        return preferences.getOrDefault(preference, false);
    }

    public Map<Preferences, Boolean> getPreferences() {
        return preferences;
    }
}
