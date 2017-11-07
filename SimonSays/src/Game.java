//source code for simon says

import java.applet.Applet;
import java.util.Date;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JApplet;
import java.applet.AudioClip;
import java.awt.Toolkit;


@SuppressWarnings("unused")
public class Game extends Applet implements MouseListener, MouseMotionListener, ActionListener {
  
  /**
	 * 
	 */
	
//Member Variables
  Image red1;
  Image blue1;
  Image yellow1;
  Image green1;
  
  Image red2;
  Image blue2;
  Image yellow2;
  Image green2;
  
  Image red3;
  Image blue3;
  Image yellow3;
  Image green3;
  
  Image soundButton;
  Image muteButton;
  
  boolean greenPress,redPress,yellowPress,bluePress;
  int maxLevels = 100;
  int simonArray[] = new int[maxLevels];
  int userArray[] = new int[maxLevels];
  int l = 0;
  int n = 0;
  boolean play = false;
  boolean gameOver = false;
  boolean sound = true;
  boolean showPattern = true;
  boolean newButtonIntroduced = true;
  Button newGameButton = new Button("Start Game");
  Date now;
  long milliNow = 0;
  long milliLater = 0;
  int showPatternProgress = 0;
  
  //Applet substitute for main()
  public void init() {
  
    newGameButton.addActionListener(this);
    add(newGameButton);
    
    addMouseListener(this);
    addMouseMotionListener(this);
    
    URL codeBase = getCodeBase();
    
    green1 = getImage(codeBase, "buttons/green1.png");
    green2 = getImage(codeBase, "buttons/green2.png");
    green3 = getImage(codeBase, "buttons/green3.png");
    
    red1 = getImage(codeBase, "buttons/red1.png");
    red2 = getImage(codeBase, "buttons/red2.png");
    red3 = getImage(codeBase, "buttons/red3.png");
    
    blue1 = getImage(codeBase, "buttons/blue1.png");
    blue2 = getImage(codeBase, "buttons/blue2.png");
    blue3 = getImage(codeBase, "buttons/blue3.png");
    
    yellow1 = getImage(codeBase, "buttons/yellow1.png");
    yellow2 = getImage(codeBase, "buttons/yellow2.png");
    yellow3 = getImage(codeBase, "buttons/yellow3.png");
    
    muteButton = getImage(codeBase, "buttons/mute.png");
    soundButton = getImage(codeBase, "buttons/sound.png");
    
    setBackground(Color.black);
    simonArray[1] = 1; simonArray[2] = 2; simonArray[3] = 3; simonArray[4] = 4;
 }
  
  public void startGame(){
    l = 0;
    n = 0;
    newButtonIntroduced = true;
    for(int i = 0; i < maxLevels; i++) {
      simonArray[i] = (int) (Math.random() * 4+1);
    }
  }

