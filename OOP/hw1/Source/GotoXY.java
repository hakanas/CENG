import static java.lang.Math.sqrt;
import java.util.Random;

public class GotoXY extends State {
    public Boolean arrived;
    public Position targetPoint;
    public int speed;
    
    GotoXY() {
        this.currentState = "GotoXY";
        this.targetPoint = new Position(new Random().nextInt(1098) + 1, new Random().nextInt(359) + 201);
        this.arrived = false;
        this.speed = new Random().nextInt(7) + 3;
    }
    
    @Override
    public void step(ConMan c, Common common) {
        double yVel = targetPoint.getY() - c.position.getY();
        double xVel = targetPoint.getX() - c.position.getX();
        double mag  = sqrt(xVel * xVel + yVel * yVel);

        xVel = xVel * speed / mag;
        yVel = yVel * speed / mag;
        
        c.position.x += xVel;
        c.position.y += yVel;
        
        if(Math.abs((int) (targetPoint.getX() - c.position.getX())) < 10 && 
                Math.abs((int) (targetPoint.getY() - c.position.getY())) < 10) {
            this.arrived = true;
        }
        
        for (int i = 0; i < common.orders.size(); i++) {
            if (intersect(c.position, common.orders.get(i).position, c.getDimension())) {
                c.moneyStolen += (common.orders.get(i).size * common.plot.graphPoints.getLast());
                common.orders.remove(i);
            }
        }
    }  
}
