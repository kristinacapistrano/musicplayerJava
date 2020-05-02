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
public class MusicLibrary implements Serializable {
    private Hashtable<String,Album> aLib;

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

    public String size(JSONObject o) throws JSONException {
	JSONObject obj = new JSONObject(o.getJSONObject("album").toString());
	JSONObject tracks = new JSONObject(obj.getJSONObject("tracks").toString());
	JSONArray trackarray = tracks.getJSONArray("track");
	int size = trackarray.length();
	//System.out.println("num of songs: " + size);
	return String.valueOf(size);
    }
    
    public ArrayList<String> getAllSongs(JSONObject o) throws JSONException {
	System.out.println("ENTERING - getAllSongs function from MusicLibrary class");
        ArrayList<String> songs = new ArrayList();
	for (int i = 0; i< Integer.parseInt(this.size(o)); i++){
	    songs.add(getTrackInfo(o,i,"name"));
	}
	return songs;
    }
    public String getRunTime(JSONObject o) throws JSONException {
	DecimalFormat d2 = new DecimalFormat("#.##");
	int total = 0, hours = 0, minutes = 0, seconds = 0;
	int h = 3600, m = 60; 
	String ret = "";
	for (int i  = 0; i < Integer.parseInt(this.size(o)); i++ ){
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
	String value = JsonPath.read(obj.toString(), "$.wiki.summary"); //value is the value pair of the key(summary)
	value =  value.replaceAll("[^A-Za-z0-9 ./,]","");
	return value;
    }
    //----------track info getter -----------------//
    public String getTrackName(JSONObject o, int index){
	String trackName = getTrackInfo(o,index,"name");
	return trackName;
    }

    public String getRankOrder(JSONObject o, int index){
	String[] rank = {"@attr","rank"}; 
    	JSONObject obj = new JSONObject(o.getJSONObject("album").toString());	
    	String prepath = "$.tracks.track[";
        String postpath = "].";
	String path = prepath + index + postpath + rank[0] + "." + rank[1]; 
        String value = JsonPath.read(obj.toString(), path);      
        return value;

    }
    public String getDuration(JSONObject o, int index){
	DecimalFormat d2 = new DecimalFormat("#.##");
	int minutes = 0, seconds = 0;
	int h = 3600, m = 60; 
	String ret = "";
	int duration = Integer.parseInt(this.getTrackInfo(o,index,"duration"));
	minutes = duration /60;
	seconds =(duration-(minutes*m));
	if (seconds <10){
	    ret = Integer.toString(minutes) +":0"+Integer.toString(seconds);
	} else {
	    ret = Integer.toString(minutes) +":"+Integer.toString(seconds);
	}
	return ret;
    }
    //------------create json file with only data needed -------//
    public void saveFile(JSONObject o) throws JSONException, FileNotFoundException{
	String _new = "" ;
	JSONObject oo = new JSONObject();
	try {
	    	removeLastChar();

	    FileWriter fw = new FileWriter("music.json",true);
	    
	    PrintWriter pw = new PrintWriter(fw); 
	    for ( int i = 0; i < Integer.parseInt(this.size(o)); i++) {
		Map<String,String> music = new LinkedHashMap<String, String>(5); 
		music.put("album", this.getAlbumName(o));
		music.put("artist", this.getArtistName(o));
		music.put("track", this.getTrackName(o,i));
		music.put("duration", this.getDuration(o,i));
		music.put("rank", this.getRankOrder(o,i));
		music.put("fileName", "optional");
		music.put("summary", this.getSummary(o));
		music.put("image",this.getImage(o));
		oo.put(this.getTrackName(o,i),music); //get name of song to be the object name per song

	    }
	    String _old = oo.toString(1);
	    _new = _old.substring(1,_old.length());
	    pw.print(_new);
	    pw.flush(); 
	    pw.close();
	}catch (FileNotFoundException e){
	    System.out.println(e);
	} catch (IOException ee ) {
	    System.out.println(ee);
	}
	System.out.println("Returning newly added album: " + _new);
    }
    public void removeLastChar()throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("music.json"));
	String str = "";
	try {
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
		sb.append("\n" +line);
		line = br.readLine();
	    }
	    str = sb.toString();
	    str = str.substring(0,str.length()-2) + ",";
	} finally {
	    br.close();
	}
        try {
	    FileWriter myWriter = new FileWriter("music.json");
	    myWriter.write(str);
	    myWriter.close();
	} catch (IOException e) {
	    System.out.println("An error occurred.");
	    e.printStackTrace();
	}
    }
    public Album get(String mediaTitle){
	Album result = null;
	try{
	    result = aLib.get(mediaTitle);
	}catch(Exception ex){
	    System.out.println("exception in get: "+ex.getMessage());
	}
	return result;
    }
}
