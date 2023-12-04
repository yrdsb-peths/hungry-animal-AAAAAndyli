import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Falls from the top of the screen, and then increases score if the player catches it
 * , and decreases score if the player doesn't
 * 
 * @author Andy
 * @version December 2023
 */
public class Apple extends Actor
{
    /**
     * Speed the apple moves at
     */
    public int speed = 1;
    /**
     * constructor creates the image of the apple
     */
    public Apple()
    {
        GreenfootImage appleImage = new GreenfootImage("images/apple.png");
        appleImage.scale(75,75);   
        setImage(appleImage);
    }
    /**
     * Act - do whatever the Apple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * Moves the apple down. Apple moves faster down as the score increases.
     */
    public void act()
    {
        int x = getX();
        int y = getY()+speed;
        setLocation(x,y);
        MyWorld world = (MyWorld) getWorld();
        speed = world.highestScore/10+1;
        if(getY() >= world.getHeight())
        {
            world.score -= 1;
            world.gameOver();
            world.removeObject(this);
            if(world.score >= 0)
            {
                world.createApple();
            }
        }
        if(world.score < 0)
        {
            setLocation(x, 100000);
        }
    }
}
