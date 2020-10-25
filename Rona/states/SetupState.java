package states;

import java.awt.Graphics;

import graphics.Assets;
import input.MouseManager;
import sharing.Share;
import ui.Button;
import ui.DataShow;
import ui.Decor;
import ui.PersonSelector;
import ui.FloatingButton;
import ui.Score;
import ui.Scroll;

public class SetupState extends State {
	
	private Button playersUp, playersDown, personsUp, personsDown;
	private Button[] increaseSelection, decreaseSelection;
	private DataShow loadData;
	private Decor gameTitle, optionsTitle, playerNumbers, personNumbers, colorTypes;
	private Decor[] decorBlock;
	private PersonSelector[] ds;
	private FloatingButton backToTitleButton, newGameButton, loadGameButton, exitGameButton, beginRaceButton;
	private Score playerNumScore, personNumScore;
	private Scroll bgCity;
	
	public boolean block, loadUpData;
	public MouseManager m;
	public State[] state;
	public Share share;

	public SetupState(MouseManager m, State[] state) {
		this.m = m;
		this.state = state;
		this.loadUpData = false;
		this.block = false;
		
		// Share Class
		share = new Share();
		Share.setMaxPlayers(share.getMaxPlayers());
		
		// SCROLL
		bgCity = new Scroll(0, 0, 1440, 768, true, Assets.bgCity, "Right", 1);
		
		// DECOR
		gameTitle = new Decor(0, 0, 1366, 120, true, Assets.decorTitle);
		optionsTitle = new Decor(0, -120, 1366, 120, true, Assets.decorGameSetup);
		playerNumbers = new Decor(338, -100, 208, 34, true, Assets.decorNumberOfPlayers);
		personNumbers = new Decor(809, -100, 230, 34, true, Assets.decorNumberOfPersons);
		colorTypes = new Decor(555, 820, 256, 34, true, Assets.decorShirtTypes);
		decorBlock = new Decor[2];
		decorBlock[0] = new Decor(693, 570, 120, 60, false, Assets.decorBlock);
		decorBlock[1] = new Decor(833, 570, 120, 60, false, Assets.decorBlock);

		// SCORES
		playerNumScore = new Score(228, -128, 90, 110, true, Assets.numbersDecor, 0);
		personNumScore = new Score(699, -128, 90, 110, true, Assets.numbersDecor, 0);
		
		// BUTTONS
		playersDown = new Button(318, -85, 80, 80, true, Assets.west);
		playersUp = new Button(513, -85, 80, 80, true, Assets.east);
		personsDown = new Button(787, -85, 80, 80, true, Assets.west);
		personsUp = new Button(982, -85, 80, 80, true, Assets.east);
		increaseSelection = new Button[5];
		decreaseSelection = new Button[5];
		for(int ids = 1; ids <= 4; ids++) {
			increaseSelection[ids] = new Button(293 + (ids * 140), 805, 80, 80, true, Assets.north);
			decreaseSelection[ids] = new Button(293 + (ids * 140), 965, 80, 80, true, Assets.south);
		}
		
		// FLOATING BUTTONS
		newGameButton = new FloatingButton(253, 240, 240, 240, true, Assets.buttonNewGame);
		loadGameButton = new FloatingButton(563, 240, 240, 240, true, Assets.buttonLoadGame);
		exitGameButton = new FloatingButton(873, 250/*(200*/, 240, 240, true, Assets.buttonExitGame);		
		backToTitleButton = new FloatingButton(-284, 232, 240, 240, true, Assets.buttonBackToTitle);
		beginRaceButton = new FloatingButton(1400, 262, 240, 240, true, Assets.buttonBegin);
		
		// PERSONSELECTOR
		ds = new PersonSelector[5];
		for(int cds = 1; cds <= 4; cds++)
			ds[cds] = new PersonSelector(303 + (cds * 140), 895, 60, 60, true, Assets.personList, 0);

		// DATASHOW
		loadData = new DataShow(503, 480, 360, 240, false, Assets.dataLoadAnimation);
	}

	public void ResetSetup() {
		if(share.isNeedToResetSetup()) {
			Share.setMaxPlayers(share.getMenuSavedArray(0));
			Share.setMaxPersons(share.getMenuSavedArray(1));
			for(int reset = 1; reset <= 4; reset++) {
				Share.setPlayerPerson(reset, share.getMenuSavedArray(reset + 1));
				ds[reset].setPosition(share.getPlayerPerson(reset));
			}
			Share.setNeedToResetSetup(false);
		}
	}
	
