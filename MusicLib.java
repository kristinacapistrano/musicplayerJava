package ser321.assign2.lindquis;
import org.json.JSONObject;

/**
 * Copyright (c) 2020 Kristina Capistrano
 * Software Engineering,
 * Arizona State University at the Polytechnic campus
 * <p/>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2
 * of the License.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but without any warranty or fitness for a particular purpose.
 * <p/>
 * Please review the GNU General Public License at:
 * http://www.gnu.org/licenses/gpl-2.0.html
 * see also: https://www.gnu.org/licenses/gpl-faq.html
 * so you are aware of the terms and your rights with regard to this software.
 * Or, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,USA
 * <p/>
 * Purpose: demonstrate using the RMI API
 * a simple serializable employee class including MusicLibrary
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @see <a href="http://pooh.poly.asu.edu/Ser321">Ser321 Home Page</a>
 * @author Kristina Capistrano, kcapist1@asu.edu CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @date    April, 2020
 * @license See above
 */
public class MusicLib implements java.io.Serializable {
    private String fmobj;
    public MusicLib(String obj){
	fmobj = obj;
    }
    public String getFMobj (){
	return fmobj;
    }
    
    
    // private String title;
    // private String artist;
    // private String fileName;
    // private String album;
    // private String rank;
    // private String summary;
    // private String duration;

    // public MusicLib(String title, String artist, String fileName,
    // 		    String album, String rank, String summary, String duration) {
    // 	title = title;
    // 	artist = artist;
    // 	fileName = fileName;
    // 	album = album;
    // 	rank = rank;
    // 	summary = summary;
    // 	duration = duration;
    // }

    // public String getTitle() {
    // 	System.out.println("getTitle: " + title);
    // 	return title;
    // }
    // public String getArtist() {
    // 	return artist;
    // }
    // public String getFileName() {
    // 	fileName = "optional";
    // 	return fileName;
    // }
    // public String getAlbum() {
    // 	return album;
    // }
    // public String getRank() {
    // 	return rank;
    // }
    // public String getSummary() {
    // 	return summary;
    // }
    // public String getDuration() {
    // 	return duration;
    // }
}

