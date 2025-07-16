package me.guss.curso.utils;

import me.guss.curso.Curso;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    
    private final Curso plugin;
    private final ConfigManager configManager;
    private final Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();
    
    public CooldownManager(Curso plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }
    
    public boolean hasCooldown(Player player, String poder) {
        UUID uuid = player.getUniqueId();
        if (!cooldowns.containsKey(uuid)) {
            return false;
        }
        
        Map<String, Long> playerCooldowns = cooldowns.get(uuid);
        if (!playerCooldowns.containsKey(poder)) {
            return false;
        }
        
        long lastUse = playerCooldowns.get(poder);
        int cooldownSeconds = configManager.getCooldown(poder);
        long currentTime = System.currentTimeMillis();
        
        return (currentTime - lastUse) < (cooldownSeconds * 1000L);
    }
    
    public void setCooldown(Player player, String poder) {
        UUID uuid = player.getUniqueId();
        cooldowns.computeIfAbsent(uuid, k -> new HashMap<>());
        cooldowns.get(uuid).put(poder, System.currentTimeMillis());
    }
    
    public long getRemainingCooldown(Player player, String poder) {
        UUID uuid = player.getUniqueId();
        if (!cooldowns.containsKey(uuid)) {
            return 0;
        }
        
        Map<String, Long> playerCooldowns = cooldowns.get(uuid);
        if (!playerCooldowns.containsKey(poder)) {
            return 0;
        }
        
        long lastUse = playerCooldowns.get(poder);
        int cooldownSeconds = configManager.getCooldown(poder);
        long currentTime = System.currentTimeMillis();
        long remaining = (cooldownSeconds * 1000L) - (currentTime - lastUse);
        
        return Math.max(0, remaining);
    }
    
    public String getCooldownMessage(Player player, String poder) {
        long remaining = getRemainingCooldown(player, poder);
        if (remaining <= 0) {
            return null;
        }
        
        int seconds = (int) (remaining / 1000);
        return configManager.getMensagem("cooldown").replace("{tempo}", String.valueOf(seconds));
    }
    
    public void clearCooldown(Player player, String poder) {
        UUID uuid = player.getUniqueId();
        if (cooldowns.containsKey(uuid)) {
            cooldowns.get(uuid).remove(poder);
        }
    }
    
    public void clearAllCooldowns(Player player) {
        cooldowns.remove(player.getUniqueId());
    }
    
    public void clearAllCooldowns() {
        cooldowns.clear();
    }
} 