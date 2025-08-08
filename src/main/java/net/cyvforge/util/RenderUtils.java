package net.cyvforge.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class RenderUtils {
    private static void prepareRender() {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);

        //GlStateManager.enableDepth();
    }
    private static void finishRender() {
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        //GlStateManager.disableDepth();
    }

    /**Draw a chain of connected lines**/
    public static void drawLines(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> z) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        for (int i=0; i<x.size(); i++) {
            worldrenderer.pos(x.get(i), y.get(i), z.get(i)).endVertex();
        }
        tessellator.draw();
    }

    /**Draw a chain of connected lines with specified colors**/
    public static void drawLines(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> z, int red, int green, int blue, int alpha) {
        prepareRender();

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        for (int i=0; i<x.size(); i++) {
            worldrenderer.pos(x.get(i), y.get(i), z.get(i)).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();

        finishRender();
    }

    /**Draw a chain of connected lines**/
    public static void drawLines(double[] x, double[] y, double[] z) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        for (int i=0; i<x.length; i++) {
            worldrenderer.pos(x[i], y[i], z[i]).endVertex();
        }
        tessellator.draw();
    }

    /**Draw a chain of connected lines with specified colors**/
    public static void drawLines(double[] x, double[] y, double[] z, int red, int green, int blue, int alpha) {
        prepareRender();

        GL11.glEnable(GL11.GL_LINE_STRIP);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        for (int i=0; i<x.length; i++) {
            worldrenderer.pos(x[i], y[i], z[i]).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();

        GL11.glDisable(GL11.GL_LINE_STRIP);

        finishRender();
    }

    /**Render square outline based on facing**/
    public static void drawSelectionBoundingBoxOnlyFocusedSide(AxisAlignedBB box, EnumFacing facing) {
        if (facing != null) {
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();

            switch (facing.toString()) {
                case "down": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                } case "up": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                } case "south": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
                    tessellator.draw();
                    break;
                } case "north": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                } case "west": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                } case "east": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                }
            }
        }
    }

    /**Render square outline based on facing with specified colors**/
    public static void drawSelectionBoundingBoxOnlyFocusedSide(AxisAlignedBB box, EnumFacing facing, int red, int green, int blue, int alpha) {
        if (facing != null) {
            prepareRender();
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();

            switch (facing.toString()) {
                case "down": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "up": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "south": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "north": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "west": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "east": {
                    worldrenderer.begin(3, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                }
            }
            finishRender();
        }
    }

    /**Render filled square based on facing**/
    public static void drawFilledBoundingBoxOnlyFocusedSide(AxisAlignedBB box, EnumFacing facing) {
        if (facing != null) {
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            switch (facing.toString()) {
                case "down": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
                    tessellator.draw();
                    break;
                } case "up": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                } case "south": {
                    worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
                    tessellator.draw();
                    break;
                } case "north": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                } case "west": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                } case "east": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
                    tessellator.draw();
                    break;
                }
            }
        }
    }

    /**Render filled square based on facing with specified colors**/
    public static void drawFilledBoundingBoxOnlyFocusedSide(AxisAlignedBB box, EnumFacing facing, int red, int green, int blue, int alpha) {
        if (facing != null) {
            prepareRender();
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            switch (facing.toString()) {
                case "down": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "up": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "south": {
                    worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "north": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "west": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                } case "east": {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                    worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
                    worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
                    tessellator.draw();
                    break;
                }
            }
            finishRender();
        }
    }

    /**Render cube outline**/
    public static void drawBoxOutline(AxisAlignedBB box) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(3, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        tessellator.draw();
        worldrenderer.begin(3, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        tessellator.draw();
        worldrenderer.begin(1, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
        tessellator.draw();
    }

    /**Render cube outline with specified colors**/
    public static void drawBoxOutline(AxisAlignedBB box, int red, int green, int blue, int alpha) {
        prepareRender();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldrenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        worldrenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(box.minX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.minZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.maxZ).color(red, green, blue, alpha).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        finishRender();
    }

    /**Render filled cube**/
    public static void drawFilledBox(AxisAlignedBB box) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
        tessellator.draw();

        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        tessellator.draw();

        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
        tessellator.draw();

        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        tessellator.draw();

        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.minX, box.minY, box.minZ).endVertex();
        worldrenderer.pos(box.minX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.minX, box.maxY, box.minZ).endVertex();
        tessellator.draw();

        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(box.maxX, box.maxY, box.minZ).endVertex();
        worldrenderer.pos(box.maxX, box.maxY, box.maxZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.maxZ).endVertex();
        worldrenderer.pos(box.maxX, box.minY, box.minZ).endVertex();
        tessellator.draw();
    }

    /**Render filled cube with specified colors**/
    public static void drawFilledBox(AxisAlignedBB box, int red, int green, int blue, int alpha) {
        prepareRender();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        GlStateManager.color(red/255.0f, green/255.0f, blue/255.0f, alpha/255.0f);

        drawFilledBox(box);

        finishRender();
    }

    public static void drawNametagAtCoords(String str, double x, double y, double z, float partialTicks, boolean depth) {
        GlStateManager.alphaFunc(516, 0.1F);

        GlStateManager.translate(x, y, z);

        FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
        float f1 = 0.02666667F;
        GlStateManager.pushMatrix();
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        if (!depth) GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-f1, -f1, f1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int i = 0;

        int j = fontrenderer.getStringWidth(str) / 2;
        GlStateManager.disableTexture2D();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(-j - 1, -1 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        worldrenderer.pos(-j - 1, 8 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        worldrenderer.pos(j + 1, 8 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        worldrenderer.pos(j + 1, -1 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        fontrenderer.drawString(str, -fontrenderer.getStringWidth(str) / 2, i, 553648127);
        GlStateManager.depthMask(true);

        fontrenderer.drawString(str, -fontrenderer.getStringWidth(str) / 2, i, -1);

        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        GlStateManager.popMatrix();

        GlStateManager.translate(-x, -y, -z);

    }

    public static void drawTextAtCoords(List<String> str, double x, double y, double z, float partialTicks, boolean depth) {
        float lineSpacing = 0.24f;

        for (String s : str) {
            if (s.length() > 0)
                drawNametagAtCoords(s, x, y -= lineSpacing, z, partialTicks, depth);
        }
    }
}