	public int[] PrepareMenuData() {
		int[] putIn = new int[6];
		putIn[0] = share.getMaxPlayers();
		putIn[1] = share.getMaxPersons();
		for(int pmd = 2; pmd < putIn.length; pmd++)
			putIn[pmd] = share.getPlayerPerson(pmd - 1);
		return putIn;
	}
	
	@Override
	public void update() {
		ResetSetup();
		bgCity.update();
		gameTitle.update();
		optionsTitle.update();
		backToTitleButton.update(m);
		if(backToTitleButton.isClicked(m)) {
			backToTitleButton.setDirectionAndSpeed("Left", 24);
			newGameButton.setDirectionAndSpeed("Down", 32);
			loadGameButton.setDirectionAndSpeed("Down", 32);
			exitGameButton.setDirectionAndSpeed("Down", 32);
			beginRaceButton.setDirectionAndSpeed("Right", 24);			
			gameTitle.setDirectionAndSpeed("Down", 15);
			optionsTitle.setDirectionAndSpeed("Up", 15);
			playerNumbers.setDirectionAndSpeed("Up", 24);
			personNumbers.setDirectionAndSpeed("Up", 24);
			colorTypes.setDirectionAndSpeed("Down", 27);
			playerNumScore.setDirectionAndSpeed("Up", 27);
			personNumScore.setDirectionAndSpeed("Up", 27);
			playersDown.setDirectionAndSpeed("Up", 26);
			playersUp.setDirectionAndSpeed("Up", 26);
			personsDown.setDirectionAndSpeed("Up", 26);
			personsUp.setDirectionAndSpeed("Up", 26);
			for(int rn = 1; rn <= 4; rn++) {
				increaseSelection[rn].setDirectionAndSpeed("Down", 25);
				decreaseSelection[rn].setDirectionAndSpeed("Down", 25);
				ds[rn].setDirectionAndSpeed("Down", 25);
			}
			block = false;
		}
		if(!loadUpData) {
			newGameButton.update(m);
			if(newGameButton.isClicked(m)) {
				backToTitleButton.setDirectionAndSpeed("Right", 24);
				newGameButton.setDirectionAndSpeed("Up", 32);
				loadGameButton.setDirectionAndSpeed("Up", 32);
				exitGameButton.setDirectionAndSpeed("Up", 32);
				beginRaceButton.setDirectionAndSpeed("Left", 24);
				gameTitle.setDirectionAndSpeed("Up", 15);
				optionsTitle.setDirectionAndSpeed("Down", 15);
				playerNumbers.setDirectionAndSpeed("Down", 24);
				personNumbers.setDirectionAndSpeed("Down", 24);
				colorTypes.setDirectionAndSpeed("Up", 27);
				playerNumScore.setDirectionAndSpeed("Down", 27);
				personNumScore.setDirectionAndSpeed("Down", 27);
				playersDown.setDirectionAndSpeed("Down", 26);
				playersUp.setDirectionAndSpeed("Down", 26);
				personsDown.setDirectionAndSpeed("Down", 26);
				personsUp.setDirectionAndSpeed("Down", 26);
				for(int rn = 1; rn <= 4; rn++) {
					increaseSelection[rn].setDirectionAndSpeed("Up", 25);
					decreaseSelection[rn].setDirectionAndSpeed("Up", 25);
					ds[rn].setDirectionAndSpeed("Up", 25);
				}
				block = true;
			}
			loadGameButton.update(m);
			if(loadGameButton.isClicked(m)) {
				loadData.setVisible(true);
				loadData.setFPM(0);
				loadUpData = true;
			}
			exitGameButton.update(m);
			if(exitGameButton.isClicked(m)) {
		//		Share.saveToFile(PrepareMenuData(), "res\\saved\\ronaMenu.sav");
				System.exit(0);
			}
		}
		beginRaceButton.update(m);
		if(beginRaceButton.isClicked(m)) {
			Share.setNeedToResetGame(true);
			Share.setLoadingSavedData(false);
	//		Share.saveToFile(PrepareMenuData(), "res\\saved\\ronaMenu.sav");
			Share.setTransition(1);
		}
		playerNumbers.update();
		personNumbers.update();
		colorTypes.update();
		playerNumScore.update(share.getMaxPlayers());
		personNumScore.update(share.getMaxPersons());
		playersUp.update(m);
		if(playersUp.isClicked(m)) {
			if(share.getMaxPlayers() < 4) {
				Share.setMaxPlayers(share.getMaxPlayers() + 1);
			}
		}
		playersDown.update(m);
		if(playersDown.isClicked(m)) {
			if(share.getMaxPlayers() > 2) {
				Share.setMaxPlayers(share.getMaxPlayers() - 1);
			}
		}
		personsUp.update(m);
		if(personsUp.isClicked(m)) {
			if(share.getMaxPersons() < 5) {
				Share.setMaxPersons(share.getMaxPersons() + 1);
			}
		}
		personsDown.update(m);
		if(personsDown.isClicked(m)) {
			if(share.getMaxPersons() > 2) {
				Share.setMaxPersons(share.getMaxPersons() - 1);
			}
		}
		for(int rn = 1; rn <= 4; rn++) {
			if(increaseSelection[rn].isClicked(m)) {
				int matches = 0, satisfied = 0;
				while(satisfied == 0) {
					ds[rn].setPosition(ds[rn].getPosition() + 1);
					if(ds[rn].getPosition() > 18)				//
						ds[rn].setPosition(ds[rn].getPosition() - 18);		//
					for(int co = 1; co <= 4; co++) {
						if(ds[rn].getPosition() == ds[co].getPosition())
							matches++;
					}
					if(matches == 1)
						satisfied = 1;
					matches = 0;
				}
				Share.setPlayerPerson(rn, ds[rn].getPosition());
			}
			if(decreaseSelection[rn].isClicked(m)) {
				int matches = 0, satisfied = 0;
				while(satisfied == 0) {
					ds[rn].setPosition(ds[rn].getPosition() - 1);
					if(ds[rn].getPosition() < 0)
						ds[rn].setPosition(ds[rn].getPosition() + 18);
					for(int co = 1; co <= 4; co++) {
						if(ds[rn].getPosition() == ds[co].getPosition())
							matches++;
					}
					if(matches == 1)
						satisfied = 1;
					matches = 0;
				}
				Share.setPlayerPerson(rn, ds[rn].getPosition());
			}
			increaseSelection[rn].update(m);
			decreaseSelection[rn].update(m);
			ds[rn].update();
		}
		if(block) {
			if(share.getMaxPlayers() == 2) {
				decorBlock[0].setVisible(true);
				decorBlock[1].setVisible(true);
			}
			if(share.getMaxPlayers() == 3) {
				decorBlock[0].setVisible(false);
				decorBlock[1].setVisible(true);
			}
			if(share.getMaxPlayers() == 4) {
				decorBlock[0].setVisible(false);
				decorBlock[1].setVisible(false);
			}
		}
		if(loadUpData) {
			loadData.update();
		}
		if(loadData.getFPM() >= 109) {
			loadData.setVisible(false);
			loadData.setFPM(-1);
			loadData.setFrame(0);
			loadUpData = false;
			Share.setNeedToResetGame(true);
			Share.setLoadingSavedData(true);
			Share.saveToFile(PrepareMenuData(), "res\\saved\\ronaMenu.sav");
			Share.setTransition(1);
		}
	}

	@Override
	public void render(Graphics g) {
		bgCity.render(g);
		gameTitle.render(g);
		optionsTitle.render(g);
		backToTitleButton.render(g);
		newGameButton.render(g);
		loadGameButton.render(g);
		exitGameButton.render(g);
		beginRaceButton.render(g);
		playerNumbers.render(g);
		personNumbers.render(g);
		colorTypes.render(g);
		playerNumScore.render(g);
		personNumScore.render(g);
		playersDown.render(g);
		playersUp.render(g);
		personsDown.render(g);
		personsUp.render(g);
		for(int rn = 1; rn <= 4; rn++) {
			increaseSelection[rn].render(g);
			decreaseSelection[rn].render(g);
			ds[rn].render(g);
		}
		if(block) {
			decorBlock[0].render(g);
			decorBlock[1].render(g);
		}
		if(loadUpData) {
			loadData.render(g);
		}
	}

} // end class Setup State
