package ferrarib.com.app.crawler.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bruno on 7/11/16.
 */
public class Utils {

    private static final String PROPS_FILE_NAME = "config.properties";

    public static String getProperty(String key, Context ctx) throws IOException {
        Properties props = new Properties();
        AssetManager assetManager = ctx.getAssets();
        InputStream stream = assetManager.open(PROPS_FILE_NAME);
        props.load(stream);

        return props.getProperty(key);
    }

}
