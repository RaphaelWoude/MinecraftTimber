package org.sympatic.timber.commands;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.sympatic.timber.Main;
import org.sympatic.timber.state.TimberState;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class TimberCommand implements CommandExecutor, TabCompleter {

    private final Main main;

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("timber.command")) {
            sender.sendMessage(ChatColor.RED + "Error: You do not have permission to use this command!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------");
            sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Commands for Timber.");
            sender.sendMessage(ChatColor.DARK_GRAY + " » " + ChatColor.YELLOW + "/timber enable");
            sender.sendMessage(ChatColor.GRAY + " Enables the timber functionality if disabled.");
            sender.sendMessage(ChatColor.GRAY + " Does not apply to configuration file.");
            sender.sendMessage(ChatColor.DARK_GRAY + " » " + ChatColor.YELLOW + "/timber disable");
            sender.sendMessage(ChatColor.GRAY + " Disables the timber functionality if enabled.");
            sender.sendMessage(ChatColor.GRAY + " Does not apply to configuration file.");
            sender.sendMessage(ChatColor.DARK_GRAY + " » " + ChatColor.YELLOW + "/timber version");
            sender.sendMessage(ChatColor.GRAY + " Shows the version of the plugin.");
            sender.sendMessage(ChatColor.DARK_GRAY + " » " + ChatColor.YELLOW + "/timber about");
            sender.sendMessage(ChatColor.GRAY + " Shows information about the plugin.");
            sender.sendMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------");
            return true;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "enable":
                    if (main.getTimberState() == TimberState.ENABLED) {
                        sender.sendMessage(ChatColor.RED + "Error: Timber functionality has already been enabled.");
                        break;
                    }

                    main.setTimberState(TimberState.ENABLED);
                    sender.sendMessage(ChatColor.GREEN + "Success: Timber functionality has been enabled.");
                    break;
                case "disable":
                    if (main.getTimberState() == TimberState.DISABLED) {
                        sender.sendMessage(ChatColor.RED + "Error: Timber functionality has already been disabled.");
                        break;
                    }

                    main.setTimberState(TimberState.DISABLED);
                    sender.sendMessage(ChatColor.GREEN + "Success: Timber functionality has been disabled.");
                    break;
                case "version":
                    sender.sendMessage(ChatColor.GOLD + "Timber version: " + ChatColor.YELLOW + main.getDescription().getVersion());
                    break;
                case "about":
                    sender.sendMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------");
                    sender.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "About Timber.");
                    sender.sendMessage(ChatColor.YELLOW + " Timber. Created by Sympatic");
                    sender.sendMessage(ChatColor.YELLOW + " A lightweight timber plugin.");
                    sender.sendMessage(ChatColor.YELLOW + " Inspired by the UHC scenario Timber.");
                    sender.sendMessage("");
                    sender.sendMessage(ChatColor.YELLOW + " Mining a tree causes the whole tree to fall down.");
                    sender.sendMessage(ChatColor.YELLOW + " Using an axe causes the tree to fall down faster.");
                    sender.sendMessage(ChatColor.YELLOW + " The better the axe is, the faster the tree falls down.");
                    sender.sendMessage(ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------");
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "Error: This command does not exist!");
                    break;
            }
        }

        return true;
    }

    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> list = new LinkedList<>();

        list.add("enable");
        list.add("disable");
        list.add("version");
        list.add("about");

        return list;
    }

}
