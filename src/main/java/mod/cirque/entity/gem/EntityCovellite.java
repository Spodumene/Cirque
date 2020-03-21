package mod.cirque.entity.gem;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.base.Predicate;

import mod.akrivus.kagic.entity.EntityGem; 
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAIScareMobs;
import mod.akrivus.kagic.entity.ai.EntityAISitStill;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModSounds;
import mod.cirque.init.CirqueItems;
import mod.heimrarnadalr.kagic.util.Colors;
import mod.heimrarnadalr.kagic.util.GemPlayerLoot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCovellite extends EntityGem implements IAnimals {
	public static final HashMap<IBlockState, Double> COVELLITE_YIELDS = new HashMap<IBlockState, Double>();
	public static final double COVELLITE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double COVELLITE_DEPTH_THRESHOLD = 32;

	private static final int SKIN_COLOR_BEGIN = 0x9693D6;
	private static final int SKIN_COLOR_END = 0x9693D6;

	private static final int NUM_HAIRSTYLES = 1;

	private static final float ENEMY_WEAPON_DROP_CHANCE = 0.5F;
	
	public EntityCovellite(World world) {
		super(world);
		this.nativeColor = 14;
		this.setSize(0.9F, 2.3F);
		this.isSoldier = true;
		this.canTalk = true;

		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BELLY);
		
		// Apply entity AI
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
		this.tasks.addTask(2, new EntityAISitStill(this, 1.0D));
		this.tasks.addTask(3, new EntityAIScareMobs(this));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));

		// Apply targeting
		this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
			@Override
			public boolean apply(EntityLiving input) {
				return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
			}
		}));

		// Apply entity attributes
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.droppedGemItem = CirqueItems.COVELLITE_GEM;
		this.droppedCrackedGemItem = CirqueItems.CRACKED_COVELLITE_GEM;
	}
	
	@Override
	public void onLivingUpdate() {
		if (this.world.isRemote && this.isPrimary()) {
			for (int i = 0; i < 2; ++i) {
				this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
			}
		}
		super.onLivingUpdate();
	}
	
	@Override
	public void whenDefective() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.setSize(0.72F, 1.61F);
	}
	
	// This is for when we attack
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean attacked = super.attackEntityAsMob(entity);
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase target = (EntityLivingBase) entity;
			if (attacked && !this.isDefective() && this.rand.nextFloat() < EntityCovellite.ENEMY_WEAPON_DROP_CHANCE) {
				GemPlayerLoot.dropEntityMainHand(target);
			}
		}
		return attacked;
	}

	// This is for when we get attacked
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source instanceof EntityDamageSourceIndirect && this.teleportToEntity(source.getTrueSource())) {
			return true;
		}
		return super.attackEntityFrom(source, amount);
	}

	protected boolean teleportToEntity(Entity target) {
		Vec3d vec3d = new Vec3d(this.posX - target.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - target.posY + target.getEyeHeight(), this.posZ - target.posZ);
		if (vec3d.lengthVector() < 4F) {
			return false;
		}
		double d1 = target.posX + (this.rand.nextDouble() - 0.5D) * 2.0D;
		double d2 = target.posY + 1;
		double d3 = target.posZ + (this.rand.nextDouble() - 0.5D) * 2.0D;
		return this.teleportTo(d1, d2, d3);
	}

	private boolean teleportTo(double x, double y, double z) {
		boolean teleportSucceeded = this.attemptTeleport(x, y, z);
		if (teleportSucceeded) {
			this.world.playSound((EntityPlayer) null, this.prevPosX, this.prevPosY, this.prevPosZ, ModSounds.HESSONITE_TELEPORT_START, this.getSoundCategory(), 1.0F, 1.0F);
			this.playSound(ModSounds.HESSONITE_TELEPORT_END, 1.0F, 1.0F);
		}
		return teleportSucceeded;
	}

	@Override
	protected boolean canEquipItem(ItemStack stack) {
		Item weapon = stack.getItem();
		if (weapon instanceof ItemSword || weapon instanceof ItemTool || weapon instanceof ItemBow) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canPickUpLoot() {
		return true;
	}

	@Override
	public void onItemPickup(Entity item, int quantity) {
		this.setAttackAI();
	}

	/*********************************************************
	 * Methods related to sounds. *
	 *********************************************************/
	@Override
	protected SoundEvent getAmbientSound() {
		if (this.rand.nextInt(3) == 0) {
			return ModSounds.HESSONITE_LIVING;
		} else {
			return null;
		}
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.HESSONITE_DEATH;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.HESSONITE_HURT;
	}

	@Override
	protected SoundEvent getObeySound() {
		return ModSounds.HESSONITE_OBEY;
	}

	/*********************************************************
	 * Methods related to rendering. *
	 *********************************************************/
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityCovellite.SKIN_COLOR_BEGIN);
		skinColors.add(EntityCovellite.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityCovellite.NUM_HAIRSTYLES);
	}
}