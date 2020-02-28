package org.sympatic.timber.listeners;

import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.sympatic.timber.Main;
import org.sympatic.timber.state.TimberState;
import org.sympatic.timber.timber.Timber;

@RequiredArgsConstructor
public class TimberListener implements Listener {

    private final Main main;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE || !player.isSneaking() || main.getTimberState() == TimberState.DISABLED) {
            return;
        }

        Block block = event.getBlock();

        switch (block.getType()) {
            case LOG:
            case LOG_2:
                Timber timber = new Timber(main, block, player.getItemInHand());
                timber.start();
                break;
        }
    }

}
