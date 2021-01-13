package franciscovenned.github.com.simplecore.utils;

import franciscovenned.github.com.simplecore.MainClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class TextUtils {
    public static void send(CommandSender sender, String message){
            MainClass plugin = MainClass.getInstance();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("prefix") + " " + message));
    }
}
