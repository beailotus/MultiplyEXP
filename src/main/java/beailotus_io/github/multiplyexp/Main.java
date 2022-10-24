package beailotus_io.github.multiplyexp;

import beailotus_io.github.multiplyexp.commands.MultiplyCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {
    private double multiplier = 2.0;
    @Override
    public void onEnable() {
        reload();
        Objects.requireNonNull(getCommand("multiply"))
                .setExecutor(new MultiplyCommand(this));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    public void reload(){
        reloadConfig();
        multiplier = getConfig().getDouble("multiplier");
    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        event.setAmount((int) (event.getAmount() * multiplier));
    }
}
