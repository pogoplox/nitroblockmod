package turniplabs.examplemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.RecipeHelper;


public class NitroBlockMod implements ModInitializer {
    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Block nitroblock = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
            .setSideTextures("nitroblock.png")
            .setTopTexture("nitroblock_top.png")
            .setBottomTexture("nitroblock_top.png")
            .build(new NitroBlock("nitroblock", 1003, Material.explosive,3.0f));

    @Override
    public void onInitialize() {
        LOGGER.info("ExampleMod initialized.");
        LOGGER.info(String.valueOf(nitroblock.id));

        RecipeHelper.Crafting.createRecipe(nitroblock, 1, new Object[]{"RRR","RTR","RRR", 'R' , Item.dustRedstone, 'T', Block.tnt});


    }
}
