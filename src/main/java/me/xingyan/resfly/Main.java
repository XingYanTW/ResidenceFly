package me.xingyan.resfly;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = getServer().getPluginManager();
        Plugin p = pm.getPlugin("Residence");
        if (p != null)
        {
            if (!(p.isEnabled()))
            {
                pm.enablePlugin(p);
            }
            FlagPermissions.addFlag("AutoResidenceFly");
            ItemStack is = new ItemStack(Material.ELYTRA);
            Residence.getInstance().getFlagUtilManager().getFlagData().addFlagButton("AutoResidenceFly", is);
            getServer().getPluginManager().registerEvents(new event(), this);
        }
        else
        {
            System.out.println(" - Residence NOT Installed, DISABLED!");
            setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
