package enjoyCafe.digester;


public class Response {
    private String name;
    private Status status;
    private Placemark placemark;
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Placemark getPlacemark() {
        return placemark;
    }
    public void setPlacemark(Placemark placemark) {
        this.placemark = placemark;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
