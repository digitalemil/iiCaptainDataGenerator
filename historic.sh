#!/bin/bash
APPANNIE_HOME=/opt/appannie



for i in `seq 2 31`;
do

	YESTERDAY=`date "+20%y-%m-%d" -d "$i day ago"`
echo Getting Storedata for: $YESTERDAY
	curl --header "Accept: application/xml" --header "Authorization: Bearer 3a5cfea52747a72e66c27f82d7f2ab65a2ecc7e1" "https://api.appannie.com/v1/accounts/108900/sales?start_date=$YESTERDAY&end_date=$YESTERDAY&&break_down=country&&break_down=country" >$APPANNIE_HOME/tmp/import-googleplay-$YESTERDAY.xml
	curl --header "Accept: application/xml" --header "Authorization: Bearer 3a5cfea52747a72e66c27f82d7f2ab65a2ecc7e1" "https://api.appannie.com/v1/accounts/109573/sales?start_date=$YESTERDAY&end_date=$YESTERDAY&&break_down=country&&break_down=country" >$APPANNIE_HOME/tmp/import-itunesconnect-$YESTERDAY.xml
	curl --header "Accept: application/xml" --header "Authorization: Bearer 3a5cfea52747a72e66c27f82d7f2ab65a2ecc7e1"  "https://api.appannie.com/v1/accounts/109275/sales?start_date=$YESTERDAY&end_date=$YESTERDAY&&break_down=country&&break_down=country" >$APPANNIE_HOME/tmp/import-amazon-$YESTERDAY.xml

	java -cp $APPANNIE_HOME/bin de.digitalemil.iicaptain.appannie.XMLParser iTunesConnect,$YESTERDAY $APPANNIE_HOME/tmp/import-itunesconnect-$YESTERDAY.xml >$APPANNIE_HOME/tmp/itunesconnect-$YESTERDAY.csv
	java -cp $APPANNIE_HOME/bin de.digitalemil.iicaptain.appannie.XMLParser GooglePlay,$YESTERDAY $APPANNIE_HOME/tmp/import-googleplay-$YESTERDAY.xml >$APPANNIE_HOME/tmp/googleplay-$YESTERDAY.csv
	java -cp $APPANNIE_HOME/bin de.digitalemil.iicaptain.appannie.XMLParser Amazon,$YESTERDAY $APPANNIE_HOME/tmp/import-amazon-$YESTERDAY.xml >$APPANNIE_HOME/tmp/amazon-$YESTERDAY.csv

done

exit 0

