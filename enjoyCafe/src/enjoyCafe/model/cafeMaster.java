package enjoyCafe.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class cafeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    //カフェ情報
    private String storeFullName;
    private String storeSubName;
    private String storeName;
    private String storeCaption;
    private String storeAddress;
    private String zipCode;
    private String phoneNumber;
    private String lat;
    private String lon;
    private String geohash4;
    private String geohash5;
    private String geohash6;
    private String geohash8;
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

    private String geohashAll;
    private String geohashAround;
    
    //店フラグ
    private String storeFlag;
    //削除フラグ
    private String deleteFlag;
    
    //こだわり系
    private String tabako;
    private String kinen;
    
    //ユーザ投稿フラグ
    private String userSendFlag;
    //投稿ユーザ名
    private String nickName;
    //イイネ
    private String iine;
    
    //更新時間
    private Date updateTime;
    
    //更新時間
    private Date updateDate;
    
    //端末ID
    private String deviceId;
/*
    public String getOnly1() {
        return only1;
    }

    public void setOnly1(String only1) {
        this.only1 = only1;
    }

    public String getOnly2() {
        return only2;
    }

    public void setOnly2(String only2) {
        this.only2 = only2;
    }

    public String getOnly3() {
        return only3;
    }

    public void setOnly3(String only3) {
        this.only3 = only3;
    }

    public String getOnly4() {
        return only4;
    }

    public void setOnly4(String only4) {
        this.only4 = only4;
    }

    public String getOnly5() {
        return only5;
    }

    public void setOnly5(String only5) {
        this.only5 = only5;
    }
*/
    
/*
    public String getDateMuki1() {
        return dateMuki1;
    }

    public void setDateMuki1(String dateMuki1) {
        this.dateMuki1 = dateMuki1;
    }

    public String getDateMuki2() {
        return dateMuki2;
    }

    public void setDateMuki2(String dateMuki2) {
        this.dateMuki2 = dateMuki2;
    }

    public String getDateMuki3() {
        return dateMuki3;
    }

    public void setDateMuki3(String dateMuki3) {
        this.dateMuki3 = dateMuki3;
    }

    public String getDateMuki4() {
        return dateMuki4;
    }

    public void setDateMuki4(String dateMuki4) {
        this.dateMuki4 = dateMuki4;
    }

    public String getDateMuki5() {
        return dateMuki5;
    }

    public void setDateMuki5(String dateMuki5) {
        this.dateMuki5 = dateMuki5;
    }
*/
   
/*
    public String getFriendMattari1() {
        return friendMattari1;
    }

    public void setFriendMattari1(String friendMattari1) {
        this.friendMattari1 = friendMattari1;
    }

    public String getFriendMattari2() {
        return friendMattari2;
    }

    public void setFriendMattari2(String friendMattari2) {
        this.friendMattari2 = friendMattari2;
    }

    public String getFriendMattari3() {
        return friendMattari3;
    }

    public void setFriendMattari3(String friendMattari3) {
        this.friendMattari3 = friendMattari3;
    }

    public String getFriendMattari4() {
        return friendMattari4;
    }

    public void setFriendMattari4(String friendMattari4) {
        this.friendMattari4 = friendMattari4;
    }

    public String getFriendMattari5() {
        return friendMattari5;
    }

    public void setFriendMattari5(String friendMattari5) {
        this.friendMattari5 = friendMattari5;
    }
*/
    
