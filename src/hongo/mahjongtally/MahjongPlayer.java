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

public class MahjongPlayer {
  private String name;
  private Integer id;
  private boolean selfPick;
  private static final String SELF_PICK = new String("Self Pick");
  
  MahjongPlayer(String name, Integer id) {
    this.name=name;
    this.id=id;
    this.selfPick=false;
  }
  public Integer getId() {
    return id;
  }
  public void selfPick() {
    this.selfPick=true;
  }
  public String toString() {
    if(this.selfPick){
      return SELF_PICK;
    } else { 
        String discard = new String(name+ " discards");
        return discard;
    }
  }
  
}