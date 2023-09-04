package turniplabs.examplemod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import useless.prismaticlibe.helper.SoundHelper;


public class NitroBlockMod implements ModInitializer {
    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Block nitroblock = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
            .setSideTextures("nitroblock.png")
            .setTopTexture("nitroblock_top.png")
            .setBottomTexture("nitroblock_top.png")
            .build(new NitroBlock("nitroblock", 1003, Material.explosive,3.0f));

    public static final Block tntBlock = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
            .setSideTextures("tnt_block.png")
            .setTopTexture("tnt_block_top.png")
            .setBottomTexture("tnt_block_top.png")
            .build(new BlockNewTnt("tntBlock", BlockNewTnt.ID , Material.explosive,3.0f));

    public static final Block tntExplBlock3 = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
            .setSideTextures("tnt_expl3.png")
            .setTopTexture("tnt_block_top.png")
            .setBottomTexture("tnt_block_top.png")
            .setHardness(10f)
            .build(new BlockIgnitedTnt("tntExplBlock3", BlockIgnitedTnt.EXP_ID_3 , Material.explosive,3, 30));

    public static final Block tntExplBlock2 = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
            .setSideTextures("tnt_expl2.png")
            .setTopTexture("tnt_block_top.png")
            .setBottomTexture("tnt_block_top.png")
            .setHardness(10f)
            .build(new BlockIgnitedTnt("tntExplBlock2", BlockIgnitedTnt.EXP_ID_2 , Material.explosive,2, 30));

    public static final Block tntExplBlock1 = new BlockBuilder(MOD_ID)
            .setBlockSound(new BlockSound("step.grass", "step.grass", 1.0f, 1.0f))
            .setSideTextures("tnt_expl1.png")
            .setTopTexture("tnt_block_top.png")
            .setBottomTexture("tnt_block_top.png")
            .setHardness(10f)
            .build(new BlockIgnitedTnt("tntExplBlock1", BlockIgnitedTnt.EXP_ID_1, Material.explosive,1, 15));

    @Override
    public void onInitialize() {
        LOGGER.info("ExampleMod initialized.");

        RecipeHelper.Crafting.createRecipe(nitroblock, 1, new Object[]{"RRR","RTR","RRR", 'R' , Item.dustRedstone, 'T', Block.tnt});


        SoundHelper.addSound(MOD_ID,"crash_tnt_explosion.ogg");
        EntityHelper.createTileEntity(TileEntityNewTnt.class, "TileEntityNewTnt");



    }
}
