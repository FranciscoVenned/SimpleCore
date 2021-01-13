package franciscovenned.github.com.simplecore.playerdata;

import franciscovenned.github.com.simplecore.MainClass;
import franciscovenned.github.com.simplecore.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;

    private Location lastlocation;
    private String kills;
    private String deaths;
    private double xp;


    public PlayerData(UUID uuid){
        this.uuid = uuid;
    }

    public void setLastlocation(Location lastlocation){
        this.lastlocation = lastlocation;
    }

    public Location getLastLocation() {
        return this.lastlocation;
    }

    public void setKills(String kills){
        this.kills = kills;
    }

    public String getKills() {
        return this.kills;
    }

    public void setDeaths(String deaths){
        this.deaths = deaths;
    }

    public String getDeaths() {
        return this.deaths;
    }

    public void setXp(Integer xp){
        this.xp = xp;
    }

    public double getXp(){
        return this.xp;
    }


    public void save() {
        new BukkitRunnable() {

            @Override
            public void run() {
                Config config = new Config(MainClass.getInstance(), "players/" + uuid.toString());

                if (lastlocation != null) {
                    config.set("lastlocation", locationToString(lastlocation));
                }

                if (kills != null){
                    config.set("kills", kills);
                }

                if (deaths != null){
                    config.set("deaths", kills);
                }

                config.set("xp", xp);


                config.save();
            }
        }.runTaskAsynchronously(MainClass.getInstance()); // Se ejecutara fuera del hilo principal.
    }

    public void load() {
        new BukkitRunnable() {

            @Override
            public void run() {
                Config config = new Config(MainClass.getInstance(), "players/" + uuid.toString());

                if(config.contains("lastlocations")){
                    lastlocation = stringToLocation(config.getString("lastlocation"));
                }

                if (config.contains("kills")){
                    kills = config.getString("kills");
                }

                if (config.contains("deaths")){
                    deaths = config.getString("deaths");
                }

                if (config.contains("xp")){
                    xp = config.getDouble("xp");
                }


            }
        }.runTaskAsynchronously(MainClass.getInstance()); // Se ejecutara fuera del hilo principal.
    }

    private String locationToString(Location location){
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
    }

    private Location stringToLocation(String string){
        String[] data = string.split(":");

        World world = Bukkit.getWorld(data[0]);

        double x = Double.parseDouble(data[1]);
        double y = Double.parseDouble(data[2]);
        double z = Double.parseDouble(data[3]);

        double yaw = Double.parseDouble(data[4]);
        double pitch = Double.parseDouble(data[5]);

        return new Location(world, x, y, z, (float) yaw, (float) pitch);
    }

}
