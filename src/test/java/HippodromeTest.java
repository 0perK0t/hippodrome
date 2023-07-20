import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void hippodromeHorseListNullException(){
        Throwable throwTest = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", throwTest.getMessage());
    }
    @Test
    public void hippodromeHorseListEmptyException(){
        Throwable throwTest = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", throwTest.getMessage());
    }

    @Test
    public void getHorsesTest(){
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horsesList.add(new Horse("Horse " + i,1,1));
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        assertEquals(horsesList,hippodrome.getHorses());
    }
    @Test
    public void moveTest(){
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            horsesList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        hippodrome.move();

        for (Horse horse : horsesList){
        verify(horse).move();
        }
    }
    @Test
    public void getWinnerTest(){
        Horse horse1 = new Horse("Horse1",1,3);
        Horse horse2 = new Horse("Horse1",1,9);
        Horse horse3 = new Horse("Horse1",1,5);
        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse2,hippodrome.getWinner());
    }

}
