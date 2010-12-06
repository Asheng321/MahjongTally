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

public class RulesHKOld extends MahjongScoring {
  public static void calculate() {
    View v = MahjongTally.MY_APP.findViewById(R.id.my_table);
    MahjongRow currentRow = (MahjongRow)v.findViewById(MahjongTally.CURRENT_ROW_ID);
    Integer points;
    if(MahjongTally.CURRENT_POINT_VALUE==MahjongTally.LIMIT_VALUE) {
      points = MahjongTally.LIMIT_VALUE;
    } else {
      points = convertFan(MahjongTally.CURRENT_POINT_VALUE);
    }
    switch(MahjongTally.CURRENT_WINNER) {
      case PLAYER_1:
        switch(MahjongTally.CURRENT_LOSER) {
          case PLAYER_1:
              currentRow.setRowScore(String.valueOf(points*6), String.valueOf(points*-2), String.valueOf(points*-2), String.valueOf(points*-2));
            break;
          case PLAYER_2:
            currentRow.setRowScore(String.valueOf(points*4), String.valueOf(points*-2), String.valueOf(points*-1), String.valueOf(points*-1));
            break;
          case PLAYER_3:
            currentRow.setRowScore(String.valueOf(points*4), String.valueOf(points*-1), String.valueOf(points*-2), String.valueOf(points*-1));
            break;
          case PLAYER_4:
            currentRow.setRowScore(String.valueOf(points*4), String.valueOf(points*-1), String.valueOf(points*-1), String.valueOf(points*-2));
            break;
        }
        break;
      case PLAYER_2:
        switch(MahjongTally.CURRENT_LOSER) {
          case PLAYER_1:
            currentRow.setRowScore(String.valueOf(points*-2), String.valueOf(points*4), String.valueOf(points*-1), String.valueOf(points*-1));
            break;
          case PLAYER_2:
            currentRow.setRowScore(String.valueOf(points*-2), String.valueOf(points*6), String.valueOf(points*-2), String.valueOf(points*-2));
            break;
          case PLAYER_3:
            currentRow.setRowScore(String.valueOf(points*-1), String.valueOf(points*4), String.valueOf(points*-2), String.valueOf(points*-1));          
            break;
          case PLAYER_4:
            currentRow.setRowScore(String.valueOf(points*-1), String.valueOf(points*4), String.valueOf(points*-1), String.valueOf(points*-2));          
            break;
        }
        break;
      case PLAYER_3:
        switch(MahjongTally.CURRENT_LOSER) {
          case PLAYER_1:
            currentRow.setRowScore(String.valueOf(points*-2), String.valueOf(points*-1), String.valueOf(points*4), String.valueOf(points*-1));          
            break;
          case PLAYER_2:
            currentRow.setRowScore(String.valueOf(points*-1), String.valueOf(points*-2), String.valueOf(points*4), String.valueOf(points*-1));
            break;
          case PLAYER_3:
            currentRow.setRowScore(String.valueOf(points*-2), String.valueOf(points*-2), String.valueOf(points*6), String.valueOf(points*-2));
            break;
          case PLAYER_4:
            currentRow.setRowScore(String.valueOf(points*-1), String.valueOf(points*-1), String.valueOf(points*4), String.valueOf(points*-2));          
            break;
        }        
        break;
      case PLAYER_4:
        switch(MahjongTally.CURRENT_LOSER) {
          case PLAYER_1:
            currentRow.setRowScore(String.valueOf(points*-2), String.valueOf(points*-1), String.valueOf(points*-1), String.valueOf(points*4));           
            break;
          case PLAYER_2:
            currentRow.setRowScore(String.valueOf(points*-1), String.valueOf(points*-2), String.valueOf(points*-1), String.valueOf(points*4));
            break;
          case PLAYER_3:
            currentRow.setRowScore(String.valueOf(points*-1), String.valueOf(points*-1), String.valueOf(points*-2), String.valueOf(points*4));
            break;
          case PLAYER_4:
            currentRow.setRowScore(String.valueOf(points*-2), String.valueOf(points*-2), String.valueOf(points*-2), String.valueOf(points*6));
            break;
        }
        break;
      default:
        break;
    }
  }
  private static int convertFan(int fan) {
    int points = 0;
    if(fan>=10) {
      points = 64;
    } else if (fan>=7) {
      points = 32;
    } else if (fan>=4) {
      points = 16;
    } else {
      points = (int)Math.pow(2.0, (double)fan);
    }
    return points;
  }
}
