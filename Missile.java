import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple enemy that spawns randomly, damages player if touching player
 * and moves from one side of the screen to the other
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Missile extends Actor
{
    /**
     * Act - do whatever the Missile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage MR = new GreenfootImage("images/rocket.png");      
    GreenfootImage ML = new GreenfootImage("images/rocket.png"); 
    private int direction = -1;
    public Missile(int direction)
    {
        ML.mirrorHorizontally();
        this.direction = direction;
    }
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();        
        if(direction == 0)
        {
            setImage(MR);
            move(7);
        }
        else
        {
            setImage(ML);
            move(-7);
        }
        if(isTouching(Elephant.class))
        {
            world.score -= 5;
            world.gameOver();
            move(5000);
        }
        if(getX() > 700 || getX() < -100)
        {
            world.removeObject(this);
        }
    }
}
