package enjoyCafe.digester;

public class Placemark {
    private String address;
    private AddressDetails addressDetails;
    private ExtendedData extendedData;
    private Point point;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public AddressDetails getAddressDetails() {
        return addressDetails;
    }
    public void setAddressDetails(AddressDetails addressDetails) {
        this.addressDetails = addressDetails;
    }
    public ExtendedData getExtendedData() {
        return extendedData;
    }
    public void setExtendedData(ExtendedData extendedData) {
        this.extendedData = extendedData;
    }
    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
    
    
}
