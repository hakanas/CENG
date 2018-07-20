import static java.lang.Math.sqrt;
import java.util.Random;

public class ChaseClosest extends State {
    public int      stateDuration;
    public Position targetPoint;
    public int      speed;
    
    public ChaseClosest() {
        this.currentState  = "ChaseClosest";
        this.stateDuration = new Random().nextInt(50) + 20;
        this.speed         = new Random().nextInt(10) + 3;
    }

    @Override
    public void step(ConMan c, Common common) {
        int indexOfClosest = closestOrder(c, common);
        
        if (indexOfClosest != -1) {
            this.targetPoint = new Position(common.orders.get(closestOrder(c, common)).position);
            
            double yVel = targetPoint.y - c.position.y;
            double xVel = targetPoint.x - c.position.x;

            double mag = sqrt(xVel * xVel + yVel * yVel);

            xVel = xVel * speed / mag;
            yVel = yVel * speed / mag;

            c.position.x += xVel;
            c.position.y += yVel;

            for (int i = 0; i < common.orders.size(); i++) {
                if (intersect(c.position, common.orders.get(i).position, c.getDimension())) {
                    c.moneyStolen += (common.orders.get(i).size * common.plot.graphPoints.getLast());
                    common.orders.remove(i);
                }
            }
        }
        else
            this.targetPoint = new Position(c.position);
        
        this.stateDuration--;
    }
    
    int closestOrder(ConMan c, Common common) {
        int closestIndex = -1;
        int closest      = -1;
        
        try {
            closest = (int) Math.sqrt(Math.pow(c.position.getX() - common.orders.get(0).position.getX(), 2) + Math.pow(c.position.getY() - common.orders.get(0).position.getY(), 2));
        } catch (IndexOutOfBoundsException e) {
            // Continue
        }
        
        for (int i = 1; i < common.orders.size(); i++) {
            if ((int) Math.sqrt(Math.pow(c.position.getX() - common.orders.get(i).position.getX(), 2) + Math.pow(c.position.getY() - common.orders.get(i).position.getY(), 2)) < closest) {
                closestIndex = i;
            }
        }
        return closestIndex;
    }
}
