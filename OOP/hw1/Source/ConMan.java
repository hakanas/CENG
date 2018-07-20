import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public abstract class  ConMan extends Entity {
    public String       name;
    public int          moneyStolen;
    public int          dimension;
    public State        state;
    public Position     position;
    public Image        image;
    public String       status;
    
    public ConMan(String name, int moneyStolen, String image) throws IOException {
        this.moneyStolen    = moneyStolen;
        this.name           = name;
        this.dimension      = 100;
        this.position       = new Position(new Random().nextInt(1098) + 1, new Random().nextInt(359) + 201);
        this.image          = ImageIO.read(new File(image));
        this.image          = this.image.getScaledInstance(dimension, dimension, Image.SCALE_DEFAULT);
        this.status         = "freshman";
        setState();
    }
    
    public ConMan(ConMan conman) {
        this.setMoneyStolen(conman.getMoneyStolen());
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font("default", Font.BOLD, 16));
        
        drawConManName(g2d);
        drawConManImage(g2d);
        drawState(g2d);
        drawMoney(g2d);
    }
    
    public void step(Common common) throws IOException {
        this.state.step(this, common);
        
        if (this.moneyStolen >= 2000) {
            setStatus( "Novice");
        }
        if (this.moneyStolen >= 3000) {
            setStatus( "Master");
        }
        if (this.moneyStolen >= 5000) {
            setStatus( "Expert");
        }
        if (this.moneyStolen >= 20000) {
            setStatus( "Guru");
        }
       
        if ("Rest".equals(this.state.currentState) && ((Rest) this.state).stateDuration <= 0) {
            setState();
        }
        else if ("GotoXY".equals(this.state.currentState) && ((GotoXY) this.state).arrived == true) {
            setState();
        }

        else if ("Shake".equals(this.state.currentState) && ((Shake) this.state).stateDuration <= 0) {
            setState();
        }

        else if ("ChaseClosest".equals(this.state.currentState) && ((ChaseClosest) this.state).stateDuration <= 0) {
            setState();
        }

        else if ("BlackHole".equals(this.state.currentState) && ((BlackHole) this.state).stateDuration <= 0) {
            setState();
        }
    }
    
    public void setState() {
        switch (new Random().nextInt(10)) {
            case 0:
            case 1:
            case 2:
                this.state = new GotoXY();
                break;
            case 3:
            case 4:
                this.state = new Shake();
                break;
            case 5:
            case 6:
                this.state = new ChaseClosest();
                break;
            case 7:
            case 8:
                this.state = new Rest();
                break;
            default:
                this.state = new BlackHole();
                break;
        }
    }
    
    public void drawConManName(Graphics2D g2d) {
        int nameWidth = g2d.getFontMetrics().stringWidth(this.name);
        g2d.setPaint(Color.BLACK);
        g2d.drawString(this.name, (int) position.getX() + makeCenter(nameWidth), (int) position.getY() - 5);
    }
    
    public void drawConManImage(Graphics2D g2d) { 
        g2d.drawImage(image, (int) position.getX() , (int) position.getY(), null);
    }
    
    public void drawState(Graphics2D g2d) { 
        int nameWidth = g2d.getFontMetrics().stringWidth(this.state.currentState);
        g2d.setPaint(Color.BLUE);
        g2d.drawString(this.state.currentState, 
                        (int) position.getX() + makeCenter(nameWidth), 
                        (int) position.getY() + getDimension() + 15);
    }
    
    public void drawMoney(Graphics2D g2d) { 
        int nameWidth = g2d.getFontMetrics().stringWidth(getMoneyStolen() + "");
        g2d.setPaint(Color.MAGENTA);
        g2d.drawString(getMoneyStolen() + "", 
                        (int) position.getX() + makeCenter(nameWidth), 
                        (int) position.getY() + getDimension() + 30);
    }
    
    public int getMoneyStolen() {
        return this.moneyStolen;
    }
    
    public void setMoneyStolen(int moneyStolen) {
        this.moneyStolen = moneyStolen;
    }
    
    public double getPositionX() {
        try {
            return this.position.getX();
        } catch (NullPointerException e) {
            return 1;
        }
    }
    
    public double getPositionY() {
        try {
            return this.position.getY();
        } catch (NullPointerException e) {
            return 1;
        }
    }
    
    public void setStatus(String s) {
        this.status = s;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public int getDimension() {
        return this.dimension;
    }
    
    public void setDimension(int newDim) {
        this.dimension = newDim;
    }
    
    public int makeCenter(int textWidth) {
        return (getDimension() - textWidth) / 2;
    }
}
