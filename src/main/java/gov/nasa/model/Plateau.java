package gov.nasa.model;

import gov.nasa.model.common.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Plateau {

    private int xLimit;
    private int yLimit;

    private Map<String, Position> blockedPositions = new HashMap<>();

    public Plateau (Position corner){
        this.xLimit = corner.getX();
        this.yLimit = corner.getY();
    }

    public Plateau (String id) throws Exception {
        Pattern pattern = Pattern.compile("\\s*([0-9]+)\\s+([0-9]+)\\s*");
        Matcher matcher = pattern.matcher(id);
        if (!matcher.matches()){
            throw new Exception("Wrong id format");
        }
        this.xLimit = Integer.parseInt(matcher.group(1));
        this.yLimit = Integer.parseInt(matcher.group(2));

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
        int x = position.getX();
        int y = position.getY();
        return x >=0 && x <= xLimit && y >= 0 && y <= yLimit;
    }

    public boolean isBlockedPosition (Position position){
        return blockedPositions.values().contains(position);
    }


    public int getxLimit() {
        return xLimit;
    }

    public int getyLimit() {
        return yLimit;
    }
}
