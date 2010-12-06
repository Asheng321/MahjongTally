/*
 * Mahjong Tally - an android Mahjong Score Keeper program
 * Copyright (C) 2010-2011 Hong Tuyen
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/.
 */
 
package hongo.mahjongtally; 
 
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;
import android.view.Gravity;

public class MahjongSettings extends PreferenceActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          addPreferencesFromResource(R.layout.settings);
          Preference customPref = (Preference) findPreference("about");
          
          
          
          customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
              String version_number = getString(R.string.version_number);
              Toast myToast = Toast.makeText(getBaseContext(),
              "Mahjong Tally\nVersion: "+version_number+"\nAuthor: Hong Tuyen\nemail: hongobongo@gmail.com",
              Toast.LENGTH_LONG);
              myToast.setGravity(android.view.Gravity.CENTER, 0, 0);
              myToast.show();
              return true;
            }
         });
         Toast.makeText(getBaseContext(), "changes take affect on new game", Toast.LENGTH_SHORT).show();
        }
        
}
