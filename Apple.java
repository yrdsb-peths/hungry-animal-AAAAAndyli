import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends Actor
{
    /**
     * Act - do whatever the Apple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int speed = 1;
    public void act()
    {
        int x = getX();
        int y = getY()+speed;
        setLocation(x,y);
        MyWorld world = (MyWorld) getWorld();
        speed = world.score/10;
        if(getY() >= world.getHeight())
        {
            world.score -= 5;
            world.gameOver();
            world.removeObject(this);
            if(world.score > 0)
            {
                world.createApple();
            }
        }
    }
}
