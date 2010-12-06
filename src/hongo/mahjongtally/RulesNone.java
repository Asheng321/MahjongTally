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

public class RulesNone extends MahjongScoring {
  public static void calculate() {
    View v = MahjongTally.MY_APP.findViewById(R.id.my_table);
    MahjongCell currentCell = (MahjongCell)v.findViewById(MahjongTally.CURRENT_COLUMN_ID);
    currentCell.setScore(String.valueOf(MahjongTally.CURRENT_POINT_VALUE));
  }
} 
