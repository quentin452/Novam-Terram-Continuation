package kipster.nt.blocks;

import net.minecraft.util.IStringSerializable;


public enum BlockFlowerModdedEnumType implements IStringSerializable {
    primevere_flower("primevere_flower");

    private final String name;

    private BlockFlowerModdedEnumType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}