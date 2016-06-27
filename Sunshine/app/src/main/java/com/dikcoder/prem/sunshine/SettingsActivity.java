package com.dikcoder.prem.sunshine;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * A {@link PreferenceActivity} that presents a set of application settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general);

        // For all preferences, attach an OnPreferenceChangeListener so the UI summary can be
        // updated when the preference changes.
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_select_key)));

        //bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_cityID_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.units_key)));

        /*Preference ps = findPreference("location");
        ps.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                locPrefs();
                return true;
            }
        });
        Preference ps1 = findPreference("cityID");
        ps1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                locPrefs();
                return true;
            }
        });*/
    }

    /**
     * Attaches a listener so the summary is always updated with the preference value.
     * Also fires the listener once, to initialize the summary (so it shows up before the value
     * is changed.)
     */
    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
                locPrefs();
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
            locPrefs();
        }

        return true;
    }

    public void locPrefs(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        String locID = prefs.getString(getString(R.string.pref_location_select_key), getString(R.string.pref_cityID_key));
        EditTextPreference city = (EditTextPreference) findPreference("cityID");
        EditTextPreference loc = (EditTextPreference) findPreference("location");
        if (locID.equals("cityID")) {
            new Preference(this).setSummary(new ListPreference(this).getEntry());
            city.setEnabled(true);
            loc.setEnabled(false);
        } else if (locID.equals("location")) {
            new Preference(this).setSummary(new ListPreference(this).getEntry());
            city.setEnabled(false);
            loc.setEnabled(true);
        }
    }
}