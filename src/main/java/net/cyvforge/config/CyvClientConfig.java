package net.cyvforge.config;

import net.cyvforge.CyvForge;
import net.cyvforge.hud.HUDManager;
import net.cyvforge.hud.structure.DraggableHUDElement;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CyvClientConfig {
    public HashMap<String,ConfigValue<?>> configFields = new LinkedHashMap<>();

    public void init() {
        //special globals
        configFields.put("color1", new ConfigValue<String>("aqua"));
        configFields.put("color2", new ConfigValue<String>("white"));
        configFields.put("theme", new ConfigValue<String>("CYVISPIRIA"));
        configFields.put("whiteChat", new ConfigValue<Boolean>(false));

        configFields.put("df", new ConfigValue<Integer>(5));
        configFields.put("trimZeroes", new ConfigValue<Boolean>(true));

        //parkour
        configFields.put("showMilliseconds", new ConfigValue<Boolean>(true));

        configFields.put("sendLbChatOffset", new ConfigValue<Boolean>(false));
        configFields.put("sendMmChatOffset", new ConfigValue<Boolean>(false));

        configFields.put("highlightLanding", new ConfigValue<Boolean>(false));
        configFields.put("highlightLandingCond", new ConfigValue<Boolean>(false));
        configFields.put("momentumPbCancelling", new ConfigValue<Boolean>(false));

        //inertia listener
        configFields.put("inertiaEnabled", new ConfigValue<Boolean>(false));
        configFields.put("inertiaTick", new ConfigValue<Integer>(4));
        configFields.put("inertiaMin", new ConfigValue<Double>(-0.02));
        configFields.put("inertiaMax", new ConfigValue<Double>(0.02));
        configFields.put("inertiaAxis", new ConfigValue<Character>('x'));
        configFields.put("inertiaGroundType", new ConfigValue<String>("normal"));

        //macros
        configFields.put("currentMacro", new ConfigValue<String>("macro"));
        configFields.put("smoothMacro", new ConfigValue<Boolean>(false));

        //position checker
        configFields.put("positionCheckerEnabled", new ConfigValue<Boolean>(false));
        configFields.put("positionCheckerTick", new ConfigValue<Integer>(7));
        configFields.put("positionCheckerMinX", new ConfigValue<Double>(-10000.0));
        configFields.put("positionCheckerMaxX", new ConfigValue<Double>(10000.0));
        configFields.put("positionCheckerMinZ", new ConfigValue<Double>(-10000.0));
        configFields.put("positionCheckerMaxZ", new ConfigValue<Double>(10000.0));
        configFields.put("positionCheckerZNeo", new ConfigValue<Boolean>(false));

        //checkpoints
        configFields.put("singleplayerCheckpointsEnabled", new ConfigValue<Boolean>(true));
        configFields.put("generatorDyeColor", new ConfigValue<Integer>(1));
        configFields.put("generatorItemSlot", new ConfigValue<Integer>(0));

        for (DraggableHUDElement mod : HUDManager.registeredRenderers) {
            String name = mod.getName();
            mod.getConfigFields().forEach((property, configField) -> {
                configFields.put(name + "_" + property, configField);
            });
        }
    }

    public static class ConfigValue<T> {
        public T value;
        public T defaultValue;
        public Type type;
        public ConfigValue(T value) {
            this.value = value;
            this.defaultValue = value;

            if (value instanceof Integer) {
                type = Type.INTEGER;
            } else if (value instanceof Long) {
                type = Type.LONG;
            } else if (value instanceof Double) {
                type = Type.DOUBLE;
            } else if (value instanceof Boolean) {
                type = Type.BOOLEAN;
            } else if (value instanceof Character) {
                type = Type.CHARACTER;
            } else if (value instanceof String) {
                type = Type.STRING;
            } else {
                type = Type.UNKNOWN;
            }
        }

        public void set(Object value) {
            try {
                this.value = (T) (value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static enum Type {
        UNKNOWN, INTEGER, LONG, DOUBLE, BOOLEAN, CHARACTER, STRING
    }

    //A bunch of methods here
    public static void set(String k, Object value) {
        try {
            CyvForge.config.configFields.get(k).set(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getInt(String k, int defaultValue) {
        try {return Integer.valueOf(CyvForge.config.configFields.get(k).value.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;}
    }

    public static long getLong(String k, long defaultValue) {
        try {return Long.valueOf(CyvForge.config.configFields.get(k).value.toString());
        } catch (Exception e) {return defaultValue;}
    }

    public static double getDouble(String k, double defaultValue) {
        try {return Double.valueOf(CyvForge.config.configFields.get(k).value.toString());
        } catch (Exception e) {return defaultValue;}
    }

    public static boolean getBoolean(String k, boolean defaultValue) {
        try {return Boolean.valueOf(CyvForge.config.configFields.get(k).value.toString());
        } catch (Exception e) {return defaultValue;}
    }

    public static char getChar(String k, char defaultValue) {
        try {return CyvForge.config.configFields.get(k).value.toString().toCharArray()[0];
        } catch (Exception e) {return defaultValue;}
    }

    public static String getString(String k, String defaultValue) {
        try {return CyvForge.config.configFields.get(k).value.toString();
        } catch (Exception e) {return defaultValue;}
    }
}
