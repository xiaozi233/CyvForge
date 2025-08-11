package net.cyvforge;

import net.cyvforge.config.ColorTheme;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.CommandInitializer;
import net.cyvforge.event.ConfigLoader;
import net.cyvforge.event.events.GuiHandler;
import net.cyvforge.event.events.KeyInputHandler;
import net.cyvforge.event.events.MacroListener;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.HUDManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//Main class of the mod
@Mod(modid = CyvForge.MODID, version = CyvForge.VERSION)
public class CyvForge {
	public static final String MODID = "cyvforge";
	public static final String VERSION = "1.0";

	public static CyvClientConfig config = new CyvClientConfig();
	public static DecimalFormat df = new DecimalFormat("#");
	public static ColorTheme theme = ColorTheme.CYVISPIRIA;
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Mod.Instance(CyvForge.MODID)
	public static CyvForge instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new HUDManager());
		CyvForge.config.init();
		ConfigLoader.init(CyvForge.config);

		MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
		MinecraftForge.EVENT_BUS.register(new GuiHandler());
		CommandInitializer.register(); //register mod commands

		MinecraftForge.EVENT_BUS.register(new ParkourTickListener());
		MinecraftForge.EVENT_BUS.register(new MacroListener());

		LogManager.getLogger().info("CyvForge mod initialized!");

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {

	}

	/**Send a client-sided message to the player*/
	public static void sendChatMessage(Object text) {
		try {
			String chatColor2 = CyvClientConfig.getBoolean("whiteChat", false) ?
					CyvClientColorHelper.colors.get(12).chatColor : CyvClientColorHelper.color2.chatColor;
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(
					CyvClientColorHelper.color1.chatColor + "<Cyv> " + chatColor2 + text.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Gets a list of collision boxes of a block**/
	public static List<AxisAlignedBB> getHitbox(BlockPos pos, World world) {
		Minecraft mc = Minecraft.getMinecraft();

		if (mc.theWorld == null) return null;

		IBlockState blockState = mc.theWorld.getBlockState(pos);
		Block block = blockState.getBlock();
		AxisAlignedBB mask = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(),
				pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
		List<AxisAlignedBB> list = new ArrayList<AxisAlignedBB>();
		block.addCollisionBoxesToList(mc.theWorld, pos, blockState, mask, list, mc.thePlayer);

		return list;
	}

}