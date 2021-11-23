package Game;
import java.util.*;

public class Board {
    int dimension;
    int totalCells;
    List<Cell> cells;
    List<Cell> alive;   // currently, alive cells
    List<Cell> new_born;  // cells birn in next state
    List<Cell> will_die;  // cells that will die in next evolution
    static int counter = 0;
    static int delay = 0;
    static boolean start = false;

    public Board(int dimension) {
        alive = new ArrayList<Cell>();
        cells = new ArrayList<Cell>();
        new_born = new ArrayList<Cell>();
        will_die = new ArrayList<Cell>();
        this.dimension = dimension;
        this.totalCells = dimension * dimension;

        Cell cd = new Cell(0,0);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                cd.setX(i);
                cd.setY(j);
                cells.add(cd);
            }
        }
    }

    public void reset(){
        alive.clear();
        will_die.clear();
        new_born.clear();
    }
    public boolean Draw(int x, int y){

        if(x <= dimension && x > 0 && y <= dimension && y > 0) {   // change state (alive/dead)
            Cell cd = new Cell(x-1, y-1);
            if(alive.contains(cd))  // cell is already alive
                alive.remove(cd);   // remove from list of alive
            else
                alive.add(cd);  // add to list of alive
            return true;
        }
        else
            return false;
    }
    public void Print(){
        System.out.println("-----------------------------------------");
        Cell temp = new Cell(0,0);
        if(alive.isEmpty()){
            for(int i = 0 ; i < dimension; i++){
                for(int j = 0 ; j < dimension; j++){
                    System.out.print('_');
                    System.out.print(' ');
                }
                System.out.print('\n');
            }
        }

        else{
            for(int i = 0 ; i < dimension; i++){
                for(int j = 0 ; j < dimension; j++){

                    temp.setX(i);   temp.setY(j);

                    if(alive.contains(temp))
                        System.out.print(0);
                    else
                        System.out.print('_');

                    System.out.print(' ');
                }
                System.out.print('\n');
            }
        }
        System.out.println();
    }
    public int giveNeighbours(int i, int j){
        Cell temp = new Cell(i,j);

        int count = 0;
        if( i -1 >= 0 ) {

            temp.SetVal(i-1,j-1);   // up left
            if(j-1 >= 0 && alive.contains(temp))
                count++;

            temp.SetVal(i-1,j);     // up
            if(alive.contains(temp))
                count++;

            temp.SetVal(i-1,j+1);   //up right
            if(j+1 < dimension && alive.contains(temp))
                count++;
        }

        if(i+1 < dimension){

            temp.SetVal(i+1,j-1);   // bottom left
            if(j-1 >= 0 && alive.contains(temp))
                count++;

            temp.SetVal(i+1, j);    // bottom
            if(alive.contains(temp))
                count++;

            temp.SetVal(i+1,j+1);   // bottom right
            if(j+1 < dimension && alive.contains(temp))
                count++;

        }

        temp.SetVal(i,j-1);     // left
        if(j-1 >= 0 && alive.contains(temp))
            count++;

        temp.SetVal(i, j+1);    // right
        if(j+1 < dimension && alive.contains(temp))
            count++;

        return count;
    }
    public boolean willSurvive(int i, int j){
        if(giveNeighbours(i,j) > 3 || giveNeighbours(i,j) < 2)  // overpopulation, solitude
            return false;
        return true;
    }
    public boolean willBorn(int i, int j){
        if(giveNeighbours(i,j) == 3){
            Cell cd = new Cell(i,j);
            if(!new_born.contains(cd))
                new_born.add(cd);
            return true;
        }
        return false;
    }
    public void Prep_Evolve(){
        int size = alive.size();

        for (int i = 0; i < alive.size(); i++) {
            int x = alive.get(i).getX();
            int y = alive.get(i).getY();
            Cell cd = new Cell(x,y);
            Cell to_add = new Cell(x,y);

            if( ! willSurvive(x,y)) {
                will_die.add(to_add);
            }


            // check for new cells
            cd.SetVal(x-1,y-1);
            if(!alive.contains(cd))
                willBorn(x-1,y-1);

            cd.SetVal(x-1,y);
            if(!alive.contains(cd))
                willBorn(x-1,y);


            cd.SetVal(x-1,y+1);
            if(!alive.contains(cd))
                willBorn(x-1,y+1);

            cd.SetVal(x,y-1);
            if(!alive.contains(cd))
                willBorn(x,y-1);

            cd.SetVal(x,y+1);
            if(!alive.contains(cd))
                willBorn(x,y+1);

            cd.SetVal(x+1,y-1);
            if(!alive.contains(cd))
                willBorn(x+1,y-1);

            cd.SetVal(x+1,y);
            if(!alive.contains(cd))
                willBorn(x+1,y);

            cd.SetVal(x+1,y+1);
            if(!alive.contains(cd))
                willBorn(x+1,y+1);

        }
    }
    public void Evolve() {

        Prep_Evolve();
        for (int i = 0; i < will_die.size(); i++) {
            int x = will_die.get(i).getX();
            int y = will_die.get(i).getY();
            Cell dead = new Cell(x, y);
            alive.remove(dead);
        }

        for (int i = 0; i < new_born.size(); i++) {
            int x = new_born.get(i).getX();
            int y = new_born.get(i).getY();
            Cell born = new Cell(x, y);
            alive.add(born);
        }

        will_die.clear();
        new_born.clear();
        counter++;
    }
    public void Delay(){
        try {
            Thread.sleep(1000*delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void SetDelay(int second){
        delay = second;
    }
    public void Stop(){
        start = false;
    }
    public void next(){
        System.out.print("Counter: ");  System.out.println(counter);
        Delay();
        Evolve();
        Print();
    }
    public void Start() {
        start = true;
        do {
            next();
        } while (!alive.isEmpty() && start);
    }
}
