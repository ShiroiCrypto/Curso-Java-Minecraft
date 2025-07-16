package me.guss.curso.utils;

import me.guss.curso.Curso;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PoderManager {
    
    private final Curso plugin;
    private final Map<UUID, Boolean> jogadoresVoando = new HashMap<>();
    private final Map<UUID, Boolean> jogadoresInvisiveis = new HashMap<>();
    private final Map<UUID, Integer> velocidadesJogadores = new HashMap<>();
    private final Map<UUID, Boolean> jogadoresComSuperForca = new HashMap<>();
    private final Map<UUID, Boolean> jogadoresComEscudo = new HashMap<>();
    private final Map<UUID, Boolean> jogadoresComTelecinese = new HashMap<>();
    private final Map<UUID, Boolean> jogadoresComSuperJump = new HashMap<>();
    
    public PoderManager(Curso plugin) {
        this.plugin = plugin;
    }
    
    // Métodos básicos
    public boolean alternarVoo(Player player) {
        boolean voando = jogadoresVoando.getOrDefault(player.getUniqueId(), false);
        
        if (voando) {
            player.setAllowFlight(false);
            player.setFlying(false);
            jogadoresVoando.put(player.getUniqueId(), false);
            player.sendMessage("§cModo voo desativado!");
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            jogadoresVoando.put(player.getUniqueId(), true);
            player.sendMessage("§aModo voo ativado!");
        }
        
        return true;
    }
    
    public boolean alternarInvisibilidade(Player player) {
        boolean invisivel = jogadoresInvisiveis.getOrDefault(player.getUniqueId(), false);
        
        if (invisivel) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            jogadoresInvisiveis.put(player.getUniqueId(), false);
            player.sendMessage("§cInvisibilidade desativada!");
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
            jogadoresInvisiveis.put(player.getUniqueId(), true);
            player.sendMessage("§aInvisibilidade ativada!");
        }
        
        return true;
    }
    
    public boolean definirVelocidade(Player player, int nivel) {
        float velocidade = Math.min(0.2f + (nivel * 0.1f), 1.0f);
        player.setWalkSpeed(velocidade);
        velocidadesJogadores.put(player.getUniqueId(), nivel);
        player.sendMessage("§aVelocidade definida para nível " + nivel + "!");
        return true;
    }
    
    public boolean curarJogador(Player player) {
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.removePotionEffect(PotionEffectType.POISON);
        player.removePotionEffect(PotionEffectType.WITHER);
        player.sendMessage("§aVocê foi curado completamente!");
        return true;
    }
    
    public boolean invocarRaio(Player player) {
        Location loc = player.getLocation();
        player.getWorld().strikeLightning(loc);
        player.sendMessage("§e⚡ Raio invocado!");
        return true;
    }
    
    public boolean criarExplosao(Player player) {
        return criarExplosao(player, 2.0f);
    }
    
    public boolean criarExplosao(Player player, float forca) {
        Location loc = player.getLocation();
        player.getWorld().createExplosion(loc, forca, false, false);
        player.sendMessage("§c💥 Explosão criada com força " + forca + "!");
        return true;
    }
    
    public boolean teletransportar(Player player, Player alvo) {
        player.teleport(alvo);
        player.sendMessage("§aVocê foi teletransportado para " + alvo.getName() + "!");
        alvo.sendMessage("§a" + player.getName() + " teletransportou-se para você!");
        return true;
    }
    
    // Novos poderes avançados
    public boolean ativarSuperForca(Player player) {
        boolean ativo = jogadoresComSuperForca.getOrDefault(player.getUniqueId(), false);
        
        if (ativo) {
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            jogadoresComSuperForca.put(player.getUniqueId(), false);
            player.sendMessage("§cSuper força desativada!");
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2, false, false));
            jogadoresComSuperForca.put(player.getUniqueId(), true);
            player.sendMessage("§a💪 Super força ativada!");
        }
        
        return true;
    }
    
    public boolean ativarEscudo(Player player) {
        boolean ativo = jogadoresComEscudo.getOrDefault(player.getUniqueId(), false);
        
        if (ativo) {
            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            jogadoresComEscudo.put(player.getUniqueId(), false);
            player.sendMessage("§cEscudo desativado!");
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2, false, false));
            jogadoresComEscudo.put(player.getUniqueId(), true);
            player.sendMessage("§a🛡️ Escudo ativado!");
        }
        
        return true;
    }
    
    public boolean ativarTelecinese(Player player) {
        boolean ativo = jogadoresComTelecinese.getOrDefault(player.getUniqueId(), false);
        
        if (ativo) {
            jogadoresComTelecinese.put(player.getUniqueId(), false);
            player.sendMessage("§cTelecinese desativada!");
        } else {
            jogadoresComTelecinese.put(player.getUniqueId(), true);
            player.sendMessage("§a🧠 Telecinese ativada! Clique em blocos para movê-los!");
        }
        
        return true;
    }
    
    public boolean criarClone(Player player) {
        Location loc = player.getLocation();
        ArmorStand clone = player.getWorld().spawn(loc, ArmorStand.class);
        clone.setCustomName("§eClone de " + player.getName());
        clone.setCustomNameVisible(true);
        clone.setVisible(false);
        clone.setGravity(false);
        clone.setInvulnerable(true);
        
        // Remove o clone após 30 segundos
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (clone.isValid()) {
                clone.remove();
            }
        }, 600L); // 30 segundos
        
        player.sendMessage("§a👥 Clone criado!");
        return true;
    }
    
    public boolean invocarMeteorito(Player player) {
        Location loc = player.getLocation().add(0, 50, 0);
        
        // Cria o meteorito
        FallingBlock meteorito = player.getWorld().spawnFallingBlock(loc, Material.MAGMA_BLOCK.createBlockData());
        meteorito.setDropItem(false);
        meteorito.setHurtEntities(true);
        
        // Efeitos visuais
        player.getWorld().spawnParticle(Particle.FLAME, loc, 50, 2, 2, 2, 0.1);
        
        player.sendMessage("§c☄️ Meteorito invocado!");
        return true;
    }
    
    public boolean invocarTempestade(Player player) {
        World world = player.getWorld();
        world.setStorm(true);
        world.setThundering(true);
        
        // Efeitos especiais
        for (int i = 0; i < 10; i++) {
            Location randomLoc = player.getLocation().add(
                (Math.random() - 0.5) * 20,
                0,
                (Math.random() - 0.5) * 20
            );
            world.strikeLightning(randomLoc);
        }
        
        player.sendMessage("§9⛈️ Tempestade invocada!");
        return true;
    }
    
    public boolean criarPortal(Player player) {
        Location loc = player.getLocation();
        
        // Cria um portal com blocos de obsidian
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (x == 0 && z == 0) continue;
                loc.clone().add(x, 0, z).getBlock().setType(Material.OBSIDIAN);
            }
        }
        
        // Efeitos de portal
        player.getWorld().spawnParticle(Particle.PORTAL, loc, 100, 1, 1, 1, 0.5);
        player.getWorld().playSound(loc, Sound.BLOCK_PORTAL_TRIGGER, 1.0f, 1.0f);
        
        player.sendMessage("§5🌀 Portal dimensional criado!");
        return true;
    }
    
    public boolean resurreicao(Player player) {
        // Procura por jogadores mortos próximos
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.isDead() || onlinePlayer.getHealth() <= 0) {
                onlinePlayer.spigot().respawn();
                onlinePlayer.setHealth(onlinePlayer.getMaxHealth());
                onlinePlayer.sendMessage("§a✨ Você foi ressuscitado por " + player.getName() + "!");
                player.sendMessage("§a✨ Você ressuscitou " + onlinePlayer.getName() + "!");
                return true;
            }
        }
        
        player.sendMessage("§cNenhum jogador morto encontrado para ressuscitar!");
        return true;
    }
    
    public boolean ativarSuperJump(Player player) {
        boolean ativo = jogadoresComSuperJump.getOrDefault(player.getUniqueId(), false);
        
        if (ativo) {
            player.removePotionEffect(PotionEffectType.JUMP);
            jogadoresComSuperJump.put(player.getUniqueId(), false);
            player.sendMessage("§cSuper jump desativado!");
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 5, false, false));
            jogadoresComSuperJump.put(player.getUniqueId(), true);
            player.sendMessage("§a🦘 Super jump ativado!");
        }
        
        return true;
    }
    
    // Getters para verificar estados
    public boolean isVoando(UUID uuid) {
        return jogadoresVoando.getOrDefault(uuid, false);
    }
    
    public boolean isInvisivel(UUID uuid) {
        return jogadoresInvisiveis.getOrDefault(uuid, false);
    }
    
    public boolean isComSuperForca(UUID uuid) {
        return jogadoresComSuperForca.getOrDefault(uuid, false);
    }
    
    public boolean isComEscudo(UUID uuid) {
        return jogadoresComEscudo.getOrDefault(uuid, false);
    }
    
    public boolean isComTelecinese(UUID uuid) {
        return jogadoresComTelecinese.getOrDefault(uuid, false);
    }
    
    public boolean isComSuperJump(UUID uuid) {
        return jogadoresComSuperJump.getOrDefault(uuid, false);
    }
    
    public Integer getVelocidade(UUID uuid) {
        return velocidadesJogadores.get(uuid);
    }
    
    // Limpar dados do jogador
    public void limparDadosJogador(UUID uuid) {
        jogadoresVoando.remove(uuid);
        jogadoresInvisiveis.remove(uuid);
        velocidadesJogadores.remove(uuid);
        jogadoresComSuperForca.remove(uuid);
        jogadoresComEscudo.remove(uuid);
        jogadoresComTelecinese.remove(uuid);
        jogadoresComSuperJump.remove(uuid);
    }
} 