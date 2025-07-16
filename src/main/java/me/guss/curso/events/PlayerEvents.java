package me.guss.curso.events;

import me.guss.curso.Curso;
import me.guss.curso.utils.PoderManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PlayerEvents implements Listener {
    
    private final Curso plugin;
    private final PoderManager poderManager;
    private final Random random = new Random();
    
    public PlayerEvents(Curso plugin, PoderManager poderManager) {
        this.plugin = plugin;
        this.poderManager = poderManager;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Mensagem de boas-vindas personalizada
        player.sendMessage("§6╔══════════════════════════════════════════════════════════════╗");
        player.sendMessage("§6║                    §e§lBEM-VINDO! §6                        ║");
        player.sendMessage("§6║                                                              ║");
        player.sendMessage("§6║ §aVocê está no servidor com §e§lSUPER PODERES§a!            ║");
        player.sendMessage("§6║ §7Use §f/superpoder §7para ver todos os comandos disponíveis! ║");
        player.sendMessage("§6║ §7Use §f/voar §7para ativar o modo voo!                    ║");
        player.sendMessage("§6║ §7Use §f/teletransporte <jogador> §7para teleportar!        ║");
        player.sendMessage("§6║                                                              ║");
        player.sendMessage("§6║ §c⚠️  Alguns poderes requerem permissões especiais!         ║");
        player.sendMessage("§6╚══════════════════════════════════════════════════════════════╝");
        
        // Efeito de entrada
        player.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 20, 1, 1, 1, 0.1);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        
        // Chance de dar um item especial
        if (random.nextDouble() < 0.1) { // 10% de chance
            player.getInventory().addItem(new ItemStack(Material.DIAMOND));
            player.sendMessage("§a💎 Você recebeu um diamante especial como presente!");
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        // Limpa dados do jogador
        poderManager.limparDadosJogador(player.getUniqueId());
        
        // Efeito de saída
        player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        
        if (to == null) return;
        
        // Efeito para jogadores voando
        if (poderManager.isVoando(player.getUniqueId())) {
            Location loc = player.getLocation();
            player.getWorld().spawnParticle(Particle.CLOUD, loc, 1, 0.2, 0.2, 0.2, 0.01);
            
            // Efeito de rastro
            if (random.nextDouble() < 0.3) {
                player.getWorld().spawnParticle(Particle.END_ROD, loc, 1, 0.1, 0.1, 0.1, 0.01);
            }
        }
        
        // Efeito para jogadores com super jump
        if (poderManager.isComSuperJump(player.getUniqueId())) {
            if (to.getY() > from.getY() + 0.5) {
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 5, 0.3, 0.3, 0.3, 0.1);
            }
        }
        
        // Efeito para jogadores invisíveis
        if (poderManager.isInvisivel(player.getUniqueId())) {
            if (random.nextDouble() < 0.1) {
                player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation(), 1, 0.1, 0.1, 0.1, 0.01);
            }
        }
        
        // Efeito para jogadores com escudo
        if (poderManager.isComEscudo(player.getUniqueId())) {
            if (random.nextDouble() < 0.05) {
                Location loc = player.getLocation();
                player.getWorld().spawnParticle(Particle.BARRIER, loc, 1, 0.5, 0.5, 0.5, 0.01);
            }
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        
        // Chance de 15% de dropar um item especial
        if (random.nextDouble() < 0.15) {
            ItemStack itemEspecial = new ItemStack(Material.DIAMOND);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), itemEspecial);
            player.sendMessage("§a💎 Você encontrou um diamante especial!");
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
        
        // Efeito especial para jogadores com super força
        if (poderManager.isComSuperForca(player.getUniqueId())) {
            event.getBlock().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, event.getBlock().getLocation(), 3, 0.5, 0.5, 0.5, 0.1);
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5f, 1.0f);
        }
        
        // Efeito para jogadores com telecinese
        if (poderManager.isComTelecinese(player.getUniqueId())) {
            event.getBlock().getWorld().spawnParticle(Particle.MAGIC, event.getBlock().getLocation(), 10, 0.3, 0.3, 0.3, 0.1);
            player.sendMessage("§a🧠 Bloco movido com telecinese!");
        }
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            
            // Jogadores com velocidade alta causam mais dano
            Integer velocidade = poderManager.getVelocidade(player.getUniqueId());
            if (velocidade != null && velocidade > 5) {
                event.setDamage(event.getDamage() * 1.5);
                player.sendMessage("§c⚡ Ataque potenciado pela velocidade!");
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, event.getEntity().getLocation(), 5, 0.3, 0.3, 0.3, 0.1);
            }
            
            // Jogadores com super força causam dano extra
            if (poderManager.isComSuperForca(player.getUniqueId())) {
                event.setDamage(event.getDamage() * 2.0);
                player.sendMessage("§c💪 Ataque com super força!");
                player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, event.getEntity().getLocation(), 2, 0.2, 0.2, 0.2, 0.1);
            }
        }
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        // Telecinese - mover blocos com a mente
        if (poderManager.isComTelecinese(player.getUniqueId()) && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getClickedBlock() != null) {
                Location loc = event.getClickedBlock().getLocation();
                Material material = event.getClickedBlock().getType();
                
                // Remove o bloco da posição atual
                event.getClickedBlock().setType(Material.AIR);
                
                // Coloca o bloco em uma nova posição (próximo ao jogador)
                Location newLoc = player.getLocation().add(0, 1, 0);
                newLoc.getBlock().setType(material);
                
                // Efeitos visuais
                player.getWorld().spawnParticle(Particle.MAGIC, loc, 20, 0.5, 0.5, 0.5, 0.1);
                player.getWorld().spawnParticle(Particle.MAGIC, newLoc, 20, 0.5, 0.5, 0.5, 0.1);
                player.playSound(loc, Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
                player.playSound(newLoc, Sound.BLOCK_GLASS_PLACE, 1.0f, 1.0f);
                
                player.sendMessage("§a🧠 Bloco movido com telecinese!");
                event.setCancelled(true);
            }
        }
    }
} 