  public void paint(Graphics g) {
    //g.setColor(Color.black);
    //g.fillRect(0,0,500,500);
    
    //Title 
    Font title = new Font("Comic Sans MS", Font.BOLD, 30);
    g.setFont(title);
    g.setColor(Color.white);
    g.drawString("Simon Says", 97, 35);
    
    //"Round Meter"
    Font score = new Font ("Comic Sans MS", Font.PLAIN, 20);
    g.setFont(score);
    g.drawString("Score", 152, 210);
    Integer lToInteger = l;
    String lToString = lToInteger.toString();
    if(l != 0){
    g.drawString(lToString, 176,245);
    }
    
    //Directions below the game board
    Font directions = new Font("Arial", Font.PLAIN, 11);
    Font titleDirections = new Font("Arial", Font.BOLD, 16);
    if(simonArray[50] == 0){
      g.setFont(titleDirections);
      g.drawString("Directions:",15,400);
      g.drawString("Goal of Game:",15,448);
      g.setFont(directions);
      g.drawString("Click each button once before the game begins for best results", 17, 412);
      g.drawString("After pressing Start Game, press any of the Buttons to begin", 17, 424);
      g.drawString("Repeat the sequence to the highest possible number!",17,460);
    }
    
    newGameButton.setLocation(125,195);
    
    //greenToggle
    if(!greenPress){
      g.drawImage(green1,10,50,green1.getWidth(this),green1.getHeight(this),this);
    } else {
      g.drawImage(green2,10,50,green2.getWidth(this),green2.getHeight(this),this);
    }
    
    //redToggle
    if(!redPress) {
      g.drawImage(red1,186,50,red1.getWidth(this),red1.getHeight(this),this);
    } else {
      g.drawImage(red2,186,50,red2.getWidth(this),red2.getHeight(this),this);
    }
    
    //blueToggle
    if(!bluePress){
      g.drawImage(blue1,186,220,blue1.getWidth(this),blue1.getHeight(this),this);
    } else {
      g.drawImage(blue2,186,220,blue2.getWidth(this),blue2.getHeight(this),this);
    }
    
    //yellowToggle
    if(!yellowPress) {
      g.drawImage(yellow1,10,220,yellow1.getWidth(this),yellow1.getHeight(this),this);
    } else {
      g.drawImage(yellow2,10,220,yellow2.getWidth(this),yellow2.getHeight(this),this);
    }
    
    //Sound - KW
    if(sound==true) {
      g.drawImage(soundButton,10,10,soundButton.getWidth(this),soundButton.getHeight(this),this);
    }
    if(sound==false) {
      g.drawImage(muteButton,10,10,muteButton.getWidth(this),muteButton.getHeight(this),this);
    }
    
    //Game Over
    Font over = new Font ("Comic Sans MS", Font.BOLD, 48);
    g.setFont(over);
    if(gameOver == true) {
      g.drawString("Game Over", 60 , 175);
    } //end if
    
    //Blinks
    if(showPattern){
      for(int i = 0; i <= showPatternProgress; i++){
        now = new Date();
          milliNow = now.getTime();
          while(milliLater < milliNow + 200) {
            now = new Date();
            milliLater = now.getTime();
          }
        if(simonArray[i] == 1){
          g.drawImage(red2,186,50,red2.getWidth(this),red2.getHeight(this),this);
          now = new Date();
          milliNow = now.getTime();
          //playAudioResource("sounds/Blow1_1.wav");
          while(milliLater < milliNow + 500) {
            now = new Date();
            milliLater = now.getTime();
          }
          g.drawImage(red1,186,50,red1.getWidth(this),red1.getHeight(this),this);
        } if (simonArray[i] == 2){
          g.drawImage(green2,10,50,green2.getWidth(this),green2.getHeight(this),this);
          now = new Date();
          milliNow = now.getTime();
          //playAudioResource("sounds/Blow2_1.wav");
          while(milliLater < milliNow + 500) {
            now = new Date();
            milliLater = now.getTime();
          }
          g.drawImage(green1,10,50,green1.getWidth(this),green1.getHeight(this),this);
        } if (simonArray[i] == 3) {
          g.drawImage(yellow2,10,220,yellow2.getWidth(this),yellow2.getHeight(this),this);
          now = new Date();
          milliNow = now.getTime();
         //playAudioResource("sounds/Blow3_1.wav");
          while(milliLater < milliNow + 500) {
            now = new Date();
            milliLater = now.getTime();
          }
          g.drawImage(yellow1,10,220,yellow1.getWidth(this),yellow1.getHeight(this),this);
        } if (simonArray[i] == 4){
          g.drawImage(blue2,186,220,blue2.getWidth(this),blue2.getHeight(this),this);
          now = new Date();
          milliNow = now.getTime();
          //playAudioResource("sounds/Blow4_1.wav");
          while(milliLater < milliNow + 500) {
            now = new Date();
            milliLater = now.getTime();
          }
          g.drawImage(blue1,186,220,blue1.getWidth(this),blue1.getHeight(this),this);
        }
      }
      showPattern = false;
    }
    
  }
  
  
  
  //Simon Logic -- TT
  public void checkButtonCode(int button) {
    if(play){
      if(newButtonIntroduced) {
        if(l == 0){
          simonArray[0] = button;
        }
        userArray[l] = button;
        if(userArray[l] == simonArray[l]) {
          l++;
          newButtonIntroduced = false;
          showPattern(l);
        } else {
          play = false;
          gameOver = true;
          add(newGameButton);
        }
      } else {
        userArray[n] = button;
        if(userArray[n] != simonArray[n]) {
          play = false;
          gameOver = true;
          add(newGameButton);
        } else {
          n++;
          if(n == l) {
            n = 0;
            newButtonIntroduced= true;
          }
        }
      }
      if(userArray[maxLevels-1] == simonArray[maxLevels-1]) {
        play = false;
        gameOver = true;
        add(newGameButton);
      }
    }
  }
  
