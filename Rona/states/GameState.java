package states;

import java.awt.Graphics;
import java.util.Random;

import graphics.Assets;
import input.MouseManager;
import sharing.Share;
import ui.Home;
import ui.Sanitizer;
import ui.Store;
import ui.Button;
import ui.DataShow;
import ui.Decor;
import ui.Person;
import ui.PersonSelector;
import ui.FloatingButton;
import ui.Roulette;
import ui.Score;
import ui.Scroll;
import ui.Rona;
import ui.Turns;
import ui.FactsAndQuestions;

public class GameState extends State {

	private Home[] home;
	private Sanitizer[] sanitizer;
	private Store[] store;
	public static Button optionsButton;
	public static Button passTurn;
	public static Roulette roulette;
	private Button[] movementButton;
	private DataShow saveData;
	private Decor woodMenu, pointsChart, decorOptions, congrats, decorVictoryScene;
	private Decor[] personCount;
	private Person[] Person;
	private PersonSelector[] ds;
	private FloatingButton backToSetupButton, saveGameButton, saveAndExitButton, backToGameButton, exitGameButton;
	private Score walkPoints, ronaPoints;
	private Score[] PersonsOut, PersonsIn;
	private Scroll bgCity;
	private Rona[] Ronas;
	public static Turns turns;
	private FactsAndQuestions FactsAndQuestions;

	public int winner;
	public boolean optionsAvaliable, savingData, exitGame;
	public MouseManager m;
	public State[] state;
	public static Share share;
	public Random r;
	public boolean showQuestion;
	
	final int[] WALKDX = {0,    0,  120, 120, 120,   0, -120, -120, -120};
	final int[] WALKDY = {0, -120, -120,   0, 120, 120,  120,    0, -120};
	
