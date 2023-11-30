import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends Actor
{
    /**
     * Act - do whatever the Menu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 0;
    GreenfootImage menu = new GreenfootImage("images/game_mode_select.png");
    public Menu()
    {
        speed = 50;
        menu.scale(300,300);
        setImage(menu);
    }
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(world.isGameStarted == false&&getX() != 200)
        {
            move(speed - getX()/10);
        }
        else if(world.isGameStarted)
        {
            move((speed - ((getX()-200)/10)));
            if(getX() < 0)
            {
                world.removeObject(this);
            }
        }
    }
}
