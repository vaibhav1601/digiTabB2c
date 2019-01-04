package z_aksys.solutions.digiappequitybb.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShareDocumentResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("page")
    private String start;
    @SerializedName("start")
    private String limit;
    @SerializedName("hasNext")
    private int hasNext;
    @SerializedName("share_document")
    private ArrayList<share_document> share_documents;


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

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
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


    public ArrayList<share_document> getShare_documents() {
        return share_documents;
    }

    public void setShare_documents(ArrayList<share_document> share_documents) {
        this.share_documents = share_documents;
    }

    public static class share_document {

        @SerializedName("doc_id")
        private String doc_id;
        @SerializedName("name")
        private String name;
        @SerializedName("doc_name")
        private String doc_name;
        @SerializedName("size")
        private String size;
        @SerializedName("publish")
        private String publish;
        @SerializedName("publish_date")
        private String publish_date;


        public String getDoc_id() {
            return doc_id;
        }

        public void setDoc_id(String doc_id) {
            this.doc_id = doc_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDoc_name() {
            return doc_name;
        }

        public void setDoc_name(String doc_name) {
            this.doc_name = doc_name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getPublish_date() {
            return publish_date;
        }

        public void setPublish_date(String publish_date) {
            this.publish_date = publish_date;
        }
    }


}
