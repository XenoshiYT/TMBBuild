package me.xenodev.tmbt.events;

import me.xenodev.tmbt.main.Main;
import me.xenodev.tmbt.utils.SBBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage(Main.prefix + "§7Der Spieler §6" + p.getName() + " §7ist §abeigetreten");

        SBBuilder.setScoreboard(p);
    }
}
