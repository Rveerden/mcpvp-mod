package us.mcpvpmod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import us.mcpvpmod.events.Events;
import us.mcpvpmod.mgi.CTFHandler;
import us.mcpvpmod.mgi.MGI;
import us.mcpvpmod.mgi.MGIGamemode;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "mcpvp", name = "MCPVP Mod", version="1.7.10-2.0", guiFactory="us.mcpvpmod.config.GuiFactory")
public class Main {

	@Instance
    public static Main instance = new Main();
	public static Minecraft mc = Minecraft.getMinecraft();
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
		FMLLog.info("[MCPVP] Mod starting up!");
    	
    	MinecraftForge.EVENT_BUS.register(new Events());
    	FMLCommonHandler.instance().bus().register(new Events());
    	
    	Sync.sync();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	MGI.addHandler(MGIGamemode.CTF, 1337, new CTFHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
    
}