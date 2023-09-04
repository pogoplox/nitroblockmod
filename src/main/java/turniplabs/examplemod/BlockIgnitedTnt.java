package turniplabs.examplemod;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockCake;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockIgnitedTnt extends BlockTileEntity {


    private int _blockIndex = 0;
    private int _maxTickRate= 0;
    protected BlockIgnitedTnt(String blockName, int id, Material material,int blockIndex, int maxTickRate) {
        super(blockName, id, material);
        _blockIndex = blockIndex;
        _maxTickRate = maxTickRate;
    }

    public static int EXP_ID_1 = 1008;
    public static int EXP_ID_2 = 1007;
    public static int EXP_ID_3 = 1006;

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityNewTnt(_maxTickRate,_blockIndex);
    }
    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause == EnumDropCause.EXPLOSION) {
            return null;
        }
        return new ItemStack[]{new ItemStack(this)};
    }


    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z) {
        NitroBlockMod.LOGGER.info("onBlockDestroyedByExplosion");
        world.createExplosion(null,(float )x,(float ) y,(float )z,3.5f);
        world.playSoundEffect(SoundType.ENTITY_SOUNDS,x,y,z,"examplemod.crash_tnt_explosion",0,1);
        super.onBlockDestroyedByExplosion(world, x, y, z);
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);

    }

    @Override
    public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
        super.onBlockPlaced(world, x, y, z, side, entity, sideHeight);
    }


    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        return super.blockActivated(world, x, y, z, player);
    }

    @Override
    public boolean isInAll(Tag<Block>... tags) {
        return super.isInAll(tags);
    }
}
