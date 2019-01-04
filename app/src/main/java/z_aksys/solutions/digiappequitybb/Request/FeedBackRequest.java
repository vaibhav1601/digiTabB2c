package z_aksys.solutions.digiappequitybb.Request;

import com.google.gson.annotations.SerializedName;

public class FeedBackRequest {

    @SerializedName("emp_no")
    private String emp_no;
    @SerializedName("customer_name")
    private String customer_name;
    @SerializedName("contact")
    private double contact;
    @SerializedName("email")
    private String email;
    @SerializedName("whatsapp_no")
    private String whatsapp_no;

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public double getContact() {
        return contact;
    }

    public void setContact(double contact) {
        this.contact = contact;
    }

    public String getWhatsapp_no() {
        return whatsapp_no;
    }

    public void setWhatsapp_no(String whatsapp_no) {
        this.whatsapp_no = whatsapp_no;
    }
}
