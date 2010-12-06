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
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import android.util.Log;
import android.content.res.Resources;

public class MahjongTally extends Activity
{
  public static int CURRENT_ROW_ID;
  public static int CURRENT_COLUMN_ID;
  public static int CURRENT_POINT_VALUE;
  public static int CURRENT_WINNER;
  public static int CURRENT_LOSER;
  public static int GAME_TYPE;
  public static int NUMBER_OF_PLAYERS;
  public static int INPUT_DIALOG_ID;
  public static int LIMIT_VALUE;
  public static LinkedList<Integer> HISTORY = new LinkedList<Integer>();
  public static Activity MY_APP; 
  
  public static final int DIALOG_3KJ_ID=0;
  public static final int DIALOG_EXIT_ID=1;
  public static final int DIALOG_HK_ID=2;
  public static final int DIALOG_MANUAL_ID=4;
  public static final int SCORING_NONE=0;
  public static final int SCORING_HK_OLD=1;
  public static final int SCORING_3KJ_PLAYER=2;
  public static final int SCORING_3_NONE=4;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        MY_APP=this;
        GAME_TYPE = Integer.valueOf(settings.getString("scoring", "1"));
        LIMIT_VALUE = Integer.valueOf(settings.getString("limitValue", "64"));
        
        
        switch (GAME_TYPE) {
          case SCORING_HK_OLD:
            NUMBER_OF_PLAYERS=4;
            INPUT_DIALOG_ID=DIALOG_HK_ID;
            setContentView(getLayoutInflater().inflate(R.layout.fourplayerlayout, null));
            break;
          case SCORING_NONE:
            NUMBER_OF_PLAYERS=4;
            INPUT_DIALOG_ID=DIALOG_MANUAL_ID;
            setContentView(getLayoutInflater().inflate(R.layout.fourplayerlayout, null));
            break;
          case SCORING_3KJ_PLAYER:
            NUMBER_OF_PLAYERS=3;
            INPUT_DIALOG_ID=DIALOG_3KJ_ID;
            setContentView(getLayoutInflater().inflate(R.layout.threeplayerlayout, null));
            break;
          case SCORING_3_NONE:
            NUMBER_OF_PLAYERS=3;
            INPUT_DIALOG_ID=DIALOG_MANUAL_ID;
            setContentView(getLayoutInflater().inflate(R.layout.threeplayerlayout, null));
            break;
          default:
            break;
        }
        
        TextView temp_textview;
        String p1_name = settings.getString("player1", "Player1");
        temp_textview = (TextView)findViewById(R.id.player1_name);
        temp_textview.setText(p1_name);
        String p2_name = settings.getString("player2", "Player2");
        temp_textview = (TextView)findViewById(R.id.player2_name);
        temp_textview.setText(p2_name);
        String p3_name = settings.getString("player3", "Player3");
        temp_textview = (TextView)findViewById(R.id.player3_name);
        temp_textview.setText(p3_name);
        
