package net.cyvforge.gui;

import mcpk.Parser;
import mcpk.Player;
import net.cyvforge.CyvForge;
import net.cyvforge.util.defaults.CyvGui;
import net.cyvforge.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

//gui for the in-game movement simulator
public class GuiSimulate extends CyvGui {
    public static ArrayList<String> chatHistory = new ArrayList<String>();

    public boolean repeatEventsEnabled;
    public GuiScreen eventReceiver;
    GuiTextField input;
    GuiButton button;
    int chatHistoryIndex = 0;

    public GuiSimulate() {
        super("Movement Simulator");
    }

    @Override
    public void initGui() {
        super.initGui();
        input = new GuiTextField(0, fontRendererObj, width/2-width*23/80, (int) (height*0.40-10), width*23/40, 20);
        button = new GuiButton(0, width/2-50, height*3/5-10, 100, 20, "Calculate");

        input.setMaxStringLength(65536);
        this.chatHistoryIndex = 0;
        this.input.setFocused(true);

        Keyboard.enableRepeatEvents(true);

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        int width = sr.getScaledWidth();
        int height = sr.getScaledHeight();

        int x1 = (int) (sr.getScaledWidth_double() * 0.20);
        int y1 = (int) (sr.getScaledHeight_double() * 0.30);
        int x2 = (int) (sr.getScaledWidth_double() * 0.80);
        int y2 = (int) (sr.getScaledHeight_double() * 0.50);

        int borderColor = new Color(0,0,0,255).getRGB();
        int consoleColor = new Color(150, 150, 150, 255).getRGB();

        super.drawDefaultBackground(); //background tint
        GuiUtils.drawRoundedRect(x1-2, y1-2, x2+2, y2+2, 9, borderColor);
        GuiUtils.drawRoundedRect(x1, y1, x2, y2, 7, consoleColor); //black box

        input.drawTextBox();

        button.drawButton(mc, mouseX, mouseY);
        GuiUtils.drawCenteredString("Movement Simulator", x1+46, y1-15, 0xFFFFFFFF, true);
    }

    public void updateScreen() {
        input.updateCursorCounter();
        super.updateScreen();
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_RETURN) {
            Minecraft.getMinecraft().thePlayer.closeScreen(); //close the gui
            String text = input.getText(); //parser shit

            if (text.equals("") || text.equals(" ")) {


            } else {
                if (chatHistory.size() == 0) {
                    chatHistory.add(text);

                } else {
                    if (!chatHistory.get(chatHistory.size()-1).equals(text)) {
                        chatHistory.add(text);
                    }
                }

                output(text);
                return;
            }

            return;
        } else if (keyCode == 200) { //scroll up
            if (chatHistoryIndex < chatHistory.size()) {
                chatHistoryIndex++;
                input.setText(chatHistory.get(chatHistory.size()-chatHistoryIndex));
                return;
            }

        } else if (keyCode == 208) { //scroll down
            if (chatHistoryIndex > 0) {
                chatHistoryIndex--;
                if (chatHistoryIndex == 0) {
                    input.setText("");
                } else {
                    input.setText(chatHistory.get(chatHistory.size()-chatHistoryIndex));
                }
                return;
            }

        }

        input.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        input.mouseClicked(mouseX, mouseY, mouseButton);
        if (button.mousePressed(mc, mouseX, mouseY)) {
            Minecraft.getMinecraft().thePlayer.closeScreen(); //close the gui
            String text = input.getText(); //parser shit

            if (text.equals("") || text.equals(" ")) {


            } else {

                if (chatHistory.size() == 0) {

                    chatHistory.add(text);

                } else {
                    if (!chatHistory.get(chatHistory.size()-1).equals(text)) {

                        chatHistory.add(text);

                    }
                }

                output(text);

            }}
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private void output(String text) {
        new Thread(() -> {
            Player player = new Player();
            DecimalFormat df = CyvForge.df;
            player.df = (byte) df.getMaximumFractionDigits();
            Parser parser = new Parser();

            try {
                parser.parse(player, text);
            } catch (Exception e) {
                CyvForge.sendChatMessage("Parsing failed.");
                return;
            }

            double z = player.z;
            double vz = player.vz;
            double x = player.x;
            double vx = player.vx;

            double vector = Math.sqrt(vx*vx + vz*vz);
            double angle = Math.atan2(-vx,vz) * 180d/Math.PI;

            CyvForge.sendChatMessage("Simulated parsed string: \247o" + text + "\n\247r"
                    + "z: " + df.format(z) + "\n"
                    + "vz: " + df.format(vz) + "\n"
                    + "x: " + df.format(x) + "\n"
                    + "vx: " + df.format(vx) + "\n"
                    + "Speed Vector: " + df.format(vector) + ", " + df.format(angle) + "\u00B0");
        }).start();

    }

    @Override
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();

    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
