package net.cyvforge.hud.labels;

import net.cyvforge.CyvForge;
import net.cyvforge.config.CyvClientColorHelper;
import net.cyvforge.config.CyvClientConfig;
import net.cyvforge.event.events.ParkourTickListener;
import net.cyvforge.hud.LabelBundle;
import net.cyvforge.hud.structure.DraggableHUDElement;
import net.cyvforge.hud.structure.ScreenPosition;
import net.minecraft.client.gui.FontRenderer;

import java.text.DecimalFormat;

public class LabelBundleMomentumOffsets extends LabelBundle {

    public LabelBundleMomentumOffsets() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelMMXOffset";}
            public String getDisplayName() {return "MM X Offset";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 110);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String x;
                if (ParkourTickListener.momentumBlock == null) {
                    x = "NaN";
                } else {
                    x = (ParkourTickListener.momentumBlock.lastOffsetX == null) ? "NaN" : df.format(ParkourTickListener.momentumBlock.lastOffsetX.doubleValue());
                }

                drawString("MM X Offset: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(x, pos.getAbsoluteX() + 1 + font.getStringWidth("MM X Offset: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("MM X Offset: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("MM X Offset: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelMMZOffset";}
            public String getDisplayName() {return "MM Z Offset";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 119);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z;
                if (ParkourTickListener.momentumBlock == null) {
                    z = "NaN";
                } else {
                    z = (ParkourTickListener.momentumBlock.lastOffsetZ == null) ? "NaN" : df.format(ParkourTickListener.momentumBlock.lastOffsetZ.doubleValue());
                }

                drawString("MM Z Offset: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("MM Z Offset: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("MM Z Offset: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("MM Z Offset: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

    }

}
