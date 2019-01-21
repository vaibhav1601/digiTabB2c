package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShareMarketResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;
    @SerializedName("success")
    private String success;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class Data {
        @SerializedName("exchangeData")
        private ArrayList<ExchangeData> exchangeData;

        public ArrayList<ExchangeData> getExchangeData() {
            return exchangeData;
        }

        public void setExchangeData(ArrayList<ExchangeData> exchangeData) {
            this.exchangeData = exchangeData;
        }
    }


    public static class ExchangeData {
        @SerializedName("symbol")
        private String symbol;
        @SerializedName("CurrValue")
        private String CurrValue;
        @SerializedName("absolutechange")
        private String absolutechange;
        @SerializedName("NetChangeFromPrevClose")
        private String NetChangeFromPrevClose;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getCurrValue() {
            return CurrValue;
        }

        public void setCurrValue(String currValue) {
            CurrValue = currValue;
        }

        public String getAbsolutechange() {
            return absolutechange;
        }

        public void setAbsolutechange(String absolutechange) {
            this.absolutechange = absolutechange;
        }

        public String getNetChangeFromPrevClose() {
            return NetChangeFromPrevClose;
        }

        public void setNetChangeFromPrevClose(String netChangeFromPrevClose) {
            NetChangeFromPrevClose = netChangeFromPrevClose;
        }
    }


}
