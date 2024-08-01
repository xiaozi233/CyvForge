package net.cyvforge.util.defaults;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class CyvGui extends GuiScreen {
    private GuiScreen parent; //parent screen
    public String name;

    public CyvGui(String name) {
        super();
        mc = Minecraft.getMinecraft();
        this.name = name;
    }

    @Override //called upon GUI initialization or resizing
    public void initGui() {}

    @Override //called each frame, put the drawScreen things here.
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override //called every tick
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override //called when the mouse is scrolled
    public void mouseClickMove(int x, int y, int mouseButton, long time) {
        super.mouseClickMove(x, y, mouseButton, time);
    }

    @Override //called upon GUI closing
    public void onGuiClosed() {
        super.onGuiClosed();
    }


}
