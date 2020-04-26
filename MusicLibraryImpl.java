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
public class MusicLibraryImpl extends Object implements MusicLibrary{
    public void toJsonFile (String result){
	try {
	    JSONObject fmobj; 
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
	    
    }
    
}
