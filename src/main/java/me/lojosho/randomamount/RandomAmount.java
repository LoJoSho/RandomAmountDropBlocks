package me.lojosho.randomamount;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomAmount extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void RandomAmountDropped(BlockBreakEvent e) {
        if (!e.isCancelled()) {
            if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                Material m = e.getBlock().getBlockData().getMaterial();
                Block b = e.getBlock();
                b.setType(Material.AIR);
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(m, random()));
                e.setCancelled(true);
            }
        }
    }

    public int random() {
        int min = 1;
        int max = 64;
        return (int) (Math.random() * (max - min) + min);
    }
}
