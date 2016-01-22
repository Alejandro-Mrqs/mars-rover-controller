package gov.nasa.model;

import gov.nasa.model.Facing.CardinalPoint;

public class Position {
    private int x;
    private int y;

    public Position (int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move (CardinalPoint cardinalPoint){
        move(cardinalPoint, 1);
    }

    public void move (CardinalPoint cardinalPoint, int positions){
        switch (cardinalPoint){
            case N:
                moveY(positions);
                break;
            case S:
                moveY(-positions);
                break;
            case E:
                moveX(positions);
                break;
            case W:
                moveX(-positions);
                break;
        }
    }

    public void moveX(int positions){
        this.x = this.x + positions;
    }

    public void moveY (int positions){
        this.y = this.y + positions;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (null == object || !(object instanceof Position)){return false;}
        if (this == object){return true;}
        Position position = (Position) object;

        return this.x == position.getX() && this.y == position.getY();
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
