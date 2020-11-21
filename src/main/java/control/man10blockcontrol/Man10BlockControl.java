package control.man10blockcontrol;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Man10BlockControl extends JavaPlugin implements Listener {

    FileConfiguration config;
    boolean pl;
    String prefix = "[§eMan10§aBC§r]";
    List list = new ArrayList();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this,this);
        saveDefaultConfig();
        config = getConfig();
        config.getBoolean("pl",false);
        config.getString("info",prefix);
        config.getList("item");
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("mdp")) {
            if (!(sender.isOp())) {
                return true;
            }
            Player p = (Player)sender;
            ItemStack i = p.getInventory().getItemInMainHand();
            list.add(i.getType());
            config.set("item",list);
        }
        return true;
    }

    @EventHandler
    public void onSet(BlockPlaceEvent e) {
        if (!(list.contains(e.getBlock().getType()))) {
            return;
        }
        e.setCancelled(true);
    }
}