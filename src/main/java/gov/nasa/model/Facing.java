package gov.nasa.model;

public class Facing {

    enum CardinalPoint {
        N ("north"), E ("east"), S ("south"), W ("west");

        String name;
        CardinalPoint (String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    private CardinalPoint cardinalPoint;

    public Facing (CardinalPoint cardinalPoint){
        this.cardinalPoint = cardinalPoint;
    }

    public void rotateRight (){
        rotate(1);
    }

    public void rotateLeft () {
        rotate(-1);
    }

    public void rotate (int positions){
        positions = positions % 4;
        if(positions < 0){positions = positions + 4;}
        cardinalPoint = CardinalPoint.values()[(cardinalPoint.ordinal() + positions) % 4];
    }

    public CardinalPoint getCardinalPoint(){
        return cardinalPoint;
    }

    @Override
    public boolean equals(Object object) {
        if (null == object || !(object instanceof Facing)){return false;}
        if (this == object){return true;}
        Facing facing = (Facing) object;

        return cardinalPoint == facing.getCardinalPoint();
    }
}
