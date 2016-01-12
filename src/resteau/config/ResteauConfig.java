package resteau.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ResteauConfig {

	static File file = new File("Resteau.properties");
	static String defaultCom = "#Le numero du retaurant dans le BD qui utilise l'application courente\r\n",
			defaultConf = "idR = ";

	public static void main(String[] args) throws FileNotFoundException {
		createConfig(1);
		if (getResteauID() == 1) {
			System.out.println("ok");
		}
	}

	public static boolean isSet() {
		Scanner scan;
		try {
			scan = new Scanner(file);
			String line = null;
			String[] arg = null;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				if (line.contains("idR")) {
					arg = line.split("=");
					arg[1] = arg[1].replaceAll("\\s", "");
					if (arg.length == 2) {
						return (Integer.parseInt(arg[1]) > 0);
					}
				}
			}
			return false;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public static boolean createConfig(int id) {
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(writer);
			String config = defaultCom + defaultConf + id;
			out.write(config);
			out.flush();
			out.close();
			return isSet();
		} catch (IOException e) {
			return false;
		}
	}

	public static int getResteauID() throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String line = null;
		String[] arg = null;
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.contains("idR")) {
				arg = line.split("=");
				arg[1] = arg[1].replaceAll("\\s", "");
				if (arg.length == 2) {
					return Integer.parseInt(arg[1]);
				}
			}
		}
		return -1;
	}

	public static boolean addLine(String line) {
		FileWriter writer;
		try {
			writer = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(writer);
			out.write(line);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static boolean contains(String value) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String line = null;
		String[] arg = null;
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.contains(value)) {
				return true;
			}
		}
		return false;
	}
}
