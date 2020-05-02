package ser321.assign2.lindquis;

import java.rmi.server.*;
import java.rmi.*;
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
 * Implementation of Music Library server - create a remote server object 
 * Register the remote server object with the rmi registry.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @see <a href="http://pooh.poly.asu.edu/Ser321">Ser321 Home Page</a>
 * @author Kristina Capistrano - kcapist1 CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @date    April, 2020
 * @license See above
 */
class MusicLibImpl extends UnicastRemoteObject implements MusicLibServer {
    public MusicLibImpl() throws RemoteException{
	MusicLib fm = new MusicLib("result");
    }
   public  JSONObject getFM (String result) throws RemoteException {
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
   public static void main(String args[]) {
      try {
         String hostId="localhost";
         String regPort="1099";
         if (args.length >= 2){
	    hostId=args[0];
            regPort=args[1];
         }
    
         MusicLibServer obj = new MusicLibImpl();
         Naming.rebind("rmi://"+hostId+":"+regPort+"/MusicLibraryServer", obj);
         System.out.println("Server bound in registry as: "+
                            "rmi://"+hostId+":"+regPort+"/MusicLibraryServer");
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
 
}
