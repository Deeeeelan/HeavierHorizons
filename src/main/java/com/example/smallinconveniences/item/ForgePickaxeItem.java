package com.example.smallinconveniences.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ForgePickaxeItem extends Item {

    public ForgePickaxeItem(Settings settings) {
        super(settings);
        this.matchGetter = ServerRecipeManager.createCachedMatchGetter(RecipeType.SMELTING);
    }
    private final ServerRecipeManager.MatchGetter<SingleStackRecipeInput, ? extends AbstractCookingRecipe> matchGetter;

    Random random = new Random();

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        if (!world.isClient && state.isIn(BlockTags.PICKAXE_MINEABLE)) {
            ItemStack input = new ItemStack(state.getBlock().asItem());
            SingleStackRecipeInput singleStackRecipeInput = new SingleStackRecipeInput(input);
            RecipeEntry<? extends AbstractCookingRecipe> recipeEntry = this.matchGetter.getFirstMatch(singleStackRecipeInput, (ServerWorld) world).orElse(null);

            if (recipeEntry != null && random.nextBoolean()) {
                // world.setBlockState(pos, Blocks.AIR.getDefaultState());
                ItemStack droppedItem = recipeEntry.value().craft(singleStackRecipeInput, ((ServerWorld) world).getServer().getRegistryManager());

                Block.dropStack(world, pos, droppedItem);

                stack.damage(1, miner, EquipmentSlot.MAINHAND);
                return true;
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

}
