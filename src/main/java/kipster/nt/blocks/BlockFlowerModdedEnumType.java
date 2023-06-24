package kipster.nt.blocks;

import net.minecraft.util.IStringSerializable;


public enum BlockFlowerModdedEnumType implements IStringSerializable {
    PRIMEVERE("primevere");

    private final String name;

    BlockFlowerModdedEnumType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}