package enjoyCafe.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-12-22 18:43:12")
/** */
public final class CafeReviewMeta extends org.slim3.datastore.ModelMeta<enjoyCafe.model.CafeReview> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview> cafeId = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview>(this, "cafeId", "cafeId");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.CafeReview, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.CafeReview, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview> nickName = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview>(this, "nickName", "nickName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview> reviewContent = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview>(this, "reviewContent", "reviewContent");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview> updateDate = new org.slim3.datastore.StringAttributeMeta<enjoyCafe.model.CafeReview>(this, "updateDate", "updateDate");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.CafeReview, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.CafeReview, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final CafeReviewMeta slim3_singleton = new CafeReviewMeta();

    /**
     * @return the singleton
     */
    public static CafeReviewMeta get() {
       return slim3_singleton;
    }

    /** */
    public CafeReviewMeta() {
        super("CafeReview", enjoyCafe.model.CafeReview.class);
    }

    @Override
    public enjoyCafe.model.CafeReview entityToModel(com.google.appengine.api.datastore.Entity entity) {
        enjoyCafe.model.CafeReview model = new enjoyCafe.model.CafeReview();
        model.setCafeId((java.lang.String) entity.getProperty("cafeId"));
        model.setKey(entity.getKey());
        model.setNickName((java.lang.String) entity.getProperty("nickName"));
        model.setReviewContent((java.lang.String) entity.getProperty("reviewContent"));
        model.setUpdateDate((java.lang.String) entity.getProperty("updateDate"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        enjoyCafe.model.CafeReview m = (enjoyCafe.model.CafeReview) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("cafeId", m.getCafeId());
        entity.setProperty("nickName", m.getNickName());
        entity.setProperty("reviewContent", m.getReviewContent());
        entity.setProperty("updateDate", m.getUpdateDate());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        enjoyCafe.model.CafeReview m = (enjoyCafe.model.CafeReview) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        enjoyCafe.model.CafeReview m = (enjoyCafe.model.CafeReview) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        enjoyCafe.model.CafeReview m = (enjoyCafe.model.CafeReview) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void incrementVersion(Object model) {
        enjoyCafe.model.CafeReview m = (enjoyCafe.model.CafeReview) model;
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