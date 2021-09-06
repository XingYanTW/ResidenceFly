package me.xingyan.resfly;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.event.ResidenceChangedEvent;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class event implements Listener {

    @EventHandler
    public void ResidenceChangedEvent(ResidenceChangedEvent e)
    {
        ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(e.getPlayer().getLocation());
        if (res != null) {
            ResidencePermissions perms = res.getPermissions();
            boolean ResFlyOwner = e.getPlayer().isPermissionSet("ResidenceFly.owner");
            boolean ResFlyMember = e.getPlayer().isPermissionSet("ResidenceFly.member");
            if ((ResFlyOwner) && (res.isOwner(e.getPlayer()))) {
                e.getPlayer().setFlying(true);
                e.getPlayer().setAllowFlight(true);
                return;
            }
            if ((ResFlyMember) && (perms.isPlayerSet(e.getPlayer().toString(), "AutoResidenceFly"))) {
                e.getPlayer().setFlying(true);
                e.getPlayer().setAllowFlight(true);
                return;
            }
            if (!(perms.isPlayerSet(e.getPlayer().toString(), "AutoResidenceFly"))) e.getPlayer().setFlying(false);
        } else {
            if (!(e.getPlayer().isPermissionSet("ResidenceFly.KeepFly"))) e.getPlayer().setFlying(false);
            if (!(e.getPlayer().isPermissionSet("ResidenceFly.KeepAllowFlight"))) e.getPlayer().setAllowFlight(false);
        }
    }
}
