import java.util.ArrayList;

public class KnapTree {

    public ArrayList<Tour> leaves;    // leaves to consider returning to
    private Tour head;

    public KnapTree(){
          head = new Tour(null, 0, 0 );
    }


}
