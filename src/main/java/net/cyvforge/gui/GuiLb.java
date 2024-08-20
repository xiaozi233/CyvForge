package net.cyvforge.gui;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.discord.DiscordRPCHandler;
import net.cyvforge.util.defaults.CyvGui;
import net.cyvforge.util.parkour.LandingAxis;
import net.cyvforge.util.parkour.LandingBlock;
import net.cyvforge.util.parkour.LandingMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public class GuiLb extends CyvGui {
    LandingBlock lb;
    GuiButton landingModeButton;
    GuiButton axisButton;
    GuiButton calculateWalls;
    GuiButton resetWalls;
    GuiButton neoAndBlock;

    public GuiLb(LandingBlock b) {
        super("Landing Block GUI");
        this.lb = b;
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void initGui() {
        if (lb == null) {
            CyvForge.sendChatMessage("NULL LB");
            mc.displayGuiScreen(null);
        }
        this.landingModeButton = new GuiButton(0, this.width - 155, 5, 150, 20, "Landing Mode: " + lb.mode.toString());
        this.axisButton = new GuiButton(1, this.width - 155, 30, 150, 20, "Axis: " + lb.axis.toString());
        this.calculateWalls = new GuiButton(4, this.width - 155, 105, 150, 20, "Calculate Walls");
        this.resetWalls = new GuiButton(5, this.width - 155, 130, 150, 20, "Reset Walls");
        this.neoAndBlock = new GuiButton(6, this.width - 155, 155, 150, 20, "Neo & Landing: " + lb.neoAndNormal);

        this.buttonList.add(this.landingModeButton);
        this.buttonList.add(this.axisButton);
        this.buttonList.add(this.neoAndBlock);

        this.buttonList.add(new GuiButton(2, this.width - 155, 55, 150, 20, "BB Visible: " + CyvClientConfig.getBoolean("highlightLanding", false)));
        this.buttonList.add(new GuiButton(3, this.width - 155, 80, 150, 20, "Cond Visible: " + CyvClientConfig.getBoolean("highlightLandingCond", false)));

        this.buttonList.add(this.calculateWalls);
        this.buttonList.add(this.resetWalls);

        if (lb.axis.equals(LandingAxis.both)) {
            lb.axis = LandingAxis.both;
            this.axisButton.displayString = "Axis: Both";
        } else if (lb.axis.equals(LandingAxis.z)) {
            lb.axis = LandingAxis.z;
            this.axisButton.displayString = "Axis: Z";
        } else {
            lb.axis = LandingAxis.x;
            this.axisButton.displayString = "Axis: X";
        }

        if (lb.mode.equals(LandingMode.landing)) {
            lb.mode = LandingMode.landing;
            this.landingModeButton.displayString = "Landing Mode: Landing";
        } else if (lb.mode.equals(LandingMode.hit)) {
            lb.mode = LandingMode.hit;
            this.landingModeButton.displayString = "Landing Mode: Hit";
        } else if (lb.mode.equals(LandingMode.z_neo)) {
            lb.mode = LandingMode.z_neo;
            this.landingModeButton.displayString = "Landing Mode: Z Neo";
        } else {
            lb.mode = LandingMode.enter;
            this.landingModeButton.displayString = "Landing Mode: Enter";
        }

        if (lb.mode == LandingMode.z_neo) {
            this.neoAndBlock.enabled = true;
        } else {
            this.neoAndBlock.enabled = false;
        }

    }

    @Override
    public void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: { //switch landing modes
                LandingMode mode = lb.mode;
                if (mode.equals(LandingMode.landing)) {
                    lb.mode = LandingMode.hit;
                    this.landingModeButton.displayString = "Landing Mode: Hit";
                } else if (mode.equals(LandingMode.hit)) {
                    lb.mode = LandingMode.z_neo;
                    this.landingModeButton.displayString = "Landing Mode: Z Neo";
                } else if (mode.equals(LandingMode.z_neo)) {
                    lb.mode = LandingMode.enter;
                    this.landingModeButton.displayString = "Landing Mode: Enter";
                } else {
                    lb.mode = LandingMode.landing;
                    this.landingModeButton.displayString = "Landing Mode: Landing";
                }

                if (lb.mode == LandingMode.z_neo) {
                    this.neoAndBlock.enabled = true;
                } else {
                    this.neoAndBlock.enabled = false;
                }

                break;
            } case 1: { //switch axis
                LandingAxis mode = lb.axis;
                if (mode.equals(LandingAxis.both)) {
                    lb.axis = LandingAxis.z;
                    this.axisButton.displayString = "Axis: Z";
                } else if (mode.equals(LandingAxis.z)) {
                    lb.axis = LandingAxis.x;
                    this.axisButton.displayString = "Axis: X";
                } else {
                    lb.axis = LandingAxis.both;
                    this.axisButton.displayString = "Axis: Both";
                }
                break;
            } case 2: {
                CyvClientConfig.set("highlightLanding", !CyvClientConfig.getBoolean("highlightLanding", false));
                this.buttonList.get(3).displayString = "BB Visible: " + CyvClientConfig.getBoolean("highlightLanding", false);
                break;
            } case 3: {
                CyvClientConfig.set("highlightLandingCond", !CyvClientConfig.getBoolean("highlightLandingCond", false));
                this.buttonList.get(4).displayString = "Cond Visible: " + CyvClientConfig.getBoolean("highlightLandingCond", false);
                break;
            } case 4: {
                lb.calculateWalls();
                break;
            } case 5: {
                lb.resetWalls();
                break;
            } case 6: {
                lb.neoAndNormal = !lb.neoAndNormal;
                this.buttonList.get(2).displayString = "Neo & Landing: " + lb.neoAndNormal;
                break;
            }
            default: break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks); //draw buttons
    }

    @Override
    public void updateScreen() {
        if (lb == null) mc.displayGuiScreen(null);
    }

}