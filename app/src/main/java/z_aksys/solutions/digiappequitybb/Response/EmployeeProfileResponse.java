package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

public class EmployeeProfileResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("employee")
    private employee employee;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public EmployeeProfileResponse.employee getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeProfileResponse.employee employee) {
        this.employee = employee;
    }

    public static class employee {

        @SerializedName("id")
        private String id;
        @SerializedName("app_id")
        private String app_id;
        @SerializedName("emp_no")
        private String emp_name;
        @SerializedName("doj")
        private String doj;
        @SerializedName("cost_tag")
        private String cost_tag;
        @SerializedName("region")
        private String region;
        @SerializedName("channel")
        private String channel;
        @SerializedName("online_tgt")
        private String online_tgt;
        @SerializedName("online_achie")
        private String online_achie;
        @SerializedName("online_chie_per")
        private String online_chie_per;
        @SerializedName("margin_tgt")
        private String margin_tgt;
        @SerializedName("margin_achie")
        private String margin_achie;
        @SerializedName("margin_achie_per")
        private String margin_achie_per;
        @SerializedName("wms_cost")
        private String wms_cost;
        @SerializedName("wms_revenue")
        private String wms_revenue;
        @SerializedName("wms_per")
        private String wms_per;
        @SerializedName("online_fund_all_ac")
        private String online_fund_all_ac;
        @SerializedName("online_fund_online_ac")
        private String online_fund_online_ac;
        @SerializedName("online_fund_achie")
        private String online_fund_achie;
        @SerializedName("online_fund_achie_per")
        private String online_fund_achie_per;
        @SerializedName("fo_ac_chie")
        private String fo_ac_chie;
        @SerializedName("fo_activation")
        private String fo_activation;
        @SerializedName("fo_achie")
        private String fo_achie;
        @SerializedName("fo_achie_per")
        private String fo_achie_per;
        @SerializedName("sip_tgt")
        private String sip_tgt;
        @SerializedName("sip_achie")
        private String sip_achie;
        @SerializedName("sip_achie_per")
        private String sip_achie_per;
        @SerializedName("total_ac_achie")
        private String total_ac_achie;
        @SerializedName("total_margin_achie")
        private String total_margin_achie;
        @SerializedName("total_wms_achie")
        private String total_wms_achie;
        @SerializedName("total_fund_transfer")
        private String total_fund_transfer;
        @SerializedName("total_fo_activation")
        private String total_fo_activation;
        @SerializedName("total_sip")
        private String total_sip;
        @SerializedName("token")
        private String token;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getEmp_name() {
            return emp_name;
        }

        public void setEmp_name(String emp_name) {
            this.emp_name = emp_name;
        }

        public String getDoj() {
            return doj;
        }

        public void setDoj(String doj) {
            this.doj = doj;
        }

        public String getCost_tag() {
            return cost_tag;
        }

        public void setCost_tag(String cost_tag) {
            this.cost_tag = cost_tag;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getOnline_tgt() {
            return online_tgt;
        }

        public void setOnline_tgt(String online_tgt) {
            this.online_tgt = online_tgt;
        }

        public String getOnline_achie() {
            return online_achie;
        }

        public void setOnline_achie(String online_achie) {
            this.online_achie = online_achie;
        }

        public String getOnline_chie_per() {
            return online_chie_per;
        }

        public void setOnline_chie_per(String online_chie_per) {
            this.online_chie_per = online_chie_per;
        }

        public String getMargin_tgt() {
            return margin_tgt;
        }

        public void setMargin_tgt(String margin_tgt) {
            this.margin_tgt = margin_tgt;
        }

        public String getMargin_achie() {
            return margin_achie;
        }

        public void setMargin_achie(String margin_achie) {
            this.margin_achie = margin_achie;
        }

        public String getMargin_achie_per() {
            return margin_achie_per;
        }

        public void setMargin_achie_per(String margin_achie_per) {
            this.margin_achie_per = margin_achie_per;
        }

        public String getWms_cost() {
            return wms_cost;
        }

        public void setWms_cost(String wms_cost) {
            this.wms_cost = wms_cost;
        }

        public String getWms_revenue() {
            return wms_revenue;
        }

        public void setWms_revenue(String wms_revenue) {
            this.wms_revenue = wms_revenue;
        }

        public String getWms_per() {
            return wms_per;
        }

        public void setWms_per(String wms_per) {
            this.wms_per = wms_per;
        }

        public String getOnline_fund_all_ac() {
            return online_fund_all_ac;
        }

        public void setOnline_fund_all_ac(String online_fund_all_ac) {
            this.online_fund_all_ac = online_fund_all_ac;
        }

        public String getOnline_fund_online_ac() {
            return online_fund_online_ac;
        }

        public void setOnline_fund_online_ac(String online_fund_online_ac) {
            this.online_fund_online_ac = online_fund_online_ac;
        }

        public String getOnline_fund_achie() {
            return online_fund_achie;
        }

        public void setOnline_fund_achie(String online_fund_achie) {
            this.online_fund_achie = online_fund_achie;
        }

        public String getOnline_fund_achie_per() {
            return online_fund_achie_per;
        }

        public void setOnline_fund_achie_per(String online_fund_achie_per) {
            this.online_fund_achie_per = online_fund_achie_per;
        }

        public String getFo_ac_chie() {
            return fo_ac_chie;
        }

        public void setFo_ac_chie(String fo_ac_chie) {
            this.fo_ac_chie = fo_ac_chie;
        }

        public String getFo_activation() {
            return fo_activation;
        }

        public void setFo_activation(String fo_activation) {
            this.fo_activation = fo_activation;
        }

        public String getFo_achie() {
            return fo_achie;
        }

        public void setFo_achie(String fo_achie) {
            this.fo_achie = fo_achie;
        }

        public String getFo_achie_per() {
            return fo_achie_per;
        }

        public void setFo_achie_per(String fo_achie_per) {
            this.fo_achie_per = fo_achie_per;
        }

        public String getSip_tgt() {
            return sip_tgt;
        }

        public void setSip_tgt(String sip_tgt) {
            this.sip_tgt = sip_tgt;
        }

        public String getSip_achie() {
            return sip_achie;
        }

        public void setSip_achie(String sip_achie) {
            this.sip_achie = sip_achie;
        }

        public String getSip_achie_per() {
            return sip_achie_per;
        }

        public void setSip_achie_per(String sip_achie_per) {
            this.sip_achie_per = sip_achie_per;
        }

        public String getTotal_ac_achie() {
            return total_ac_achie;
        }

        public void setTotal_ac_achie(String total_ac_achie) {
            this.total_ac_achie = total_ac_achie;
        }

        public String getTotal_margin_achie() {
            return total_margin_achie;
        }

        public void setTotal_margin_achie(String total_margin_achie) {
            this.total_margin_achie = total_margin_achie;
        }

        public String getTotal_wms_achie() {
            return total_wms_achie;
        }

        public void setTotal_wms_achie(String total_wms_achie) {
            this.total_wms_achie = total_wms_achie;
        }

        public String getTotal_fund_transfer() {
            return total_fund_transfer;
        }

        public void setTotal_fund_transfer(String total_fund_transfer) {
            this.total_fund_transfer = total_fund_transfer;
        }

        public String getTotal_fo_activation() {
            return total_fo_activation;
        }

        public void setTotal_fo_activation(String total_fo_activation) {
            this.total_fo_activation = total_fo_activation;
        }

        public String getTotal_sip() {
            return total_sip;
        }

        public void setTotal_sip(String total_sip) {
            this.total_sip = total_sip;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


}
