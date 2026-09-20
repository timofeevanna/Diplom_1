package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerRemoveIngredientTest {
    private final int index;
    private final int firstRemaining;
    private final int secondRemaining;

    public BurgerRemoveIngredientTest(int index, int firstRemaining, int secondRemaining) {
        this.index = index;
        this.firstRemaining = firstRemaining;
        this.secondRemaining = secondRemaining;
    }

    @Parameterized.Parameters(name = "Удаление ингредиента с индексом {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {{0, 1, 2}, {1, 0, 2}, {2, 0, 1}});
    }

    @Test
    public void shouldRemoveIngredientByIndexAndKeepOthers() {
        Burger burger = new Burger();
        Ingredient[] ingredients = {mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class)};
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        burger.removeIngredient(index);
        assertEquals(Arrays.asList(ingredients[firstRemaining], ingredients[secondRemaining]), burger.ingredients);
    }
}
