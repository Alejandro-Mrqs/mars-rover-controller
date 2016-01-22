package gov.nasa.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPlateau {
    @Test
    public void testPositionInsideLimits(){
        Plateau plateau = new Plateau(new Position(5,5));
        assertTrue(plateau.isValidPosition(new Position(2,5)));
    }

    @Test
    public void testPositionOutsideLimits(){
        Plateau plateau = new Plateau(new Position(5,5));
        assertFalse(plateau.isValidPosition(new Position(9,5)));
    }

    @Test
    public void testPlaceObjectIntoValidPosition() throws Exception {
        Plateau plateau = new Plateau(new Position(5,5));
        Position objectPosition = new Position(3,2);
        plateau.placeObject("1", objectPosition);
        assertTrue(plateau.isBlockedPosition(objectPosition));
    }

    @Test
    public void testMoveObjectToValidPosition() throws Exception {
        Plateau plateau = new Plateau(new Position(5,5));
        Position objectPosition = new Position(3,2);
        plateau.placeObject("1", objectPosition);
        assertTrue(plateau.isBlockedPosition(objectPosition));
        Position newObjectPosition = new Position(4,5);
        plateau.placeObject("1", newObjectPosition);
        assertFalse(plateau.isBlockedPosition(objectPosition));
        assertTrue(plateau.isBlockedPosition(newObjectPosition));
    }

    @Test (expected = Exception.class)
    public void testPlaceObjectOutsideLimits() throws Exception {
        Plateau plateau = new Plateau(new Position(5,5));
        plateau.placeObject("1", new Position(6,2));
    }

    @Test (expected = Exception.class)
    public void testPlaceObjectInBlockedPositon() throws Exception {
        Plateau plateau = new Plateau(new Position(5,5));
        plateau.placeObject("1", new Position(3,2));
        plateau.placeObject("2", new Position(3,2));
    }
}