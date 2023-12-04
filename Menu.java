import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shows the player the different difficulties it can pick
 * 
 * @Andy
 * @version December 2023
 */
public class Menu extends Actor
{
    int speed = 50;
    GreenfootImage menu = new GreenfootImage("images/game_mode_select.png");
    /**
     * creates the image
     */
    public Menu()
    {
        menu.scale(300,300);
        setImage(menu);
    }
    /**
    * Moves onto the screen, and out
    */
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
