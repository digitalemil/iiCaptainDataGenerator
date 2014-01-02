#!/bin/bash
APPANNIE_HOME=/opt/appannie

YESTERDAY=`date "+20%y-%m-%d" -d "yesterday"`

curl --header "Accept: application/xml" --header "Authorization: Bearer 3a5cfea52747a72e66c27f82d7f2ab65a2ecc7e1" "https://api.appannie.com/v1/accounts/108900/sales?start_date=$YESTERDAY&end_date=$YESTERDAY&&break_down=country&&break_down=country" >$APPANNIE_HOME/tmp/import-googleplay.xml
curl --header "Accept: application/xml" --header "Authorization: Bearer 3a5cfea52747a72e66c27f82d7f2ab65a2ecc7e1" "https://api.appannie.com/v1/accounts/109573/sales?start_date=$YESTERDAY&end_date=$YESTERDAY&&break_down=country&&break_down=country" >$APPANNIE_HOME/tmp/import-itunesconnect.xml
curl --header "Accept: application/xml" --header "Authorization: Bearer 3a5cfea52747a72e66c27f82d7f2ab65a2ecc7e1"  "https://api.appannie.com/v1/accounts/109275/sales?start_date=$YESTERDAY&end_date=$YESTERDAY&&break_down=country&&break_down=country" >$APPANNIE_HOME/tmp/import-amazon.xml

java -cp $APPANNIE_HOME/bin de.digitalemil.iicaptain.appannie.XMLParser iTunesConnect,$YESTERDAY $APPANNIE_HOME/tmp/import-itunesconnect.xml >$APPANNIE_HOME/tmp/itunesconnect.csv
java -cp $APPANNIE_HOME/bin de.digitalemil.iicaptain.appannie.XMLParser GooglePlay,$YESTERDAY $APPANNIE_HOME/tmp/import-googleplay.xml >$APPANNIE_HOME/tmp/googleplay.csv
java -cp $APPANNIE_HOME/bin de.digitalemil.iicaptain.appannie.XMLParser Amazon,$YESTERDAY $APPANNIE_HOME/tmp/import-amazon.xml >$APPANNIE_HOME/tmp/amazon.csv

exit 0

