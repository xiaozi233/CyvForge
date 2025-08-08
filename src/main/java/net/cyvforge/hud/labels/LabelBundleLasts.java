package net.cyvforge.hud.labels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.LabelBundle;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.MovementInput;

import java.text.DecimalFormat;

public class LabelBundleLasts extends LabelBundle {

    public LabelBundleLasts() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLast45";}
            public String getDisplayName() {return "Last 45";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 74);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                
                DecimalFormat df = CyvForge.df;
                String str = df.format(ParkourTickListener.last45);

                drawString("Last 45: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("Last 45: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("Last 45: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("Last 45: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLastInput";}
            public String getDisplayName() {return "Last Input";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 92);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                MovementInput input = mc.thePlayer.movementInput;
                String str = (input.moveForward > 0 ? "W" : "")
                        + (input.moveStrafe > 0 ? "A" : "")
                        + (input.moveForward < 0 ? "S" : "")
                        + (input.moveStrafe < 0 ? "D" : "");

                drawString("Last Input: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Input: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                drawString("Last Input: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString("WASD", pos.getAbsoluteX() + 1 + font.getStringWidth("Last Input: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLastTurningYaw";}
            public String getDisplayName() {return "Last Turning";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 83);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.lastTurning);

                drawString("Last Turning: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Turning: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("Last Turning: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Turning: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelLastSidestep";}
            public String getDisplayName() {return "Last Sidestep";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 101);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "None";

                if (ParkourTickListener.sidestep == 0) {
                    str = "WAD " + ParkourTickListener.sidestepTime + " ticks";
                }
                else if (ParkourTickListener.sidestep == 1) {
                    str = "WDWA";
                }

                drawString("Last Sidestep: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("Last Sidestep: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                drawString("Last Sidestep: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString("WAD", pos.getAbsoluteX() + 1 + font.getStringWidth("Last Sidestep: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });
    }

}
