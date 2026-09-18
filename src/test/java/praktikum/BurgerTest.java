package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock private Bun bun;
    @Mock private Bun anotherBun;
    @Mock private Ingredient sauce;
    @Mock private Ingredient filling;
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void newBurgerHasNoIngredients() {
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldSetBun() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void shouldReplaceBun() {
        burger.setBuns(bun);
        burger.setBuns(anotherBun);
        assertSame(anotherBun, burger.bun);
    }

    @Test
    public void shouldAddIngredientsInOrder() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertEquals(Arrays.asList(sauce, filling), burger.ingredients);
    }

    @Test
    public void shouldAllowSameIngredientTwice() {
        burger.addIngredient(sauce);
        burger.addIngredient(sauce);
        assertEquals(Arrays.asList(sauce, sauce), burger.ingredients);
    }

    @Test
    public void shouldRemoveOnlyIngredient() {
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }
}
