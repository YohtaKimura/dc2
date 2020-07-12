package dc2_4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

public class ConfigurationPreferences {
    public static final String KEY_LOCATION_X = "frame.location.x";
    public static final String KEY_LOCATION_Y = "frame.location.y";
    public static final String KEY_WIDTH = "frame.width";
    public static final String KEY_HEIGHT = "frame.height";
    public static final String KEY_FONT_NAME = "panel.font.name";
    public static final String KEY_FONT_SIZE = "panel.font.size";
    public static final String KEY_COLOR_FOREGROUND_R = "panel.color.fg.r";
    public static final String KEY_COLOR_FOREGROUND_G = "panel.color.fg.g";
    public static final String KEY_COLOR_FOREGROUND_B = "panel.color.fg.b";
    public static final String KEY_COLOR_BACKGROUND_R = "panel.color.bg.r";
    public static final String KEY_COLOR_BACKGROUND_G = "panel.color.bg.g";
    public static final String KEY_COLOR_BACKGROUND_B = "panel.color.bg.b";
    public static final int DEFAULT_WIDTH = 370;
    public static final int DEFAULT_HEIGHT = 80;
    public static final int DEFAULT_FONT_SIZE = 50;
    public static final String DEFAULT_FONT_NAME = Font.MONOSPACED;
    public static final DisplayColor DEFAULT_COLOR_FOREGROUND = DisplayColor.BLACK;
    public static final DisplayColor DEFAULT_COLOR_BACKGROUND = DisplayColor.WHITE;

    private Preferences prefs;

    public ConfigurationPreferences() {
        prefs = Preferences.userNodeForPackage(this.getClass());
    }


    public void update(Point location, int width, int height) {
        prefs.putInt(KEY_LOCATION_X, location.x);
        prefs.putInt(KEY_LOCATION_Y, location.y);
        prefs.putInt(KEY_WIDTH, width);
        prefs.putInt(KEY_HEIGHT, height);
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(Point location, int width, int height, Font font, DisplayColor foreground,
                       DisplayColor background) {
        prefs.putInt(KEY_LOCATION_X, location.x);
        prefs.putInt(KEY_LOCATION_Y, location.y);
        prefs.putInt(KEY_WIDTH, width);
        prefs.putInt(KEY_HEIGHT, height);
        prefs.put(KEY_FONT_NAME, font.getName());
        prefs.putInt(KEY_FONT_SIZE, font.getSize());
        prefs.putInt(KEY_COLOR_FOREGROUND_R, foreground.getValue().getRed());
        prefs.putInt(KEY_COLOR_FOREGROUND_G, foreground.getValue().getGreen());
        prefs.putInt(KEY_COLOR_FOREGROUND_B, foreground.getValue().getBlue());
        prefs.putInt(KEY_COLOR_BACKGROUND_R, background.getValue().getRed());
        prefs.putInt(KEY_COLOR_BACKGROUND_G, background.getValue().getGreen());
        prefs.putInt(KEY_COLOR_BACKGROUND_B, background.getValue().getBlue());
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Point getLocation() {
        int x = prefs.getInt(KEY_LOCATION_X, -1);
        int y = prefs.getInt(KEY_LOCATION_Y, -1);
        if (x == -1 && y == -1)
            return null;
        return new Point(x, y);
    }

    public int getWidth() {
        return prefs.getInt(KEY_WIDTH, DEFAULT_WIDTH);
    }

    public int getHeight() {
        return prefs.getInt(KEY_HEIGHT, DEFAULT_HEIGHT);
    }

    public String getFontName() {
        return prefs.get(KEY_FONT_NAME, DEFAULT_FONT_NAME);
    }

    public int getFontSize() {
        return prefs.getInt(KEY_FONT_SIZE, DEFAULT_FONT_SIZE);
    }

    public DisplayColor getForeground() {
        int r = prefs.getInt(KEY_COLOR_FOREGROUND_R, -1);
        int g = prefs.getInt(KEY_COLOR_FOREGROUND_G, -1);
        int b = prefs.getInt(KEY_COLOR_FOREGROUND_B, -1);
        try {
            return DisplayColor.valueOf(new Color(r, g, b));
        } catch (IllegalArgumentException e) {
            return DEFAULT_COLOR_FOREGROUND;
        }
    }

    public DisplayColor getBackground() {
        int r = prefs.getInt(KEY_COLOR_BACKGROUND_R, -1);
        int g = prefs.getInt(KEY_COLOR_BACKGROUND_G, -1);
        int b = prefs.getInt(KEY_COLOR_BACKGROUND_B, -1);
        try {
            return DisplayColor.valueOf(new Color(r, g, b));
        } catch (IllegalArgumentException e) {
            return DEFAULT_COLOR_BACKGROUND;
        }
    }
}
