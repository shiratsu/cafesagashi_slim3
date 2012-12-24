package enjoyCafe.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-12-22 18:43:12")
/** */
public final class areaDataMeta extends org.slim3.datastore.ModelMeta<enjoyCafe.model.areaData> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> geohash4 = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "geohash4", "geohash4");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> geohash5 = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "geohash5", "geohash5");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> geohash6 = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "geohash6", "geohash6");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> geohash8 = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "geohash8", "geohash8");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> geohashAll = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "geohashAll", "geohashAll");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> geohashAround = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "geohashAround", "geohashAround");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.areaData, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.areaData, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> lat = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "lat", "lat");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> lineCode = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "lineCode", "lineCode");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> lineName = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "lineName", "lineName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> lineSort = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "lineSort", "lineSort");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> lon = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "lon", "lon");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> prefCode = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "prefCode", "prefCode");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> rcCode = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "rcCode", "rcCode");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> rcName = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "rcName", "rcName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> rcType = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "rcType", "rcType");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> stationCode = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "stationCode", "stationCode");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> stationGroup = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "stationGroup", "stationGroup");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> stationName = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "stationName", "stationName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> stationSort = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "stationSort", "stationSort");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData> updateDate = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.areaData>(this, "updateDate", "updateDate");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.areaData, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.areaData, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final areaDataMeta slim3_singleton = new areaDataMeta();

    /**
     * @return the singleton
     */
    public static areaDataMeta get() {
       return slim3_singleton;
    }

    /** */
    public areaDataMeta() {
        super("areaData", enjoyCafe.model.areaData.class);
    }

    @Override
    public enjoyCafe.model.areaData entityToModel(com.google.appengine.api.datastore.Entity entity) {
        enjoyCafe.model.areaData model = new enjoyCafe.model.areaData();
        model.setGeohash4((java.lang.String) entity.getProperty("geohash4"));
        model.setGeohash5((java.lang.String) entity.getProperty("geohash5"));
        model.setGeohash6((java.lang.String) entity.getProperty("geohash6"));
        model.setGeohash8((java.lang.String) entity.getProperty("geohash8"));
        model.setGeohashAll((java.lang.String) entity.getProperty("geohashAll"));
        model.setGeohashAround((java.lang.String) entity.getProperty("geohashAround"));
        model.setKey(entity.getKey());
        model.setLat((java.lang.String) entity.getProperty("lat"));
        model.setLineCode((java.lang.String) entity.getProperty("lineCode"));
        model.setLineName((java.lang.String) entity.getProperty("lineName"));
        model.setLineSort((java.lang.String) entity.getProperty("lineSort"));
        model.setLon((java.lang.String) entity.getProperty("lon"));
        model.setPrefCode((java.lang.String) entity.getProperty("prefCode"));
        model.setRcCode((java.lang.String) entity.getProperty("rcCode"));
        model.setRcName((java.lang.String) entity.getProperty("rcName"));
        model.setRcType((java.lang.String) entity.getProperty("rcType"));
        model.setStationCode((java.lang.String) entity.getProperty("stationCode"));
        model.setStationGroup((java.lang.String) entity.getProperty("stationGroup"));
        model.setStationName((java.lang.String) entity.getProperty("stationName"));
        model.setStationSort((java.lang.String) entity.getProperty("stationSort"));
        model.setUpdateDate((java.lang.String) entity.getProperty("updateDate"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        enjoyCafe.model.areaData m = (enjoyCafe.model.areaData) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("geohash4", m.getGeohash4());
        entity.setProperty("geohash5", m.getGeohash5());
        entity.setProperty("geohash6", m.getGeohash6());
        entity.setProperty("geohash8", m.getGeohash8());
        entity.setProperty("geohashAll", m.getGeohashAll());
        entity.setProperty("geohashAround", m.getGeohashAround());
        entity.setProperty("lat", m.getLat());
        entity.setProperty("lineCode", m.getLineCode());
        entity.setProperty("lineName", m.getLineName());
        entity.setProperty("lineSort", m.getLineSort());
        entity.setProperty("lon", m.getLon());
        entity.setProperty("prefCode", m.getPrefCode());
        entity.setProperty("rcCode", m.getRcCode());
        entity.setProperty("rcName", m.getRcName());
        entity.setProperty("rcType", m.getRcType());
        entity.setProperty("stationCode", m.getStationCode());
        entity.setProperty("stationGroup", m.getStationGroup());
        entity.setProperty("stationName", m.getStationName());
        entity.setProperty("stationSort", m.getStationSort());
        entity.setProperty("updateDate", m.getUpdateDate());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        enjoyCafe.model.areaData m = (enjoyCafe.model.areaData) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        enjoyCafe.model.areaData m = (enjoyCafe.model.areaData) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        enjoyCafe.model.areaData m = (enjoyCafe.model.areaData) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void incrementVersion(Object model) {
        enjoyCafe.model.areaData m = (enjoyCafe.model.areaData) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        assignKeyIfNecessary(model);
        incrementVersion(model);
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

}