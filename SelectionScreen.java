import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionScreen extends World
{
    public SelectionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }

    //Main world act loop
    public void act()
    {
        if(Greenfoot.isKeyDown("1"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
            gameWorld.gameMode = 0;
        }
        if(Greenfoot.isKeyDown("2"))
        {
            MyWorld gameWorld = new MyWorld();
            gameWorld.gameMode = 0;
            Greenfoot.setWorld(gameWorld);
        }
        if(Greenfoot.isKeyDown("3"))
        {
            MyWorld gameWorld = new MyWorld();
            gameWorld.gameMode = 0;
            Greenfoot.setWorld(gameWorld);
        }
        if(Greenfoot.isKeyDown("4"))
        {
            MyWorld gameWorld = new MyWorld();
            gameWorld.gameMode = 0;
            Greenfoot.setWorld(gameWorld);
        }
        if(Greenfoot.isKeyDown("5"))
        {
            MyWorld gameWorld = new MyWorld();
            gameWorld.gameMode = 0;
            Greenfoot.setWorld(gameWorld);
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

    }
}
