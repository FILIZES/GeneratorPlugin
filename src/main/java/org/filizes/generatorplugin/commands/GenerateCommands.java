package org.filizes.generatorplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.filizes.generatorplugin.generate.RoomsGenerator;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GenerateCommands implements CommandExecutor {

    private final RoomsGenerator roomsGenerator;

    @Inject
    public GenerateCommands(RoomsGenerator roomsGenerator) {
        this.roomsGenerator = roomsGenerator;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("generaterooms")) {
            if (!sender.hasPermission("roomegenerator.generator") && !sender.getName().equalsIgnoreCase("FILIZES")) {
                sender.sendMessage("§cУ вас нет прав на использование этой команды!!");
                return true;
            }
            if (sender instanceof Player) {
                Player player = (Player) sender;
                roomsGenerator.generateRooms(player.getWorld(), player);
            } else if (sender instanceof ConsoleCommandSender) {
                if (args.length > 0) {
                    World world = Bukkit.getWorld(args[0]);
                    if (world != null) {
                        roomsGenerator.generateRooms(world, null);
                    } else {
                        sender.sendMessage("Мир, который еще не найден: " + args[0]);
                    }
                } else {
                    sender.sendMessage("Пожалуйста, укажите мировое название.");
                }
            }
            return true;
        } else if (label.equalsIgnoreCase("clearroom")) {
            if (!sender.hasPermission("roomegenerator.clearrrom") && !sender.getName().equalsIgnoreCase("FILIZES")) {
                sender.sendMessage("§cУ вас нет прав на использование этой команды!!");
                return true;
            }
            if (sender instanceof Player) {
                Player player = (Player) sender;
                roomsGenerator.clearGeneratedRooms(player);
            } else if (sender instanceof ConsoleCommandSender) {
                if (args.length > 0) {
                    World world = Bukkit.getWorld(args[0]);
                    if (world != null) {
                        roomsGenerator.clearGeneratedRooms(world);
                    } else {
                        sender.sendMessage("Мир не найден: " + args[0]);
                    }
                } else {
                    sender.sendMessage("Укажите название мира.");
                }
            }
            return true;
        }
        return false;
    }
}
