package mod.cirque.entity.gem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAICommandGems;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAIPickUpItems;
import mod.akrivus.kagic.entity.ai.EntityAIStandGuard;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModSounds;
import mod.cirque.init.CirqueItems;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityIndicolite extends EntityGem implements IAnimals {
	public static final HashMap<IBlockState, Double> INDICOLITE_YIELDS = new HashMap<IBlockState, Double>();
	public static final double INDICOLITE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double INDICOLITE_DEPTH_THRESHOLD = 0;

	private static final int SKIN_COLOR = 0x23DAFF;
	
	private static final int HAIR_COLOR = 0x1C85FF;
	
	private static final int NUM_HAIRSTYLES = 1;

	public EntityIndicolite(World worldIn) {
		super(worldIn);
		this.nativeColor = 14;
		this.setSize(0.7F, 1.2F);
		this.isImmuneToFire = true;
		this.isSoldier = true;

		// Define valid gem cuts and placements
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_EYE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_SHOULDER);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_SHOULDER);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BELLY);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_THIGH);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_THIGH);
		
		// Apply entity AI.
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIAvoidEntity<EntityCreeper>(this, EntityCreeper.class, new Predicate<EntityCreeper>() {
			@Override
			public boolean apply(EntityCreeper input) {
				return input.getCreeperState() == 1;
			}
		}, 6.0F, 1.0D, 1.2D));
		
		// Other entity AIs.
		this.tasks.addTask(2, new EntityAIPickUpItems(this, 1.0D));
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.414D, 32.0F));
		this.tasks.addTask(4, new EntityAIFollowDiamond(this, 1.0D));
		this.tasks.addTask(4, new EntityAICommandGems(this, 0.6D));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIStandGuard(this, 0.6D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		
		// Apply targetting.
		this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
			@Override
			public boolean apply(EntityLiving input) {
				return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
			}
		}));
		
		// Apply entity attributes.
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.droppedGemItem = CirqueItems.INDICOLITE_GEM;
		this.droppedCrackedGemItem = CirqueItems.CRACKED_INDICOLITE_GEM;
	}
	
	@Override
	protected int generateGemColor() {
		return 0xE0FFFF;
	}

	/*********************************************************
	 * Methods related to loading. *
	 *********************************************************/
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
	}
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		this.setSpecial(this.rand.nextInt(6));
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	/*********************************************************
	 * Methods related to interaction. *
	 *********************************************************/
	@Override
	public void whenDefective() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		this.setSize(0.7F, 0.9F);
		this.stepHeight = 0.5F;
	}
	public boolean canPickupItem(Item itemIn) {
		return this.isDefective() && (itemIn instanceof ItemSword || itemIn instanceof ItemTool || itemIn instanceof ItemBow);
	}

	/*********************************************************
	 * Methods related to living. *
	 *********************************************************/
	@Override
	public void onLivingUpdate() {
		this.startJumping();
		super.onLivingUpdate();
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
		super.setAttackTarget(entitylivingbaseIn);
	}
	
	private void startJumping() {
		if (!this.world.isRemote) {
			AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.posX, this.posY, this.posZ, this.posX + 1, this.posY + 1, this.posZ + 1).grow(16.0, this.world.getHeight(), 16.0);
			List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
			for (EntityLivingBase entity : list) {
				if (!entity.isDead || entity.getHealth() > 0.0F) {
					if (entity instanceof EntityGem) {
						EntityGem gem = (EntityGem) entity;
						if (this.getServitude() == gem.getServitude()) {
							if (this.getServitude() == EntityGem.SERVE_HUMAN) {
								if (this.isOwnerId(gem.getOwnerId())) {
									entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10));
								}
							} else {
								entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10));
							}
						}
					}
					if (this.isOwner(entity)) {
						entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10));
					}
				}
			}
		}
	}

	/*********************************************************
	 * Methods related to combat. *
	 *********************************************************/
	public int getSpecialSkin() {
		return this.getSpecial();
	}

	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityIndicolite.SKIN_COLOR);
		return Colors.arbiLerp(skinColors);
	}

	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityIndicolite.NUM_HAIRSTYLES);
	}

	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityIndicolite.HAIR_COLOR);
		return Colors.arbiLerp(hairColors);
	}

	/*********************************************************
	 * Methods related to sounds. *
	 *********************************************************/
	
	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.RUBY_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.RUBY_HURT;
	}

	@Override
	protected SoundEvent getObeySound() {
		return ModSounds.RUBY_OBEY;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.RUBY_DEATH;
	}
}