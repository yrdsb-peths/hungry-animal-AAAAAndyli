import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world where the game takes place
 * 
 * @author Andy, following tutorial from Mr.Chan 
 * @version November 21
 */
public class MyWorld extends World
{
    /**
     * The score is the number of health the elephant has that increases or decreases depending
     * on whether they touch enemies and apples
     */
    public int score = 0;
    /**
     * Highest score in a round.
     */
    public int highestScore = score;
    Label scoreLabel;
    Label highScoreLabel;
    Label gameOverLabel = new Label("", 100);
    
    SimpleTimer enemyTimer = new SimpleTimer();
    SimpleTimer missileTimer = new SimpleTimer();
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer worldTimer = new SimpleTimer();
        
    GreenfootSound loss = new GreenfootSound("loss.mp3");
    GreenfootSound eSong = new GreenfootSound("EasySong.mp3");
    GreenfootSound nSong = new GreenfootSound("NormalSong.mp3");
    GreenfootSound hSong1 = new GreenfootSound("HardSongIntro.mp3");
    GreenfootSound hSong2 = new GreenfootSound("HardSong.mp3");
    GreenfootSound xSong = new GreenfootSound("ExtremeSong.mp3");
    GreenfootSound empty = new GreenfootSound("silence.mp3");
    GreenfootSound waiting = new GreenfootSound("waitingMusic.mp3");
    /**
     * gameOver is if the game is over
     */
    public boolean gameOver = false;
    boolean warningOnScreen = false;
    boolean mwarningOnScreen = false;
    boolean isSongPlaying = false;
    boolean croc = false;
    boolean miss = false;
    boolean apple = false;
    boolean isGameStarted = false;
    boolean isTimer = false;
    boolean isSecret = false;
    boolean isSecretStarted = false;
    
    /**
     * X value of the elephant
     */
    public int eleX = 0;
    /**
     * Y value of the elephant
     */
    public int eleY = 0;

    int song = -1;
    int dir = 0;
    int mdir = 0;
    int intervals = 0;
    int height = 0;
    int volume = 75;
    int wVolume = 0;
    
    GreenfootImage normalImage = new GreenfootImage("images/nmode.png");
    GreenfootImage extremeImage = new GreenfootImage("images/emode.png");
    
    Elephant elephant = new Elephant();
    Menu menu = new Menu();
    /**
     * Constructor for objects of class MyWorld.
     * Generates the new world, and prepares the world for gaming.
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels
        super(600, 400, 1, false);
        
        addObject(elephant, 300, 300);
        addObject(menu, 0, 200);
        
        scoreLabel = new Label(0,40);
        addObject(scoreLabel, 50, 30);
        
        highScoreLabel = new Label(0,40);
        addObject(highScoreLabel, 50, 80);
        Greenfoot.setSpeed(51);
        enemyTimer.mark();
        missileTimer.mark();
        timer.mark();
        worldTimer.mark();
        
        waiting.playLoop();
        waiting.setVolume(0);
    }
    
    /**
     * Runs the game
     */
    public void act()
    {
        //sets volume
        eSong.setVolume(volume);
        nSong.setVolume(volume);
        hSong1.setVolume(volume);
        hSong2.setVolume(volume);
        xSong.setVolume(volume);
        waiting.setVolume(wVolume);
        if(!isGameStarted)
        {
            worldTimer.mark();
            timer.mark();
            enemyTimer.mark();
            missileTimer.mark();
            playMusic();
            stopMusic();
            pickDifficulty();
            if(wVolume < volume)
            {
                wVolume++;
            }
            else if(wVolume > volume)
            {
                wVolume--;
            }
        }
        else
        {
            playMusic();
            if(wVolume > 0)
            {
                wVolume--;
            }
        }
        //changes Volume
        if((Greenfoot.isKeyDown("=")||Greenfoot.isKeyDown("+"))&&volume < 100)
        {
            volume++;
        }
        if(Greenfoot.isKeyDown("_")||Greenfoot.isKeyDown("-")&& volume > 0)
        {
            volume--;
        }

        eleX = elephant.getX();
        eleY = elephant.getY();
        
        highScoreLabel.setValue(highestScore); 
        isSongPlaying = eSong.isPlaying() || nSong.isPlaying() || hSong1.isPlaying() || hSong2.isPlaying() || xSong.isPlaying();
        
        if(isTimer&&!gameOver)
        {
            highestScore = (worldTimer.millisElapsed())/1000;
        }
        else if(highestScore < score)
        {
            highestScore = score;
        }
        
        if(timer.millisElapsed() > 4000-intervals*50 || isSecret &&timer.millisElapsed() > 1000-intervals*5)
        {            
            spawnEnemies();
        }
        if(gameOver&&Greenfoot.isKeyDown("enter"))
        {
            restart();
        }
    }
    
