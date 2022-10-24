package beailotus_io.github.multiplyexp.commands;

import beailotus_io.github.multiplyexp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MultiplyCommand implements CommandExecutor {
    private final Main plugin;

    public MultiplyCommand(Main main) {
        this.plugin = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("multiplyexp.admin")) {
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("Usage: /multiply <reload/get/set> [value]");
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reload();
            sender.sendMessage("Config reloaded!");
            return true;
        }

        if (args[0].equalsIgnoreCase("get")) {
            sender.sendMessage("Multiplier: " + plugin.getConfig().getDouble("multiplier"));
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length == 1) {
                sender.sendMessage("Usage: /multiply set <value>");
                return false;
            }
            try {
                double value = Double.parseDouble(args[1]);
                plugin.getConfig().set("multiplier", value);
                plugin.saveConfig();
                plugin.reload();
                sender.sendMessage("Multiplier set to " + value);
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid number!");
                return false;
            }
        }

        return false;
    }
}
