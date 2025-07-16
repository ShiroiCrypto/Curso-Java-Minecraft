package me.guss.curso.events;

import me.guss.curso.Curso;
import me.guss.curso.utils.PoderManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class WorldEvents implements Listener {
    
    private final Curso plugin;
    private final PoderManager poderManager;
    private final Random random = new Random();
    
    public WorldEvents(Curso plugin, PoderManager poderManager) {
        this.plugin = plugin;
        this.poderManager = poderManager;
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        
        // Chance de 20% de dropar um item especial quando um mob morre
        if (random.nextDouble() < 0.2) {
            ItemStack itemEspecial = new ItemStack(Material.EMERALD);
            entity.getWorld().dropItemNaturally(entity.getLocation(), itemEspecial);
            
            // Efeito visual
            entity.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
        
        // Se foi morto por um jogador com super força
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            if (poderManager.isComSuperForca(killer.getUniqueId())) {
                // Drop extra para jogadores com super força
                event.getDrops().add(new ItemStack(Material.DIAMOND));
                killer.sendMessage("§a💪 Sua super força garantiu um diamante extra!");
            }
        }
    }
    
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        
        // Chance de 5% de spawnar um mob especial
        if (random.nextDouble() < 0.05 && entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            
            // Adiciona efeitos especiais ao mob
            livingEntity.setCustomName("§6§lMob Especial");
            livingEntity.setCustomNameVisible(true);
            
            // Efeito visual
            entity.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, entity.getLocation(), 20, 1, 1, 1, 0.1);
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
    }
    
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        // Efeito especial quando o clima muda
        if (event.toWeatherState()) {
            // Chance de 30% de criar uma tempestade especial
            if (random.nextDouble() < 0.3) {
                event.getWorld().setThundering(true);
                
                // Efeitos visuais especiais
                for (int i = 0; i < 5; i++) {
                    Location randomLoc = event.getWorld().getSpawnLocation().add(
                        (random.nextDouble() - 0.5) * 100,
                        0,
                        (random.nextDouble() - 0.5) * 100
                    );
                    event.getWorld().strikeLightning(randomLoc);
                }
                
                // Notifica todos os jogadores
                for (Player player : event.getWorld().getPlayers()) {
                    player.sendMessage("§9⛈️ Uma tempestade mágica se aproxima!");
                }
            }
        }
    }
} 