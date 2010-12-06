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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableRow;
import java.lang.CharSequence;

public class MahjongRow extends TableRow {

  public MahjongRow(Context context) {
    super(context);
  }
  
  public MahjongRow(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public void setRowScore(CharSequence value1, CharSequence value2, CharSequence value3) {
    ((MahjongCell)this.getChildAt(0)).setScore(value1);
    ((MahjongCell)this.getChildAt(1)).setScore(value2);
    ((MahjongCell)this.getChildAt(2)).setScore(value3);
  }
  
  public void setRowScore(CharSequence value1, CharSequence value2, CharSequence value3, CharSequence value4) {
    ((MahjongCell)this.getChildAt(0)).setScore(value1);
    ((MahjongCell)this.getChildAt(1)).setScore(value2);
    ((MahjongCell)this.getChildAt(2)).setScore(value3);
    ((MahjongCell)this.getChildAt(3)).setScore(value4);
  }
  
  public void clearRow() {
   int row_size = this.getChildCount();
   for (int i=0; i<row_size; i++) {
    ((MahjongCell)this.getChildAt(i)).clearCell();
   }    
  }
  
} 
