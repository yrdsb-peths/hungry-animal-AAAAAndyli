import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Elephant extends Actor
{
    int x = 0;
    int y = 0;
    int jumpHeight = 0;
    public void act()
    {
        x = getX();
        y = getY();
        if(Greenfoot.isKeyDown("left"))
        {
            move(-5);
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(5);
        }
        if(Greenfoot.isKeyDown("up"))
        {
            jump();
        }
        setLocation(x, y+jumpHeight);
        if(jumpHeight == 0)
        {
            if(y<300)
            {
                jumpHeight += 5;
            }
        }
        else if(jumpHeight < 0)
        {
            jumpHeight += 1;
        }
        else if(jumpHeight > 0)
        {
            jumpHeight -= 1;
        }
        eat();
    }
    public void eat()
    {
        if(isTouching(Apple.class))
        {
            removeTouching(Apple.class);
            MyWorld world = (MyWorld) getWorld();
            world.createApple();
            world.increaseScore();
        }
    }
    public void jump()
    {
        jumpHeight = -5;
    }
}
