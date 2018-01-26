import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*; 
import java.awt.*;
public class Board extends JPanel implements ActionListener
{
    private Timer timer;
    private Hero hero;
    private ArrayList<Ground> groundList;
    private ArrayList<Enemy> enemies;
    private ArrayList<MovingGround> platforms;

    private Image bgMenu;
    private Image bg;
    private Image gameOverimg;
    private Image winImage;
    private Image instructionsImg;

    Camera c=new Camera(500, 500);
    private final int IHERO_X=500;
    private final int IHERO_Y=400;
    private final int B_WIDTH=1000;//window size
    private final int B_HEIGHT=1000;//window size
    //private Spike spikeEx;
    private ArrayList<Spike> spikes;
    private ArrayList<VertSpikeLeft> VertSpikeLefts;
    private ArrayList<VertSpikeRight> vertRight;
    private WinZone winzone;

    private boolean inGame;
    private boolean inMenu;
    private boolean gameOver;
    private boolean instructions;
    private boolean win;

    private final int DELAY = 10;

    private final int[][] posEnemy={
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}
        };//locations of all enemies
    private Point posKey;

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initSpikes();
        initVertSpikes();
        initGround();
        winzone = new WinZone(3700, 100);
        //         winzone = new WinZone(1000, 250);
        winzone.makeWinZone();

        setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));

        bg = Toolkit.getDefaultToolkit().createImage("DTBG.jpg");
        bgMenu = Toolkit.getDefaultToolkit().createImage("backgroundMenu.jpg");
        gameOverimg= Toolkit.getDefaultToolkit().createImage("gameOver.png");
        winImage= Toolkit.getDefaultToolkit().createImage("winScreen.png");
        instructionsImg=Toolkit.getDefaultToolkit().createImage("instructions.png");
        hero=new Hero(IHERO_X, IHERO_Y);
        //initEnemies();
        inGame = false;
        inMenu=true;
        gameOver=false;
        instructions=false;
        win=false;
        addMouseListener(new MAdapter());
        timer=new Timer(DELAY, this);

    }

    private void initGround()
    {
        groundList=new ArrayList<Ground>();
        groundList.add(new Ground(0, 500, "ground1.jpg"));
        groundList.add(new Ground(900,700,"ground1.jpg"));
        groundList.add(new Ground(1800,500,"thinTall.png"));
        //groundList.add(new Ground(1800,500,"ground1.jpg"));
        groundList.add(new Ground(1850,700,"ground1.jpg"));
        groundList.add(new Ground(2750,500,"thinTall.png"));
        groundList.add(new Ground(2800,700,"ground1.jpg"));
        groundList.add(new Ground(3700,500,"thinTall.png"));

        platforms=new ArrayList<MovingGround>();
        platforms.add(new MovingGround(1000, 500, "thinPlatform.png", 500));
        platforms.add(new MovingGround(1950, 500, "thinPlatform.png", 500));
        platforms.add(new MovingGround(2850, 500, "thinPlatform.png", 500));
    }

    //     private void initEnemies()
    //     {
    //         enemies=new ArrayList<Enemy>();
    //         for (int[] p: posEnemy)
    //         {
    //             enemies.add(new Enemy(p[0], p[1]));
    //         }
    //     }

    private void initSpikes()
    {
        spikes=new ArrayList<Spike>();
        spikes.add(new Spike(900, 620));
        spikes.add(new Spike(1100, 620));
        spikes.add(new Spike(1300, 620));
        spikes.add(new Spike(1500, 620));
        spikes.add(new Spike(1850, 620));
        spikes.add(new Spike(2050, 620));
        spikes.add(new Spike(2250, 620));
        spikes.add(new Spike(2450, 620));
        spikes.add(new Spike(2800, 620));
        spikes.add(new Spike(3000, 620));
        spikes.add(new Spike(3200, 620));
        spikes.add(new Spike(3400, 620));
    }

    private void initVertSpikes()
    {
        VertSpikeLefts = new ArrayList<VertSpikeLeft>();
        VertSpikeLefts.add(new VertSpikeLeft(1720, 505));
        VertSpikeLefts.add(new VertSpikeLeft(2670, 505));
        VertSpikeLefts.add(new VertSpikeLeft(3620, 505));

        vertRight=new ArrayList<VertSpikeRight>();
        vertRight.add(new VertSpikeRight(900, 505));
        vertRight.add(new VertSpikeRight(1850, 505));
        vertRight.add(new VertSpikeRight(2800, 505));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (inGame)
        {
            drawObjects(g);
        }
        else if (inMenu)
        {
            drawMenu(g);
        }
        else if (gameOver)
        {
            drawGameOver(g);
        }
        else if (instructions)
        {
            drawInstructions(g);
        }
        else if (win)
        {
            drawWin(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawMenu (Graphics g)
    {
        g.drawImage(bgMenu, 0, 0, this);
    }

    private void drawInstructions(Graphics g)
    {
        g.drawImage(instructionsImg, 0, 0, this);
        System.out.println("Draw Image Instructions");
    }

    private void drawGameOver (Graphics g)
    {
        g.drawImage(gameOverimg, 0, 0, this);
    }

    private void drawWin (Graphics g)
    {
        g.drawImage(winImage, 0, 0, this);
    }

    private void drawObjects(Graphics g)
    {

        g.drawImage(bg, 0, 0, this);
        g.drawImage(winzone.getImage(), winzone.getX(), winzone.getY(), this);
        for(Ground gr:groundList)
        {
            if (gr.isVisible())
            {
                gr.makeGround();
                g.drawImage(gr.getImage(), gr.getX(), gr.getY(), this);
            }
        }
        for (MovingGround gr:platforms)
        {
            gr.makeGround();
            g.drawImage(gr.getImage(), gr.getX(), gr.getY(), this);
        }
        if (hero.isVisible())
        {
            hero.makeHero();
            g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        }
        for (Spike sp: spikes)
        {
            sp.makeSpike();
            g.drawImage(sp.getImage(), sp.getX(), sp.getY(), this);
        }
        for (VertSpikeLeft vp: VertSpikeLefts)
        {
            vp.makeVertSpikeLeft();
            g.drawImage(vp.getImage(), vp.getX(), vp.getY(), this);
        }
        for (VertSpikeRight vr: vertRight)
        {
            vr.makeVertSpikeRight();
            g.drawImage(vr.getImage(), vr.getX(), vr.getY(), this);
        }

        //         for (Enemy e: enemies)
        //         {
        //             if (e.isVisible())
        //             {
        //                 e.makeEnemy();
        //                 g.drawImage(e.getImage(), e.getX(), e.getY(), this);
        //             }
        //         }
        g.setColor(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

        inGame();

        updateHero();
        //         updateEnemies();
        //         updateSpikes();
        updateCamera();
        checkCollisions();

        repaint();
    }

    private void inGame() 
    {
        if (!inGame) {
            timer.stop();
        }
        else
        {
            timer.start();
        }
    }

    private void updateHero() 
    {
        winzone.move(c);
    }

    private void updateCamera()
    {
        c.move();
        c.gravity(hero.gravity);
        for (Spike sp:spikes)
        {
            sp.move(c);
        }
        for (VertSpikeLeft vp : VertSpikeLefts)
        {
            vp.move(c);
        }
        for (VertSpikeRight vr : vertRight)
        {
            vr.move(c);
        }
        updateGround();
    }

    //     private void updateSpikes()
    //     {
    //         spikeEx.move(c);
    //     }

    private void updateGround()
    {
        for (Ground ground : groundList)
        {
            ground.move(c);
        }
        for(MovingGround g:platforms)
        {
            g.moveGround(c);
            g.move(c);
        }
    }

    //     private void updateEnemies()
    //     {
    //         for (int i = 0; i < enemies.size(); i++) 
    //         {
    //             Enemy a = enemies.get(i);
    //             if (a.isVisible()) 
    //             {
    //                 a.move(c);
    //             } 
    //             else 
    //             {
    //                 enemies.remove(i);
    //             }
    //         }
    //     }

    public void checkCollisions() 
    {
        //         for (Enemy e : enemies) 
        //         {
        //             Rectangle r2 = e.getBounds();
        // 
        //             if (r3.intersects(r2)) 
        //             {
        //                 e.setVisible(false);
        //             }
        //         }
        //         checkHorizontalCollide();
        Rectangle r3 = hero.getBounds();
        boolean grav=true;
        Rectangle r4;
        for (Ground ground: groundList)
        {
            r4=ground.getBounds();
            if (r3.intersects(r4))
            {
                grav=false;
            }
        }
        for (MovingGround gr:platforms)
        {
            r4=gr.getBounds();
            if (r3.intersects(r4))
            {
                grav=false;
            }
        }
        if (!grav)
        {
            hero.gravityOff(c);
        }
        else
        {
            hero.gravityOn(c);
        }
        Rectangle r5;
        for (Spike sp: spikes)
        {
            r5=sp.getBounds();
            if(r5.intersects(r3))
            {
                gameOver=true;
                inGame=false;
                inMenu=false;
                instructions=false;
                win = false;
            }
        }
        Rectangle r6;
        for (VertSpikeLeft vp: VertSpikeLefts)
        {
            r6 = vp.getBounds();
            if(r6.intersects(r3))
            {
                gameOver=true;
                inGame=false;
                inMenu=false;
                instructions=false;
                win=false;
            }
        }
        for (VertSpikeRight vr: vertRight)
        {
            r6 = vr.getBounds();
            if(r6.intersects(r3))
            {
                gameOver=true;
                inGame=false;
                inMenu=false;
                instructions=false;
                win=false;
            }
        }
        Rectangle winner=winzone.getBounds();
        if (r3.intersects(winner))
        {
            win=true; 
            instructions=false;
            inGame=false;
            inMenu=false;
            gameOver=false;
        }
    }

    private void checkHorizontalCollide()
    {
        //         int leftHero=hero.getX();
        //         int rightHero=hero.getX()+hero.getWidth();
        //         int topHero=hero.getY();
        //         int botHero=hero.getY()+hero.getHeight();
        //         boolean xOff=false;
        //         
        //         for (Ground ground: groundList)
        //         {
        //             int leftground=ground.getX();
        //             int rightGround=ground.getX()+ground.getWidth();
        //             int topGround=ground.getY();
        //             int botGround=ground.getY()+ground.getHeight();
        // 
        //             System.out.println("leftHero = "+leftHero+" rightHero = "+rightHero+" leftground = "+leftground+" rightGround = "+rightGround);
        //             if (( rightHero>leftground && !(botHero>topGround||topHero<botGround))||(leftHero>rightGround && !(topHero<botGround||botHero>topGround)))
        //             {
        //                 xOff=true;
        //                 System.out.println("intersects");
        //             }
        // 
        //         }
        //         if (xOff)
        //         {
        //             c.togXMotion(false);
        //         }
        //         else
        //         {
        //             c.togXMotion(true);
        //         }
        Rectangle left=new Rectangle (hero.getX(), hero.getY(),5, hero.getHeight()-5);
        Rectangle right=new Rectangle (hero.getX()+hero.getWidth()-5, hero.getY(),5, hero.getHeight()-5);
        Rectangle r;
        boolean moveLeft=true;
        boolean moveRight=true;
        for (Ground gr:groundList)
        {
            r=gr.getBounds();
            if (left.intersects(r))
            {
                moveLeft=false;
            }
            if (right.intersects(r))
            {
                moveRight=false;

            }
        }
        if (!moveLeft)
        {
            c.xLeft(false);
        }
        if (!moveRight)
        {
            c.xRight(false);
        }
        if (moveLeft)
        {
            c.xLeft(true);
        }
        if (moveRight)
        {
            c.xRight(true);
        }
    }
    private class MAdapter extends MouseAdapter 
    {

        //@override//restart the game if Mouse is clicked
        public void mousePressed(MouseEvent e) 
        {

            if(e.getY()<=400&&e.getY()>=325 && e.getX()>=400&&e.getX()<=600&&inMenu)
            {
                inGame = true; 
                //initEnemies(); 
                inMenu=false;
                instructions=false;
                win=false;
                gameOver=false;
                hero.setVisible(true);
                timer.start(); 
            }
            else if (e.getY()>=400&&e.getY()<=550 && e.getX()>=400&&e.getX()<=600&&inMenu)
            {
                instructions=true;
                inMenu=false;
                inGame=false;
                win=false;
                gameOver=false;
            }

            //             else if(e.getY()>=0 && win)
            //             {
            //                 instructions=false;
            //                 inMenu=true;
            //                 inGame=false;
            //                 win=false;
            //                 gameOver=false;
            //             }
            //             else if (e.getY()>=0 && gameOver)
            //             {
            //                 instructions=false;
            //                 inMenu=true;
            //                 inGame=false;
            //                 win=false;
            //                 gameOver=false;
            //             }
            //             else if (e.getY()>=0 && instructions)
            //             {
            //                 instructions=true;
            //                 inMenu=false;
            //                 inGame=false;
            //                 win=false;
            //                 gameOver=false;
            //             }
        }

    }

    private class TAdapter extends KeyAdapter 
    {
        @Override
        public void keyReleased(KeyEvent e) 
        {
            c.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            c.keyPressed(e);
        }
        //         @Override        
        //         public void keyTyped(KeyEvent e)
        //         {
        //             hero.keyTyped(e);
        //         }
    }

}