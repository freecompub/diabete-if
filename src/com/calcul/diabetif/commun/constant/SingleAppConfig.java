package com.calcul.diabetif.commun.constant;

import com.calcul.diabetif.commun.config.AppConfig;

public class SingleAppConfig {

    private static SingleAppConfig instance = new SingleAppConfig();

    private String[] carrefourFeedPath;

    private long catalogCacheMaxAge;

    private long eventCacheMaxAge;
    
    private long promotionCacheMaxAge;

    private int numberOfMonthsForLastOrder;

    private String countryCallingCode;

    private String currencySymbol;

    private int minimumFractionDigits;

    private int maximumFractionDigits;

    private String dbName;
    
    private String dbEventName;

    private String dbFilePattern;

    private String metadataFilePath;

    private String catalogDetail;

    private SingleAppConfig() {
    	// README FISRT
    	// Now URLs are stored into AppConfig class that is in common package.
    	// By default application use production environnement.
    	// In the first activity you can use menu button to switch environnement (except in release mode). This action will also exit application.
    	
        // Attention! If you want to add a facebook page to the app you still have to filter the
        // feeds on doesFeedComeFromCarrefourUser
        carrefourFeedPath = new String[] { "beaute.cosmetique.carrefour/feed?limit=10",
                "spectacles.carrefour/feed?limit=10", "ideesetrecettes.carrefour/feed?limit=10",
                "boiteaideesproduitscarrefour/feed?limit=10", "CarrefourOoshop/feed?limit=10" };

        // Cache the Catalog Content for 2 hours.
//        catalogCacheMaxAge = 1000 * 60 * 60 * 2;
        catalogCacheMaxAge = 1;
        
        // Cache the Catalog Content for 2 hours.
//        eventCacheMaxAge = 1000 * 60 * 60 * 2;
        eventCacheMaxAge = 1;

//        promotionCacheMaxAge = 1000 * 60;
        promotionCacheMaxAge = 1;

        numberOfMonthsForLastOrder = 3;

        // Country calling codes
        countryCallingCode = "+33";

        // Currency Symbol
        currencySymbol = "EUR";

        // Minimum Fraction Digits
        minimumFractionDigits = 2;

        // Maximum Fraction Digits
        maximumFractionDigits = 2;

        dbName = "folder_v4";
        
        dbEventName = "event";

        dbFilePattern = "%s.db_%s";

        metadataFilePath = "%s.plist";
        
        catalogDetail = "info.html";
    }

    public String getNewsUrl() {
        return AppConfig.getNewsURL();
    }

    public static SingleAppConfig getSingleAppConfig() {
        return instance;
    }

    public String getBrowserUrl() {
        return AppConfig.getBrowserURL();
    }

    public String getStoreLocatorUrl() {
        return AppConfig.getStorelocator();
    }

    public String getLoyaltyUrl() {
        return AppConfig.getLoyaltyURL();
    }

    public long getCatalogCacheMaxAge() {
        return catalogCacheMaxAge;
    }

    public long getEventCacheMaxAge() {
		return eventCacheMaxAge;
	}

	public long getPromotionCacheMaxAge() {
        return promotionCacheMaxAge;
    }

    public int getNumberOfMonthsForLastOrder() {
        return numberOfMonthsForLastOrder;
    }

    public String getCountryCallingCode() {
        return countryCallingCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public int getMinimumFractionDigits() {
        return minimumFractionDigits;
    }

    public void setMinimumFractionDigits(int minimumFractionDigits) {
        this.minimumFractionDigits = minimumFractionDigits;
    }

    public int getMaximumFractionDigits() {
        return maximumFractionDigits;
    }

    public void setMaximumFractionDigits(int maximumFractionDigits) {
        this.maximumFractionDigits = maximumFractionDigits;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbFilePattern() {
        return dbFilePattern;
    }

    public String getMetadataFilePath() {
        return metadataFilePath;
    }

    public String[] getCarrefourFeedPath() {
        return carrefourFeedPath;
    }

    public String getSubDomain() {
        return AppConfig.getSubdomain();
    }

    public String getSiteId() {
        return AppConfig.getSiteId();
    }

    public String getSubSite() {
        return AppConfig.getSubSite();
    }

    public String getScratchGameUrl() {
        return AppConfig.getScratchGame();
    }

    public String getEventUrl() {
        return AppConfig.getEventURL();
    }

    public String getDbEventName() {
        return dbEventName;
    }

    public String getCatalogDetail() {
        return catalogDetail;
    }
}
