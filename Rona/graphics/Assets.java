package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
		
	private static BufferedImage sheet;
	
	public static BufferedImage[] bgCity = new BufferedImage[1], decorTitle = new BufferedImage[1],
			decorGameSetup = new BufferedImage[1], buttonNewGame = new BufferedImage[2],
			buttonLoadGame = new BufferedImage[2], buttonExitGame = new BufferedImage[2],
			buttonBackToTitle = new BufferedImage[2], decorShirtTypes = new BufferedImage[1], 
			decorNumberOfPersons = new BufferedImage[1], decorNumberOfPlayers = new BufferedImage[1], 
			numbersDecor = new BufferedImage[10],
			north = new BufferedImage[2], south = new BufferedImage[2],
			east = new BufferedImage[2], west = new BufferedImage[2],
			personList = new BufferedImage[24], buttonBegin = new BufferedImage[2],
			oceanWaves = new BufferedImage[1], woodMenu = new BufferedImage[1],
			personCount = new BufferedImage[1], decorTurns = new BufferedImage[6],
			store = new BufferedImage[2], rouletteFull = new BufferedImage[14],
			buttonPassTurn = new BufferedImage[2], selectAPerson = new BufferedImage[1],
			buttonWalkHere = new BufferedImage[2], buttonCancelPerson = new BufferedImage[2],
			sanitzer = new BufferedImage[2], home = new BufferedImage[2], ronaAnimation = new BufferedImage[8],
			pointsChart = new BufferedImage[1], decorCongratulations = new BufferedImage[1],
			oceanWaveSingle = new BufferedImage[1], buttonPlayAgain = new BufferedImage[2], 
			buttonBackToSetup = new BufferedImage[2], decorBlock = new BufferedImage[1],
			buttonOptions = new BufferedImage[2], decorOptions = new BufferedImage[1],
			buttonSaveGame = new BufferedImage[2], buttonSaveGameAndExit = new BufferedImage[2],
			buttonBackToGame = new BufferedImage[2], dataSaveAnimation = new BufferedImage[11],
			dataLoadAnimation = new BufferedImage[11], decorVictoryScene = new BufferedImage[1];

	public static BufferedImage[][] displayQA = new BufferedImage[10][5];
	
	public static void getAllImages(){
		getBackgrounds();
		getMenuButtons();
		getDecors();
		getPersonSelection();
		getLocations();
		getRonas();
		getRoulette();
		getDatas();
		getFactsQuestions();
	} // end getAllImages()

	public static void getBackgrounds() {
		bgCity[0] = readInImage("res/backgrounds/City.png");
	} // end getBackgrounds()
	
	public static void getMenuButtons() {
		sheet = readInImage("res/buttons/ButtonNewGame.png");
		buttonNewGame[0] = sheet.getSubimage(0, 0, 120, 120);
		buttonNewGame[1] = sheet.getSubimage(120, 0, 120, 120);
		
		sheet = readInImage("res/buttons/ButtonLoadGame.png");
		buttonLoadGame[0] = sheet.getSubimage(0, 0, 120, 120);
		buttonLoadGame[1] = sheet.getSubimage(120, 0, 120, 120);
		
		sheet = readInImage("res/buttons/ButtonExitGame.png");
		buttonExitGame[0] = sheet.getSubimage(0, 0, 120, 120);
		buttonExitGame[1] = sheet.getSubimage(120, 0, 120, 120);
		
		sheet = readInImage("res/buttons/ButtonBackToTitleScreen.png");
		buttonBackToTitle[0] = sheet.getSubimage(0, 0, 240, 240);
		buttonBackToTitle[1] = sheet.getSubimage(240, 0, 240, 240);
		
		sheet = readInImage("res/buttons/ButtonUpArrow.png");
		north[0] = sheet.getSubimage(0, 0, 80, 80);
		north[1] = sheet.getSubimage(80, 0, 80, 80);
		
		sheet = readInImage("res/buttons/ButtonRightArrow.png");
		east[0] = sheet.getSubimage(0, 0, 80, 80);
		east[1] = sheet.getSubimage(80, 0, 80, 80);
		
		sheet = readInImage("res/buttons/ButtonDownArrow.png");
		south[0] = sheet.getSubimage(0, 0, 80, 80);
		south[1] = sheet.getSubimage(80, 0, 80, 80);
		
		sheet = readInImage("res/buttons/ButtonLeftArrow.png");
		west[0] = sheet.getSubimage(0, 0, 80, 80);
		west[1] = sheet.getSubimage(80, 0, 80, 80);
		
		sheet = readInImage("res/buttons/ButtonBeginRace.png");
		buttonBegin[0] = sheet.getSubimage(0, 0, 240, 240);
		buttonBegin[1] = sheet.getSubimage(240, 0, 240, 240);
		
		sheet = readInImage("res/buttons/ButtonPassTurn.png");
		buttonPassTurn[0] = sheet.getSubimage(0, 0, 140, 140);
		buttonPassTurn[1] = sheet.getSubimage(140, 0, 140, 140);
		
		sheet = readInImage("res/buttons/ButtonPlayAgain.png");
		buttonPlayAgain[0] = sheet.getSubimage(0, 0, 240, 240);
		buttonPlayAgain[1] = sheet.getSubimage(240, 0, 240, 240);
		
		sheet = readInImage("res/buttons/ButtonBackToSetup.png");
		buttonBackToSetup[0] = sheet.getSubimage(0, 0, 240, 240);
		buttonBackToSetup[1] = sheet.getSubimage(240, 0, 240, 240);
		
		sheet = readInImage("res/buttons/ButtonOptions.png");
		buttonOptions[0] = sheet.getSubimage(0, 0, 120, 120);
		buttonOptions[1] = sheet.getSubimage(120, 0, 120, 120);
		
		sheet = readInImage("res/buttons/ButtonSaveGame.png");
		buttonSaveGame[0] = sheet.getSubimage(0, 0, 240, 240);
		buttonSaveGame[1] = sheet.getSubimage(240, 0, 240, 240);
		
		sheet = readInImage("res/buttons/ButtonSaveGameAndExit.png");
		buttonSaveGameAndExit[0] = sheet.getSubimage(0, 0, 240, 240);
		buttonSaveGameAndExit[1] = sheet.getSubimage(240, 0, 240, 240);
		
		sheet = readInImage("res/buttons/ButtonBackToGame.png");
		buttonBackToGame[0] = sheet.getSubimage(0, 0, 240, 240);
		buttonBackToGame[1] = sheet.getSubimage(240, 0, 240, 240);
		
	} // end getMenuButtons()
		
	public static void getDecors() {
		decorTitle[0] = readInImage("res/decor/DecorRonaTitle.png");
		decorGameSetup[0] = readInImage("res/decor/DecorGameSetup.png");
		decorShirtTypes[0] = readInImage("res/decor/DecorShirtTypes.png");
		decorNumberOfPersons[0] = readInImage("res/decor/DecorNumberOfPersons.png");
		decorNumberOfPlayers[0] = readInImage("res/decor/DecorNumberOfPlayers.png");
		sheet = readInImage("res/decor/NumbersDecor.png");
		for(int n = 0; n < 10; n++)
			numbersDecor[n] = sheet.getSubimage(18 * n, 0, 18, 22);
		woodMenu[0] = readInImage("res/decor/WoodenMenu.png");
		personCount[0] = readInImage("res/decor/DecorStoreHomePeople.png");
		sheet = readInImage("res/decor/DecorTurn.png");
		for(int n = 0; n < 6; n++)
			decorTurns[n] = sheet.getSubimage(n * 126, 0, 126, 168);
		pointsChart[0] = readInImage("res/decor/PointsChart.png");
		decorCongratulations[0] = readInImage("res/decor/DecorCongratulations.png");
		decorBlock[0] = readInImage("res/decor/DecorBlock.png");
		decorOptions[0] = readInImage("res/decor/DecorOptions.png");
	} // end getDecors()
	
	public static void getPersonSelection() {
		sheet = readInImage("res/persons/PeopleSelections.png");
		int dol = 0;
		for(int m = 0; m < 3; m++) {
			for(int n = 0; n < 6; n++)  personList[dol++] = sheet.getSubimage(120 * n, 120 * m, 120, 120);
		}
		selectAPerson[0] = readInImage("res/persons/SelectAPerson.png");
		
		sheet = readInImage("res/persons/ButtonWalkHere.png");
		buttonWalkHere[0] = sheet.getSubimage(0, 0, 120, 34);
		buttonWalkHere[1] = sheet.getSubimage(120, 0, 120, 34);
		
		sheet = readInImage("res/persons/ButtonCancelPerson.png");
		buttonCancelPerson[0] = sheet.getSubimage(0, 0, 120, 34);
		buttonCancelPerson[1] = sheet.getSubimage(120, 0, 120, 34);
	} // end getpersonSelection()
	
	public static void getLocations() {
		sheet = readInImage("res/locations/Home.png");
		store[0] = sheet.getSubimage(0, 0, 60, 60);
		store[1] = sheet.getSubimage(60, 0, 60, 60);
		
		sheet = readInImage("res/locations/Sanitizer.png");
		sanitzer[0] = sheet.getSubimage(0, 0, 100, 120);
		sanitzer[1] = sheet.getSubimage(0, 0, 60, 60);		// Changed 0 to 60
		
		sheet = readInImage("res/locations/Store.png");
		home[0] = sheet.getSubimage(0, 0, 60, 60);
		home[1] = sheet.getSubimage(60, 0, 60, 60);
	} // end getlocations()
	
	public static void getRoulette() {
		sheet = readInImage("res/roulette/RouletteFULL.png");
		for(int n = 0; n < 14; n++)
			rouletteFull[n] = sheet.getSubimage(n * 280, 0, 280, 140);
	} // end getRoulette()
	
	public static void getRonas() {
		sheet = readInImage("res/rona/RonaAnimation.png");
		for(int n = 0; n < 8; n++)
			ronaAnimation[n] = sheet.getSubimage(n * 120, 0, 120, 120);
	} // end getronas()
	
	public static void getDatas() {
		sheet = readInImage("res/data/DataLoadingAnimation.png");
		for(int n = 0; n < 11; n++)
			dataLoadAnimation[n] = sheet.getSubimage(n * 360, 0, 360, 70);
			
		sheet = readInImage("res/data/DataSavingAnimation.png");
		for(int n = 0; n < 11; n++)
			dataSaveAnimation[n] = sheet.getSubimage(n * 360, 0, 360, 70);
	} // getDatas()
	public static void getFactsQuestions() {
		for(int f = 1; f < 11; f++)	{
				for(int a = 0; a < 5; a++) {
				sheet = readInImage("res/factsquestions/" + "Q" + f + "/" + a + ".png");
				if(a == 0) 
					displayQA[f - 1][a] = sheet.getSubimage(0, 0, 1368, 68);		
				else
					displayQA[f - 1][a] = sheet.getSubimage(0, 0, 683, 58);		
			}
		}
	}
	public static BufferedImage readInImage(String path) {
		try {
			// BufferedImage image = ImageIO.read(ImageIO.class.getResource(path));
			BufferedImage image = ImageIO.read(new File(path));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	} // end readInImage
	
} // end Assets
