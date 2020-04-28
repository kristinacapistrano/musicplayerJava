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
public class Album extends Object implements Serializable {

    public  String albumName; //string 
    public  String artist; //string
    public  String[] tracks; // string array 
    public  String image; //string
    public  String genre; //optional
    public  String runTime; // string hh:mm:ss
    public  String summary; //string 

    public Album(){
	this.albumName = "";
	this.artist = "";
	this.image = "";
	this.genre = "";
	this.runTime = "";
	this.summary = "";
	this.tracks = new String[100];
    }
    public Album(String album, String artist, String[] tracks,
		 String img, String genre, String time, String summary){
	this.albumName = album;
	this.artist = artist;
	this.tracks = tracks;
	this.image = img;
	this.genre = genre;
	this.runTime = time;
	this.summary = summary;
	this.tracks = tracks;
    }
    public Album(JSONObject o){
	MusicLibrary lib = new MusicLibrary();
	this.albumName = lib.getAlbumName(o);
	this.artist = lib.getArtistName(o);
	this.image = lib.getImage(o);
	this.genre = "Optional";
	this.runTime = lib.getRunTime(o);
	this.summary = lib.getSummary(o);
	this.tracks = lib.getAllSongs(o);
    }
    public void print(){
	System.out.println("Album: " + albumName+
			   "\nArtist: "+ artist+
			   "\nimage: " +image+
			   "\ngenre: "+genre+
			   "\nrunTime: "+runTime+
			   "\nsummary: " +summary);
	for(String x:tracks){System.out.println(x);}
    }

}
