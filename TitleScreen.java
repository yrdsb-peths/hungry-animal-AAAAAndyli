import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen :)
 * 
 * @author Andy
 * @version Nov 2023
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label("Hungry Elephant", 70);
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 

        addObject(titleLabel, getWidth()/2, 50);
        prepare();
    }

    //Main world act loop
    public void act()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Elephant elephant = new Elephant();
        addObject(elephant,300,300);
        elephant.getRotation();
        Label label = new Label("Use Arrow keys to move", 40);
        addObject(label,getWidth()/2,159);
        Label label2 = new Label("Use up Arrow to jump", 30);
        addObject(label2,getWidth()/2,194);
        Label label3 = new Label("Press Space to start", 40);
        addObject(label3,getWidth()/2,373);
    }
}
