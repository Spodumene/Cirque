package mod.cirque.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

public class CirqueSounds {

    public static final SoundEvent GADOLINITE_LIVING = new SoundEvent(new ResourceLocation("cirque:entities.gadolinite.living"));
    public static final SoundEvent GADOLINITE_DEATH = new SoundEvent(new ResourceLocation("cirque:entities.gadolinite.death"));
    public static final SoundEvent GADOLINITE_OBEY = new SoundEvent(new ResourceLocation("cirque:entities.gadolinite.obey"));
    public static final SoundEvent GADOLINITE_HURT = new SoundEvent(new ResourceLocation("cirque:entities.gadolinite.hurt"));

    public static void register(RegistryEvent.Register<SoundEvent> event) {

        registerSound(GADOLINITE_LIVING, new ResourceLocation("cirque:entities.gadolinite.living"), event);
        registerSound(GADOLINITE_DEATH, new ResourceLocation("cirque:entities.gadolinite.death"), event);
        registerSound(GADOLINITE_OBEY, new ResourceLocation("cirque:entities.gadolinite.obey"), event);
        registerSound(GADOLINITE_HURT, new ResourceLocation("cirque:entities.gadolinite.hurt"), event);

    }
    private static void registerSound(SoundEvent sound, ResourceLocation location, RegistryEvent.Register<SoundEvent> event) {
        sound.setRegistryName(location);
        event.getRegistry().register(sound);
    }
}