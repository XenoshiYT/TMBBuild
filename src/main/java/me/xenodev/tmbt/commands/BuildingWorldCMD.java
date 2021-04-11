package me.xenodev.tmbt.commands;

import me.xenodev.tmbt.main.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class BuildingWorldCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // /world create name type
        // /world add/remove name spieler
        // /world delete name
        // /world visit name
        // /world list

        // /worlds

        if(cmd.getName().equalsIgnoreCase("world")){
            if(args.length == 3){
                if(args[0].equalsIgnoreCase("create")){
                    String name = String.valueOf(args[1]);
                    if(args[2].equalsIgnoreCase("flat")){
                        World world = Bukkit.getWorld(name);
                        File worldfile = world.getWorldFolder();
                        if(!worldfile.exists()){
                            WorldCreator creator = new WorldCreator(name);
                            creator.environment(World.Environment.NORMAL);
                            creator.type(WorldType.FLAT);
                            creator.generateStructures(false);
                            creator.createWorld();
                            sender.sendMessage(Main.prefix + "§7Du hast erfolgreich die Welt §6" + name + " §7erstellt");
                        }else{
                            sender.sendMessage(Main.error + "§cDiese Welt exisitiert schon");
                            return true;
                        }
                    }else if(args[2].equalsIgnoreCase("normal")){
                        World world = Bukkit.getWorld(name);
                        File worldfile = world.getWorldFolder();
                        if(!worldfile.exists()){
                            WorldCreator creator = new WorldCreator(name);
                            creator.environment(World.Environment.NORMAL);
                            creator.type(WorldType.NORMAL);
                            creator.generateStructures(false);
                            creator.createWorld();
                            sender.sendMessage(Main.prefix + "§7Du hast erfolgreich die Welt §6" + name + " §7erstellt");
                        }else{
                            sender.sendMessage(Main.error + "§cDiese Welt exisitiert schon");
                            return true;
                        }
                    }else{
                        sender.sendMessage(Main.error + "§cDer Type §6" + args[2] + " §7exisitert nicht!");
                        sender.sendMessage(Main.prefix + "§7Benutze die Typen <FLAT oder NORMAL>");
                        return true;
                    }
                }else if(args[0].equalsIgnoreCase("add")){
                    String name = String.valueOf(args[1]);
                    OfflinePlayer t = Bukkit.getPlayerExact(args[2]);
                }else if(args[0].equalsIgnoreCase("remove")){
                    String name = String.valueOf(args[1]);
                    OfflinePlayer t = Bukkit.getPlayerExact(args[2]);
                }else{
                    sender.sendMessage(Main.error + "§cBitte benutze den Command richtig!");
                    sender.sendMessage(Main.error + "§c/world create name <flat oder normal>");
                    sender.sendMessage(Main.error + "§c/world <add, remove> name spieler");
                }
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("delete")){
                    String name = String.valueOf(args[1]);

                    if(name.equalsIgnoreCase("world") || name.equalsIgnoreCase("world_nether") || name.equalsIgnoreCase("world_the_end")){
                        sender.sendMessage(Main.error + "§cDu darfst die Standart-Welten nicht löschen!");
                        return true;
                    }
                    World world = Bukkit.getWorld(name);
                    File worldfile = world.getWorldFolder();
                    if(worldfile.exists()){
                        if(world.getPlayers().size() == 0){
                            File files[] = worldfile.listFiles();
                            for(int i = 0; i < files.length; i++){
                                files[i].delete();
                            }
                        }else{
                            sender.sendMessage(Main.error + "§cEs sind noch Spieler auf der Welt");
                            return true;
                        }
                    }else{
                        sender.sendMessage(Main.error + "§cDiese Welt exisitiert nicht");
                        return true;
                    }
                }else if(args[0].equalsIgnoreCase("visit")){
                    String name = String.valueOf(args[1]);

                    World world = Bukkit.getWorld(name);
                    File worldfile = world.getWorldFolder();
                    if(worldfile.exists()){
                        Location loc = world.getSpawnLocation();
                        if(sender instanceof Player){
                            Player p = (Player) sender;
                            p.teleport(loc);
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(Main.prefix + "§7Du hast dich zur Welt §6" + name + " §7teleportiert");
                        }
                    }else{
                        sender.sendMessage(Main.error + "§cDiese Welt exisitiert nicht");
                        return true;
                    }
                }else{
                    sender.sendMessage(Main.error + "§cBitte benutze den Command richtig!");
                    sender.sendMessage(Main.error + "§c/world <delete, visit> name");
                }
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("list")){

                }
            }
        }

        if(cmd.getName().equalsIgnoreCase("worlds")){

        }

        return false;
    }
}
