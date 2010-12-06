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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;

 public class MahjongHKInputDialog extends AlertDialog {
 
  public MahjongHKInputDialog(Context context) {
    super(context);
    View v = getLayoutInflater().inflate(R.layout.hk_input_dialog, null);
    Button minus = (Button)v.findViewById(R.id.minus);
    minus.setOnClickListener(new MahjongHKButtonListener());
    Button plus = (Button)v.findViewById(R.id.plus);
    plus.setOnClickListener(new MahjongHKButtonListener());
    Spinner spinner = (Spinner) v.findViewById(R.id.loser_spinner);
    spinner.setOnItemSelectedListener(new MahjongHKSpinnerListener());
    this.setTitle("Enter Fan");
    this.setButton(android.content.DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        try {
          EditText textlabel = (EditText)((Dialog)dialog).findViewById(R.id.fan_textview);
          Integer score;
          if(textlabel.getText().toString().equals("LIMIT")) {
            score = MahjongTally.LIMIT_VALUE;
          } else {
            score = Integer.decode(textlabel.getText().toString());
          }
          MahjongTally.setCurrentPointValue(score);
          MahjongTally.updateScore();
          }
        catch(Exception e) {
          MahjongTally.clearMyFocus();
          dialog.dismiss();
        }        
        MahjongTally.clearMyFocus();
        dialog.dismiss();

      }
    });
    
    this.setButton(android.content.DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
       MahjongTally.clearMyFocus();
       dialog.cancel(); 
      }
    });
    
    this.setView(v);
  }

 }
