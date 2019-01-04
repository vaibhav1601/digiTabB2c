package z_aksys.solutions.digiappequitybb.Request;

import com.google.gson.annotations.SerializedName;

public class ShareRequest {

    @SerializedName("emp_no")
    private String emp_no;
    @SerializedName("whatsapp_no")
    private String whatsapp_no;
    @SerializedName("contact")
    private String contact;
    @SerializedName("email")
    private String email;
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("link")
    private String link;

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getWhatsapp_no() {
        return whatsapp_no;
    }

    public void setWhatsapp_no(String whatsapp_no) {
        this.whatsapp_no = whatsapp_no;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
