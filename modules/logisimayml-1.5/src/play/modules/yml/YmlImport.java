/**
 *  This file is part of LogiSima.
 *
 *  LogiSima is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  LogiSima is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with LogiSima.  If not, see <http://www.gnu.org/licenses/>.
 */
package play.modules.yml;

import java.beans.PropertyVetoException;
import java.io.File;

import javax.persistence.EntityManager;

import play.Play;
import play.test.Fixtures;

/**
 * Main class for import.
 * 
 * @author bsimard
 * 
 */
public class YmlImport {

    /**
     * Main method !
     * 
     * @param args
     * @throws PropertyVetoException
     */
    public static void main(String[] args) throws Exception {
        // we initiate play! framework
        File root = new File(System.getProperty("application.path"));
        Play.init(root, System.getProperty("play.id", ""));

        // we retrieve parameters
        String filename = "data";
        String input = "conf";
        Boolean reset = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (args[i].startsWith("--filename=")) {
                    filename = args[i].substring(11);
                }
                if (args[i].startsWith("--input=")) {
                    input = args[i].substring(9);
                }
                if (args[i].startsWith("--reset")) {
                    reset = true;
                }
            }
        }

        // get an entityManager to acces play DB
        EntityManager em = YmlExtractorUtil.iniateJPA();
        if (reset) {
            Fixtures.deleteAll();
        }
        Fixtures.load(filename + ".yml");
    }
}
