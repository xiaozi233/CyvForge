package net.cyvforge.event.events;

import net.cyvforge.keybinding.ChatMacro.ChatMacro;
import net.cyvforge.keybinding.ChatMacro.ChatMacroManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;


//This class is used to handle GUIs for CyvFabric
public class GuiHandler {
    public static int scrollBuffer = 0;
    public static int latestDWheel = 0;
    private static GuiScreen screenAwaiting; //screen which will be shown next tick

    public static void setScreen(GuiScreen screen) {
        screenAwaiting = screen;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().theWorld == null) return; //don't run unless in-game
        if (event.phase != TickEvent.Phase.START) return;
        if (screenAwaiting != null) {
            Minecraft.getMinecraft().displayGuiScreen(screenAwaiting); //set the screen
            screenAwaiting = null; //now that no screen is awaiting, clear it
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.currentScreen != null || mc.thePlayer == null) return;

        if (Keyboard.getEventKeyState()) {
            int eventKey = Keyboard.getEventKey();

            for (ChatMacro hk : ChatMacroManager.hotkeys) {
                if (hk.keyCodes.contains(eventKey)) {
                    boolean allDown = true;
                    for (int code : hk.keyCodes) {
                        if (!Keyboard.isKeyDown(code)) { allDown = false; break; }
                    }

                    if (allDown) {
                        if (hk.isChain) {
                            if (hk.isLink) {
                                executeCmd(hk.commandOn, hk.isClient);
                                executeCmd(hk.commandOff, hk.isClient);
                            } else {
                                hk.chainState = !hk.chainState;
                                executeCmd(hk.chainState ? hk.commandOn : hk.commandOff, hk.isClient);
                            }
                        } else {
                            executeCmd(hk.commandOn, hk.isClient);
                        }
                    }
                }
            }
        }
    }

    private void executeCmd(String cmd, boolean isClient) {
        if (cmd == null || cmd.isEmpty()) return;
        if (isClient) {
            net.minecraftforge.client.ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, cmd);
        } else {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(cmd);
        }
    }

    @SubscribeEvent
    public void onMouseInput(net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent.Pre event) {
        int d = org.lwjgl.input.Mouse.getEventDWheel();
        if (d != 0) {
            scrollBuffer += d;
        }
    }
}