	public GameState(MouseManager m, State[] state) {
		this.m = m;
		this.state = state;
		this.optionsAvaliable = false;
		this.savingData = false;
		this.exitGame = false;
		this.r = new Random();
		this.winner = 0;
		this.showQuestion = false;
		
		// Share Class
		share = new Share();
		
		// SCROLL
		bgCity = new Scroll(0, 0, 1440, 768, true, Assets.bgCity, "Right", 0);
		
		// DECOR
		woodMenu = new Decor(0, 600, 1366, 168, true, Assets.woodMenu);
		personCount = new Decor[5];
		for(int og = 1; og <= 4; og++)
			personCount[og] = new Decor(-116 + (130 * og), 705, 86, 60, true, Assets.personCount);
		pointsChart = new Decor(880, 614, 210, 140, true, Assets.pointsChart);
		decorOptions = new Decor(447, 60, 472, 80, true, Assets.decorOptions);
		congrats = new Decor(177, 120, 1012, 72, true, Assets.decorCongratulations);
		decorVictoryScene = new Decor(0, 468, 1366, 300, true, Assets.decorVictoryScene);
		
		// TURNS
		turns = new Turns(7, 600, 126, 168, true, Assets.decorTurns);

		// BUTTONS
		optionsButton = new Button(1241, 628, 120, 120, true, Assets.buttonOptions);
		passTurn = new Button(1095, 614, 140, 140, true, Assets.buttonPassTurn);
		movementButton = new Button[9];
		movementButton[0] = new Button(-120, -120, 120, 34, false, Assets.buttonCancelPerson);
		for(int mb = 1; mb <= 8; mb++)
			movementButton[mb] = new Button(-120, -120, 120, 34, false, Assets.buttonWalkHere);
		
		// LOCATIONS
		home = new Home[5];
		for(int stb = 0; stb <= 4; stb++)
			home[stb] = new Home(30, -99 + (120 * (stb + 1) ), 60, 60, true, Assets.store);
		sanitizer = new Sanitizer[45];
		for(int stb = 0; stb <= 44; stb++)
			sanitizer[stb] = new Sanitizer(-120, -120, 60, 60, false, Assets.sanitzer);
		store = new Store[5];
		for(int stb = 0; stb <= 4; stb++)
			store[stb] = new Store(1200, -99 + (120 * (stb + 1) ), 60, 60, true, Assets.home);
		
		// PERSONSELECTOR
		ds = new PersonSelector[5];
		ds[0] = new PersonSelector(503, 550, 360, 180, true, Assets.personList, 0);
		for(int d = 1; d <= 4; d++)
			ds[d] = new PersonSelector( -90 + (130 * d), 640, 60, 60, true, Assets.personList, d);
		
		// SCORE
		PersonsOut = new Score[5];
		PersonsIn = new Score[5];
		for(int dido = 1; dido <= 4; dido++) {
			PersonsOut[dido] = new Score(-61 + (130 * dido), 703, 18, 22, true, Assets.numbersDecor, share.getHomePersons(dido));
			PersonsIn[dido] = new Score(-61 + (130 * dido), 745, 18, 22, true, Assets.numbersDecor, share.getHomePersons(dido));
		}
		walkPoints = new Score(960, 647, 18, 22, true, Assets.numbersDecor, 0);
		ronaPoints = new Score(960, 717, 18, 22, true, Assets.numbersDecor, 0);
		
		// ROULETTE
		roulette = new Roulette(580, 614, 280, 140, true, Assets.rouletteFull);
		
		// PERSONS
		int dolp = 0, play = 1;
		Person = new Person[20];
		for(int u = 1; u <= 4; u++) {
			for(int v = 1; v <= 5; v++) {
				Person[dolp] = new Person(-120, -120, 120, 120, true, Assets.personList, Assets.selectAPerson, share.getPlayerPerson(play), play);
				dolp++;
			}
			play++;
		}
		
		// RONAS
		Ronas = new Rona[5];
		for(int sh = 0; sh <= 4; sh++)
			Ronas[sh] = new Rona(-120, -120, 120, 120, false, Assets.ronaAnimation);
		
		//FLOATING BUTTONS
		backToSetupButton = new FloatingButton(63, 232, 240, 240, true, Assets.buttonBackToTitle);
		saveGameButton = new FloatingButton(313, 240/*210*/, 240, 240, true, Assets.buttonSaveGame);
		saveAndExitButton = new FloatingButton(563, 210, 240, 240, true, Assets.buttonSaveGameAndExit);
		backToGameButton = new FloatingButton(813, 240, 240, 240, true, Assets.buttonBackToGame);
		exitGameButton = new FloatingButton(1063, 250/*200*/, 240, 240, true, Assets.buttonExitGame);

		// FACTS AND QUESTIONS
		FactsAndQuestions = new FactsAndQuestions(0, 0, 0, 0, true, Assets.displayQA);
		
		//DATASHOW
		saveData = new DataShow(503, 480, 360, 240, false, Assets.dataSaveAnimation);
	}
	
