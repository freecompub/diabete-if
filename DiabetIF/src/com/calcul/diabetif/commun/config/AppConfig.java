package com.calcul.diabetif.commun.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AppConfig {
    private static SharedPreferences sharedPrefs = null;
    private static boolean isInDebugMode = false;
    private static String svnRevision = null;

    private static final String BROWSER_URL_PROD = "http://mobile-cdn.carrefour.com/static/browser/fr_fr/v4/";
    private static final String NEWS_URL_PROD = "http://cms-mobile-cdn.carrefour.com/";
    private static final String STORELOCATOR_URL_PROD = "http://store-reference-prd.bl.carrefour.com/stores/js-api/";
    private static final String LOYALTY_URL_PROD = "https://secure-mobile.carrefour.com/";
    private static final String SCRATCHGAME_URL_PROD = "http://igmobile-carrefour.ocito.com";
    private static final String EVENT_URL_PROD = "http://mobile-cdn.carrefour.com/static/event/fr_fr/";
    private static final String SUBDOMAIN_PROD = "logc174";
    private static final String SITE_ID_PROD = "503733";
    private static final String SUB_SITE_PROD = "1";

    private static final String BROWSER_URL_PREPROD = "http://teke-pp.hmg.carrefour.com/static/browser/fr_be/";
    private static final String NEWS_URL_PREPROD = "http://cms-mobile-cdn-test.carrefour.com/";
    private static final String STORELOCATOR_URL_PREPROD = "http://store-reference-prd.bl.carrefour.com/stores/js-api/";
    private static final String LOYALTY_URL_PREPROD = "http://teke-pp.hmg.carrefour.com/";
    private static final String SCRATCHGAME_URL_PREPROD = "http://integ.igmobile-carrefour.ocito.com";
    private static final String EVENT_URL_PREPROD = "http://teke-pp.hmg.carrefour.com/static/event/fr_fr/";
    private static final String SUBDOMAIN_PREPROD = "logc174";
    private static final String SITE_ID_PREPROD = "503734";
    private static final String SUB_SITE_PREPROD = "1";
    
    private static final String BROWSER_URL_INTE = "http://mobile-uat.fr.carrefour.com/static/browser/fr_fr/v4/";
    private static final String NEWS_URL_INTE = "http://cms-mobile-cdn-test.carrefour.com/";
    private static final String STORELOCATOR_URL_INTE= "http://store-reference-prd.bl.carrefour.com/stores/js-api/";
    private static final String LOYALTY_URL_INTE = "http://mobile-uat.fr.carrefour.com/";
    private static final String SCRATCHGAME_URL_INTE= "http://integ.igmobile-carrefour.ocito.com";
    private static final String EVENT_URL_INTE = "http://mobile-uat.fr.carrefour.com/static/event/fr_fr/";
    private static final String SUBDOMAIN_INTE = "logc174";
    private static final String SITE_ID_INTE = "503734";
    private static final String SUB_SITE_INTE = "1";

    private static final int INTEGRATION = 1;
    private static final int PREPROD = 2;
    private static final int PROD = 3;
    private static final int INFOS = 4;
    
    public  static final boolean actuality=false;
    public  static final boolean shoppingListe=false;
    public  static final boolean priceChecker=false;
    public  static final boolean setting=false;

    private AppConfig() {
    }

    public static void initConfig(Context context, String currentSvnRevision) {
        if (sharedPrefs == null) {
            sharedPrefs = context.getSharedPreferences("ConfigSA", Context.MODE_PRIVATE);
        }
        isInDebugMode = isInDebugMode(context);
        svnRevision = currentSvnRevision;
    }

    private static void checkInitConfig() {
        if (sharedPrefs == null) {
            throw new RuntimeException(
                    "Configuration have to be initialize with AppConfig.initConfig(Context context)");
        }
    }

    public static Menu createConfigMenu(Menu menu) {
        checkInitConfig();
        if (isInDebugMode) {
            menu.add(1, INTEGRATION, 1, "integration");
            menu.add(1, PREPROD, 2, "preprod");
            menu.add(1, PROD, 3, "prod");
            menu.add(1, INFOS, 4, "infos");
        }
        return menu;
    }

    private static boolean isInDebugMode(Context context) {
        boolean isDebuggable = false;
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            isDebuggable = (0 != (appinfo.flags &= ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (NameNotFoundException e) {
            /* debuggable variable will remain false */
        }
        return isDebuggable;
    }

    public static void configMenuItemSelected(MenuItem item, Context context) {
        switch (item.getItemId()) {
        case INTEGRATION:
            setIntegration();
            break;
        case PREPROD:
            setPreProduction();
            break;
        case PROD:;
            setProduction();
            break;
        default:
            showInfoToast(context);
            break;
        }
    }

    private static void setProduction() {
        Editor editPrefs = sharedPrefs.edit();
        editPrefs.putString("BROWSER_URL", BROWSER_URL_PROD);
        editPrefs.putString("NEWS_URL", NEWS_URL_PROD);
        editPrefs.putString("STORELOCATOR_URL", STORELOCATOR_URL_PROD);
        editPrefs.putString("LOYALTY_URL", LOYALTY_URL_PROD);
        editPrefs.putString("SCRATCHGAME_URL", SCRATCHGAME_URL_PROD);
        editPrefs.putString("EVENT_URL", EVENT_URL_PROD);
        editPrefs.putString("SUBDOMAIN", SUBDOMAIN_PROD);
        editPrefs.putString("SITE_ID", SITE_ID_PROD);
        editPrefs.putString("SUB_SITE", SUB_SITE_PROD);
        editPrefs.commit();
        System.exit(0); // yes this is nasty, but Romain S. agree with this
    }

    private static void setPreProduction() {
        Editor editPrefs = sharedPrefs.edit();
        editPrefs.putString("BROWSER_URL", BROWSER_URL_PREPROD);
        editPrefs.putString("NEWS_URL", NEWS_URL_PREPROD);
        editPrefs.putString("STORELOCATOR_URL", STORELOCATOR_URL_PREPROD);
        editPrefs.putString("LOYALTY_URL", LOYALTY_URL_PREPROD);
        editPrefs.putString("SCRATCHGAME_URL", SCRATCHGAME_URL_PREPROD);
        editPrefs.putString("EVENT_URL", EVENT_URL_PREPROD);
        editPrefs.putString("SUBDOMAIN", SUBDOMAIN_PREPROD);
        editPrefs.putString("SITE_ID", SITE_ID_PREPROD);
        editPrefs.putString("SUB_SITE", SUB_SITE_PREPROD);
        editPrefs.commit();
        System.exit(0); // yes this is nasty, but Romain S. agree with this
    }

    private static void setIntegration() {
        Editor editPrefs = sharedPrefs.edit();
        editPrefs.putString("BROWSER_URL", BROWSER_URL_INTE);
        editPrefs.putString("NEWS_URL", NEWS_URL_INTE);
        editPrefs.putString("STORELOCATOR_URL", STORELOCATOR_URL_INTE);
        editPrefs.putString("LOYALTY_URL", LOYALTY_URL_INTE);
        editPrefs.putString("SCRATCHGAME_URL", SCRATCHGAME_URL_INTE);
        editPrefs.putString("EVENT_URL", EVENT_URL_INTE);
        editPrefs.putString("SUBDOMAIN", SUBDOMAIN_INTE);
        editPrefs.putString("SITE_ID", SITE_ID_INTE);
        editPrefs.putString("SUB_SITE", SUB_SITE_INTE);
        editPrefs.commit();
        System.exit(0); // yes this is nasty, but Romain S. agree with this
    }

    private static void showInfoToast(Context context) {
        StringBuilder config = new StringBuilder(5);
        for (String key : sharedPrefs.getAll().keySet()) {
            config.append(key);
            config.append("=");
            config.append(sharedPrefs.getString(key, null));
            config.append("\n");
        }
        try {
            PackageInfo infos = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            config.append("\nAPP VERSION : ");
            config.append(infos.versionName);
            config.append("\nSVN REVISION : ");
            config.append(svnRevision);
        } catch (NameNotFoundException e) {
            // I don't care
        }
        Toast.makeText(context, "This is current configuration : \n\n" + config, Toast.LENGTH_LONG)
                .show();
    }

    public static String getBrowserURL() {
        checkInitConfig();
        if (isInDebugMode) {
//            return sharedPrefs.getString("BROWSER_URL", BROWSER_URL_PROD);
            return sharedPrefs.getString("BROWSER_URL", BROWSER_URL_PREPROD);
        }
//        return BROWSER_URL_PROD;
        // TODO quick fix for dev. This method MUST return BROWSER_URL_PROD
        return BROWSER_URL_PREPROD;
    }
    
    public static String getBrowserImageSmallSize() {
    	return AppConfig.getBrowserURL() + "%s/%s_single_small.jpg";
    }
    
    public static String getBrowserImageFullSize() {
    	return AppConfig.getBrowserURL() + "%s/%s_single.jpg";
    }

    public static String getNewsURL() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("NEWS_URL", NEWS_URL_PROD);
        }
        return NEWS_URL_PROD;
    }

    public static String getStorelocator() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("STORELOCATOR_URL", STORELOCATOR_URL_PROD);
        }
        return STORELOCATOR_URL_PROD;
    }

    public static String getLoyaltyURL() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("LOYALTY_URL", LOYALTY_URL_PROD);
        }
        return LOYALTY_URL_PROD;
    }

    public static String getScratchGame() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("SCRATCHGAME_URL", SCRATCHGAME_URL_PROD);
        }
        return SCRATCHGAME_URL_PROD;
    }

    public static String getEventURL() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("EVENT_URL", EVENT_URL_PROD);
        }
        return EVENT_URL_PROD;
    }

    public static String getSubdomain() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("SUBDOMAIN", SUBDOMAIN_PROD);
        }
        return SUBDOMAIN_PROD;
    }

    public static String getSiteId() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("SITE_ID", SITE_ID_PROD);
        }
        return SITE_ID_PROD;
    }

    public static String getSubSite() {
        checkInitConfig();
        if (isInDebugMode) {
            return sharedPrefs.getString("SUB_SITE", SUB_SITE_PROD);
        }
        return SUB_SITE_PROD;
    }
}
