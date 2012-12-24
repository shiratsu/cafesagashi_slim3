package enjoyCafe.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-12-22 18:43:12")
/** */
public final class UploadedDataFragmentMeta extends org.slim3.datastore.ModelMeta<enjoyCafe.model.UploadedDataFragment> {

    /** */
    public final org.slim3.datastore.CoreUnindexedAttributeMeta<enjoyCafe.model.UploadedDataFragment, byte[]> bytes = new org.slim3.datastore.CoreUnindexedAttributeMeta<enjoyCafe.model.UploadedDataFragment, byte[]>(this, "bytes", "bytes", byte[].class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedDataFragment, com.google.appengine.api.datastore.ShortBlob> bytes2 = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedDataFragment, com.google.appengine.api.datastore.ShortBlob>(this, "bytes2", "bytes2", com.google.appengine.api.datastore.ShortBlob.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedDataFragment, java.lang.Integer> index = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedDataFragment, java.lang.Integer>(this, "index", "index", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedDataFragment, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<enjoyCafe.model.UploadedDataFragment, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<enjoyCafe.model.UploadedDataFragment, org.slim3.datastore.ModelRef<enjoyCafe.model.UploadedData>, enjoyCafe.model.UploadedData> uploadDataRef = new org.slim3.datastore.ModelRefAttributeMeta<enjoyCafe.model.UploadedDataFragment, org.slim3.datastore.ModelRef<enjoyCafe.model.UploadedData>, enjoyCafe.model.UploadedData>(this, "uploadDataRef", "uploadDataRef", org.slim3.datastore.ModelRef.class, enjoyCafe.model.UploadedData.class);

    private static final UploadedDataFragmentMeta slim3_singleton = new UploadedDataFragmentMeta();

    /**
     * @return the singleton
     */
    public static UploadedDataFragmentMeta get() {
       return slim3_singleton;
    }

    /** */
    public UploadedDataFragmentMeta() {
        super("UploadedDataFragment", enjoyCafe.model.UploadedDataFragment.class);
    }

    @Override
    public enjoyCafe.model.UploadedDataFragment entityToModel(com.google.appengine.api.datastore.Entity entity) {
        enjoyCafe.model.UploadedDataFragment model = new enjoyCafe.model.UploadedDataFragment();
        model.setBytes(blobToBytes((com.google.appengine.api.datastore.Blob) entity.getProperty("bytes")));
        model.setBytes2((com.google.appengine.api.datastore.ShortBlob) entity.getProperty("bytes2"));
        model.setIndex(longToPrimitiveInt((java.lang.Long) entity.getProperty("index")));
        model.setKey(entity.getKey());
        if (model.getUploadDataRef() == null) {
            throw new NullPointerException("The property(uploadDataRef) is null.");
        }
        model.getUploadDataRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("uploadDataRef"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        enjoyCafe.model.UploadedDataFragment m = (enjoyCafe.model.UploadedDataFragment) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setUnindexedProperty("bytes", bytesToBlob(m.getBytes()));
        entity.setProperty("bytes2", m.getBytes2());
        entity.setProperty("index", m.getIndex());
        if (m.getUploadDataRef() == null) {
            throw new NullPointerException("The property(uploadDataRef) must not be null.");
        }
        entity.setProperty("uploadDataRef", org.slim3.datastore.ModelMeta.assignKeyIfNecessary(m.getUploadDataRef().getModelMeta(), m.getUploadDataRef().getModel()));
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        enjoyCafe.model.UploadedDataFragment m = (enjoyCafe.model.UploadedDataFragment) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        enjoyCafe.model.UploadedDataFragment m = (enjoyCafe.model.UploadedDataFragment) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(enjoyCafe.model.UploadedDataFragment) is not defined.");
    }

    @Override
    protected void incrementVersion(Object model) {
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