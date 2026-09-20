package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientTest {
    private final int from;
    private final int to;
    private final int[] expectedOrder;

    public BurgerMoveIngredientTest(int from, int to, int[] expectedOrder) {
        this.from = from;
        this.to = to;
        this.expectedOrder = expectedOrder;
    }

    @Parameterized.Parameters(name = "Перемещение {0} -> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 2, new int[] {1, 2, 0}}, {2, 0, new int[] {2, 0, 1}},
                {1, 2, new int[] {0, 2, 1}}, {1, 1, new int[] {0, 1, 2}}
        });
    }

    @Test
    public void shouldMoveIngredientAndKeepAllIngredients() {
        Burger burger = new Burger();
        Ingredient[] ingredients = {mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class)};
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        burger.moveIngredient(from, to);
        assertEquals(Arrays.asList(ingredients[expectedOrder[0]], ingredients[expectedOrder[1]],
                ingredients[expectedOrder[2]]), burger.ingredients);
    }
}
