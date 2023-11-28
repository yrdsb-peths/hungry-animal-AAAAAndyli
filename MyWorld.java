import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world where the game takes place
 * 
 * @author Andy, following tutorial from Mr.Chan 
 * @version November 21
 */
public class MyWorld extends World
{
    public int score = 0;
    public int highestScore = score;
    Label scoreLabel;
    
    SimpleTimer enemyTimer = new SimpleTimer();
    SimpleTimer missileTimer = new SimpleTimer();
    SimpleTimer timer = new SimpleTimer();
        
    boolean gameOver = false;
    boolean warningOnScreen = false;
    boolean mwarningOnScreen = false;
    boolean croc = true;
    boolean miss = true;
    
    public int eleX = 0;
    public int eleY = 0;
    
    int dir = 0;
    int mdir = 0;
    int intervals = 0;
    int height = 0;
    Elephant elephant = new Elephant();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        addObject(elephant, 300, 300);
        
        scoreLabel = new Label(0,80);
        addObject(scoreLabel, 50, 50);
        
        createApple();
        enemyTimer.mark();
        missileTimer.mark();
        timer.mark();
    }
    
    public void act()
    {
        eleX = elephant.getX();
        eleY = elephant.getY();
        if(timer.millisElapsed() > 4000-intervals*50)
        {            
            if(miss)
            {
                createEnemyMH();
            }
            if(croc)
            {
                createEnemyH();
            }
            timer.mark();
            intervals++;
        }
        if(highestScore < score)
        {
            highestScore = score;
        }
    }
    
    public void gameOver()
    {
        scoreLabel.setValue(score);
        if(score < 0)
        {
            scoreLabel.setValue(0);
            Label gameOverLabel = new Label ("Game Over", 100);
            addObject(gameOverLabel, 300, 200);
            gameOver = true;
        }
    }
    
    public void increaseScore()
    {
        score++;
        scoreLabel.setValue(score);
    }
    
    public void createApple()
    {
        Apple apple = new Apple();
        int x = Greenfoot.getRandomNumber(600);
        addObject(apple, x, 0);
    }
    
    public void createEnemyH()
    {
        Warning warning = new Warning();
        if(!warningOnScreen)
        {
            enemyTimer.mark();
            dir = Greenfoot.getRandomNumber(2);
        }
        EnemyH croc = new EnemyH(dir);
        if(dir == 0)
        {
            addObject(warning, 20, 200);
            warningOnScreen = true;
            if(enemyTimer.millisElapsed() > 1000-highestScore*enemyTimer.millisElapsed()/10)
            {
                warningOnScreen = false;
                addObject(croc, 0, 330);
                enemyTimer.mark();
            }
        }
        else
        {
            addObject(warning, 580, 200);
            warningOnScreen = true;
            if(enemyTimer.millisElapsed() > 1000-highestScore*enemyTimer.millisElapsed()/10)
            {
                warningOnScreen = false;
                addObject(croc, 600, 330);
                enemyTimer.mark();
            }
        }
    }
    public void createEnemyMH()
    {
        MWarning missilewarning = new MWarning();
        if(!mwarningOnScreen)
        {
            missileTimer.mark();
            mdir = Greenfoot.getRandomNumber(2);
            height = Greenfoot.getRandomNumber(400);
        }
        Missile missile = new Missile(mdir);
        if(mdir == 0)
        {
            addObject(missilewarning, 20, height);
            mwarningOnScreen = true;
            if(missileTimer.millisElapsed() > 1000)
            {
                missileTimer.mark();
                addObject(missile, 1, height);
                mwarningOnScreen = false;
            }
        }
        else
        {
            addObject(missilewarning, 580, height);
            mwarningOnScreen = true;
            if(missileTimer.millisElapsed() > 1000)
            {
                missileTimer.mark();
                addObject(missile, 599, height);
                mwarningOnScreen = false;
            }
        }
    }
}
