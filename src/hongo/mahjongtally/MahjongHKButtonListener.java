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
import android.widget.Button;
import android.widget.EditText;

public class MahjongHKButtonListener implements OnClickListener {
    @Override
    public void onClick(View v) {
      Integer id = v.getId();
      View parent = (View)v.getParent();
      EditText textlabel = (EditText)parent.findViewById(R.id.fan_textview);
      String value = textlabel.getText().toString();
      
      switch(id) {
        case R.id.minus:
          if(value.equals("0")) {
           textlabel.setText("LIMIT");
          } else if (value.equals("LIMIT")) {
           textlabel.setText("10");
          } else {
           textlabel.setText(String.valueOf(Integer.valueOf(value)-1));
          }
        break;
        case R.id.plus:
         if(value.equals("10")) {
          textlabel.setText("LIMIT");
         } else if (value.equals("LIMIT")) {
          textlabel.setText("0");
         } else {
          textlabel.setText(String.valueOf(Integer.valueOf(value)+1));
         }        
         break;
      }
    }
}





 
