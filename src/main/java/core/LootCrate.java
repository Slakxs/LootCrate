package core;

import java.util.HashMap;

public class LootCrate {
    private final HashMap<String,LootSlot> inventory = new HashMap<>();

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public LootSlot createSlot(String name, double stackSize) {
        if (inventory.containsKey(name))
            throw new SlotNameInUseException();
        inventory.put(name, new LootSlot(stackSize));
        return inventory.get(name);
    }

    public LootSlot removeSlot(String name) {
        if (!inventory.containsKey(name))
            throw new NoSuchSlotException();
        return inventory.remove(name);
    }

    public boolean hasSlot(String name) {
        return inventory.containsKey(name);
    }

    public LootSlot getSlot(String name) {
        if (!inventory.containsKey(name))
            throw new NoSuchSlotException();
        return inventory.get(name);
    }

    public static class SlotNameInUseException extends RuntimeException {
    }

    public static class NoSuchSlotException extends RuntimeException {
    }
}
