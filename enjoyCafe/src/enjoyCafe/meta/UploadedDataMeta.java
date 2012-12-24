package enjoyCafe.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-12-22 18:43:12")
/** */
public final class UploadedDataMeta extends org.slim3.datastore.ModelMeta<enjoyCafe.model.UploadedData> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.UploadedData> fileName = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.UploadedData>(this, "fileName", "fileName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedData, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedData, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedData, java.lang.Integer> length = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedData, java.lang.Integer>(this, "length", "length", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedData, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedData, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final UploadedDataMeta slim3_singleton = new UploadedDataMeta();

    /**
     * @return the singleton
     */
    public static UploadedDataMeta get() {
       return slim3_singleton;
    }

    /** */
    public UploadedDataMeta() {
        super("UploadedData", enjoyCafe.model.UploadedData.class);
    }

    @Override
    public enjoyCafe.model.UploadedData entityToModel(com.google.appengine.api.datastore.Entity entity) {
        enjoyCafe.model.UploadedData model = new enjoyCafe.model.UploadedData();
        model.setFileName((java.lang.String) entity.getProperty("fileName"));
        model.setKey(entity.getKey());
        model.setLength(longToPrimitiveInt((java.lang.Long) entity.getProperty("length")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        enjoyCafe.model.UploadedData m = (enjoyCafe.model.UploadedData) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("fileName", m.getFileName());
        entity.setProperty("length", m.getLength());
        entity.setProperty("version", m.getVersion());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        enjoyCafe.model.UploadedData m = (enjoyCafe.model.UploadedData) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        enjoyCafe.model.UploadedData m = (enjoyCafe.model.UploadedData) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        enjoyCafe.model.UploadedData m = (enjoyCafe.model.UploadedData) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void incrementVersion(Object model) {
        enjoyCafe.model.UploadedData m = (enjoyCafe.model.UploadedData) model;
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