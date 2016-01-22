package gov.nasa.model;

import java.util.HashMap;
import java.util.Map;

public class Plateau {

    private int maxX;
    private int maxY;

    private Map<String, Position> blockedPositions = new HashMap<>();

    public Plateau (Position corner){
        this.maxX = corner.getX();
        this.maxY = corner.getY();
    }

    public void placeObject (String id, Position position) throws Exception {
        if (null == id || null == position){
            throw new Exception("Neither object id or position can be null");
        }
        if (!isInsideLimits(position)){
            throw new Exception("Trying to place an object outside the limits of the plateau");
        }
        if (isBlockedPosition(position) && !(position == blockedPositions.get(id))){
            throw new Exception("There is already another object in the requested position");
        }
        blockedPositions.put(id, position);
    }

    public boolean isValidPosition (Position position){
         return isInsideLimits(position) && !isBlockedPosition(position);
    }

    public boolean isInsideLimits (Position position){
        return position.getX() <= maxX && position.getY() <= maxY;
    }

    public boolean isBlockedPosition (Position position){
        return blockedPositions.values().contains(position);
    }
}
