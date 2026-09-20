package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerReceiptTest {
    @Mock private Bun bun;
    @Mock private Ingredient sauce;
    @Mock private Ingredient filling;
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        when(bun.getName()).thenReturn("Краторная булка");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
    }

    @Test
    public void shouldPrintReceiptWithoutIngredients() {
        String expected = String.format("(==== Краторная булка ====)%n(==== Краторная булка ====)%n%nPrice: %f%n", 200f);
        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void shouldPrintNamesTypesOrderAndTotalPrice() {
        addSauceAndFilling();
        String expected = String.format("(==== Краторная булка ====)%n= sauce Острый соус =%n"
                + "= filling Котлета =%n(==== Краторная булка ====)%n%nPrice: %f%n", 275f);
        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void receiptShouldReflectMovedAndRemovedIngredients() {
        addSauceAndFilling();
        burger.moveIngredient(1, 0);
        burger.removeIngredient(1);
        String expected = String.format("(==== Краторная булка ====)%n= filling Котлета =%n"
                + "(==== Краторная булка ====)%n%nPrice: %f%n", 250f);
        assertEquals(expected, burger.getReceipt());
    }

    private void addSauceAndFilling() {
        // Lenient нужен только для стаба соуса, который удаляется в одном из тестов.
        org.mockito.Mockito.lenient().when(sauce.getName()).thenReturn("Острый соус");
        org.mockito.Mockito.lenient().when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        org.mockito.Mockito.lenient().when(sauce.getPrice()).thenReturn(25f);
        when(filling.getName()).thenReturn("Котлета");
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getPrice()).thenReturn(50f);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
    }
}
