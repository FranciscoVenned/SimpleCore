package franciscovenned.github.com.simplecore.commands;

import franciscovenned.github.com.simplecore.MainClass;
import franciscovenned.github.com.simplecore.bukkitrunnable.Reboot;
import franciscovenned.github.com.simplecore.utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RebootCommand implements CommandExecutor {

    private final MainClass plugin;

    public RebootCommand(MainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("simplecore.reboot")) {
            TextUtils.send(sender, "You not permissions");
            return true;
        }

        Reboot.reboot();

        return true;
    }

}
