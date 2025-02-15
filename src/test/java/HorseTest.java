import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    Horse horse;
    Horse horse2;

    @BeforeEach
    void setUp() {
        horse = new Horse("name", 23, 100);
        horse2 = new Horse("name", 23);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void constructorTestWhitNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.0, 23);
        });
    }

    @Test
    public void constructorTestWhitNullComment() {
        try {
            new Horse(null, 2.0, 23);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void constructorParameterizedTest(String input) {
        int flag;
        try {
            new Horse(input, 2.0, 23);
        }catch (IllegalArgumentException e){
            flag = 1;
            assertEquals(1, flag);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void constructorParameterizedTestComment(String input) {
        try {
            new Horse(input, 2.0, 23);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void constructorTestNegativeNumSpeed() {
        int flag;
        try {
            new Horse("input", -2.0, 23);
        }catch (IllegalArgumentException e){
            flag = 1;
            assertEquals(1, flag);
        }
    }

    @Test
    public void constructorTestNegativeNumSpeedComment() {
        try {
            new Horse("input", -2.0, 23);
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void constructorTestNegativeNumDistance() {
        int flag;
        try {
            new Horse("input", 2.0, -23);
        }catch (IllegalArgumentException e){
            flag = 1;
            assertEquals(1, flag);
        }
    }

    @Test
    public void constructorTestNegativeNumDistanceComment() {
        try {
            new Horse("input", 2.0, -23);
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }



    @Test
    void getNameTest() {
        assertEquals("name", horse.getName());
    }

    @Test
    void getSpeedTest() {
        assertEquals(23, horse.getSpeed());
    }

    @Test
    void getDistanceThreeParametersTest() {
        assertEquals(100, horse.getDistance());
    }

    @Test
    void getDistanceTwoParametersTest() {
        assertEquals(0, horse2.getDistance());
    }

    @Test
    void moveTest(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, 11.0, 0.5, 10.0",
            "0.0, 10.0, 0.5, 10.0"
    })
    void moveParameterizedTest(double speed, double expectDistance, double expectRandomNum, double beginDistance){
        Horse horseTemp = new Horse("temp", speed, beginDistance);
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(expectRandomNum);
            horseTemp.move();
            assertEquals(expectDistance, horseTemp.getDistance());
        }
    }
}