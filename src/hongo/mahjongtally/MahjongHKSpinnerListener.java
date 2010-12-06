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

import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;

public class MahjongHKSpinnerListener implements OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
      MahjongPlayer loser = (MahjongPlayer)parent.getItemAtPosition(pos);
      MahjongTally.setCurrentLoser(loser.getId());
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
      MahjongPlayer loser = (MahjongPlayer)parent.getItemAtPosition(0);
      MahjongTally.setCurrentLoser(loser.getId());
    }
}
