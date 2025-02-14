import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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
    public void constructorTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.0, 23);
        });
    }

    @Test
    public void constructorTest1() {
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
    public void constructorParameterized1Test(String input) {
        try {
            new Horse(input, 2.0, 23);
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void constructorTest3() {
        int flag;
        try {
            new Horse("input", -2.0, 23);
        }catch (IllegalArgumentException e){
            flag = 1;
            assertEquals(1, flag);
        }
    }

    @Test
    public void constructorTest4() {
        try {
            new Horse("input", -2.0, 23);
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void constructorTest5() {
        int flag;
        try {
            new Horse("input", 2.0, -23);
        }catch (IllegalArgumentException e){
            flag = 1;
            assertEquals(1, flag);
        }
    }

    @Test
    public void constructorTest6() {
        try {
            new Horse("input", 2.0, -23);
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }



    @Test
    void getName() {
        assertEquals("name", horse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(23, horse.getSpeed());
    }

    @Test
    void getDistanceThreeParameters () {
        assertEquals(100, horse.getDistance());
    }

    @Test
    void getDistanceTwoParameters () {
        assertEquals(0, horse2.getDistance());
    }
}