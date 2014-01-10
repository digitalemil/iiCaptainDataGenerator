storeData = LOAD 'default.rawstoredata' USING org.apache.hcatalog.pig.HCatLoader();

refinedStoreData= FOREACH storeData GENERATE appstore, startdate, 
	MilliSecondsBetween(ToDate(startdate), ToDate('1970-01-01', 'yyyy-mm-dd'))-978307200000L AS geostartdate, 
    MilliSecondsBetween(ToDate(startdate), ToDate('1970-01-01', 'yyyy-mm-dd'))-978307200000L+86400000L AS geoenddate, 
    country, unitsdownloads, unitsupdates, unitsrefunds, unitspromotions, 
    revenuedownloads, revenueupdates, revenuerefunds, revenuepromotions;

DUMP refinedStoreData;

STORE refinedStoreData INTO 'default.refinedstoredata' USING org.apache.hcatalog.pig.HCatStorer();

