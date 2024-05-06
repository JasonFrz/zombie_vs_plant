
public abstract class Plant {

    public int health = 200;

    public int x;
    public int y;

    public OnGame gp;


    public Plant(OnGame parent,int x,int y){
        this.x = x;
        this.y = y;
        gp = parent;
    }

    public void stop(){}

}
