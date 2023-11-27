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
    private int direction = -1;
    public EnemyH(int direction)
    {
        this.direction = direction;
    }
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();        
        if(getY() > 600 || getY() < 0)
        {
            world.removeObject(this);
        }
        if(direction == 0)
        {
            move(10);
        }
        else
        {
            
        }
    }
}
