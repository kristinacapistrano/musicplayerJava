package ser321.assign2.lindquis;

import java.io.*;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;

import com.jayway.jsonpath.JsonPath;
import java.text.DecimalFormat;

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
 * Purpose: to assist with functionality for music player 
 * Ser321 Principles of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Kristina Capistrano , Kcapist1@asu.edu
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @version April 2020
 */
public class MusicLibrary extends Object implements Serializable {
    public static JSONObject getFM (String result){
	JSONObject fmobj = new JSONObject();
	System.out.println(" ....GETTING FM DATA ") ; 
	try {
	    File myObj = new File("musicfile.json");
	    FileWriter fw = new FileWriter("musicfile.json");
	    if (myObj.createNewFile()) {

		System.out.println("File created: " + myObj.getName());
	    } else {
		System.out.println("File already exists.");
	    }
	    fmobj = new JSONObject(result);
	    fw.write(fmobj.toString(4));
	    fw.flush();
	} catch (IOException e) {
	    System.out.println("An error occurred.");
	    e.printStackTrace();
	} catch (JSONException e) {
	    e.printStackTrace();
	}


	System.out.println("RETURNING THE FM OBJECT " ) ;
	System.out.println(fmobj);
	return fmobj;
    }
    public static String getAlbumName(JSONObject o)throws JSONException{
	JSONObject getalbum = new JSONObject(o.getJSONObject("album").toString());
	String albumname = getalbum.getString("name");
	//System.out.println(albumname);
	return albumname;
    }


    public static String getArtistName(JSONObject o) throws JSONException {
	JSONObject getalbum = new JSONObject(o.getJSONObject("album").toString());    
	String artist = getalbum.getString("artist");
	//System.out.println(artist);
	return artist;
    }

    /**
     * This will go in to the last layer of the nested json album file from lastfm
     * @param x the index wanted from the track array
     * @param data array where index 1 matches the key name of data needed, 
     * 			index 0 is the key_parent where key_wanted is located.
     * @return
     * @throws JSONException
     */
    public static String getTrackInfo(JSONObject o, int x, String data) throws JSONException {
	JSONObject obj = new JSONObject(o.getJSONObject("album").toString());	
	String prepath = "$.tracks.track[";
	String postpath = "].";
	//path - $.tracks.track[1].duration
	String path = prepath + x + postpath + data; 		
	String value = JsonPath.read(obj.toString(), path); //value is the value pair of the key
	//System.out.println(value);
	return value;
    }
    public static String getImage(JSONObject o) throws JSONException {
	JSONObject obj = new JSONObject(o.getJSONObject("album").toString());	
	String prepath = "$.image[";
	String postpath = "].";
	//path - $.tracks.track[1].duration
	String path = prepath + 3 + postpath + "#text"; 		
	String value = JsonPath.read(obj.toString(), path); //value is the value pair of the key
	//System.out.println(value);
	return value;
    }

    public int size(JSONObject o) throws JSONException {
	JSONObject obj = new JSONObject(o.getJSONObject("album").toString());
	JSONObject tracks = new JSONObject(obj.getJSONObject("tracks").toString());
	JSONArray trackarray = tracks.getJSONArray("track");
	int size = trackarray.length();
	//System.out.println("num of songs: " + size);
	return size;
    }
    
    public String[] getAllSongs(JSONObject o) throws JSONException {
	System.out.println("ENTERING");
        String[] songs = new String[this.size(o)];
	for (int i = 0; i< this.size(o); i++){
	    songs[i]= getTrackInfo(o,i,"name");
	}
	return songs;
    }
    public String getRunTime(JSONObject o) throws JSONException {
	DecimalFormat d2 = new DecimalFormat("#.##");
	int total = 0, hours = 0, minutes = 0, seconds = 0;
	int h = 3600, m = 60; 
	String ret = "";
	for (int i  = 0; i < this.size(o); i++ ){
	    total = total + Integer.parseInt(this.getTrackInfo(o,i,"duration"));
	}
	if (total>=3600){
	    hours = total/3600;
	    minutes = (total -(h*hours))/60;
	    seconds =(total -(h*hours))%60;
	    if(seconds <10){
		ret = Integer.toString(hours)+":"+Integer.toString(minutes) +":0"+Integer.toString(seconds);
	    } else {
		ret = Integer.toString(hours)+":"+Integer.toString(minutes) +":"+Integer.toString(seconds);
	    }
	}else {
	    minutes = total /60;
	    seconds =(total-(minutes*m));
	    if (seconds <10){
		ret = Integer.toString(minutes) +":0"+Integer.toString(seconds);
	    } else {
		ret = Integer.toString(minutes) +":"+Integer.toString(seconds);
	    }
	}
	return ret;
    }
    public static String getSummary(JSONObject o) throws JSONException {
	JSONObject obj = new JSONObject(o.getJSONObject("album").toString());	
	String value = JsonPath.read(obj.toString(), "$.wiki.summary"); //value is the value pair of the key
	value =  value.replaceAll("[^A-Za-z0-9 ./,]","");
	System.out.println(value);
	return value;
    }
}
