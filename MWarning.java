import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MWarning here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MWarning extends Actor
{
    /**
     * Act - do whatever the MWarning wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
