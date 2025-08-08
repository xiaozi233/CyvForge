package net.cyvforge.hud.structure;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ScreenPosition {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private int x, y;

    public ScreenPosition(double x, double y) {
        setRelative(x, y);
    }

    public ScreenPosition(int x, int y) {
        setAbsolute(x, y);
    }

    public static ScreenPosition fromRelativePosition(double x, double y) {
        return new ScreenPosition(x, y);
    }

    public static ScreenPosition fromAbsolutePosition(int x, int y) {
        return new ScreenPosition(x, y);
    }

    public int getAbsoluteX() {
        return x;
    }

    public int getAbsoluteY() {
        return y;
    }

    public double getRelativeX() {
        return (double) this.x / new ScaledResolution(mc).getScaledWidth();
    }

    public double getRelativeY() {
        return (double) this.y / new ScaledResolution(mc).getScaledHeight();
    }

    public void setRelative(double x, double y) {
        ScaledResolution res = new ScaledResolution(mc);
        this.x = (int) (x * res.getScaledWidth());
        this.y = (int) (y * res.getScaledHeight());
    }

    public void setAbsolute(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
