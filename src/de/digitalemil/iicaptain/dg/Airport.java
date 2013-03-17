package de.digitalemil.iicaptain.dg;

public class Airport {
	Integer id;//      Unique OpenFlights identifier for this airport.
	String name;//    Name of airport. May or may not contain the City name.
	String city;//    Main city served by airport. May be spelled differently from Name.
	String country;// Country or territory where airport is located.
	String iata; ///FAA        3-letter FAA code, for airports located in Country "United States of America". 3-letter IATA code, for all other airports. Blank if not assigned.
	String icao; //   4-letter ICAO code. Blank if not assigned.
	String latitude; //        Decimal degrees, usually to six significant digits. Negative is South, positive is North.
	String longitude; //       Decimal degrees, usually to six significant digits. Negative is West, positive is East.
	String altitude; //        In feet.
	String timezone; //        Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5.
	String dst;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getIcao() {
		return icao;
	}
	public void setIcao(String icao) {
		this.icao = icao;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	@Override
	public String toString() {
		return "Airport [id=" + id + ", name=" + name + ", city=" + city
				+ ", country=" + country + ", iata=" + iata + ", icao=" + icao
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", altitude=" + altitude + ", timezone=" + timezone
				+ ", dst=" + dst + "]";
	}
	
	
}
