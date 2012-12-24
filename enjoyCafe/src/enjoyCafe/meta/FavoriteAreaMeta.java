package enjoyCafe.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-12-22 18:43:12")
/** */
public final class FavoriteAreaMeta extends org.slim3.datastore.ModelMeta<enjoyCafe.model.FavoriteArea> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.FavoriteArea, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.FavoriteArea, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.FavoriteArea, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.FavoriteArea, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final FavoriteAreaMeta slim3_singleton = new FavoriteAreaMeta();

    /**
     * @return the singleton
     */
    public static FavoriteAreaMeta get() {
       return slim3_singleton;
    }

    /** */
    public FavoriteAreaMeta() {
        super("FavoriteArea", enjoyCafe.model.FavoriteArea.class);
    }

    @Override
    public enjoyCafe.model.FavoriteArea entityToModel(com.google.appengine.api.datastore.Entity entity) {
        enjoyCafe.model.FavoriteArea model = new enjoyCafe.model.FavoriteArea();
        model.setKey(entity.getKey());
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        enjoyCafe.model.FavoriteArea m = (enjoyCafe.model.FavoriteArea) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        enjoyCafe.model.FavoriteArea m = (enjoyCafe.model.FavoriteArea) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        enjoyCafe.model.FavoriteArea m = (enjoyCafe.model.FavoriteArea) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        enjoyCafe.model.FavoriteArea m = (enjoyCafe.model.FavoriteArea) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void incrementVersion(Object model) {
        enjoyCafe.model.FavoriteArea m = (enjoyCafe.model.FavoriteArea) model;
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