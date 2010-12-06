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
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Mahjong3KJButtonListener implements OnClickListener {
  @Override
  public void onClick(View v) {
      Integer id = v.getId();
      View parent = (View)v.getParent().getParent();
      EditText textlabel = (EditText)parent.findViewById(R.id.value_text);
    switch(id){
      case R.id.button1: 
        textlabel.append("1");
        break;
      case R.id.button2:
        textlabel.append("2");
        break;
      case R.id.button3:
        textlabel.append("3");
        break;
      case R.id.button4:
        textlabel.append("4");
        break;
      case R.id.button5:
        textlabel.append("5");
        break;
      case R.id.button6:
        textlabel.append("6");
        break;
      case R.id.button7:
        textlabel.append("7");
        break;
      case R.id.button8:
        textlabel.append("8");
        break;
      case R.id.button9:
        textlabel.append("9");
        break;
      case R.id.button0:
        textlabel.append("0");
        break;
      case R.id.buttonlimit:
        textlabel.setText("LIMIT");
        break;
      case R.id.button_clear:
        textlabel.setText("");
        break;
      case R.id.button_minus:
        textlabel.append("-");
        break;
      default:
        break;
    }
  }
}





 
 
