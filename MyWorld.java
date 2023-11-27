import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author Andy, following tutorial from Mr.Chan 
 * @version November 21
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public int score = 0;
    Label scoreLabel;
    SimpleTimer enemyTimer = new SimpleTimer();
    boolean gameOver = false;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        Elephant elephant = new Elephant();
        addObject(elephant, 300, 300);
        
        scoreLabel = new Label(0,80);
        addObject(scoreLabel, 50, 50);
        
        createApple();
        enemyTimer.mark();
    }
    
    public void act()
    {
        if(enemyTimer.millisElapsed() > 10000-10*score&&!gameOver)
        {
            createEnemyH();
            enemyTimer.mark();
        }
    }
    
    public void gameOver()
    {
        Label gameOverLabel = new Label ("Game Over", 100);
        addObject(gameOverLabel, 300, 200);
        gameOver = true;
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
        int x = Greenfoot.getRandomNumber(2);
        EnemyH croc = new EnemyH(x);
        if(x == 0)
        {
            addObject(croc, 0, 330);
        }
        else
        {
            addObject(croc, 600, 330);
        }
    }
}
