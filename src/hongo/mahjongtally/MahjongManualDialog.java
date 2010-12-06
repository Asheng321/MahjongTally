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
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;


 public class MahjongManualDialog extends AlertDialog {
  public MahjongManualDialog(Context context) {
    super(context);
    View v = getLayoutInflater().inflate(R.layout.manual_input_dialog, null);
    this.setTitle("Enter Points");
    this.setButton(android.content.DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        try {
          EditText textlabel = (EditText)((Dialog)dialog).findViewById(R.id.value_text);        
          Integer score = Integer.decode(textlabel.getText().toString());
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
