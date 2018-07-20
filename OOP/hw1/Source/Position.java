public class Position {
    public double x;
    public double y;
    
    Position (double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    Position (Position position) {
        this.x = position.x;
        this.y = position.y;
    }
    
    public int getX() {
        return (int)x;
    }
    
    public int getY() {
        return (int)y;
    }
    
    public double distanceTo(Position other) {
        return Math.sqrt(Math.pow((this.getX() - other.getX()), 2) + Math.pow((this.getY() - other.getY()), 2));
    }
}
