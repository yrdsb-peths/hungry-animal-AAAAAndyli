import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Spawns to show where the Croc is coming from
 * 
 * @author Andy
 * @version December 2023
 */
public class Warning extends Actor
{
    /**
     * Destroys itself
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();   
        if(!world.warningOnScreen)
        {
            world.removeObject(this);
        }
    }
}
