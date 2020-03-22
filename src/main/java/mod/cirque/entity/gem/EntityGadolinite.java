package mod.cirque.entity.gem;


import java.util.HashMap;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAISitStill;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.cirque.init.CirqueItems;
import mod.cirque.init.CirqueSounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityGadolinite extends EntityGem implements IAnimals {
	public static final HashMap<IBlockState, Double> GADOLINITE_YIELDS = new HashMap<IBlockState, Double>();
	public static final double GADOLINITE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double GADOLINITE_DEPTH_THRESHOLD = 32;
	
	public EntityGadolinite(World world) {
		super(world);
		this.nativeColor = 14;
		this.setSize(0.9F, 2.3F);
		this.isSoldier = true;
		this.canTalk = true;

		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_EYE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_SHOULDER);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_SHOULDER);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BELLY);
		
		// Apply entity AI
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
		this.tasks.addTask(2, new EntityAISitStill(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));

		// Apply targeting
		this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));

		// Apply entity attributes
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.droppedGemItem = CirqueItems.GADOLINITE_GEM;
		this.droppedCrackedGemItem = CirqueItems.CRACKED_GADOLINITE_GEM;
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	
	@Override
	public void whenDefective() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.setSize(0.72F, 1.61F);
	}

	/*********************************************************
	 * Methods related to sounds. *
	 *********************************************************/
	@Override
	protected SoundEvent getAmbientSound() {
		if (this.rand.nextInt(3) == 0) {
			return CirqueSounds.GADOLINITE_LIVING;
		} else {
			return null;
		}
	}

	@Override
	protected SoundEvent getDeathSound() {
		return CirqueSounds.GADOLINITE_DEATH;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return CirqueSounds.GADOLINITE_HURT;
	}

	@Override
	protected SoundEvent getObeySound() {
		return CirqueSounds.GADOLINITE_OBEY;
	}
}