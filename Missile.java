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
    SimpleTimer animationTimer = new SimpleTimer();
    static GreenfootImage MR = new GreenfootImage("images/rocket.png");      
    GreenfootSound boom = new GreenfootSound("boom.mp3");
    private int direction = -1;
    int turn = 2;
    GreenfootImage[] missile = new GreenfootImage[3];
    boolean isSpawned = false;
    public Missile(int direction)
    {
        this.direction = direction;
        for(int i = 0; i < 3; i++)
        {
            missile[i] = new GreenfootImage("images/rocket/_a_frm" + i + ",100.png");
            missile[i].scale(64,50);
        }
    }
    public void act()
    {
        animate();
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
            boom.play();
        }
        if(getX() > 700 || getX() < -100)
        {
            world.removeObject(this);
        }
        isSpawned = true;
    }
    
    int imageIndex = 0;
    public void animate()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        setImage(missile[imageIndex]);
        imageIndex = (imageIndex + 1) % missile.length;
    }
}