    /**
     * Method used to check if the game is over, and if it is, it will display the game being over.
     */
    public void gameOver()
    {
        scoreLabel.setValue(score);
        if(score < 0)
        {
            worldTimer.mark();
            scoreLabel.setValue(0);
            gameOverLabel.setValue("Game Over\n<Enter>");
            addObject(gameOverLabel, 300, 200);
            gameOver = true;
            croc = false;
            miss = false;
            warningOnScreen = false;
            mwarningOnScreen = false;
            loss.play();
            
            stopMusic();
            song = -1;
        }
    }
    /**
     * Restarts the round
     */
    public void restart()
    {
        //resets all the boolean statements, and other variables
        gameOver = false;
        warningOnScreen = false;
        mwarningOnScreen = false;
        croc = false;
        miss = false;
        apple = false;
        isGameStarted = false;
        isTimer = false;
        isSecret = false;
        isSecretStarted = false;
        worldTimer.mark();
        timer.mark();
        enemyTimer.mark();
        missileTimer.mark();
        highestScore = 0;
        score = 0;
        intervals = 0;
        gameOverLabel.setValue("");
        setBackground(normalImage);
    }
    /**
     * Increases the score, and displays the score
     */
    public void increaseScore()
    {
        score++;
        scoreLabel.setValue(score);
    }
    /**
     * Creates an apple if the apple variable is true
     */
    public void createApple()
    {
        if(apple)
        {
            Apple apple = new Apple();
            int x = Greenfoot.getRandomNumber(600);
            addObject(apple, x, 0);
        }
    }
    /**
     * Creates a warning, and then a crocodile
     */
    public void createEnemyH()
    {
        Warning warning = new Warning();
        if(!warningOnScreen)
        {
            enemyTimer.mark();
            dir = Greenfoot.getRandomNumber(2);
        }
        EnemyH croc = new EnemyH(dir);
        if(dir == 0)
        {
            addObject(warning, 20, 200);
            warningOnScreen = true;
            if(enemyTimer.millisElapsed() > 1000-highestScore*enemyTimer.millisElapsed()/10)
            {

                warningOnScreen = false;
                addObject(croc, 0, 330);
                enemyTimer.mark();
            }
        }
        else
        {
            addObject(warning, 580, 200);
            warningOnScreen = true;
            if(enemyTimer.millisElapsed() > 1000-highestScore*enemyTimer.millisElapsed()/10)
            {
                warningOnScreen = false;
                addObject(croc, 600, 330);
                enemyTimer.mark();
            }
        }
    }
    /**
     * creates a warning, and then a missile
     */
    public void createEnemyMH()
    {
        MWarning missilewarning = new MWarning();
        Missile missile = new Missile(mdir);
        if(!mwarningOnScreen)
        {
            missileTimer.mark();
            mdir = Greenfoot.getRandomNumber(2);
            height = Greenfoot.getRandomNumber(400);
        }
        if(mdir == 0)
        {
            addObject(missilewarning, 20, height);
            mwarningOnScreen = true;
            if(missileTimer.millisElapsed() > 1000)
            {
                missileTimer.mark();
                addObject(missile, -20, height);
                mwarningOnScreen = false;
            }
        }
        else
        {
            addObject(missilewarning, 580, height);
            mwarningOnScreen = true;
            if(missileTimer.millisElapsed() > 1000)
            {
                missileTimer.mark();
                addObject(missile, 620, height);
                mwarningOnScreen = false;
            }
        }
    }
    /**
     * Plays music
     */
    public void playMusic()
    {    
        if(!isSongPlaying)
        {
            if(song == 0)
            {
                eSong.play();
            }
            else if(song == 1)
            {
                nSong.play();
            }
            else if(song == 2)
            {
                hSong1.play();
                song = 3;
            }
            else if(song == 3)
            {
                hSong2.play();
            }
            else if(song == 4)
            {
                /*Please do not deduct marks for this absolute travesty, 
                 * I couldn't find any other way to fix the issue
                 */
                empty.play();
                xSong.playLoop();
                stopMusic();
                xSong.playLoop();
                stopMusic();
                xSong.playLoop();
            }
        }
    }
    public void stopMusic()
    {
        eSong.stop();
        nSong.stop();
        hSong1.stop();
        hSong2.stop();
        xSong.stop();
    }
    /**
     * Chooses the difficulty of the game
     */
    public void pickDifficulty()
    {
        if(Greenfoot.isKeyDown("1"))
        {
            //easy gm
            song = 0;
            apple = true;
            croc = false;
            miss = false;
            isGameStarted = true;
            createApple();
        }
        else if(Greenfoot.isKeyDown("2"))
        {
            //normal gm
            song = 1;
            apple = true;
            croc = true;
            miss = false;
            isGameStarted = true;
            createApple();
        }
        else if(Greenfoot.isKeyDown("3"))
        {
            //hard gm
            song = 2;
            apple = true;
            croc = true;
            miss = true;
            isGameStarted = true;
            createApple();
        }
        else if(Greenfoot.isKeyDown("4"))
        {
            //missile gm
            song = 2;
            apple = false;
            croc = false;
            miss = true;
            isTimer = true;
            isGameStarted = true;
            worldTimer.mark();
        }
        else if(Greenfoot.isKeyDown("5"))
        {
            //enemy gm
            song = 2;
            apple = false;
            croc = true;
            miss = true;
            isTimer = true;
            isGameStarted = true;
            worldTimer.mark();
        }
        else if(Greenfoot.isKeyDown("6"))
        {
            //extreme difficulty, Secret
            score = 99;
            increaseScore();
            song = 4;
            apple = true;
            croc = true;
            miss = true;
            isTimer = true;
            isGameStarted = true;
            isSecret = true;
            worldTimer.mark();
            setBackground(extremeImage);
            createApple();
        }
    }
    /**
     * Spawns enemies
     */
    public void spawnEnemies()
    {
        if(timer.millisElapsed() < 21000&&isSecret&&!isSecretStarted||hSong1.isPlaying())
        {
            return;
        }
        isSecretStarted = true;        
        if(miss)
        {
            createEnemyMH();
        }
        if(croc)
        {
            createEnemyH();
        }
        timer.mark();
        if(intervals < 60)
        {
            intervals++;
        }
    }
}