  //Shows the pattern to the player
  public final void showPattern(int visibleSequence) {
    showPatternProgress = visibleSequence;
    showPattern = true;
    repaint();
  }
  
  
  /*************************************************************************************
   *                                      MOUSE EVENTS                                 *
   *************************************************************************************/
  
  
  public void mousePressed(MouseEvent e) {
    boolean isCenter = e.getX() >= 170 && e.getX() <= 217 && e.getY() >= 145 && e.getY() <= 230;
    //green
    if(e.getY() >= 50 && e.getY() <= 215 && e.getX() >= 10 && e.getX() <= 180 && !isCenter){
      playAudioResource("sounds/Blow2_1.wav");
      greenPress = true;
      repaint();
    }
    //red
    if(e.getY() >= 50 && e.getY() <= 215 && e.getX() >= 185 && e.getX() <= 355 && !isCenter) {
      playAudioResource("sounds/Blow1_1.wav");
      redPress = true;
      repaint();
    }
    //yellow
    if(e.getY() >= 220 && e.getY() <= 385 && e.getX() >= 10 && e.getX() <= 180 && !isCenter) {
      playAudioResource("sounds/Blow3_1.wav");
      yellowPress = true;
      repaint();
    }
    //blue
    if(e.getY() >= 220 && e.getY() <= 385 && e.getX() >= 185 && e.getX() <= 355 && !isCenter) {
      playAudioResource("sounds/Blow4_1.wav");
      bluePress = true;
      repaint();
    }
    if(e.getY() >= 10 && e.getY() <= 40 && e.getX() >= 10 && e.getX() <= 40 && sound==false) {
      sound = true;
      repaint();
    }  
    if(e.getY() >= 10 && e.getY() <= 40 && e.getX() >= 10 && e.getX() <= 40 && sound==true) {
      sound = false;
      repaint();
    } 
    
    
  }
     
  public void mouseReleased(MouseEvent e) {
    boolean isCenter = e.getX() >= 170 && e.getX() <= 217 && e.getY() >= 145 && e.getY() <= 230;
    //green
    if(e.getY() >= 50 && e.getY() <= 215 && e.getX() >= 10 && e.getX() <= 180 && greenPress && !isCenter){
      greenPress = false;
      repaint();
      checkButtonCode(2);
    }
    //red
    if(e.getY() >= 50 && e.getY() <= 215 && e.getX() >= 185 && e.getX() <= 355 && redPress && !isCenter) {
      redPress = false;
      repaint();
      checkButtonCode(1);
    }
    //yellow
    if(e.getY() >= 220 && e.getY() <= 385 && e.getX() >= 10 && e.getX() <= 180 && yellowPress && !isCenter) {
      yellowPress = false;
      repaint();
      checkButtonCode(3);
    }
    //blue
    if(e.getY() >= 220 && e.getY() <= 385 && e.getX() >= 185 && e.getX() <= 355 && bluePress && !isCenter) {
      bluePress = false;
      repaint();
      checkButtonCode(4);
    }
  }
     
  public void mouseEntered(MouseEvent e) {
  }
     
  public void mouseExited(MouseEvent e) {
  }
     
  public void mouseClicked(MouseEvent e) {
  }
  
  public void mouseMoved(MouseEvent e) {
  }
  
  public void mouseDragged(MouseEvent e) {
    boolean isCenter = e.getX() >= 170 && e.getX() <= 217 && e.getY() >= 145 && e.getY() <= 230;
    //green
    if(e.getY() >= 50 && e.getY() <= 210 && e.getX() >= 10 && e.getX() <= 180 && !isCenter){
    } else {
      greenPress = false;
      repaint();
    }
    //red
    if(e.getY() >= 50 && e.getY() <= 215 && e.getX() >= 185 && e.getX() <= 355 && !isCenter) {
    } else {
      redPress = false;
      repaint();
    }
    //yellow
    if(e.getY() >= 222 && e.getY() <= 385 && e.getX() >= 10 && e.getX() <= 180 && !isCenter) {
    } else {
      yellowPress = false;
      repaint();
    }
    //blue
    if(e.getY() >= 220 && e.getY() <= 385 && e.getX() >= 185 && e.getX() <= 355 && !isCenter) {
    } else {
      bluePress = false;
      repaint();
    }
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == newGameButton) {
      remove(newGameButton);
      play = true;
      gameOver = false;
      showPattern(0);
      startGame();
      repaint();
    }
  }
  private void playAudioResource(String audioResourceName){
     ClassLoader cl = Game.class.getClassLoader();
    URL resourceURL = cl.getResource(audioResourceName);
    if(resourceURL != null) {
      AudioClip sound = JApplet.newAudioClip(resourceURL);
      sound.play();
    }
  } 
}
