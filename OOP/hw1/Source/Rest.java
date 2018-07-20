import java.util.Random;

public class Rest extends State {
    public int stateDuration;
    
    Rest() {
        this.currentState  = "Rest";
        this.stateDuration = new Random().nextInt(50) + 20; 
    }

    @Override
    public void step(ConMan c, Common common) {
        this.stateDuration--;
        
        for (int i = 0; i < common.orders.size(); i++) {
            if (intersect(c.position, common.orders.get(i).position, c.getDimension())) {
                c.moneyStolen += (common.orders.get(i).size * common.plot.graphPoints.getLast());
                common.orders.remove(i);
            }
        }
    }
}
