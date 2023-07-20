import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void horseNameNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10, 10));
    }
    @Test
    public void horseNameNullMessage(){
        try{
            new Horse(null,1,2);
            throw new IllegalArgumentException();
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.",e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings =  {""," ", "\t","\n"})
    public void horseNameSpace(String name){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10, 10));
    }
    @ParameterizedTest
    @ValueSource(strings =  {""," ", "\t","\n"})
    public void horseNameSpaceMessage(String name){
        try{
            new Horse(name,1,2);
            throw new IllegalArgumentException();
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.",e.getMessage());
        }
    }
    @Test
    public void horseSpeedSubZeroException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse1", -1, 10));
    }
    @Test
    public void horseSpeedSubZeroMessage(){
        try{
            new Horse("Horse1", -1, 10);
            throw new IllegalArgumentException();
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.",e.getMessage());
        }
    }
    @Test
    public void horseDistanceSubZeroException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse1", 1, -10));
    }
    @Test
    public void horseDistanceSubZeroMessage(){
        try{
            new Horse("Horse1", 1, -10);
            throw new IllegalArgumentException();
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.",e.getMessage());
        }
    }

    @Test
    public void getName(){
        Horse horse = new Horse("Horse1",1,1);
        assertEquals("Horse1",horse.getName());
    }
    @Test
    public void getSpeed(){
        Horse horse = new Horse("Horse1",1,1);
        assertEquals(1,horse.getSpeed());
    }
    @Test
    public void getDistance(){
        Horse horse = new Horse("Horse1",1,1);
        assertEquals(1,horse.getDistance());
    }
    @Test
    public void getDistanceZero(){
        Horse horse = new Horse("Horse1",1);
        assertEquals(0,horse.getDistance());
    }

    @Test
    public void moveWithGetRandomDouble(){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class))
        {
            new Horse("Horse1", 1,1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles =  {0.2, 0.5, 0.99, 1.5, 2.22})
    public void moveMock(double doubleList){
        try(MockedStatic<Horse> mockStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Name",3 ,2);
            mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doubleList);
            horse.move();
            assertEquals(2+3*doubleList,horse.getDistance());
        }
    }

}