	public void ResetGame() {
		if(share.isNeedToResetGame()) {
			// Reset Options
			optionsAvaliable = false;
			
			// Reset Max Players and Persons
			Share.setMaxPlayers(share.getMaxPlayers());
			Share.setMaxPersons(share.getMaxPersons());
			
			// Reset Person colors from menu saved
			for(int u = 1; u <= 4; u++)
				Share.setPlayerPerson(u, share.getMenuSavedArray(u + 1));
			
			// Reset player to 0 to randomly select first player, and num to reset animation
			turns.setPlayer(0);
			turns.setFrame(0);
			turns.setGlow("Up");
						
			// Reset all movement buttons
			for(int mb = 0; mb < 9; mb++) {
				movementButton[mb].setSpecial(-1);
				movementButton[mb].setVisible(false);
				movementButton[mb].teleport(-120, -120);
			}
			
			// Reset Start locations
			for(int rstb = 0; rstb <= 4; rstb++) {
				home[rstb].setX(30);
				home[rstb].setY(-99 + (120 * (rstb + 1) ));
			}
			
			// Reset Safety locations and associated vars
			for(int stb = 0; stb <= 44; stb++) {
				sanitizer[stb].teleport(-120, -120);
				sanitizer[stb].setRealX(-120);
				sanitizer[stb].setRealY(-120);
				sanitizer[stb].setFloatTime(0);
				sanitizer[stb].setFloatDirection("Up");
				sanitizer[stb].setVisible(false);
			}
			Share.resetSafetyZones();
			Share.setSafetycap(44);
			
			// Reset Finish
			for(int stb = 0; stb <= 4; stb++)
				store[stb].teleport(1200, -99 + (120 * (stb + 1) ));
			
			// Reset all Person selectors and scoreboard values
			for(int u = 1; u <= 4; u++) {
				ds[u].setPosition(share.getPlayerPerson(u));
				if(u <= share.getMaxPlayers()) {
					ds[u].setVisible(true);
					personCount[u].setVisible(true);
					PersonsOut[u].setVisible(true);
					PersonsIn[u].setVisible(true);
					Share.setPersons(u, share.getMaxPersons());
				}
				if(u > share.getMaxPlayers()) {
					ds[u].setVisible(false);
					personCount[u].setVisible(false);
					PersonsOut[u].setVisible(false);
					PersonsIn[u].setVisible(false);
					Share.setPersons(u, 0);
				}
				Share.setHomePersons(u, 0);
				PersonsIn[u].setValue(0);
			}

			// Reset walk and rona points
			walkPoints.setValue(0);
			ronaPoints.setValue(0);
			
			// Reset roulette entirely
			roulette.setNum(0);
			roulette.setSpinning(0);
			roulette.setWalk(0);
			roulette.setRona(0);
			roulette.setShowRona(false);
			
			// Reset all persons
			int dolp = 0;
			for(int u = 1; u <= 4; u++) {
				for(int v = 1; v <= 5; v++) {
					Person[dolp].setPosition(share.getPlayerPerson(u));
					Person[dolp].teleport(-120, -120);
					Person[dolp].setInStore(false);
					dolp++;
				}
			}
			
			// Reset rona positions
			for(int shk = 0; shk <= 4; shk++)
				Ronas[shk].teleport(-120, -120);
			
			// Set random player
			turns.FirstPlayerToStart(share.getMaxPlayers());

			// Prevent full resetting
			Share.setNeedToResetGame(false);		// PREVENT RELOAD
			winner = 0;								// Prevent winning screen reload
		}
		if(share.isLoadingSavedData()) {
			int[] recover = new int[346];
			recover = share.loadFromFile(346, "res\\saved\\ronaGame.sav");
			turns.setPlayer(recover[0]);
			for(int loadMe = 1; loadMe <= 4; loadMe++) {
				Share.setPlayerPerson(loadMe, recover[loadMe]);									// Load colors of persons
				ds[loadMe].setPosition(share.getPlayerPerson(loadMe));
				Share.setPersons(loadMe, recover[loadMe + 4]);								// Load # of persons 
				Share.setHomePersons(loadMe, recover[loadMe + 8]);								// Load # of persons 
			}
			for(int loadMe = 0; loadMe <= 19; loadMe++) {
				Person[loadMe].teleport(recover[loadMe + 13], recover[loadMe + 33]);				// Load Person's X & Y
				if(recover[loadMe + 53] == 1)
					Person[loadMe].setInStore(true);											// Load Person's presence 
				Person[loadMe].setPosition(share.getPlayerPerson(Person[loadMe].getOwner()));	// Load positons for correct graphics
			}
			for(int loadMe = 0; loadMe <= 44; loadMe++) {											// Load Safe location data
				sanitizer[loadMe].teleport(recover[loadMe + 73], recover[loadMe + 118]);
				sanitizer[loadMe].setRealX(recover[loadMe + 73]);
				sanitizer[loadMe].setRealY(recover[loadMe + 118]);
				sanitizer[loadMe].setFloatTime(recover[loadMe + 163]);
				if(recover[loadMe + 208] == 0)
					sanitizer[loadMe].setFloatDirection("Up");
				if(recover[loadMe + 208] == 1)
					sanitizer[loadMe].setFloatDirection("Down");
				if(recover[loadMe + 253] == 0)
					sanitizer[loadMe].setVisible(false);
				if(recover[loadMe + 253] == 1)
					sanitizer[loadMe].setVisible(true);
				Share.setSafetyZones(loadMe, recover[loadMe + 298]);								// Load Zones Vars
			}
			Share.setSafetycap(recover[343]);
			Share.setMaxPlayers(recover[344]);												// Load Max # Players
			Share.setMaxPersons(recover[345]);												// Load Max # personss
			Share.setLoadingSavedData(false);												// PREVENT RELOADING DATA OVER AND OVER	
			for(int u = 1; u <= 4; u++) {													// Load all Person selectors and scoreboard
				if(u > share.getMaxPlayers()) {
					ds[u].setVisible(false);
					personCount[u].setVisible(false);
					PersonsOut[u].setVisible(false);
					PersonsIn[u].setVisible(false);
				}
			}
		}
	}
	
