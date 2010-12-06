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
import android.widget.EditText;
import java.lang.CharSequence;

public class MahjongCell extends EditText {
  private boolean dirty;
  private String scoreValue;
  
  public MahjongCell(Context context) {
    super(context);
    this.setOnFocusChangeListener(new MahjongCellListener());
    //this.setOnClickListener(new MahjongCellListener());
    this.dirty=false;
    this.scoreValue="0";
  }
  
  public MahjongCell(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.setOnFocusChangeListener(new MahjongCellListener());
    //this.setOnClickListener(new MahjongCellListener());
    this.dirty=false;
    this.scoreValue="0";
  }
  public String getScore() {
    return scoreValue; 
  }
  public void setScore(CharSequence text) {
    if (this.dirty==false) {
      this.scoreValue=(String)text;
      this.setText(text);
      this.dirty=true;
      this.clearFocus();
      this.setEnabled(false);
    } 
    else return;
  }
  
  public void clearCell() {
    this.scoreValue="0";
    this.setText("");
    this.dirty=false;
    this.setEnabled(true);
  }
  
  public boolean isDirty() {
    return dirty;
  }
  
}