package enjoyCafe.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class areaData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String rcCode;
    private String lineCode;
    private String stationCode;
    private String lineSort;
    private String stationSort;
    private String stationGroup;
    private String rcType;
    private String rcName;
    private String lineName;
    private String stationName;
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    private String prefCode;
    private String lon;
    private String lat;
    private String geohash4;
    private String geohash5;
    private String geohash6;
    private String geohash8;
    private String geohashAll;
    private String geohashAround;
    public String getGeohash4() {
        return geohash4;
    }

    public void setGeohash4(String geohash4) {
        this.geohash4 = geohash4;
    }

    public String getGeohashAround() {
        return geohashAround;
    }

    public void setGeohashAround(String geohashAround) {
        this.geohashAround = geohashAround;
    }


    private String updateDate;
    
    
    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        areaData other = (areaData) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public String getRcCode() {
        return rcCode;
    }

    public void setRcCode(String rcCode) {
        this.rcCode = rcCode;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getLineSort() {
        return lineSort;
    }

    public void setLineSort(String lineSort) {
        this.lineSort = lineSort;
    }

    public String getStationSort() {
        return stationSort;
    }

    public void setStationSort(String stationSort) {
        this.stationSort = stationSort;
    }

    public String getStationGroup() {
        return stationGroup;
    }

    public void setStationGroup(String stationGroup) {
        this.stationGroup = stationGroup;
    }

    public String getRcType() {
        return rcType;
    }

    public void setRcType(String rcType) {
        this.rcType = rcType;
    }

    public String getRcName() {
        return rcName;
    }

    public void setRcName(String rcName) {
        this.rcName = rcName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPrefCode() {
        return prefCode;
    }

    public void setPrefCode(String prefCode) {
        this.prefCode = prefCode;
    }


    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getGeohash6() {
        return geohash6;
    }

    public void setGeohash6(String geohash6) {
        this.geohash6 = geohash6;
    }

    public String getGeohash8() {
        return geohash8;
    }

    public void setGeohash8(String geohash8) {
        this.geohash8 = geohash8;
    }

    public String getGeohashAll() {
        return geohashAll;
    }

    public void setGeohashAll(String geohashAll) {
        this.geohashAll = geohashAll;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the updateDate
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param geohash5 the geohash5 to set
     */
    public void setGeohash5(String geohash5) {
        this.geohash5 = geohash5;
    }

    /**
     * @return the geohash5
     */
    public String getGeohash5() {
        return geohash5;
    }

    
    
}
