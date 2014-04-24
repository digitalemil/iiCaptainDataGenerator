storeData = LOAD '/apps/hive/warehouse/rawstoredata/*.csv' USING PigStorage(',') 
	AS (appstore:CHARARRAY, d:CHARARRAY, country:CHARARRAY, 
    	UnitsDownloads:INT, UnitsUpdates:INT, UnitsRefunds:INT, UnitsPromotions:INT, 
        RevenueDownloads:FLOAT, RevenueRefunds:FLOAT, RevenueUpdates:FLOAT, RevenuePromotions:FLOAT );

refinedStoreData= FOREACH storeData GENERATE appstore, d, 
	MilliSecondsBetween(ToDate(d), ToDate('1970-01-01', 'yyyy-mm-dd'))-978307200000L, 
    MilliSecondsBetween(ToDate(d), ToDate('1970-01-01', 'yyyy-mm-dd'))-978307200000L+86400000L, 
    country,UnitsDownloads, UnitsUpdates, UnitsRefunds, UnitsPromotions, 
    RevenueDownloads, RevenueUpdates, RevenueRefunds, RevenuePromotions;

STORE refinedStoreData INTO '/apps/hive/warehouse/refinedstoredata';

