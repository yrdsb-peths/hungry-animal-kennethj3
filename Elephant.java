import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Elephant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Elephant extends Actor
{
    GreenfootSound sound;
    GreenfootImage[] imagesRight = new GreenfootImage[8];
    GreenfootImage[] imagesLeft = new GreenfootImage[8];
    SimpleTimer animationTimer = new SimpleTimer(); 
    String facing = "right";
    public Elephant(){
        sound = new GreenfootSound("elephantcub.mp3");
        
        for(int i = 0; i < imagesRight.length; i++)
        {
            imagesRight[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            imagesRight[i].scale(75,75);
        }
        
        for(int i = 0; i < imagesLeft.length; i++)
        {
            imagesLeft[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            imagesLeft[i].mirrorHorizontally();
            imagesLeft[i].scale(75,75);
        }
        
        animationTimer.mark();
        
        setImage(imagesRight[0]);
        
        
    }
    
    /**
     * Act - do whatever the Elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.isKeyDown("d"))
        {
            move(3);
            facing = "right";
        }
        if(Greenfoot.isKeyDown("a"))
        {
            move(-3);
            facing = "left";
        }
        
        eat();
        animations();
    
    }
    
    int i = 0;
    public void animations()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("right"))
        {
            setImage(imagesRight[i]);
        }
        else
        {
            setImage(imagesLeft[i]);
        }
        
        i++;
        if(i > 7)
        {
            i = 0;
        }
        
        
    }
    
    public void eat()
    {
        if(isTouching(Apple.class))
        {
            removeTouching(Apple.class);
            MyWorld world = (MyWorld) getWorld();
            world.spawnApple();
            world.increaseScore();
            sound.play();
        }
    }
}
