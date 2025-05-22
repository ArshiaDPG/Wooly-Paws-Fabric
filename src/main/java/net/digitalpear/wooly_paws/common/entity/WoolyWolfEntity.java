package net.digitalpear.wooly_paws.common.entity;


import net.digitalpear.wooly_paws.init.WPDataComponentTypes;
import net.digitalpear.wooly_paws.init.WPEntityType;
import net.digitalpear.wooly_paws.init.WPLootTables;
import net.digitalpear.wooly_paws.init.WPTags;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.WolfVariants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WoolyWolfEntity extends WolfEntity implements Shearable {
    private static final TrackedData<Byte> COLOR = DataTracker.registerData(WoolyWolfEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Integer> DIGESTION = DataTracker.registerData(WoolyWolfEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final DyeColor DEFAULT_COLOR = DyeColor.WHITE;

    public WoolyWolfEntity(EntityType<? extends WolfEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR,(byte) 0);
        builder.add(DIGESTION, 0);
    }
    public boolean isSheared() {
        return (this.dataTracker.get(COLOR) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b = this.dataTracker.get(COLOR);
        if (sheared) {
            this.dataTracker.set(COLOR, (byte)(b | 16));
        } else {
            this.dataTracker.set(COLOR, (byte)(b & -17));
        }
    }


    @Override
    public void sheared(ServerWorld world, SoundCategory shearedSoundCategory, ItemStack shears) {
        world.playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.forEachShearedItem(world, WPLootTables.WOOLY_WOLF_SHEARING, shears, (serverWorld, itemStack) -> {
            for(int i = 0; i < itemStack.getCount(); ++i) {
                ItemEntity itemEntity = this.dropStack(serverWorld, itemStack.copyWithCount(1), 1.0F);
                if (itemEntity != null) {
                    itemEntity.setVelocity(itemEntity.getVelocity().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
                }
            }
        });
        this.setSheared(true);
    }

    public boolean isShearable() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }
    @SuppressWarnings("deprecation")
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sheared", this.isSheared());
        nbt.put("Color", DyeColor.INDEX_CODEC, this.getColor());
        nbt.putInt("Digestion", this.getDigestion());
    }
    @SuppressWarnings("deprecation")
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSheared(nbt.getBoolean("Sheared").orElse(false));
        this.setDigestion(nbt.getInt("Digestion").orElse(0));
        this.setColor(nbt.get("Color", DyeColor.INDEX_CODEC).orElse(DEFAULT_COLOR));
    }

    @Override
    protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
        if (type == DataComponentTypes.SHEEP_COLOR) {
            this.setColor(castComponentValue(DataComponentTypes.SHEEP_COLOR, value));
            return true;
        } else if (type == WPDataComponentTypes.DIGESTION) {
            this.setDigestion(castComponentValue(WPDataComponentTypes.DIGESTION, value));
            return true;
        } else {
            return super.setApplicableComponent(type, value);
        }
    }

    @Override
    protected void copyComponentsFrom(ComponentsAccess from) {
        this.copyComponentFrom(from, DataComponentTypes.SHEEP_COLOR);
        this.copyComponentFrom(from, WPDataComponentTypes.DIGESTION);
        super.copyComponentsFrom(from);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        EntityData data = super.initialize(world, difficulty, spawnReason, entityData);
        this.setDigestion(0);
        if (Objects.requireNonNull(this.get(DataComponentTypes.WOLF_VARIANT)).isIn(WPTags.WolfVariants.WOOLY_WOLF_VARIANT_BLACKLIST)){
            this.setComponent(DataComponentTypes.WOLF_VARIANT, this.getRegistryManager().getOrThrow(RegistryKeys.WOLF_VARIANT).getOrThrow(WolfVariants.DEFAULT));
        }
        return data;
    }

    @Nullable
    @Override
    public <T> T get(ComponentType<? extends T> type) {
        if (type == WPDataComponentTypes.DIGESTION){
            return castComponentValue(type, this.getDigestion());
        }
        return type == DataComponentTypes.SHEEP_COLOR ? castComponentValue(type, this.getColor()) : super.get(type);
    }

    public DyeColor getColor() {
        return DyeColor.byIndex(this.dataTracker.get(COLOR) & 15);
    }

    public void setColor(DyeColor color) {
        byte b = this.dataTracker.get(COLOR);
        this.dataTracker.set(COLOR, (byte)(b & 240 | color.getIndex() & 15));
    }

    public void setDigestion(int i){
        this.dataTracker.set(DIGESTION, i);
    }
    public Integer getDigestion(){
        return this.dataTracker.get(DIGESTION);
    }
    public int maxDigestion(){
        return 4;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack handStack = player.getStackInHand(hand);
        if (handStack.isOf(Items.SHEARS) && !this.isWearingBodyArmor()) {
            World var5 = this.getWorld();
            if (var5 instanceof ServerWorld serverWorld) {
                if (this.isShearable()) {
                    this.sheared(serverWorld, SoundCategory.PLAYERS, handStack);
                    this.emitGameEvent(GameEvent.SHEAR, player);
                    handStack.damage(1, player, getSlotForHand(hand));
                    return ActionResult.SUCCESS_SERVER;
                }
            }
            return ActionResult.CONSUME;
        }
        else if (this.isShearable() && handStack.getItem() instanceof DyeItem dyeItem){
            player.swingHand(hand);
            handStack.decrementUnlessCreative(1, player);
            this.setColor(dyeItem.getColor());
            this.getWorld().playSoundFromEntity(player, this, SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    @Override
    public boolean onKilledOther(ServerWorld world, LivingEntity other) {
        if (other.getType().isIn(WPTags.EntityTypes.WOOLY_WOLF_CAN_STEAL_WOOL)){
            if (this.isSheared()){
                this.setSheared(false);
                if (other.get(DataComponentTypes.SHEEP_COLOR) != null){
                    this.setColor(other.get(DataComponentTypes.SHEEP_COLOR));
                }

            }
        }
        else{
            this.setDigestion(this.getDigestion() + 1);
            if (this.getDigestion() >= maxDigestion() && this.isSheared()){
                this.setDigestion(0);
                this.setSheared(false);
            }
        }
        return super.onKilledOther(world, other);
    }

    @Nullable
    @Override
    public WolfEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        WolfEntity wolfEntity = super.createChild(serverWorld, passiveEntity);
        WoolyWolfEntity woolyWolfEntity = WPEntityType.WOOLY_WOLF.create(serverWorld, SpawnReason.BREEDING);
        if (woolyWolfEntity != null && wolfEntity != null) {
            if (this.isTamed()){
                woolyWolfEntity.setOwner(this.getOwner());
                woolyWolfEntity.setTamed(true, true);
            }
            woolyWolfEntity.copyComponentsFrom(wolfEntity);
            DyeColor dyeColor = this.getColor();
            DyeColor dyeColor2 = ((WoolyWolfEntity)passiveEntity).getColor();
            woolyWolfEntity.setColor(DyeColor.mixColors(serverWorld, dyeColor, dyeColor2));

            if (Objects.requireNonNull(woolyWolfEntity.get(DataComponentTypes.WOLF_VARIANT)).isIn(WPTags.WolfVariants.WOOLY_WOLF_VARIANT_BLACKLIST)){
                woolyWolfEntity.setComponent(DataComponentTypes.WOLF_VARIANT, this.getRegistryManager().getOrThrow(RegistryKeys.WOLF_VARIANT).getOrThrow(WolfVariants.DEFAULT));
            }
        }
        return woolyWolfEntity;
    }
}
