package kipster.nt.items.fruits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOliveFruit extends ItemFood {

    public ItemOliveFruit(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setTranslationKey(name);
        setRegistryName(name);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        // Consume the item when finished using
        stack.shrink(1);
        return stack;
    }
}