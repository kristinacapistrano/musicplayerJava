package ser321.assign2.lindquis;

import java.io.Serializable;
import org.json.JSONObject;


import com.jayway.jsonpath.JsonPath;

/**
 * Copyright 2020 Kristina Capistrano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Purpose: Album is a class whose properties describe a single
 * media work -- song or video/clip.
 * Ser321 Principles of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Kristina Capistrano , kcapist1@asu.edu
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @version April 2020
 */
public class Track extends Object implements Serializable {
    public  String trackName; //string 
    public  String artist; //string
    public  String rankOrder; //int
    public  String duration; //string
    public  String fileName; //optional

    public Track(){
	this.trackName = "";
	this.artist = "";
	this.rankOrder = "";
	this.duration = "";
	this.fileName = "";
    }
    public Track(String trackName,String artist,String rankOrder,String duration,String fileName){
	this.trackName = trackName;
	this.artist = artist;
	this.rankOrder = rankOrder;
	this.duration = duration;
	this.fileName = fileName;
    }
    public Track(JSONObject o,int index){
	MusicLibrary lib = new MusicLibrary();
	this.trackName = lib.getTrackName(o,index);
	this.artist = lib.getArtistName(o);
	this.rankOrder = lib.getRankOrder(o,index);
	this.duration = lib.getDuration(o,index);
	this.fileName = "Optional";
    }
    public void print(){
	System.out.println("PRINTING TRACK OBJECT");
	System.out.println("Track: " + trackName+
			   "\nArtist: "+ artist+
			   "\nRank Order: " +rankOrder+
			   "\nDuration: "+duration+
			   "\nFile Name: " +fileName);
    }
}
