package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceTest {
    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public BurgerPriceTest(float bunPrice, float[] ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Булка {0}, итог {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100f, new float[] {}, 200f}, {100f, new float[] {50f}, 250f},
                {100.25f, new float[] {20.5f, 30.75f}, 251.75f},
                {0f, new float[] {0f}, 0f}, {100f, new float[] {50f, 50f}, 300f}
        });
    }

    @Test
    public void shouldIncludeTwoBunHalvesAndEveryIngredientInPrice() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        for (float price : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}
