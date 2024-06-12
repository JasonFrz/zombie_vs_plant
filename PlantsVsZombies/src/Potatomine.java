import java.util.Timer;
import java.util.TimerTask;

public class Potatomine extends Plant{
    public Timer shootTimer;

    public Potatomine(Game parent, int x,int y){
        super(parent, x, y);
        setHealth(1);
        shootTimer = new Timer();
        checkingForZombies();
    }

    private void checkingForZombies() {
        shootTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (checkForZombies()) {
                    explode();
                }
            }
        }, null, health);
    }

    private boolean checkForZombies() {
        return getGame().isZombieAt(getX(), getY());
    }

    private void explode() {
        // Logic to remove Potatomine from the game
        // parent.removePlant(this);
        getGame().removePlant(this);
        
        // Logic to kill the zombie at (x, y)
        // parent.killZombieAt(getX(), getY()); // This is a placeholder method, implement it in your Game class
        getGame().killZombieAt(getX(), getY());

        
        // Cancel the timer since Potatomine has exploded
        shootTimer.cancel();
    }

    @Override
    public void stop(){
        shootTimer.cancel();
    }

    public void resume(){
        shootTimer = new Timer();
        checkingForZombies();
    }

    public void pause(){
        shootTimer.cancel();
    }
}
