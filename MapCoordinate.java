package assignment;

public class MapCoordinate{
    public final double LATITUDE;
    public final double LONGITUDE;
    public final double ALTITUDE;

    public MapCoordinate(double LATITUDE, double LONGITUDE, double ALTITUDE) {
        this.LATITUDE = LATITUDE;
        this.LONGITUDE = LONGITUDE;
        this.ALTITUDE =  ALTITUDE;
    }
    public double distanceTo(MapCoordinate mp){
        double earthRadius = 6371; // km
        double lat1 = Math.toRadians(LATITUDE);
        double lon1 = Math.toRadians(LONGITUDE);
        double lat2 = Math.toRadians(mp.LATITUDE);
        double lon2 = Math.toRadians(mp.LONGITUDE);

        double dlon = lon2-lon1;
        double dlat = lat2-lat1;

        double sinlat = Math.sin(dlat / 2);
        double sinlon = Math.sin(dlon / 2);

        double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlon*sinlon);
        double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

        double distanceInKM = earthRadius * c;
        double roundedResult = Math.round(distanceInKM * 10) / 10.0;
        return roundedResult;
    }
}
