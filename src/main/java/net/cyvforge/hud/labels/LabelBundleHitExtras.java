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

public class LabelBundleHitExtras extends LabelBundle {

    public LabelBundleHitExtras() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelHitAngle";}
            public String getDisplayName() {return "Hit Angle";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 146);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String z = df.format(ParkourTickListener.formatYaw(ParkourTickListener.hf))+"\u00B0";

                drawString("Hit Angle: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(z, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Angle: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("Hit Angle: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Angle: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelHitVector";}
            public String getDisplayName() {return "Hit Vector";}
            public int getWidth() {
                FontRenderer font = mc.fontRendererObj;
                StringBuilder str = new StringBuilder(getDisplayName() + ": 00000.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                str.append("/000.");
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str.append("0");
                str.append("\u00B0");
                return font.getStringWidth(str.toString());
            }
            public int getHeight() {return getLabelHeight();}
            public boolean enabledByDefault() {return false;}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(177, 155);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String speed = df.format(Math.hypot(ParkourTickListener.hvx, ParkourTickListener.hvz));
                String angle = df.format(Math.toDegrees(Math.atan2((ParkourTickListener.hvx == 0) ? 0 : -ParkourTickListener.hvx, ParkourTickListener.hvz)));

                drawString("Hit Vector: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + getHeight()*0, color1);
                drawString(speed, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + speed)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(angle+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + speed + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i = 0; i<Integer.valueOf(CyvForge.config.configFields.get("df").value.toString()); i++) str += "0";
                drawString("Hit Vector: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + getHeight()*0, color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: ")
                        , pos.getAbsoluteY() + 1, color2);
                drawString("/", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + str)
                        , pos.getAbsoluteY() + 1, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Hit Vector: " + str + "/")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });
    }

}