	public boolean CheckPersons(int a, int b) {
		for(int dc = 0; dc < 20; dc++) {
			if(Person[dc].getX() == a && Person[dc].getY() == b)
				return false;
		}
		return true;
	}
	
	public boolean CheckAllPersons(int caX, int caY) {
		for(int all = 0; all < 20; all++) {
			if(Person[all].getX() == caX && Person[all].getY() == caY)
				return false;
		}
		if(caX < 120 || caY < 0 || caX > 1200 || caY > 510)
			return false;
		return true;
	}
	
	public int[] PrepareSaveData() {
		int[] generateData = new int[346];
		generateData[0] = turns.getPlayer();									// Save whose turn it currently is
		for(int saveMe = 1; saveMe <= 4; saveMe++) {
			generateData[saveMe] = share.getPlayerPerson(saveMe);				// Save colors of personss
			generateData[saveMe + 4] = share.getHomePersons(saveMe);			// Save # of personss in Ocean
			generateData[saveMe + 8] = share.getStorePersons(saveMe);			// Save # of personss on Shore
		}
		for(int saveMe = 0; saveMe <= 19; saveMe++) {
			generateData[saveMe + 13] = (int) Person[saveMe].getX();			// Save Person's X
			generateData[saveMe + 33] = (int) Person[saveMe].getY();			// Save Person's Y
			generateData[saveMe + 53] = 0;
			if(Person[saveMe].isInStore())
				generateData[saveMe + 53] = 1;									// Save Person's presence in shoreline
		}
		for(int saveMe = 0; saveMe <= 44; saveMe++) {							// Save Safe location data
			generateData[saveMe + 73] = (int) sanitizer[saveMe].getX();
			generateData[saveMe + 118] = (int) sanitizer[saveMe].getY();
			generateData[saveMe + 163] = sanitizer[saveMe].getFloatTime();
			generateData[saveMe + 208] = 0;
			if(sanitizer[saveMe].getFloatDirection() == "Down")
				generateData[saveMe + 208] = 1;
			generateData[saveMe + 253] = 0;
			if(sanitizer[saveMe].isVisible())
				generateData[saveMe + 253] = 1;
			generateData[saveMe + 298] = share.getSafetyZones(saveMe);				// Save Zones Vars
		}
		generateData[343] = share.getSafetycap();
		generateData[344] = share.getMaxPlayers();
		generateData[345] = share.getMaxPersons();
		return generateData;
	}
	
