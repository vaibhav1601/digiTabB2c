package z_aksys.solutions.digiappequitybb.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Feedback")
public class Feedback {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int emp_no;
    private String emp_number;
    private String customer_name;
    private String mobile_number;
    private String email;
    private String comments;


    @NonNull
    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(@NonNull int emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
