package turniplabs.examplemod;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemFirestriker;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundType;
import net.minecraft.core.world.World;

public class BlockNewTnt extends Block implements IBlockNewTnt {

    private float power = 0.0f;
    public static int ID = 1005;

    public BlockNewTnt(String key, int id, Material material, float _power) {
        super(key, id, material);
        power = _power;

    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);
        NitroBlockMod.LOGGER.info("onBlockAdded");
        if (world.isBlockIndirectlyGettingPowered(i, j, k)) {
            explode(world,i,j,k);
        }
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause == EnumDropCause.EXPLOSION) {
            return null;
        }
        return new ItemStack[]{new ItemStack(this)};
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        if (blockId > 0 && Block.blocksList[blockId].canProvidePower() && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            explode(world, x, y, z);
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z) {
        NitroBlockMod.LOGGER.info("onBlockDestroyedByExplosion");
        explode(world,x,y,z);
        super.onBlockDestroyedByExplosion(world, x, y, z);
    }




    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {

        ignite(world,x,y,z);

        super.onEntityWalking(world, x, y, z, entity);
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        NitroBlockMod.LOGGER.info("blockActivated");
        if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemFirestriker) {
            ignite(world,x,y,z);
            return true;
        }
        return super.blockActivated(world, x, y, z, player);
    }

    @Override
    public void explode(World world, int x, int y, int z){
        world.createExplosion(null,(float )x,(float ) y,(float )z,this.power);
    }

    @Override
    public void ignite(World world, int x, int y, int z){
        world.setBlock(x,y,z,0);
        world.notifyBlockChange(x,y,z,0 );
        world.playSoundEffect(SoundType.ENTITY_SOUNDS,x,y,z,"examplemod.crash_tnt_explosion",1,1);
        world.setBlock(x,y,z,BlockIgnitedTnt.EXP_ID_3);
        world.notifyBlockChange(x,y,z,BlockIgnitedTnt.EXP_ID_3 );
    }

}


interface IBlockNewTnt {
    void explode(World world, int x, int y, int z);

    void ignite(World world, int x, int y, int z);

}