	@Override
	public void update() {
		ResetGame();
		bgCity.update();	
		if(winner > 0) {
			congrats.update();
			backToSetupButton.update(m);
			if(backToSetupButton.isClicked(m)) {
				winner = 0;
				Share.setNeedToResetSetup(true);
				Share.setNeedToResetGame(true);
				Share.setTransition(0);
			}
			ds[0].update();
			decorVictoryScene.update();
		}
		else {
			if(optionsAvaliable) {
				decorOptions.update();
				if(!savingData) {
					backToSetupButton.update(m);
					if(backToSetupButton.isClicked(m)) {
						Share.setNeedToResetSetup(true);
						Share.setNeedToResetGame(true);
						Share.setTransition(0);
					}
					saveGameButton.update(m);
					if(saveGameButton.isClicked(m)) {
						saveData.setVisible(true);
						saveData.setFPM(0);
						savingData = true;
					}
					saveAndExitButton.update(m);
					if(saveAndExitButton.isClicked(m)) {
						saveData.setVisible(true);
						saveData.setFPM(0);
						savingData = true;
						exitGame = true;
					}
					backToGameButton.update(m);
					if(backToGameButton.isClicked(m)) {
						optionsAvaliable = false;
					}
					exitGameButton.update(m);
					if(exitGameButton.isClicked(m)) {
						System.exit(0);
					}
				}
				if(savingData) {
					saveData.update();
				}
				if(saveData.getFPM() >= 109) {
					Share.saveToFile(PrepareSaveData(), "res\\saved\\ronaGame.sav");
					saveData.setVisible(false);
					saveData.setFPM(-1);
					saveData.setFrame(0);
					if(exitGame)
						System.exit(0);
					savingData = false;
				}
			} // end if
			else {
				for(int u = 0; u <= 4; u++) {
					if(roulette.getNum() > 0 && roulette.getNum() < 10 && roulette.getSpinning() == 0 && roulette.getWalk() > 0
							&& share.getHomePersons(turns.getPlayer()) > 0 && CheckPersons(120, (/* 30 + */ (u * 120)))) {
						home[u].setNum(1);
					}
					else
						home[u].setNum(0);
					if(home[u].isClicked(m) && home[u].getDelayClick() == 0 && share.getHomePersons(turns.getPlayer()) > 0 
							&& roulette.getWalk() > 0 && CheckPersons(120, (/* 30 + */ (u * 120)))) {
						// Find Person not on store
						int dolNum = 0;
						for(int dc = ((5 * turns.getPlayer()) - 5); dc < (5 * turns.getPlayer()); dc++) {
							if(!Person[dc].isInStore() && share.getHomePersons(turns.getPlayer()) > 0) {
								dolNum = dc;
								Person[dolNum].setInStore(true);
								break;
							}
						}
						Person[dolNum].teleport(-133, (int)((u * 120)));
						Person[dolNum].setSpeedX(22);
						Person[dolNum].setDelayClick(25);		// Delay clicking to prevent premature selections
						Share.setPersons(turns.getPlayer(), (share.getHomePersons(turns.getPlayer()) - 1));
						roulette.setWalk(roulette.getWalk() - 1);
						
						// Delay clicking to prevent multiple persons from walking out
						home[u].setDelayClick(25);
						
						// Increase to the next player
						if(roulette.getWalk() == 0) {
							roulette.setNum(0);
							turns.NextPlayer(share.getMaxPlayers());
						}
						
					}
					home[u].update(m);
				}
				for(int d = 0; d < 20; d++) {
					Person[d].setSelectable(false);
					if(roulette.getWalk() > 0 && (Person[d].getOwner() == turns.getPlayer()) && !movementButton[0].isVisible())
						Person[d].setSelectable(true);				// Allow Person to be selectable
					if(Person[d].isClicked(m) && roulette.getWalk() > 0 && Person[d].getDelayClick() == 0
							&& (Person[d].getOwner() == turns.getPlayer()) && !movementButton[0].isVisible()) {
						movementButton[0].setVisible(true);
						movementButton[0].teleport((int)Person[d].getX(), (int)Person[d].getY() - 34);
						movementButton[0].setSpecial(d);
						for(int cad = 1; cad <= 8; cad++) {
							if(CheckAllPersons((int)Person[d].getX() + WALKDX[cad], (int)Person[d].getY() + WALKDY[cad])) {
								movementButton[cad].setJumping(true);
								if(cad == 3 || cad == 7)
									movementButton[cad].setJumping(false);
								movementButton[cad].setSpeedX(WALKDX[cad]);
								movementButton[cad].setSpeedY(WALKDY[cad]);
								movementButton[cad].setSpecial(d);
								movementButton[cad].setVisible(true);
								movementButton[cad].teleport((int)Person[d].getX() + WALKDX[cad], (int)Person[d].getY() + WALKDY[cad]);
							}
						}
					}
					if(Person[d].isFinishline()) {
						Share.setHomePersons(Person[d].getOwner(), share.getStorePersons(Person[d].getOwner()) + 1);
						Person[d].teleport(-120, -120);
						Person[d].setFinishline(false);
					}
					Person[d].update(m);
				}
				for(int sb = 0; sb <= 44; sb++)
					sanitizer[sb].update(m);
				for(int sb = 0; sb <= 4; sb++)
					store[sb].update(m);
				for(int shk = 0; shk <= 4; shk++) {
					if(Ronas[shk].isClicked(m) && roulette.getRona() > 0 && Ronas[shk].getStage() == 0) {
						Ronas[shk].setStage(1);
						roulette.setRona(roulette.getRona() - 1);
					}
					if(Ronas[shk].isNotSafe() && Ronas[shk].getStage() == 4) {
						for(int d = 0; d < 20; d++) {
							if((int)Ronas[shk].getTarget() == (int)Person[d].getX() && (int)Person[d].getY() == ((shk * 120)/* + 30 */)) {
								boolean sendBack = true;
								for(int csb = 0; csb <= 44 ; csb++) {
									if((int)(sanitizer[csb].getRealX()/* - 30*/) == (int)Person[d].getX() 
											&& (int)(sanitizer[csb].getRealY()) == (int)Person[d].getY()) {
										Ronas[shk].setNotSafe(false);
										sendBack = false;
										break; //csb = 45;
									}
								}
								if(sendBack) {
									Person[d].teleport(-120, -120);
									Person[d].setInStore(false);
									Share.setPersons(Person[d].getOwner(), share.getHomePersons(Person[d].getOwner()) + 1);
								}
							}
						}
					}
					Ronas[shk].update(m);
				}
				woodMenu.update();
				for(int u = 1; u <= share.getMaxPlayers(); u++) {
					ds[u].update();
					personCount[u].update();
					PersonsOut[u].update(share.getHomePersons(u));
					PersonsIn[u].update(share.getStorePersons(u));
				}
				turns.update();
				if(roulette.isClicked(m)) {
					if(roulette.getNum() == 0 && roulette.getSpinning() == 0) {
						roulette.setNum(1);
						roulette.setSpinning(60);
					}
				}
				if(roulette.getNum() > 0 && roulette.getSpinning() == 0 && roulette.getWalk() == 0 && roulette.getRona() == 0 && !roulette.isShowRonas()) {
					if(roulette.getNum() >= 1 && roulette.getNum() <= 3) {
						roulette.setWalk(1);
						if(share.getSafetycap() > -1) {
							int randomPosition = r.nextInt(share.getSafetycap() + 1);
							sanitizer[share.getSafetycap()].setX(30 + (120 * (int)(share.getSafetyZones(randomPosition) % 10) ) );
							sanitizer[share.getSafetycap()].setY(30 + (120 * (int)(Math.floor(share.getSafetyZones(randomPosition) / 10) ) ) );
							sanitizer[share.getSafetycap()].setRealX(/*30 + */ (120 * (int)(share.getSafetyZones(randomPosition) % 10) ) );
							sanitizer[share.getSafetycap()].setRealY(/*30 + */(120 * (int)(Math.floor(share.getSafetyZones(randomPosition) / 10) ) ) );
							sanitizer[share.getSafetycap()].setVisible(true);
							Share.swapSafetyZones(share.getSafetycap(), randomPosition);
							Share.setSafetycap(share.getSafetycap() - 1);
						}
					}
					if(roulette.getNum() >= 4 && roulette.getNum() <= 7)
						roulette.setWalk(2);
					if(roulette.getNum() >= 8 && roulette.getNum() <= 9)
						roulette.setWalk(3);
					if(roulette.getNum() == 10)
						roulette.setRona(2);
					if(roulette.getNum() == 11)
						roulette.setRona(3);
					if(roulette.getNum() == 12)
						roulette.setRona(5);
					if(roulette.getRona() > 0) {
						roulette.setShowRona(true);
						for(int shk = 0; shk <= 4; shk++) {
							Ronas[shk].prepareToInfect(1200, (shk * 120));
						}
					}
				}
				roulette.update(m);
				pointsChart.update();
				walkPoints.update(roulette.getWalk());
				ronaPoints.update(roulette.getRona());
				passTurn.update(m);
				int ronaIsNear = 0;
				for(int shk = 0; shk <= 4; shk++) {
					if(Ronas[shk].getStage() > 0) {
						ronaIsNear++;
					}
				}
				if(passTurn.isClicked(m) && ronaIsNear == 0 && roulette.getRona() == 0) {
					
					for(int mb = 0; mb < 9; mb++) {
						movementButton[mb].setSpecial(-1);
						movementButton[mb].setVisible(false);
						movementButton[mb].teleport(-120, -120);
					}
					roulette.setNum(0);
					roulette.setSpinning(0);
					roulette.setWalk(0);
					roulette.setRona(0);
					roulette.setShowRona(false);
					for(int shk = 0; shk <= 4; shk++)
						Ronas[shk].teleport(-120, -120);
					
					// Increase to the next player					
					turns.NextPlayer(share.getMaxPlayers());			
					//activate question
					showQuestion = true;
					FactsAndQuestions.setVisible(true);
					roulette.setVisible(false);
					passTurn.setVisible(false);
					optionsButton.setVisible(false);
					FactsAndQuestions.setPreventRedraw(false);
				}
				if(movementButton[0].isClicked(m)) {
					for(int mb = 0; mb < 9; mb++) {
						movementButton[mb].setSpecial(-1);
						movementButton[mb].setVisible(false);
						movementButton[mb].teleport(-120, -120);
					}
				}
				for(int mbc = 1; mbc < 9; mbc++) {
					if(movementButton[mbc].isClicked(m)) {
						Person[movementButton[mbc].getSpecial()].setJumping(movementButton[mbc].isJumping());
						Person[movementButton[mbc].getSpecial()].setSpeedX(movementButton[mbc].getSpeedX() / 8);
						Person[movementButton[mbc].getSpecial()].setSpeedY(movementButton[mbc].getSpeedY() / 8);
						Person[movementButton[mbc].getSpecial()].setDelayClick(25);
						for(int mb = 0; mb < 9; mb++) {
							movementButton[mb].setVisible(false);
							movementButton[mb].teleport(-120, -120);
							movementButton[mb].setSpecial(-1);
						}
						
						// Increase to the next player
						roulette.setWalk(roulette.getWalk() - 1);
						if(roulette.getWalk() == 0) {
							roulette.setNum(0);
							turns.NextPlayer(share.getMaxPlayers());
						}
					}
				}
				for(int mb = 0; mb < 9; mb++)
					movementButton[mb].update(m);
				optionsButton.update(m);
				if(optionsButton.isClicked(m))
					optionsAvaliable = true;
			} 
			FactsAndQuestions.update(showQuestion, m);
		} //else
	} // end update()

