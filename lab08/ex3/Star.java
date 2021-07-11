import java.awt.*;
import startypes.StarType;

public class Star {
    
    private int x;
    private int y;
    private StarType starType;

    Star (int x, int y, StarType starType) {
        this.x = x;
        this.y = y;
        this.starType = starType;
    }

    public void draw(Graphics g) {
            g.setColor(this.starType.getColor());
            g.fillOval(this.x, this.y , this.starType.getSize(), this.starType.getSize());
        }
}