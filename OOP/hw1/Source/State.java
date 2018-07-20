public abstract class State {
    String currentState;
    
    State() {
        
    }
    
    public abstract void step(ConMan c, Common common);
    
    String getCurrentName() {
        return this.currentState;
    }
    
    void setStateName(String s) {
        this.currentState = s;
    }
    
    public Boolean intersect(Position conmanPos, Position orderPos, int dimension) {
        if (conmanPos.x < orderPos.x && orderPos.x < (conmanPos.x + dimension)) {
            if (conmanPos.y < orderPos.y && orderPos.y < (conmanPos.y + dimension)) {
                return true;
            }
        }
        return false;
    }
  
}
