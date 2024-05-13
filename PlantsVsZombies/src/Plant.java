
public abstract class Plant {

    public int health = 200;

    public int x;
    public int y;

    public Game game;


    public Plant(Game parent,int x,int y){
        this.x = x;
        this.y = y;
        game = parent;
    }

    public void stop(){}

    public void setHealth (int h){
        this.health = h;
    }

}
