package core;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LootSlotTest {

    LootSlot slot;

    @BeforeEach
    public void init() {
        slot = new LootSlot(1);
    }

    @Test
    void newSlot_IsEmpty() {
        assertTrue(slot.isEmpty());
    }

    @Test
    void filledSlot_IsNotEmpty() {
        slot.store(1);
        assertFalse(slot.isEmpty());
    }

    @Test
    void storeOne_IsOne() {
        slot.store(1);
        assertEquals(1.0, slot.getContent());
    }

    @Test
    void storeOneTwice_IsTwo() {
        slot.store(1);
        slot.store(1);
        assertEquals(2.0, slot.getContent());
    }

    @Test
    void storeOneRemoveOne_IsZero() {
        slot.store(1);
        slot.remove(1);
        assertEquals(0.0, slot.getContent());
    }

    @Test
    void storeOneRemoveOne_IsEmpty() {
        slot.store(1);
        slot.remove(1);
        assertTrue(slot.isEmpty());
    }

    @Test
    void removeEmpty_ExpectException() {
        assertThrows(LootSlot.ItemShortageException.class,() -> slot.remove(1));
    }

    @Test
    void newSlotStackSizeOne_IsOne() {
        assertEquals(1.0, slot.getStackSize());
    }

    @Test
    void newSlotStackSizeRandom_IsRandom() {
        double random = new Random().nextDouble();
        LootSlot slot2 = new LootSlot(random);
        assertEquals(random, slot2.getStackSize());
    }

    @Test
    void resizedSlot_IsResized() {
        slot.resize(2);
        assertEquals(2.0, slot.getStackSize());
    }

    @Test
    void resizedSlotIsTooSmall_ExpectException() {
        slot.store(1);
        assertThrows(LootSlot.ItemOverflowException.class,() -> slot.resize(0.5));
    }

    @Test
    void overflowSlot_ExpectException() {
        assertThrows(LootSlot.ItemOverflowException.class,() -> slot.store(2));
    }

    @Test
    void lootedSlotWithOne_ReturnsOne() {
        slot.store(1);
        assertEquals(1.0, slot.loot());
    }

    @Test
    void filledSlot_IsFull() {
        slot.store(1);
        assertTrue(slot.isFull());
    }

    @Test
    void halfFilledSlot_IsNotFull() {
        slot.store(0.5);
        assertFalse(slot.isFull());
    }

}
