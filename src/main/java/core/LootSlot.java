package core;

public class LootSlot {

    private double stackSize;
    private double content;

    LootSlot(double stackSize) {
        this.stackSize = stackSize;
    }

    public boolean isEmpty() {
        return getContent() == 0;
    }

    public boolean isFull() {
        return content == getStackSize();
    }

    public void store(double value) {
        if (value > getStackSize())
            throw new ItemOverflowException();
        content += value;
    }

    public void remove(double value) {
        if (isEmpty())
            throw new ItemShortageException();
        content -= value;
    }

    public void resize(double newSize) {
        if (newSize < getContent())
            throw new ItemOverflowException();
        stackSize = newSize;
    }

    public double loot() {
        double loot = content;
        content = 0;
        return loot;
    }

    public double getContent() {
        return content;
    }

    public double getStackSize() {
        return stackSize;
    }

    public static class ItemShortageException extends RuntimeException {
    }

    public static class ItemOverflowException extends RuntimeException {
    }
}
