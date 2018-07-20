import java.util.Random;

public class BlackHole extends State {
    public int stateDuration;
    
    BlackHole() {
        this.currentState  = "BlackHole";
        this.stateDuration = new Random().nextInt(50) + 20;
    }

    @Override
    public void step(ConMan c, Common common) {
        for (int i = 0; i < common.orders.size(); i++) {
            if (intersect(new Position(c.position.getX() - 75, c.position.getY() - 75), 
                    common.orders.get(i).position, c.getDimension() + 250)) {
                if(common.orders.get(i).attachedTo == -1) {
                    common.orders.get(i).attachedTo = 1;
                    common.orders.get(i).speed = 25;
                    common.orders.get(i).targetPoint = new Position(new Position(c.position.getX() + (c.getDimension() / 2), c.position.getY() + (c.getDimension()/2)));
                }
            }
            if (intersect(c.position, common.orders.get(i).position, c.getDimension())) {
                c.moneyStolen += (common.orders.get(i).size * common.plot.graphPoints.getLast());
                common.orders.remove(i);
            }
        }
        
        this.stateDuration--;
        
        if(this.stateDuration == 0) {
            for (Order order : common.orders) {
                order.attachedTo = -1;
                order.speed = new Random().nextInt(8) + 3;
                order.targetPoint = new Position(new Random().nextInt(998) + 201, 200);
            }
        }
    }
}
    
