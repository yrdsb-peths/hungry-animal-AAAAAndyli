import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple enemy that spawns randomly, damages player if touching player
 * and moves from one side of the screen to the other
 * 
 * @author Andy
 * @version December 2023
 */
public class EnemyH extends Actor
{
    /**
     * Act - do whatever the EnemyH wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage crocR = new GreenfootImage("images/alligator.png");      
    GreenfootImage crocL = new GreenfootImage("images/alligator.png"); 
    GreenfootSound bite = new GreenfootSound("bite.mp3");
    private int direction = -1;
    /**
     * Sets the direction of the crocodile
     */
    public EnemyH(int direction)
    {
        crocL.mirrorHorizontally();
        this.direction = direction;
    }
    /**
     * Controls the movement and checks if it is touching the elephant
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();        

        if(direction == 0)
        {
            setImage(crocR);
            move(world.highestScore/10+1);
        }
        else
        {
            setImage(crocL);
            move(-1*world.highestScore/10-1);
        }
        if(isTouching(Elephant.class))
        {
            world.score -= 5;
            world.gameOver();
            move(5000);
            bite.play();
        }
        if(world.gameOver)
        {
            setLocation(0, 100000);
        }
        if(getX() > 600 || getX() < 0)
        {
            world.removeObject(this);
        }

    }
}
