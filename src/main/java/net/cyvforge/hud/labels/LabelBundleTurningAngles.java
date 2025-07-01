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

public class LabelBundleTurningAngles extends LabelBundle {

    public LabelBundleTurningAngles() {
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelJumpAngle";}
            public String getDisplayName() {return "Jump Angle";}
            public int getWidth() {return getLabelWidth(getDisplayName(), true);}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 103);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String ja = df.format(ParkourTickListener.formatYaw(ParkourTickListener.jf));
                drawString("Jump Angle: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + getHeight()*0, color1);
                drawString(ja+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Angle: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;
                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("Jump Angle: ", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + getHeight()*0, color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Jump Angle: ")
                        , pos.getAbsoluteY() + 1, color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelSecondTurn";}
            public String getDisplayName() {return "Second Turn";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 112);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String st = df.format(ParkourTickListener.formatYaw(ParkourTickListener.sf - ParkourTickListener.jf));

                drawString("Second Turn: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(st, pos.getAbsoluteX() + 1 + font.getStringWidth("Second Turn: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("Second Turn: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("Second Turn: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelThirdTurn";}
            public String getDisplayName() {return "3: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200,112);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String tt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.tf - ParkourTickListener.sf));

                drawString("3: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(tt, pos.getAbsoluteX() + 1 + font.getStringWidth("3: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("3: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("3: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

         this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelFourthTurn";}
            public String getDisplayName() {return "4: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200,103);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String fot = df.format(ParkourTickListener.formatYaw(ParkourTickListener.fof - ParkourTickListener.tf));

                drawString("4: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(fot, pos.getAbsoluteX() + 1 + font.getStringWidth("4: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("4: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("4: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

 this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelFithTurn";}
            public String getDisplayName() {return "5: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200,94);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String fit = df.format(ParkourTickListener.formatYaw(ParkourTickListener.fif - ParkourTickListener.fof));

                drawString("5: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(fit, pos.getAbsoluteX() + 1 + font.getStringWidth("5: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("5: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("5: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

         this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelSixthTurn";}
            public String getDisplayName() {return "6: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 112);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String sit = df.format(ParkourTickListener.formatYaw(ParkourTickListener.sif - ParkourTickListener.fif));

                drawString("6: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(sit, pos.getAbsoluteX() + 1 + font.getStringWidth("6: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("6: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("6: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

 this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelSeventhTurn";}
            public String getDisplayName() {return "7: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200,85);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String sevt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.sevf - ParkourTickListener.sif));

                drawString("7: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(sevt, pos.getAbsoluteX() + 1 + font.getStringWidth("7: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("7: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("7: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

 this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelEigthTurn";}
            public String getDisplayName() {return "8: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200,76);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String eit = df.format(ParkourTickListener.formatYaw(ParkourTickListener.eif - ParkourTickListener.sevf));

                drawString("8: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(eit, pos.getAbsoluteX() + 1 + font.getStringWidth("8: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("8: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("8: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

 this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelNinthTurn";}
            public String getDisplayName() {return "9: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200,67);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String nt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.nf - ParkourTickListener.eif));

                drawString("9: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(nt, pos.getAbsoluteX() + 1 + font.getStringWidth("9: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("9: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("9: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

 this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelTenthTurn";}
            public String getDisplayName() {return "10: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200,58);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String tet = df.format(ParkourTickListener.formatYaw(ParkourTickListener.tef - ParkourTickListener.nf));

                drawString("10: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(tet, pos.getAbsoluteX() + 1 + font.getStringWidth("10: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("10: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("10: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

         this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelEleventhTurn";}
            public String getDisplayName() {return "11: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200, 49);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String elt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.elf - ParkourTickListener.tef));

                drawString("11: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(elt, pos.getAbsoluteX() + 1 + font.getStringWidth("11: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("11: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("11: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });

 this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelTwelthTurn";}
            public String getDisplayName() {return "12: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200, 40);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String twt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.twf - ParkourTickListener.elf));

                drawString("12: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(twt, pos.getAbsoluteX() + 1 + font.getStringWidth("12: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("12: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("12: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });
 
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelThirtenthTurn";}
            public String getDisplayName() {return "13: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200, 31);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String tht = df.format(ParkourTickListener.formatYaw(ParkourTickListener.thf - ParkourTickListener.twf));

                drawString("13: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(tht, pos.getAbsoluteX() + 1 + font.getStringWidth("13: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("13: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("13: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });
      
         this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelFourteenthTurn";}
            public String getDisplayName() {return "14: ";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(200, 22);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String ftt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.ftf - ParkourTickListener.thf));

                drawString("14: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(twt, pos.getAbsoluteX() + 1 + font.getStringWidth("14: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",3);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",3); i++) str += "0";

                drawString("14: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str, pos.getAbsoluteX() + 1 + font.getStringWidth("14: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });
        
        this.labels.add(new DraggableHUDElement() {
            public String getName() {return "labelPreturn";}
            public String getDisplayName() {return "Preturn";}
            public int getWidth() {return getLabelWidth(getDisplayName());}
            public int getHeight() {return getLabelHeight();}
            public ScreenPosition getDefaultPosition() {return new ScreenPosition(0, 121);}
            public void render(ScreenPosition pos) {
                if (!this.isVisible) return;
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                DecimalFormat df = CyvForge.df;
                String pt = df.format(ParkourTickListener.formatYaw(ParkourTickListener.pf));

                drawString("Preturn: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(pt+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Preturn: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
            public void renderDummy(ScreenPosition pos) {
                int d = CyvClientConfig.getInt("df",5);
                long color1 = CyvClientColorHelper.color1.drawColor;
                long color2 = CyvClientColorHelper.color2.drawColor;
                FontRenderer font = mc.fontRendererObj;

                String str = "0.";
                for (int i=0; i<CyvClientConfig.getInt("df",5); i++) str += "0";

                drawString("Preturn: ", pos.getAbsoluteX() + 1, (int) (pos.getAbsoluteY() + 1), color1);
                drawString(str+"\u00B0", pos.getAbsoluteX() + 1 + font.getStringWidth("Preturn: ")
                        , (int) (pos.getAbsoluteY() + 1), color2);
            }
        });
    }

}
