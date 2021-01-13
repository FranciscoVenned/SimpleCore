package franciscovenned.github.com.simplecore;

import franciscovenned.github.com.simplecore.commands.RebootCommand;
import franciscovenned.github.com.simplecore.commands.TeleportCommand;
import franciscovenned.github.com.simplecore.playerdata.PlayerData;
import franciscovenned.github.com.simplecore.playerdata.PlayerDataListener;
import franciscovenned.github.com.simplecore.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class MainClass extends JavaPlugin {

    private final PluginDescriptionFile pdfFile = getDescription();
    private final String version = pdfFile.getVersion();
    private static MainClass instance;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerDataListener(), this);
        registerConfig();
        registerCommands();
        registerEvents();
        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Simple Core]&f-------------------");
        TextUtils.send(Bukkit.getConsoleSender(), "&fStatus Plugin: &e&lENABLED");
        TextUtils.send(Bukkit.getConsoleSender(), "&fVersion: &e0.1b");
        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Simple Core]&f-------------------");

    }

    @Override
    public void onDisable() {
        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Simple Core]&f-------------------");
        TextUtils.send(Bukkit.getConsoleSender(), "&fStatus Plugin: &e&lDisabled ):");
        TextUtils.send(Bukkit.getConsoleSender(), "&fGood bye!");
        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Simple Core]&f-------------------");
    }

    private void registerConfig() {

        File config = new File(getDataFolder(),"config.yml");
        if(!config.exists()) {
            saveDefaultConfig();
        }
    }

    public void saveConfig(){
        try {
            getConfig().save(new File(getDataFolder(), "config.yml")); //Guardo el item en el yml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
    }

    private void registerCommands() {
        this.getCommand("tp").setExecutor(new TeleportCommand(this));
        this.getCommand("reboot").setExecutor(new RebootCommand(this));
    }

    public static PlayerData getData(UUID uuid){
        PlayerData playerData = PlayerDataListener.dataMap.get(uuid);

        if(playerData == null) {
            playerData = new PlayerData(uuid);
        }

        return playerData;
    }


    public static MainClass getInstance(){
        return instance;
    }
}
