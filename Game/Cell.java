package Game;

public class Cell {
    int x;
    int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    public void SetVal(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    public boolean equals(Object o) {

        Cell c = (Cell) o;
        if(this.x == c.getX() && this.y == c.getY())
            return true;
        return false;
    }
}
