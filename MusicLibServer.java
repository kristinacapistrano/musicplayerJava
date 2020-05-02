package ser321.assign2.lindquis;

import java.rmi.*;
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
 * remote interface for the Music Library server.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @see <a href="http://pooh.poly.asu.edu/Ser321">Ser321 Home Page</a>
 * @author Kristina Capistrano- kcapist1@asu.edu CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @date    April, 2020
 * @license See above
 */
public interface MusicLibServer extends Remote {
   public JSONObject getFM (String result) throws RemoteException;
   // public MusicLib addEmp(String name) throws RemoteException;
   // public String[] getNames() throws RemoteException;
}
