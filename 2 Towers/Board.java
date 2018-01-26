import java.awt.geom.*;
import java.awt.event.*;
import java.awt.Color.*;
import java.awt.*;
import javax.swing.JOptionPane.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener
{
    protected Timer timer;
    protected Pillar p1;
    protected Pillar p2;
    protected Line l1;
    protected Ball b1;
    protected Pillar p3Double;
    private final int DELAY = 10;
    private int B_W = 600; 
    private int B_H = 600;
    private int mousexPos = 0;
    private int mouseyPos = 0;
    private boolean inLoseScreen;
    private boolean inWinScreen;
    private boolean inMenu;
    private boolean inHelp;
    private boolean inGame;
    private boolean inMode;
    private boolean lost;
    private boolean easy;
    private boolean medium;
    private boolean hard;
    private boolean lineFall;
    private boolean extreme;
    private int mode;
    private int score;
    private boolean doubleScore;
    private boolean lip3Done;
    private boolean coolCheat;
    private int time ;
    public Board()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        addMouseListener(new MAdapter());
        initBoard();
    }

    public void initBoard()
    {
        p1 = new Pillar(0,300,150, 300);
        int random = randomX();
        p2 = new Pillar(random,300,randomWidth(), 300);
        b1 = new Ball(p1.getWidth()-10, 280, 20, 20);
        l1 = new Line(150,300); 
        p3Double = new Pillar(p2.getX() + p2.getWidth()/3, 300, p2.getWidth()/3, 300);
        score = -1;
        inGame = false;
        inMenu = true;
        inHelp = false;
        inLoseScreen = false;
        inWinScreen = false;
        lip3Done = false;
        doubleScore = false;
        coolCheat = false;
       
        mode = 1;
        easy = true;
        inMode = false;
        time = 500;
        timer = new Timer(DELAY, this);

    }

    public void newGame()
    {
        if(lost)
            score = 0;
        else
            score++;

        if(doubleScore)
        {
            score++;
        }
        time = 500;
        inGame = true;
        inMenu = false;
        inHelp = false;
        inLoseScreen = false;
        inWinScreen = false;
        lip3Done = false;
        doubleScore = false;
        coolCheat = false;
        
        p1.setX(0);
        p1.setY(300);
        p1.setWidth(150);
        p1.setHeight(300);

        int random = randomX();
        p2.setX(random);
        p2.setY(300);
        p2.setWidth(randomWidth());
        p2.setHeight(300);

        b1.setX(p1.getWidth()-10);
        b1.setY(280);

        p3Double.setX(p2.getX() + p2.getWidth()/3);
        p3Double.setY(300);
        p3Double.setWidth(p2.getWidth()/3);
        p3Double.setHeight(300);

        l1.setX(150);
        l1.setY(300);
        l1.setWidth(0);
        l1.setHeight(0);
        l1.setLineDone(false);
        timer.start();

    }

    private boolean checkLine()
    {
        //System.out.println(l1.getWidth() + 150);
        //System.out.println(p2.getX());
        if((l1.getWidth() + 150) >= p2.getX())
        {
            if((l1.getWidth() + 150) <= p2.getX() + p2.getWidth())
            {
                if(l1.getWidth() + 150 >= p3Double.getX())
                {
                    if((l1.getWidth() + 150) <= p3Double.getX() + p3Double.getWidth() && lip3Done && l1.lineDone())
                    {
                        doubleScore = true;
                        lip3Done = false;
                        return true;
                    }
                }
                return true; // win
            }
        }

        return false; //lose
    }

    public int randomX()//random p2 position
    {
        int random = (int)(Math.random()*340)+160;
        return random;
    }

    public int randomWidth()//random p2 width
    {
        int random = (int)(Math.random()*65)+18;
        return random;
    }

    public void actionPerformed(ActionEvent e)
    {       
        
        updateBall(); 

        repaint(); 

    }

    private void bonus(Graphics g)
    {
        String bonus = "Perfect!";
        g.drawString(bonus, 180,200);
    }

    private void updateBall() 
    {
        b1.move(l1, checkLine(),l1.lineFall());
        if(l1.lineFall())
        {
            l1.fall();
            repaint();
        }
        if(checkLine())
        {
            if(b1.x >= p2.getX() && b1.x <= p2.getX() + p2.getWidth())
            {
                if(b1.x + 10 >= l1.getWidth() + p1.getWidth())
                {
                    timer.stop();

                    inWinScreen = true;
                    inGame = false;
                    inHelp = false;
                    inMenu = false;
                    lost = false;
                    try {
                        Thread.sleep(time);                 //700 milliseconds is one second.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    repaint();
                }

            }
        }
        
        else if(b1.y > 800)
        {
            timer.stop();
            inLoseScreen = true;
            inHelp = false;
            inGame = false;     
            inWinScreen = false;
            lost = true;

            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {   
        super.paintComponent(g);
        if(coolCheat)
        {
            getCheat();
        }
        if(doubleScore)
        {
            Font fontName= new Font("Allstar", Font.BOLD, 48);
            FontMetrics pop = getFontMetrics(fontName);
            g.setFont(fontName);
            g.setColor(Color.red);
            String bonus = "Perfect!";
            g.drawString(bonus,180, 100);
            time = 900;
        }
        if(inHelp)
            drawHelp(g);
        else if(inMenu)
        {
            setBackground(Color.red); 
            drawMenu(g);
        }
        else if(inGame)
        {
            drawFirstPillar(g);
            drawRandomPillar(g);
            drawP3(g);
            drawLine(g); 
            drawScore(g);
          
            if(l1.getLineDone())
            {
                drawBall(g); 
            }
            else
                drawFakeBall(g);
        }
        else if(inWinScreen)
        {
            drawWinScreen(g);
        }
        else if(inLoseScreen)
        {
            drawLoseScreen(g);
        }
        else if(inMode)
        {
            drawModeScreen(g);
        }

    }

    private void getCheat()
    {
        int x = Integer.parseInt(JOptionPane.showInputDialog("Enter Score"));
        score+=x;
        coolCheat = false;
        repaint();
    }

    private void drawP3(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Color red = new Color(186, 13, 13);
        g2.setColor(Color.green);
        g2.fillRect(p3Double.getX(),p3Double.getY(), p3Double.getWidth()+1, p3Double.getHeight());
    }

    private void drawScore(Graphics g)
    {

        String s = Integer.toString(score);
        Font fontName= new Font("Allstar", Font.BOLD, 48);
        FontMetrics pop = getFontMetrics(fontName);
        g.setFont(fontName);

        g.drawString(s, 100, 100);
    }

    public void drawWinScreen( Graphics g)
    {

        super.paintComponent(g);
        String win = "You win";

        String playAgain = "(Enter) Play Again";
        String back = "Back to Menu";
        Font smeagol = new Font("Magneto", Font.BOLD, 64);
        FontMetrics hx = getFontMetrics(smeagol);
        g.setFont(smeagol);
        setBackground(Color.yellow);
        g.setColor(Color.black);
        g.drawString(win, 180,200);

        Font f2 = new Font("Magneto", Font.BOLD, 25);
        FontMetrics jk = getFontMetrics(f2);
        g.setFont(f2);
        g.drawString(back, 400, 485);
        g.drawString(playAgain, 320,450);

    }

    public void drawLoseScreen( Graphics g)
    {

        super.paintComponent(g);
        String win = "You Lose";
        String playAgain = "(Enter) Play Again";
        String back = "Back to Menu";
        Font smeagol = new Font("Magneto", Font.BOLD, 64);
        FontMetrics hx = getFontMetrics(smeagol);
        g.setFont(smeagol);
        setBackground(Color.yellow);
        g.setColor(Color.black);
        g.drawString(win, 180,200);

        Font f2 = new Font("Magneto", Font.BOLD, 25);
        FontMetrics jk = getFontMetrics(f2);
        g.setFont(f2);

        g.drawString(playAgain, 320,450);
        g.drawString(back, 400, 485);
    }

    private void drawBall(Graphics g)
    {
        g.fillOval( b1.x, b1.y, b1.width, b1.height);
    }

    private void drawFakeBall(Graphics g)
    {
        g.fillOval( p1.getWidth()-10, 280, 20, 20);
    }

    private void drawMenu(Graphics g) {
        super.paintComponent(g);
        String title = "2 Towers";
        String play = "Play";
        String mode = "Mode";
        String help = "Help";
        String exit = "Exit";
        String copyright = "©";
        String credits = "Created By";  
        String j = "Julian Heymans";
        String q = "Quintin Bybee";
        Font small = new Font("Magneto", Font.BOLD, 64);
        FontMetrics fm = getFontMetrics(small);
        setBackground(Color.yellow);
        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(title, 130, B_H / 4);
        g.drawString(play, 230, 250);
        g.drawString(mode, 230, 315);
        g.drawString(help, 230, 375);
        Font smalll = new Font("Microsoft JhengHei", Font.BOLD, 20);
        FontMetrics t = getFontMetrics(smalll);
        g.setFont(smalll);
        g.drawString(credits,230,450);
        g.drawString(j,245,500);
        g.drawString(q,245,475);
        g.drawString(copyright,560,560);

        drawFirstPillar(g);
        g.fillRect(470, 300, 50, 300); 

    }

    private void cheat()
    {
        coolCheat = true;
        inHelp = false;
        inMenu = false;
        inGame = false;
    }

    public void clickHelp()
    {

        inHelp = true; 
        inMenu = false;
        inGame = false;

    }

    private void drawHelp(Graphics g) {
        super.paintComponent(g);
        String title = "How to play";
        String help = "Hold down spacebar and a line will draw! ";
        String menu = "Back to Menu";
        String help1 = "Keep holding and try to make it to the 2nd ";
        String help2 = "pillar without stopping too early or too late! ";
        String help6 = "Try to land in the green pillar for 2 points. ";
        String help3 = "Challenge yourself with different game ";
        String help4 = "modes, where the line goes forward at a ";
        String help5 = "normal, or random length!";
        String help7 = "Drop mode draws a line at a random length";
        String help8 = " above the towers and drops it.";
        Font small = new Font("Magneto", Font.BOLD, 64);
        FontMetrics fm = getFontMetrics(small);
        Font newFont = new Font("Helvetica", Font.BOLD, 16);
        FontMetrics tb = getFontMetrics(newFont);
        setBackground(Color.yellow);
        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(title, 95, 150);
        g.setFont(newFont);
        g.drawString(help, 135, 250);
        g.drawString(help1, 135, 280);
        g.drawString(help2, 135, 310);
        g.drawString(help6, 135, 340);
        g.drawString(help3, 135, 370);
        g.drawString(help4, 135, 400);
        g.drawString(help5, 135, 430);
        g.drawString(help7,135 , 460);
        g.drawString(help8,135, 490);
        g.setColor(Color.red);
        g.drawString(menu, 230, 510);
        g.setColor(Color.black);
    }

    private void drawModeScreen(Graphics g) {
        super.paintComponent(g);

        String e = "Easy";
        String m = "Random";
        String h = "Drop";
        //String ex = "Extreme";
        String back = "Back to menu";

        Font small = new Font("Magneto", Font.BOLD, 64);
        FontMetrics fm = getFontMetrics(small);
        setBackground(Color.yellow);
        g.setColor(Color.black);
        g.setFont(small);

        if(easy)
        {
            g.setColor(Color.red);
            g.drawString(e,200,130);
            g.setColor(Color.black);
            g.drawString(m,200,210);
            g.drawString(h,200,280);
            //g.drawString(ex,200,350);
        }

        if(medium)
        {
            g.setColor(Color.red);
            g.drawString(m,200,210);
            g.setColor(Color.black);
            g.drawString(e,200,130);
            g.drawString(h,200,280);
            //g.drawString(ex,200,350);
        }

        if(hard)
        {
            g.setColor(Color.red);
            g.drawString(h,200,280);
            g.setColor(Color.black);
            g.drawString(e,200,130);
            g.drawString(m,200,210);
           // g.drawString(ex,200,350);
        }

        //         if(extreme)
        //         {
        //             g.setColor(Color.red);
        //             g.drawString(ex,200,350);
        //             g.setColor(Color.black);
        //             g.drawString(e,200,130);
        //             g.drawString(m,200,210);
        //             g.drawString(h,200,280);
        //         }
        Font big = new Font("Magneto", Font.BOLD, 28);
        FontMetrics tm = getFontMetrics(big);
        g.setColor(Color.black);
        g.setFont(big);
        g.drawString(back,200,410);
    }

    public void drawLine(Graphics g)
    {
        Graphics2D g3 = (Graphics2D) g;
        g3.setColor(Color.red);
        g3.fillRect(l1.x, l1.y, l1.getWidth(), l1.getHeight());
    }

    public void drawFirstPillar(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(p1.getX(),p1.getY(), p1.getWidth(), p1.getHeight());
    }

    public void drawRandomPillar(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect((int)p2.getX(), (int)p2.getY(), (int)p2.getWidth(), (int)p2.getHeight());
    }

    private class TAdapter extends KeyAdapter 
    {
        @Override
        public void keyPressed(KeyEvent e) //Mr.E's Modified Power Bar
        {  
            int k = e.getKeyCode();
            if(k == KeyEvent.VK_ESCAPE)
            {
                System.exit(0);
            }
            if(inWinScreen || inLoseScreen)
            {
                if(k == KeyEvent.VK_ENTER)
                {
                    newGame();
                }
            }
            l1.keyPressed(e,mode); 
        }

        public void keyReleased(KeyEvent e)
        {
            l1.keyReleased(e,mode); 
            lip3Done = true;
        }
    }

    private class MAdapter extends MouseAdapter
    {
        @Override//restart the game if Mouse is clicked
        public void mousePressed(MouseEvent e) {
            mousexPos = e.getX();
            mouseyPos = e.getY(); 
            //System.out.println(mousexPos + " " + mouseyPos);
            if ( (mousexPos > 128 &&  mousexPos < 170) && ( mouseyPos > 100 && mouseyPos < 150)) 
            {
                cheat();
            }
            if ( inMenu && (mousexPos > 210 &&  mousexPos < 400) && ( mouseyPos > 200 && mouseyPos < 260)) 
            {
                newGame();
            }
            if ( inMenu && (mousexPos > 220 &&  mousexPos < 372) && ( mouseyPos > 325 && mouseyPos < 390)) 
            {
                clickHelp();
            }

            //help
            if ( inHelp && (mousexPos > 228 &&  mousexPos < 335) && ( mouseyPos > 497 && mouseyPos < 510)) 
            {
                inMenu = true;
                inHelp = false;
                repaint();
            }

            if ( (inLoseScreen || inWinScreen ) &&(mousexPos > 395 &&  mousexPos < 590) && ( mouseyPos > 465 && mouseyPos < 487)) 
            {
                inMenu = true;
                inLoseScreen = false;
                inWinScreen = false;
                inGame = false;
                repaint();
            }
            // mode Screen
            if ( inMenu && (mousexPos > 225 &&  mousexPos < 418) && ( mouseyPos > 260 && mouseyPos < 315)) 
            {
                inMenu = false;
                inLoseScreen = false;
                inWinScreen = false;
                inGame = false;
                inMode = true;
                repaint();
            }
            //easy
            if ( inMode && (mousexPos > 190 &&  mousexPos < 375) && ( mouseyPos > 85 && mouseyPos < 137)) 
            {
                mode = 1;
                easy = true;
                medium = false;
                hard = false;
                extreme = false;
                repaint();
            }
            //medium
            if ( inMode && (mousexPos > 195 &&  mousexPos < 480) && ( mouseyPos > 160 && mouseyPos < 210)) 
            {
                mode = 2;
                easy = false;
                medium = true;
                hard = false;
                extreme = false;
                repaint();
            }
            //hard
            if ( inMode && (mousexPos > 195 &&  mousexPos < 405) && ( mouseyPos > 237 && mouseyPos < 285)) 
            {
                mode = 3;
                easy = false;
                medium = false;
                hard = true;
                extreme = false;
                repaint();
            }
            //extreme
            //             if ( inMode && (mousexPos > 197 &&  mousexPos < 485) && ( mouseyPos > 310 && mouseyPos < 349)) 
            //             {
            //                 mode = 4;
            //                 easy = false;
            //                 medium = false;
            //                 hard = false;
            //                 extreme = true;
            //                 repaint();
            //             }
            //Back to menu in mode screen
            if ( inMode && (mousexPos > 197 &&  mousexPos < 410) && ( mouseyPos > 391 && mouseyPos < 410)) 
            {
                inMode = false;
                inMenu = true;
                doubleScore = false;
                score = 0;
                lost = true;
                repaint();
            }
        }

    }

}

