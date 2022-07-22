package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LootCrateTest {

    LootCrate crate;

    @BeforeEach
    public void init() {
        crate = new LootCrate();
    }

    @Test
    void newCrate_IsEmpty() {
        assertTrue(crate.isEmpty());
    }

    @Test
    void createSlot_IsNotEmpty() {
        crate.createSlot("Test", 1.0);
        assertFalse(crate.isEmpty());
    }

    @Test
    void createSlot_HasSlot() {
        crate.createSlot("Test", 1.0);
        assertTrue(crate.hasSlot("Test"));
    }

    @Test
    void createSlotTwice_ExpectException() {
        crate.createSlot("Test", 1.0);
        assertThrows(LootCrate.SlotNameInUseException.class, () -> crate.createSlot("Test", 2.0));
    }

    @Test
    void getSlotWithoutSlot_ExpectException() {
        assertThrows(LootCrate.NoSuchSlotException.class, () -> crate.getSlot("Test"));
    }

    @Test
    void getSlotWithSlot_returnsSlot() {
        LootSlot slot = crate.createSlot("Test", 1.0);
        assertEquals(slot, crate.getSlot("Test"));
    }

    @Test
    void removeSlotWithSlot_returnsSlot() {
        LootSlot slot = crate.createSlot("Test", 1.0);
        assertEquals(slot, crate.removeSlot("Test"));
    }

    @Test
    void removeSlotWithoutSlot_ExpectException() {
        assertThrows(LootCrate.NoSuchSlotException.class, () -> crate.removeSlot("Test"));
    }
}
