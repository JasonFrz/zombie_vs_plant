
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
