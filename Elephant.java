import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Elephant extends Actor
{
    int x = 0;
    int y = 0;
    int hMovement = 0;
    int jumpHeight = 0;
    int gravityModifier = 0;
    boolean isGrounded = true;
    boolean peakJump = false;
    public void act()
    {
        GreenfootSound elephantSound = new GreenfootSound("elephantcub.mp3");
        
        x = getX();
        y = getY();
        if(Greenfoot.isKeyDown("left"))
        {
            hMovement = -5;
        }
        if(Greenfoot.isKeyDown("right"))
        {
            hMovement = 5;
        }
        if(Greenfoot.isKeyDown("up"))
        {
            isGrounded = false;
            jump();
            gravityModifier--;
        }
        //jump code
        if((peakJump==true&&isGrounded == false))
        {
            gravityModifier++;
        }
        setLocation(x+hMovement, y+jumpHeight);
        if(y< 300)
        {
            if(!Greenfoot.isKeyDown("up"))
            {
                if(peakJump == false)
                {
                    gravityModifier = 0;
                }
                jumpHeight = 6;
                peakJump = true;
                jumpHeight += gravityModifier;
            }
        }
        if(getY() > 300)
        {
            gravityModifier =0;
            jumpHeight = 0;
            setLocation(x, 300);
            isGrounded = true;
            peakJump = false;
        }
        hMovement = 0;
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
            elephantSound.play();
        }
    }
    public void jump()
    {
        if(peakJump == false)
        {
            jumpHeight = -12;
            jumpHeight -= gravityModifier;
        }
        if(getY() < 200&&isGrounded == false)
        {
            jumpHeight = 6;
            peakJump = true;
        }
    }
}
