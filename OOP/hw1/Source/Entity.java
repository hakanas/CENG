import java.awt.Graphics2D;

public abstract class Entity {
    public Position position;
    
    Entity(Position position) {
        this.position = position;
    }
    
    Entity() {
        
    }
    
    abstract void draw(Graphics2D g2d);
    abstract void step();
    
}
