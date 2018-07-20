import java.util.Random;

public class Shake extends State{
    public int stateDuration;
    public Position closePoint;
    
    Shake() {
        this.currentState = "Shake";
        this.stateDuration = new Random().nextInt(50) + 20;
    }

    @Override
    public void step(ConMan c, Common common) {
        this.closePoint = new Position(c.position.x + new Random().nextInt(10), c.position.y + new Random().nextInt(10));
        double jumpToX = c.position.x + new Random().nextInt(20) - 10;
        double jumpToY = c.position.y + new Random().nextInt(20) - 10;
        
        if (jumpToX > 1150)
            jumpToX = 1140;
        else if (jumpToX < 50)
            jumpToX = 60;
        
        if (jumpToY > 610)
            jumpToY = 600;
        else if (jumpToY < 150)
            jumpToY = 160;
        
        c.position.x = jumpToX;
        c.position.y = jumpToY;
        
        for (int i = 0; i < common.orders.size(); i++) {
            if (intersect(c.position, common.orders.get(i).position, c.getDimension())) {
                c.moneyStolen += (common.orders.get(i).size * common.plot.graphPoints.getLast());
                common.orders.remove(i);
            }
        }
        this.stateDuration--;
    }
}
