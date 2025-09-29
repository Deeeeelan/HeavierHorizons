package com.example.smallinconveniences.block.entity.custom;

import com.example.smallinconveniences.ModItems;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import com.example.smallinconveniences.block.entity.ImplementedInventory;
import com.example.smallinconveniences.block.ModBlockEntities;
import com.example.smallinconveniences.screen.custom.SteelMillScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SteelMillBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

	private static final int INPUT_SLOT = 0; // iron ingot
    private static final int INPUT_SLOT2 = 2; // coal dust
    private static final int INPUT_SLOT3 = 3; // coal
	private static final int OUTPUT_SLOT = 1; // steel ingot

	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 240; // 20ticks/sec = 12 sec

	public SteelMillBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.STEEL_MILL_BE, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> SteelMillBlockEntity.this.progress;
					case 1 -> SteelMillBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0: SteelMillBlockEntity.this.progress = value;
					case 1: SteelMillBlockEntity.this.maxProgress = value;
				}
			}

			@Override
			public int size() {
				return 2;
			}
		};
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
		return this.pos;
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return Text.translatable("block.smallinconveniences.steel_mill");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return new SteelMillScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
	}

	// fyi: https://wiki.fabricmc.net/tutorial:blockentity_modify_data
	// readNbt got updated to something new, crazy

	@Override
	protected void writeData(WriteView view) {
        super.writeData(view);

        Inventories.writeData(view, inventory);
        view.putInt("steel_mill.progress", progress);
		view.putInt("steel_mill.max_progress", maxProgress);
    }

	@Override
	protected void readData(ReadView view) {
		super.readData(view);
        Inventories.readData(view, inventory);
		progress = view.getInt("steel_mill.progress",0 );
		maxProgress = view.getInt("steel_mill.max_progress", 0);

    }

	public void tick(World world, BlockPos pos, BlockState state) {
		if (hasRecipe()) {
			increaseCraftingProgress();
			markDirty(world, pos, state);

			if (hasCraftingFinished()) {
				craftItem();
				resetProgress();
			}
		} else {
			resetProgress();
		}
	}

	private void resetProgress() {
		this.progress = 0;
		this.maxProgress = 240;
	}

	private void craftItem() {
		ItemStack output = new ItemStack(ModItems.STEEL_INGOT, 1);

		this.removeStack(INPUT_SLOT, 1);
        this.removeStack(INPUT_SLOT2, 1);
        this.removeStack(INPUT_SLOT3, 1);
		this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
				this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
	}

	private boolean hasCraftingFinished() {
		return this.progress >= this.maxProgress;
	}

	private void increaseCraftingProgress() {
		this.progress++;
	}

	private boolean hasRecipe() {
		Item input = Items.IRON_INGOT;
        Item input2 = ModItems.COAL_DUST;
        Item input3 = Items.COAL;

		ItemStack output = new ItemStack(ModItems.STEEL_INGOT, 1);

		return this.getStack(INPUT_SLOT).isOf(input) && this.getStack(INPUT_SLOT2).isOf(input2) &&
                this.getStack(INPUT_SLOT3).isOf(input3) &&
				canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
	}

	private boolean canInsertItemIntoOutputSlot(ItemStack output) {
		return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
	}

	private boolean canInsertAmountIntoOutputSlot(int count) {
		int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
		int currentCount = this.getStack(OUTPUT_SLOT).getCount();

		return maxCount >= currentCount + count;
	}

	@Nullable
	@Override
	public Packet<ClientPlayPacketListener> toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
		return createNbt(registryLookup);
	}
}