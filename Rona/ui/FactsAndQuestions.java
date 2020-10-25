package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import input.MouseManager;
import states.GameState;

public class FactsAndQuestions extends UIObject {
    
    private int question;
    public Random r;
    public BufferedImage[][] QAList;
    public int randomQuestion;
    public boolean preventRedraw;
    
    public Button[] answers;
    public int[] correct;
    
    public FactsAndQuestions(float x, float y, int w, int h, boolean visible, BufferedImage[][] bufferedImage) {
    	super(x, y, w, h, visible, null);
        this.question = 0;
        this.r = new Random();
        this.QAList = bufferedImage;
        this.preventRedraw = false;
//    	this.test = Assets.buttonLoadGame;
    	this.answers = new Button[4];
    	this.answers[0] = new Button((int) -10000, (int) -10000, 684, 58, true, null); 
    	this.answers[1] = new Button((int) -10000, (int) -10000, 684, 58, true, null); 
    	this.answers[2] = new Button((int) -10000, (int) -10000, 684, 58, true, null); 
    	this.answers[3] = new Button((int) -10000, (int) -10000, 684, 58, true, null);  
    	this.correct = new int[] {2, 3, 0, 0, 2, 1, 3, 2, 0, 3};
    }

    public void update(boolean show, MouseManager m) {
    	if(show == true && preventRedraw == false) {			
    		randomQuestion = r.nextInt(10); 
    		preventRedraw = true;
    		visible = true;
    		answers[0].teleport(0, 658);
    		answers[1].teleport(684, 658);
    		answers[2].teleport(0, 716);
    		answers[3].teleport(684, 716);
    	}
    	if (show == true && preventRedraw == true) {
    		for (int i = 0; i < 4; i++) {
    			if (answers[i].isClicked(m)) {
    				if (correct[randomQuestion] == i) {
    					//add walk-point if correct answer
    					GameState.roulette.setWalk(1);
    					GameState.turns.PreviousPlayer(GameState.share.getMaxPlayers());			

    				}
    				//move question
    				//move answers
    				for (int c = 0; c < 4; c++) {
    					//moves buttons WAY off screen
    					answers[c].teleport(-10000, -10000);
    				}
    				//removes images
    				visible = false;
    				GameState.optionsButton.setVisible(true);
    				GameState.passTurn.setVisible(true);
    				GameState.roulette.setVisible(true);
    			}
    		}
    	}
    }
    
	@Override
    public void render(Graphics g) {
    	if(visible == true && preventRedraw == true) {
			g.drawImage(QAList[randomQuestion][0], (int) 0, (int) 590, 1368, 68, null);
			g.drawImage(QAList[randomQuestion][1], (int) 0, (int) 658, 684, 58, null);
			g.drawImage(QAList[randomQuestion][2], (int) 684, (int) 658, 684, 58, null);
			g.drawImage(QAList[randomQuestion][3], (int) 0, (int) 716, 684, 58, null);
			g.drawImage(QAList[randomQuestion][4], (int) 684, (int) 716, 684, 58, null);
    	}
    }
    
    public int getQuestion() {
		return question;
	}

	public void setQuestion(int ques) {
		this.question = ques;
	}
	
	public void setPreventRedraw(boolean preventRedraw) {
		this.preventRedraw = preventRedraw;
	}
}