        if(NUMBER_OF_PLAYERS==4) {
          String p4_name=settings.getString("player4", "Player4");
          temp_textview = (TextView)findViewById(R.id.player4_name);
          temp_textview.setText(p4_name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.main_menu, menu);
      return true;
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
      super.onWindowFocusChanged(hasFocus);
      if(Config.LOGGING) {
        View v = getCurrentFocus();
        Log.d("MAIN", getResources().getResourceEntryName(v.getId()));
        if(hasFocus) {
          Log.d("MAIN", "this window is gaining focus");
        } else {
        Log.d("MAIN", "this window is losing focus");
        }
      }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      // Handle item selection
      switch (item.getItemId()) {
        case R.id.new_game:
          newGame();
          return true;
        case R.id.undo:
          undoLastRow();
          return true;
        case R.id.settings:
          Intent settingsActivity = new Intent(getBaseContext(), MahjongSettings.class);
          startActivity(settingsActivity); 
          return true;
       case R.id.new_page:
          newPage();
          return true;
        default:
          return super.onOptionsItemSelected(item);
      }
    }
  
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      //Handle the back button    
      if(keyCode == KeyEvent.KEYCODE_BACK) {
        showDialog(DIALOG_EXIT_ID);
        return true;
      }
      else {
        return super.onKeyDown(keyCode, event);
      }
    }
     
    protected Dialog onCreateDialog(int id) {
      Dialog dialog;
      switch(id) {
        case DIALOG_3KJ_ID:
          dialog= new Mahjong3KJDialog(this);
          break;
        case DIALOG_HK_ID:
          dialog = new MahjongHKInputDialog(this);
          break;
        case DIALOG_MANUAL_ID:
          dialog = new MahjongManualDialog(this);
          break;
        case DIALOG_EXIT_ID:
          AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setMessage("Are you sure you want to exit?")
                 .setCancelable(false)
                 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      finish();
                    }
                 })
                 .setNegativeButton("No", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int id) {
                  dialog.cancel();
                 }
                 });
         dialog = (Dialog)builder.create();
         break;
        default:
          dialog = null;
          break;
      }
      return dialog;
    }
    
    protected void onPrepareDialog(int id, Dialog dialog) {
      EditText temp_editText;
      switch(id) {
        case DIALOG_3KJ_ID:
          temp_editText = (EditText)dialog.findViewById(R.id.value_text);
          temp_editText.setText("");
          break;
        case DIALOG_MANUAL_ID:
          temp_editText = (EditText)dialog.findViewById(R.id.value_text);
          temp_editText.setText("");
          break;
        case DIALOG_HK_ID:
          ((EditText)dialog.findViewById(R.id.fan_textview)).setText("3");
          temp_editText = (EditText)findViewById(R.id.player1_name);
          String player1name = temp_editText.getText().toString();
          MahjongPlayer p1 = new MahjongPlayer(player1name, 0);
          temp_editText = (EditText)findViewById(R.id.player2_name);
          String player2name = temp_editText.getText().toString();
          MahjongPlayer p2 = new MahjongPlayer(player2name, 1);
          temp_editText = (EditText)findViewById(R.id.player3_name);
          String player3name = temp_editText.getText().toString();
          MahjongPlayer p3 = new MahjongPlayer(player3name, 2);
          temp_editText = (EditText)findViewById(R.id.player4_name);
          String player4name = temp_editText.getText().toString();
          MahjongPlayer p4 = new MahjongPlayer(player4name, 3);
          ArrayList<MahjongPlayer> mySpinnerData = new ArrayList<MahjongPlayer>();
          switch(CURRENT_WINNER) {
            case 0:
              p1.selfPick();
              mySpinnerData.add(p1);
              mySpinnerData.add(p2);
              mySpinnerData.add(p3);
              mySpinnerData.add(p4);
              break;
            case 1:
              p2.selfPick();
              mySpinnerData.add(p2);
              mySpinnerData.add(p1);
              mySpinnerData.add(p3);
              mySpinnerData.add(p4);
              break;
            case 2:
              p3.selfPick();
              mySpinnerData.add(p3);
              mySpinnerData.add(p1);
              mySpinnerData.add(p2);
              mySpinnerData.add(p4);
              break;
            case 3:
              p4.selfPick();
              mySpinnerData.add(p4);
              mySpinnerData.add(p1);
              mySpinnerData.add(p2);
              mySpinnerData.add(p3);
              break;
            default:
              break;
          }
          
          ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item, mySpinnerData);
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          Spinner mySpinner = (Spinner)dialog.findViewById(R.id.loser_spinner);
          mySpinner.setAdapter(adapter);
          break;
        default:
          break;
     }
    }
     
    public static void setCurrentColumn(int id) {
      CURRENT_COLUMN_ID=id;
    }
    
    public static void setCurrentRow(int id) {
      CURRENT_ROW_ID = id;
    }
    
    public static void setCurrentWinner(int player) {
      CURRENT_WINNER=player;
    }

    public static void setCurrentLoser(int player) {
      CURRENT_LOSER=player;
    }
    
    public static void setCurrentPointValue(int value) {
      CURRENT_POINT_VALUE=value;
    }
    
    public static void showInputDialog() {
      MY_APP.showDialog(INPUT_DIALOG_ID);
    }
    
    public static void clearMyFocus() {
      View mytable = MY_APP.findViewById(R.id.my_table);
      //mytable.findFocus().clearFocus();
    }
    
    public static void updateScore() {
        try {
          switch(GAME_TYPE) {
            case SCORING_HK_OLD:
              RulesHKOld.calculate();
              break;
            case SCORING_3KJ_PLAYER:   
              Rules3PlayerKJ.calculate();
              break;
            case SCORING_NONE:
              RulesNone.calculate();
              break;
            case SCORING_3_NONE:
              RulesNone.calculate();
              break;
           default:
              break;
          }
          HISTORY.add(CURRENT_ROW_ID);
          if(Config.LOGGING) {
            Log.d("HISTORY", "Adding to History "+MY_APP.getResources().getResourceEntryName(CURRENT_ROW_ID));
          }
          refreshScore();
        }
        catch (Exception e) {}
    }
    
    public static void refreshScore() {
          LinkedList <Integer>player1 = new LinkedList<Integer>();
          LinkedList <Integer>player2 = new LinkedList<Integer>();
          LinkedList <Integer>player3 = new LinkedList<Integer>();
          LinkedList <Integer>player4 = new LinkedList<Integer>();
          TableLayout myTable = (TableLayout)MY_APP.findViewById(R.id.my_table);
          
          int count = 1;
          while(count<=(myTable.getChildCount()-2)) {
            TableRow temp_row = (TableRow) myTable.getChildAt(count);
            try {
              MahjongCell temp_cell1 = (MahjongCell) temp_row.getChildAt(0);
              MahjongCell temp_cell2 = (MahjongCell) temp_row.getChildAt(1);
              MahjongCell temp_cell3 = (MahjongCell) temp_row.getChildAt(2);
              player1.add(Integer.decode(temp_cell1.getScore()));
              player2.add(Integer.decode(temp_cell2.getScore()));
              player3.add(Integer.decode(temp_cell3.getScore()));
              if (NUMBER_OF_PLAYERS==4) {
                MahjongCell temp_cell4 = (MahjongCell) temp_row.getChildAt(3);
                player4.add(Integer.decode(temp_cell4.getScore()));
              }
            }
            catch (Exception e) {
              
            }
            count++;
          }
         
          int player1_score=0;
          int player2_score=0;
          int player3_score=0;
          int player4_score=0;
          
          Iterator myiterator = player1.iterator();
          while(myiterator.hasNext()) {
            player1_score+=(Integer)myiterator.next();
          }
          TextView scoreboard1 = (TextView)myTable.findViewById(R.id.player1_score);
          scoreboard1.setText(String.valueOf(player1_score));
          myiterator = player2.iterator();
          
          while(myiterator.hasNext()) {
            player2_score+=(Integer)myiterator.next();
          }
          TextView scoreboard2 = (TextView)myTable.findViewById(R.id.player2_score);
          scoreboard2.setText(String.valueOf(player2_score));
          
          myiterator = player3.iterator();
          while(myiterator.hasNext()) {
            player3_score+=(Integer)myiterator.next();
          }
          TextView scoreboard3 = (TextView)myTable.findViewById(R.id.player3_score);
          scoreboard3.setText(String.valueOf(player3_score));
          if(NUMBER_OF_PLAYERS==4) {
            myiterator = player4.iterator();
            while(myiterator.hasNext()) {
            player4_score+=(Integer)myiterator.next();
            }
            TextView scoreboard4 = (TextView)myTable.findViewById(R.id.player4_score);
            scoreboard4.setText(String.valueOf(player4_score));
          }
    }
    private void newGame() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    
    private void newPage() {
      if(HISTORY.isEmpty()==false) {
        TextView temp_textview;
        temp_textview = (TextView)findViewById(R.id.player1_score);
        CharSequence player1_score = temp_textview.getText();
        temp_textview = (TextView)findViewById(R.id.player2_score);
        CharSequence player2_score = temp_textview.getText();
        temp_textview = (TextView)findViewById(R.id.player3_score);
        CharSequence player3_score = temp_textview.getText();
        CharSequence player4_score="";
        if(NUMBER_OF_PLAYERS==4) {
          temp_textview = (TextView)findViewById(R.id.player4_score);
          player4_score = temp_textview.getText();            
        }
        Iterator myIterator = HISTORY.iterator();
        while (HISTORY.isEmpty()==false) {
          undoLastRow();
        }
        if(NUMBER_OF_PLAYERS==4) {
          MahjongRow firstRow = (MahjongRow)findViewById(R.id.row_4_1);
          firstRow.setRowScore(player1_score, player2_score, player3_score, player4_score);
          HISTORY.add(R.id.row_4_1);
          CURRENT_ROW_ID=R.id.row_4_1;
          if(Config.LOGGING) {
            Log.d("HISTORY", "Adding to History "+getResources().getResourceEntryName(R.id.row_4_1));
          }
        } else {
          MahjongRow firstRow = (MahjongRow)findViewById(R.id.row_3_1);
          firstRow.setRowScore(player1_score, player2_score, player3_score);
          HISTORY.add(R.id.row_3_1);
          CURRENT_ROW_ID=R.id.row_3_1;
          if(Config.LOGGING) {
            Log.d("HISTORY", "Adding to History "+getBaseContext().getResources().getResourceEntryName(R.id.row_3_1));
          }
        }
        refreshScore();
      }
    }
    
    private void undoLastRow() {
    
       if(HISTORY.isEmpty()==false) {
        try {
          Integer id = HISTORY.removeLast();
          if(Config.LOGGING) {
            Log.d("HISTORY","Undoing from History"+getBaseContext().getResources().getResourceEntryName(id));
          }
          MahjongRow lastRow = (MahjongRow)findViewById(id);
          lastRow.clearRow();
          refreshScore();
          }
        catch (Exception e) {}
      }
    }
}
