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
    private int direction = -1;
    int turn = 2;
    boolean isSpawned = false;
    public Missile(int direction)
    {
        this.direction = direction;
    }
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();  
        if(!isSpawned)
        {
            turnTowards(world.eleX, world.eleY);
        }   
        move(7);
        if(direction == 0)
        {
            if(world.eleY > getY())
            {
                turn(turn);
            }
            else
            {
                turn(-1*turn);
            }
        }
        else
        {
            if(world.eleY > getY())
            {
                turn(-1*turn);
            }
            else
            {
                turn(turn);
            }
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
        isSpawned = true;
    }
}
