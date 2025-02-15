import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {
    List<Horse> horseEmpty;
    @BeforeEach
    void setUp() {
        horseEmpty = new ArrayList<>();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void constructorTestNull(){
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void constructorTestNullComment(){
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e){
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void constructorTestEmpty(){
        try {
            new Hippodrome(horseEmpty);
        } catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }

    @Test
    public void constructorTestEmptyComment(){
        try {
            new Hippodrome(horseEmpty);
        } catch (IllegalArgumentException e){
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorsesTest(){
        List<Horse> temp = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            temp.add(new Horse("" + i, i, i + 100));
        }

        Hippodrome hippodrome = new Hippodrome(temp);

        assertEquals(hippodrome.getHorses(), temp);
    }

    @Test
    public void moveTest(){
        List<Horse> horseList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horseList);

        hippodrome.move();

        for (Horse horse : horseList) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest(){
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("1", 10, 100));
        horses.add(new Horse("2", 10, 101));
        horses.add(new Horse("3", 10, 102));

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.get(2), hippodrome.getWinner());
    }




}