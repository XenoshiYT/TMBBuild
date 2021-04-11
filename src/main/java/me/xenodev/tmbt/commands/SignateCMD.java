package me.xenodev.tmbt.commands;

import me.xenodev.tmbt.main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SignateCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("signate") || cmd.getName().equalsIgnoreCase("sign")){
            if(p.hasPermission("tmb.signate")){
                if(args.length > 0) {
                    if (p.getItemInHand().getType().equals(Material.AIR)) {
                        String msg = "";
                        for (int i = 0; i < args.length; i++){
                            msg = msg + args[i] + " ";
                        }
                        ItemStack item = p.getItemInHand();
                        ItemMeta meta = item.getItemMeta();
                        meta.setLore(Arrays.asList("", msg.replace("&", "§")));
                        item.setItemMeta(meta);
                        p.sendMessage(Main.prefix + "§7Du hast das Item segniert");
                    } else {
                        p.sendMessage(Main.error + "§cDu hast kein Item in der Hand");
                    }
                }else{
                    p.sendMessage(Main.error + "§7Bitte benutze §a/sign <name>");
                }
            }else{
                p.sendMessage(Main.error + "§7Dazu hast du §ckeine §7Rechte");
            }
        }

        return false;
    }
}
