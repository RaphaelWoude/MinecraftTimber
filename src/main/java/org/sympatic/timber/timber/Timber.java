package org.sympatic.timber.timber;

import lombok.AllArgsConstructor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.sympatic.timber.Main;

@AllArgsConstructor
public class Timber {

    /**
     * Main class instance.
     */
    private final Main main;

    /**
     * Starting block.
     */
    private Block block;

    /**
     * Item in hand.
     */
    private ItemStack hand;

    /**
     * Starts timber.
     */
    public void start() {
        breakBlock(block);
    }

    /**
     * Breaks a certain block and looks around the block for other blocks.
     * Sets the durability if a tool is present.
     *
     * @param block Starting block.
     */
    private void breakBlock(Block block) {
        BlockFace[] BLOCK_FACES = new BlockFace[] {
                BlockFace.DOWN,
                BlockFace.UP,
                BlockFace.SOUTH,
                BlockFace.NORTH,
                BlockFace.EAST,
                BlockFace.WEST
        };

        for (BlockFace blockFace : BLOCK_FACES) {
            Block b = block.getRelative(blockFace);

            if (b.getType() == Material.LOG || b.getType() == Material.LOG_2)  {
                b.getWorld().playEffect(b.getLocation(), Effect.TILE_BREAK, b.getType().getId());
                b.breakNaturally();

                int delay = 8;

                // Set durability
                // Change delay based on axe.
                if (hand.getType() != Material.AIR) {
                    hand.setDurability((short) (hand.getDurability() + 1));

                    switch (hand.getType()) {
                        case WOOD_AXE:
                            delay = 7;
                            break;
                        case STONE_AXE:
                            delay = 6;
                            break;
                        case IRON_AXE:
                            delay = 5;
                            break;
                        case GOLD_AXE:
                            delay = 3; // Gold tools are faster than diamond tools.
                            break;
                        case DIAMOND_AXE:
                            delay = 4;
                            break;
                    }
                }

                // Try around the next block as well. Delay of 3 ticks.
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        breakBlock(b);
                    }
                }.runTaskLater(main, delay);
            }
        }
    }

}
