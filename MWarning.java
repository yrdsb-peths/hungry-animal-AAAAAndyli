import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Spawns to show where the Missile is coming from
 * 
 * @author Andy
 * @version December 2023
 */
public class MWarning extends Actor
{
    /**
     * Destroys itself
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();   
        if(!world.mwarningOnScreen)
        {
            world.removeObject(this);
        }
    }
}
