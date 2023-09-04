package turniplabs.examplemod;

import net.minecraft.core.block.entity.TileEntity;

public class TileEntityNewTnt extends TileEntity  {
    private int tickRate = 0 ;

    private int _maxTickRate = 0;

    private int _blockIndex = 0;


    public TileEntityNewTnt(int maxTickRate, int blockIndex){
        _maxTickRate = maxTickRate;
        _blockIndex = blockIndex;
    }

    @Override
    public void updateEntity() {

         tickRate = tickRate +1;
            NitroBlockMod.LOGGER.info(String.valueOf(tickRate));
            if (tickRate >= _maxTickRate) {
                switch (_blockIndex){
                    case 1:
                        this.worldObj.createExplosion(null, (double) this.xCoord, (double) this.yCoord, (double) this.zCoord, 3.5f);
                        break;
                    case 2:
                        this.worldObj.setBlock(this.xCoord,this.yCoord,this.zCoord,BlockIgnitedTnt.EXP_ID_1);
                        this.worldObj.notifyBlockChange(this.xCoord,this.yCoord,this.zCoord,BlockIgnitedTnt.EXP_ID_1);
                        break;
                    case 3:
                        this.worldObj.setBlock(this.xCoord,this.yCoord,this.zCoord,BlockIgnitedTnt.EXP_ID_2);
                        this.worldObj.notifyBlockChange(this.xCoord,this.yCoord,this.zCoord,BlockIgnitedTnt.EXP_ID_2);
                        break;

                }
            }
    }


}
