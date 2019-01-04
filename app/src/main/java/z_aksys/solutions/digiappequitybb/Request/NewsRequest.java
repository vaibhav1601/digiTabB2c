package z_aksys.solutions.digiappequitybb.Request;


import com.google.gson.annotations.SerializedName;


public class NewsRequest {

    @SerializedName("emp_no")
    private String emp_no;
    @SerializedName("start")
    private String start;
    @SerializedName("limit")
    private String limit;


    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
