import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Elephant extends Actor
{
    int x = 0;
    int y = 0;
    double hMovement = 0;
    int jumpHeight = 0;
    double gravityModifier = 0;
    int dashable = 0;
    boolean isGrounded = true;
    boolean peakJump = false;
    GreenfootSound elephantSound = new GreenfootSound("elephantcub.mp3");
    GreenfootImage[] idleRight = new GreenfootImage[8];
    GreenfootImage[] idleLeft = new GreenfootImage[8];
    
    //direction elephant is facing
    String facing = "right";
    
    public Elephant()
    {
        for(int i = 0; i < 8; i++)
        {
            idleRight[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            idleRight[i].scale(75,75);
        }
        for(int i = 0; i < 8; i++)
        {
            idleLeft[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            idleLeft[i].mirrorHorizontally();
            idleLeft[i].scale(75,75);
        }
        setImage(idleRight[0]);
    }
    
    
    public void act()
    {
        x = getX();
        y = getY();
        if(Greenfoot.isKeyDown("left"))
        {
            hMovement -= 1.2;
            facing = "left";
        }
        if(Greenfoot.isKeyDown("right"))
        {
            hMovement += 1.2;
            facing = "right";
        }
        if(Greenfoot.isKeyDown("up"))
        {
            isGrounded = false;
            jump();
            gravityModifier-=0.5;
        }
        //jump code
        if((peakJump==true&&isGrounded == false))
        {
            gravityModifier+=0.9;
        }
        
        if(Greenfoot.isKeyDown("space"))
        {
            dash();
        }
        setLocation(x+(int)hMovement, y+jumpHeight);
        if(y< 300)
        {
            if(!Greenfoot.isKeyDown("up"))
            {
                peakJump = true;
                jumpHeight += gravityModifier;
            }
            if(peakJump)
            {
                jumpHeight = 0;
                peakJump = true;
                jumpHeight += gravityModifier;
            }
        }
        if(getY() > 300)
        {
            gravityModifier = 0;
            jumpHeight = 0;
            setLocation(x, 300);
            isGrounded = true;
            peakJump = false;
        }
        if(hMovement < 0)
        {
            hMovement++;
        }
        else if (hMovement > 0)
        {
            hMovement--;
        }
        eat();
        dashable++;
        animateElephant();
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
            jumpHeight = -16;
            jumpHeight -= gravityModifier;
        }
        if(getY() < 200&&isGrounded == false)
        {
            peakJump = true;
        }
    }
    
    public void dash()
    {
        if(dashable > 100)
        {
            jumpHeight = 0;
            gravityModifier = 0;
            peakJump = true;
            if(facing == "left")
            {
                hMovement = -20;
            }
            else
            {
                hMovement = 20;
            }
            dashable = 0;
        }
    }
    
    int imageIndex = 0;
    public void animateElephant()
    {
        if(facing.equals("right"))
        {
            setImage(idleRight[imageIndex]);
            imageIndex = (imageIndex + 1) % idleRight.length;
        }
        else
        {
            setImage(idleLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % idleLeft.length;
        }
    }
}