	@Override
	public void render(Graphics g) {
		bgCity.render(g);
		if(winner > 0) {
			congrats.render(g);
			backToSetupButton.render(g);
			ds[0].render(g);
			decorVictoryScene.render(g);	
		}
		else {
			if(optionsAvaliable) {
				decorOptions.render(g);
				backToSetupButton.render(g);
				saveGameButton.render(g);
				saveAndExitButton.render(g);
				backToGameButton.render(g);
				exitGameButton.render(g);
				if(savingData) {
					saveData.render(g);
				}
			}
			else {
				for(int r = 0; r <= 4; r++)
					home[r].render(g);
				for(int sb = 0; sb <= 44; sb++)
					sanitizer[sb].render(g);
				for(int sb = 0; sb <= 4; sb++)
					store[sb].render(g);
				for(int d = 0; d < 20; d++) {
					if(!Person[d].isJumping())
						Person[d].render(g);
				}
				for(int shk = 0; shk <= 4; shk++)
					Ronas[shk].render(g);
				for(int d = 0; d < 20; d++) {
					if(Person[d].isJumping())
						Person[d].render(g);
				}
				woodMenu.render(g);
				for(int r = 1; r <= 4; r++) {
					ds[r].render(g);
					personCount[r].render(g);
					PersonsOut[r].render(g);
					PersonsIn[r].render(g);
				}
				turns.render(g);
				roulette.render(g);
				pointsChart.render(g);
				walkPoints.render(g);
				ronaPoints.render(g);
				passTurn.render(g);
				for(int mb = 0; mb < 9; mb++)
					movementButton[mb].render(g);
				optionsButton.render(g);
				for(int win = 1; win <= share.getMaxPlayers(); win++) {
					if(PersonsIn[win].getValue() == share.getMaxPersons()) {
						winner = win;
						ds[0].setPosition(share.getPlayerPerson(winner));
					}
				} // end for
			} // end else
		} // end else
	FactsAndQuestions.render(g);
	} // end render(g)
} // end class GameState


