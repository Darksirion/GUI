package Core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurationReader {
	private static final String FILE_NAME = "settings.config";
	private static final String NO_MARKER_MATCHED = "Angeforderte Variable konnte nicht gefunden werden";

	/**
	 * Druckt eine Beispieldatei aus in die nur noch die Werte eingetragen werden muessen
	 */
	public static void createTemplate() {
		try {
			File f = new File(FILE_NAME);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bfw = new BufferedWriter(fw);

			// Hier alle Variablen eintragen nach denen gesucht werden soll
			// Achtet darauf, dass sie hier den selben Namen haben muessen wie unten im Getter (+ "=\n")
			bfw.write("language=");
			bfw.newLine();

			bfw.write("targetDisplacement=");
			bfw.newLine();

			// ========

			bfw.flush();
			bfw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sucht nach einer Variable mit angegebenem Namen in einer Datei und gibt dessen Wert als String zurueck
	 * 
	 * @param marker Der Variablenname nach dem gesucht werden soll
	 * @return Der Wert der Variablen
	 * @throws Exception Wenn die Variable nicht gefunden werden konnte
	 */
	private static String getValue(String marker) throws Exception {
		File f = new File(FILE_NAME);
		FileReader fr = new FileReader(f);
		BufferedReader bfr = new BufferedReader(fr);

		String line = bfr.readLine();
		String[] token;

		while (line != null) {
			line.trim();
			token = line.split("=", 2);

			if (token[0].compareTo(marker) == 0) {
				bfr.close();
				return token[1];
			}

			line = bfr.readLine();
		}

		bfr.close();
		throw new Exception(NO_MARKER_MATCHED);
	}

	// ================================================
	// ==== Hier kommen alle Variablen-Reader rein ====
	// ================================================

	public static int getLanguage() throws Exception {
		return Integer.parseInt(getValue("language"));
	}

	public static int getTargetDisplacement() throws Exception {
		return Integer.parseInt(getValue("targetDisplacement"));
	}

}
