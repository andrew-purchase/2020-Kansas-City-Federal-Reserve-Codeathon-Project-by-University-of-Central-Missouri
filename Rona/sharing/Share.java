package sharing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Share {
	
	public static boolean loadingSavedData, needToResetGame, needToResetSetup;
	public static int transition, maxPlayers, maxPersons, safetycap;
	public static int[] menuSavedArray, gameSavedArray, playerPersons, homePersons, storePersons, safetyZones;
	
	public Share() {
		// GAME ARRAYS
		playerPersons = new int[5];
		homePersons = new int[5];
		storePersons = new int[5];
		menuSavedArray = new int[6];
		gameSavedArray = new int[346];
		
		// SAFE LOCATIONS
		safetycap = 44;
		safetyZones = new int[]{ 1,  2,  3,  4,  5,  6,  7, 8,  9,
	 							11, 12, 13, 14, 15, 16, 17, 18, 19,
	 							21, 22, 23, 24, 25, 26, 27, 28, 29,
	 							31, 32, 33, 34, 35, 36, 37, 38, 39,
	 							41, 42, 43, 44, 45, 46, 47, 48, 49};

		// GAME CONTROL VARS
		setNeedToResetGame(true);
		setTransition(-1);
		setNeedToResetSetup(true);
		setLoadingSavedData(true);
		
		// READ SAVED FILES
		menuSavedArray = loadFromFile(6, "res/saved/ronaMenu.sav");			// READ FROM SAVED MENU
		gameSavedArray = loadFromFile(346, "res/saved/ronaGame.sav");		// READ FROM SAVED GAME

	} // end Share() Constructor

	public int[] loadFromFile(int arraySize, String fileName) {
		int[] loadedData = new int[arraySize];
		String reading = new String();
		if(new File(fileName).exists()) {
			try {
				BufferedReader readSaved = new BufferedReader(new FileReader(new File(fileName)));
				int readNextLine = 0;
				while ((reading = readSaved.readLine()) != null)
					loadedData[readNextLine++] = Integer.parseInt(reading);
				readSaved.close();
			} catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }	
		}
		else {
			System.out.println("NO FILE FOUND IN: " + fileName);
			System.exit(0);
		}
		return loadedData;
	}
	
	public static void saveToFile(int[] incomingArray, String fileName) {
		if(new File(fileName).exists()) {
			try {
				BufferedWriter writeSaved = new BufferedWriter(new FileWriter(new File(fileName)));
				for(int wri = 0; wri < incomingArray.length; wri++) {
					writeSaved.write(String.valueOf(incomingArray[wri]));
					if(wri < incomingArray.length - 1)
						writeSaved.newLine();
				}
				writeSaved.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
		else {
			System.out.println("NO FILE FOUND IN: " + fileName);
			System.exit(0);
		}
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}

	public static void setMaxPlayers(int mp) {
		Share.maxPlayers = mp;
	}

	public int getMaxPersons() {
		return maxPersons;
	}

	public static void setMaxPersons(int maxPersons) {
		Share.maxPersons = maxPersons;
	}

	public int getTransition() {
		return transition;
	}

	public static void setTransition(int transition) {
		Share.transition = transition;
	}

	public int getPlayerPerson(int num) {
		return playerPersons[num];
	}

	public static void setPlayerPerson(int playerNum, int value) {
		Share.playerPersons[playerNum] = value;
	}

	public boolean isNeedToResetGame() {
		return needToResetGame;
	}

	public static void setNeedToResetGame(boolean needToResetGame) {
		Share.needToResetGame = needToResetGame;
	}

	public int getHomePersons(int od) {
		return homePersons[od];
	}

	public static void setPersons(int od, int sod) {
		Share.homePersons[od] = sod;
	}

	public int getStorePersons(int sd) {
		return storePersons[sd];
	}

	public static void setHomePersons(int sd, int sdd) {
		Share.storePersons[sd] = sdd;
	}

	public int getSafetyZones(int sz) {
		return safetyZones[sz];
	}

	public static void setSafetyZones(int pos, int val) {
		Share.safetyZones[pos] = val;
	}
	
	public static void resetSafetyZones() {
		for(int rsz = 0; rsz <= 8; rsz++)
			Share.safetyZones[rsz] = rsz + 1;
		for(int rsz = 9; rsz <= 17; rsz++)
			Share.safetyZones[rsz] = rsz + 2;
		for(int rsz = 18; rsz <= 26; rsz++)
			Share.safetyZones[rsz] = rsz + 3;
		for(int rsz = 27; rsz <= 35; rsz++)
			Share.safetyZones[rsz] = rsz + 4;
		for(int rsz = 36; rsz <= 44; rsz++)
			Share.safetyZones[rsz] = rsz + 5;
	}
	
	public static void swapSafetyZones(int end, int chosen) {
		int temp = Share.safetyZones[end];
		Share.safetyZones[end] = Share.safetyZones[chosen];
		Share.safetyZones[chosen] = temp;
	}

	public int getSafetycap() {
		return safetycap;
	}

	public static void setSafetycap(int safetycap) {
		Share.safetycap = safetycap;
	}

	public boolean isLoadingSavedData() {
		return loadingSavedData;
	}

	public static void setLoadingSavedData(boolean haveSavedData) {
		Share.loadingSavedData = haveSavedData;
	}

	public int getMenuSavedArray(int position) {
		return menuSavedArray[position];
	}

	public static void setMenuSavedArray(int position, int value) {
		Share.menuSavedArray[position] = value;
	}

	public boolean isNeedToResetSetup() {
		return needToResetSetup;
	}

	public static void setNeedToResetSetup(boolean needToResetSetup) {
		Share.needToResetSetup = needToResetSetup;
	}
	
} // end class Share
