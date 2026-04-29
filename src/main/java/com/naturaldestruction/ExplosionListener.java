package com.naturaldestruction;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ExplosionListener implements Listener {

    /**
     * The only blocks TNT (or any explosion) is allowed to destroy.
     * Logs are intentionally excluded. Player-crafted/placed blocks are not listed.
     */
    private static final Set<Material> BREAKABLE = EnumSet.of(

        // ── Overworld stone & rock ────────────────────────────────────────────
        Material.STONE,
        Material.GRANITE,
        Material.DIORITE,
        Material.ANDESITE,
        Material.DEEPSLATE,
        Material.CALCITE,
        Material.TUFF,
        Material.TUFF_BRICKS,
        Material.CHISELED_TUFF,
        Material.OBSIDIAN,
        Material.CRYING_OBSIDIAN,
        Material.BEDROCK,

        // ── Dirt & soil ───────────────────────────────────────────────────────
        Material.DIRT,
        Material.GRASS_BLOCK,
        Material.COARSE_DIRT,
        Material.ROOTED_DIRT,
        Material.PODZOL,
        Material.MYCELIUM,
        Material.MUD,

        // ── Sand & gravel ─────────────────────────────────────────────────────
        Material.SAND,
        Material.RED_SAND,
        Material.GRAVEL,

        // ── Sandstone (naturally generates) ──────────────────────────────────
        Material.SANDSTONE,
        Material.CHISELED_SANDSTONE,
        Material.CUT_SANDSTONE,
        Material.RED_SANDSTONE,
        Material.CHISELED_RED_SANDSTONE,
        Material.CUT_RED_SANDSTONE,

        // ── Ores ──────────────────────────────────────────────────────────────
        Material.COAL_ORE,
        Material.DEEPSLATE_COAL_ORE,
        Material.IRON_ORE,
        Material.DEEPSLATE_IRON_ORE,
        Material.COPPER_ORE,
        Material.DEEPSLATE_COPPER_ORE,
        Material.GOLD_ORE,
        Material.DEEPSLATE_GOLD_ORE,
        Material.REDSTONE_ORE,
        Material.DEEPSLATE_REDSTONE_ORE,
        Material.LAPIS_ORE,
        Material.DEEPSLATE_LAPIS_ORE,
        Material.DIAMOND_ORE,
        Material.DEEPSLATE_DIAMOND_ORE,
        Material.EMERALD_ORE,
        Material.DEEPSLATE_EMERALD_ORE,
        Material.NETHER_QUARTZ_ORE,
        Material.NETHER_GOLD_ORE,
        Material.ANCIENT_DEBRIS,

        // ── Raw ore blocks (generate in blobs) ───────────────────────────────
        Material.RAW_IRON_BLOCK,
        Material.RAW_COPPER_BLOCK,
        Material.RAW_GOLD_BLOCK,

        // ── Ice & snow ────────────────────────────────────────────────────────
        Material.ICE,
        Material.PACKED_ICE,
        Material.BLUE_ICE,
        Material.SNOW_BLOCK,
        Material.SNOW,
        Material.POWDER_SNOW,

        // ── Leaves ───────────────────────────────────────────────────────────
        Material.OAK_LEAVES,
        Material.SPRUCE_LEAVES,
        Material.BIRCH_LEAVES,
        Material.JUNGLE_LEAVES,
        Material.ACACIA_LEAVES,
        Material.DARK_OAK_LEAVES,
        Material.MANGROVE_LEAVES,
        Material.CHERRY_LEAVES,
        Material.AZALEA_LEAVES,
        Material.FLOWERING_AZALEA_LEAVES,

        // ── Vegetation ───────────────────────────────────────────────────────
        Material.TALL_GRASS,
        Material.FERN,
        Material.LARGE_FERN,
        Material.DEAD_BUSH,
        Material.SEAGRASS,
        Material.TALL_SEAGRASS,
        Material.KELP,
        Material.KELP_PLANT,
        Material.VINE,
        Material.GLOW_LICHEN,
        Material.HANGING_ROOTS,
        Material.MOSS_BLOCK,
        Material.MOSS_CARPET,
        Material.BIG_DRIPLEAF,
        Material.SMALL_DRIPLEAF,
        Material.SPORE_BLOSSOM,
        Material.AZALEA,
        Material.FLOWERING_AZALEA,
        Material.PINK_PETALS,
        Material.SHORT_GRASS,

        // ── Flowers ───────────────────────────────────────────────────────────
        Material.DANDELION,
        Material.POPPY,
        Material.BLUE_ORCHID,
        Material.ALLIUM,
        Material.AZURE_BLUET,
        Material.RED_TULIP,
        Material.ORANGE_TULIP,
        Material.WHITE_TULIP,
        Material.PINK_TULIP,
        Material.OXEYE_DAISY,
        Material.CORNFLOWER,
        Material.LILY_OF_THE_VALLEY,
        Material.SUNFLOWER,
        Material.LILAC,
        Material.ROSE_BUSH,
        Material.PEONY,
        Material.WITHER_ROSE,
        Material.TORCHFLOWER,
        Material.PITCHER_PLANT,

        // ── Mushrooms ─────────────────────────────────────────────────────────
        Material.BROWN_MUSHROOM,
        Material.RED_MUSHROOM,
        Material.BROWN_MUSHROOM_BLOCK,
        Material.RED_MUSHROOM_BLOCK,
        Material.MUSHROOM_STEM,

        // ── Clay ──────────────────────────────────────────────────────────────
        Material.CLAY,

        // ── Terracotta (badlands) ─────────────────────────────────────────────
        Material.TERRACOTTA,
        Material.WHITE_TERRACOTTA,
        Material.ORANGE_TERRACOTTA,
        Material.MAGENTA_TERRACOTTA,
        Material.LIGHT_BLUE_TERRACOTTA,
        Material.YELLOW_TERRACOTTA,
        Material.LIME_TERRACOTTA,
        Material.PINK_TERRACOTTA,
        Material.GRAY_TERRACOTTA,
        Material.LIGHT_GRAY_TERRACOTTA,
        Material.CYAN_TERRACOTTA,
        Material.PURPLE_TERRACOTTA,
        Material.BLUE_TERRACOTTA,
        Material.BROWN_TERRACOTTA,
        Material.GREEN_TERRACOTTA,
        Material.RED_TERRACOTTA,
        Material.BLACK_TERRACOTTA,

        // ── Dripstone ────────────────────────────────────────────────────────
        Material.DRIPSTONE_BLOCK,
        Material.POINTED_DRIPSTONE,

        // ── Amethyst ─────────────────────────────────────────────────────────
        Material.AMETHYST_BLOCK,
        Material.BUDDING_AMETHYST,
        Material.AMETHYST_CLUSTER,
        Material.LARGE_AMETHYST_BUD,
        Material.MEDIUM_AMETHYST_BUD,
        Material.SMALL_AMETHYST_BUD,

        // ── Sculk (deep dark) ─────────────────────────────────────────────────
        Material.SCULK,
        Material.SCULK_VEIN,
        Material.SCULK_CATALYST,
        Material.SCULK_SHRIEKER,
        Material.SCULK_SENSOR,

        // ── Ocean / underwater ───────────────────────────────────────────────
        Material.SPONGE,
        Material.WET_SPONGE,
        Material.PRISMARINE,
        Material.PRISMARINE_BRICKS,
        Material.DARK_PRISMARINE,
        Material.SEA_LANTERN,
        Material.TUBE_CORAL_BLOCK,
        Material.BRAIN_CORAL_BLOCK,
        Material.BUBBLE_CORAL_BLOCK,
        Material.FIRE_CORAL_BLOCK,
        Material.HORN_CORAL_BLOCK,
        Material.DEAD_TUBE_CORAL_BLOCK,
        Material.DEAD_BRAIN_CORAL_BLOCK,
        Material.DEAD_BUBBLE_CORAL_BLOCK,
        Material.DEAD_FIRE_CORAL_BLOCK,
        Material.DEAD_HORN_CORAL_BLOCK,
        Material.TUBE_CORAL,
        Material.BRAIN_CORAL,
        Material.BUBBLE_CORAL,
        Material.FIRE_CORAL,
        Material.HORN_CORAL,
        Material.TUBE_CORAL_FAN,
        Material.BRAIN_CORAL_FAN,
        Material.BUBBLE_CORAL_FAN,
        Material.FIRE_CORAL_FAN,
        Material.HORN_CORAL_FAN,

        // ── Nether ────────────────────────────────────────────────────────────
        Material.NETHERRACK,
        Material.SOUL_SAND,
        Material.SOUL_SOIL,
        Material.MAGMA_BLOCK,
        Material.GLOWSTONE,
        Material.BASALT,
        Material.SMOOTH_BASALT,
        Material.BLACKSTONE,
        Material.CRIMSON_NYLIUM,
        Material.WARPED_NYLIUM,
        Material.CRIMSON_STEM,
        Material.WARPED_STEM,
        Material.SHROOMLIGHT,
        Material.NETHER_WART_BLOCK,
        Material.WARPED_WART_BLOCK,
        Material.BONE_BLOCK,

        // ── End ───────────────────────────────────────────────────────────────
        Material.END_STONE,
        Material.CHORUS_FLOWER,
        Material.CHORUS_PLANT,
        Material.PURPUR_BLOCK,
        Material.PURPUR_PILLAR,

        // ── Mangrove swamp ────────────────────────────────────────────────────
        Material.MANGROVE_ROOTS,
        Material.MUDDY_MANGROVE_ROOTS,

        // ── Misc naturally occurring ──────────────────────────────────────────
        Material.COBWEB,
        Material.SPAWNER
    );

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().removeIf(block -> !BREAKABLE.contains(block.getType()));
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockExplode(BlockExplodeEvent event) {
        event.blockList().removeIf(block -> !BREAKABLE.contains(block.getType()));
    }
}
