package de.digitalemil.iicaptain.appannie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XMLParser {
	
	public static void main(String[] args) throws FileNotFoundException,
			XMLStreamException {
		
		XMLParser p = new XMLParser(args[0], args[1]);
	}

	public XMLParser(String appname, String fname) throws XMLStreamException,
			FileNotFoundException {
		boolean inCountry= false, inUnits= false, inApp= false, inDownload= false, inUpdates= false, inRefunds= false, inRevenue= false;
		String country="", downloads="", updates="", refunds="", promotions="", revenue="", revenueUpdate="", revenueRefunds="", revenuePromotions= "";
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory
				.createXMLStreamReader(new FileInputStream(fname));
		
		StringBuilder spacer = new StringBuilder();

		while (parser.hasNext()) {
			
			switch (parser.getEventType()) {
			case XMLStreamConstants.START_DOCUMENT:
				break;

			case XMLStreamConstants.END_DOCUMENT:
				parser.close();
				break;

			case XMLStreamConstants.NAMESPACE:
				break;

			case XMLStreamConstants.START_ELEMENT:
				String e= parser.getLocalName();
				
				if(e.equals("country")) {
					country= parser.getElementText();
					inCountry= true;
				}
				if(e.equals("units")) {
					inUnits= true;
				}
				if(e.equals("revenue")) {
					inRevenue= true;
				}			
				if(e.equals("app") && (inUnits || inRevenue)) {
					inApp= true;
				}
				if(inApp && inUnits && e.equals("downloads")) {
					downloads= parser.getElementText();
				}
				if(inApp && inUnits && e.equals("updates")) {
					updates= parser.getElementText();
				}
				if(inApp && inUnits && e.equals("refunds")) {
					refunds= parser.getElementText();
				}
				if(inApp && inUnits && e.equals("promotions")) {
					promotions= parser.getElementText();
				}
				if(inApp && inRevenue && e.equals("downloads")) {
					revenue= parser.getElementText();
				}
				if(inApp && inRevenue && e.equals("updates")) {
					revenueUpdate= parser.getElementText();
				}
				if(inApp && inRevenue && e.equals("refunds")) {
					revenueRefunds= parser.getElementText();
				}
				if(inApp && inRevenue && e.equals("promotions")) {
					revenuePromotions= parser.getElementText();
				}
			
				break;

			case XMLStreamConstants.CHARACTERS:
				break;

			case XMLStreamConstants.END_ELEMENT:
				e= parser.getLocalName();
				if(e.equals("app"))
					inApp= false;
				if(e.equals("revenue")) {
					inRevenue= false;
					System.out.println(appname+","+country+","+downloads+","+updates+","+refunds+","+promotions+","+revenue+","+revenueUpdate+","+revenueRefunds+","+revenuePromotions);				
				}
				if(e.equals("country")) {
					inCountry= false;
				}
				if(e.equals("units")) {
					inUnits= false;
				}				
				break;

			default:
				break;
			}
			parser.next();
		}
	}
}