/*
    public String getManyPerson1() {
        return manyPerson1;
    }

    public void setManyPerson1(String manyPerson1) {
        this.manyPerson1 = manyPerson1;
    }

    public String getManyPerson2() {
        return manyPerson2;
    }

    public void setManyPerson2(String manyPerson2) {
        this.manyPerson2 = manyPerson2;
    }

    public String getManyPerson3() {
        return manyPerson3;
    }

    public void setManyPerson3(String manyPerson3) {
        this.manyPerson3 = manyPerson3;
    }

    public String getManyPerson4() {
        return manyPerson4;
    }

    public void setManyPerson4(String manyPerson4) {
        this.manyPerson4 = manyPerson4;
    }

    public String getManyPerson5() {
        return manyPerson5;
    }

    public void setManyPerson5(String manyPerson5) {
        this.manyPerson5 = manyPerson5;
    }
*/
    

    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private String koshitsu;
    private String wifi;
    private String pc;
    private String shinya;
    private String terace;
    private String pet;
    
    //雰囲気系
    private String only;
    /*
    private String only1;
    private String only2;
    private String only3;
    private String only4;
    private String only5;
    */
    private String dateMuki;
    /*
    private String dateMuki1;
    private String dateMuki2;
    private String dateMuki3;
    private String dateMuki4;
    private String dateMuki5;
    */
    private String friendMattari;
    /*
    private String friendMattari1;
    private String friendMattari2;
    private String friendMattari3;
    private String friendMattari4;
    private String friendMattari5;
    */
    private String manyPerson;
    /*
    private String manyPerson1;
    private String manyPerson2;
    private String manyPerson3;
    private String manyPerson4;
    private String manyPerson5;
    */
    //時間帯検索
    private String suiteruTime;
    

    private String updateDateTime;
    
    //更新回数
    private String updateCount;
    
    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
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

    public String getStoreFlag() {
        return storeFlag;
    }

    public void setStoreFlag(String storeFlag) {
        this.storeFlag = storeFlag;
    }

    public String getTabako() {
        return tabako;
    }

    public void setTabako(String tabako) {
        this.tabako = tabako;
    }

    public String getKinen() {
        return kinen;
    }

    public void setKinen(String kinen) {
        this.kinen = kinen;
    }

    public String getKoshitsu() {
        return koshitsu;
    }

    public void setKoshitsu(String koshitsu) {
        this.koshitsu = koshitsu;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getShinya() {
        return shinya;
    }

    public void setShinya(String shinya) {
        this.shinya = shinya;
    }

    public String getTerace() {
        return terace;
    }

    public void setTerace(String terace) {
        this.terace = terace;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

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
        cafeMaster other = (cafeMaster) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeCaption the storeCaption to set
     */
    public void setStoreCaption(String storeCaption) {
        this.storeCaption = storeCaption;
    }

    /**
     * @return the storeCaption
     */
    public String getStoreCaption() {
        return storeCaption;
    }

    /**
     * @param deleteFlag the deleteFlag to set
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return the deleteFlag
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    public String getOnly() {
        return only;
    }

    public void setOnly(String only) {
        this.only = only;
    }

    public String getDateMuki() {
        return dateMuki;
    }

    public void setDateMuki(String dateMuki) {
        this.dateMuki = dateMuki;
    }

    public String getFriendMattari() {
        return friendMattari;
    }

    public void setFriendMattari(String friendMattari) {
        this.friendMattari = friendMattari;
    }

    public String getManyPerson() {
        return manyPerson;
    }

    public void setManyPerson(String manyPerson) {
        this.manyPerson = manyPerson;
    }

    public String getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(String updateCount) {
        this.updateCount = updateCount;
    }

    /**
     * @param userSendFlag the userSendFlag to set
     */
    public void setUserSendFlag(String userSendFlag) {
        this.userSendFlag = userSendFlag;
    }

    /**
     * @return the userSendFlag
     */
    public String getUserSendFlag() {
        return userSendFlag;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param iine the iine to set
     */
    public void setIine(String iine) {
        this.iine = iine;
    }

    /**
     * @return the iine
     */
    public String getIine() {
        return iine;
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

    

    /**
     * @param suiteruTime the suiteruTime to set
     */
    public void setSuiteruTime(String suiteruTime) {
        this.suiteruTime = suiteruTime;
    }

    /**
     * @return the suiteruTime
     */
    public String getSuiteruTime() {
        return suiteruTime;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param storeFullName the storeFullName to set
     */
    public void setStoreFullName(String storeFullName) {
        this.storeFullName = storeFullName;
    }

    /**
     * @return the storeFullName
     */
    public String getStoreFullName() {
        return storeFullName;
    }

    /**
     * @param storeSubName the storeSubName to set
     */
    public void setStoreSubName(String storeSubName) {
        this.storeSubName = storeSubName;
    }

    /**
     * @return the storeSubName
     */
    public String getStoreSubName() {
        return storeSubName;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    

    
}
