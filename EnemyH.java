import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple enemy that spawns randomly, damages player if touching player
 * and moves from one side of the screen to the other
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyH extends Actor
{
    /**
     * Act - do whatever the EnemyH wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage crocR = new GreenfootImage("images/alligator.png");      
    GreenfootImage crocL = new GreenfootImage("images/alligator.png"); 
    private int direction = -1;
    public EnemyH(int direction)
    {
        crocL.mirrorHorizontally();
        this.direction = direction;
    }
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();        
        if(direction == 0)
        {
            setImage(crocR);
            move(world.highestScore/5+1);
        }
        else
        {
            setImage(crocL);
            move(-1*world.highestScore/5-1);
        }
        if(isTouching(Elephant.class))
        {
            world.score -= 5;
            world.gameOver();
            move(5000);
        }
        if(getX() > 600 || getX() < 0)
        {
            world.removeObject(this);
        }
    }
}
