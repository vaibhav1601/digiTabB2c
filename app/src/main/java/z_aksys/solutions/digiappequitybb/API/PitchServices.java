package z_aksys.solutions.digiappequitybb.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import z_aksys.solutions.digiappequitybb.Request.FeedBackRequest;
import z_aksys.solutions.digiappequitybb.Request.QuestionRequest;
import z_aksys.solutions.digiappequitybb.Request.ShareRequest;
import z_aksys.solutions.digiappequitybb.Response.EmployeeProfileResponse;
import z_aksys.solutions.digiappequitybb.Response.FaqDetailsResponse;
import z_aksys.solutions.digiappequitybb.Response.FaqResponse;
import z_aksys.solutions.digiappequitybb.Response.FaqSearchResponse;
import z_aksys.solutions.digiappequitybb.Response.FeedBackResponse;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.Response.NewsResponse;
import z_aksys.solutions.digiappequitybb.Response.QuestionResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareDocumentResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareMarketResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareVideoTopicResponse;

public interface PitchServices {

    @GET("pitchapp/api/news")
    Call<NewsResponse> getNews(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no,
            @Query("start") int start,
            @Query("limit") int limit
    );


    @GET("pitchapp/api/faq")
    Call<FaqResponse> getFaq(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no,
            @Query("start") int start,
            @Query("limit") int limit
    );


    @GET("pitchapp/api/faq_detail")
    Call<FaqDetailsResponse> getFaqDetails(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no,
            @Query("start") int start,
            @Query("limit") int limit,
            @Query("topic_id") String topic_id
    );


    @GET("pitchapp/api/share_document")
    Call<ShareDocumentResponse> getShareDocument(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no,
            @Query("start") int start,
            @Query("limit") int limit
    );


    @GET("pitchapp/api/learn")
    Call<LearnResponse> getLearn(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no
    );

    @POST("pitchapp/api/learn_result")
    Call<QuestionResponse> postQuestion(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Body QuestionRequest questionRequest
    );


    @POST("pitchapp/api/learn_result")
    Call<QuestionResponse> postAllQuestion(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Body List<QuestionRequest> questionRequest
    );


    @POST("pitchapp/api/feedback")
    Call<FeedBackResponse> postFeedBack(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Body FeedBackRequest feedBackRequests
    );


    @POST("pitchapp/api/feedback")
    Call<FeedBackResponse> postAllFeedBack(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Body List<FeedBackRequest> feedBackRequests
    );


    @GET("pitchapp/api/profile")
    Call<EmployeeProfileResponse> getEmployee(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no
    );


    @POST("pitchapp/api/share")
    Call<ShareResponse> postShare(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Body List<ShareRequest> shareRequest
    );


    @POST("telesales/api/exchange/exchangeData")
    Call<ShareMarketResponse> getShareMarket();


    @GET("pitchapp/api/share_video_topic")
    Call<ShareVideoTopicResponse> getVideoTopic(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no
    );


    @GET("pitchapp/api/share_video")
    Call<ShareVideoResponse> getShareVideo(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no,
            @Query("start") int start,
            @Query("limit") int limit,
            @Query("topic_id") int topic_id
    );


    @GET("pitchapp/api/faq_search")
    Call<FaqSearchResponse> getFaqSearch(
            @Header("Content-Type") String content_type,
            @Header("APP-KEY") String app_Key,
            @Header("APP-SECRET") String app_Secret,
            @Query("emp_no") String emp_no,
            @Query("search") String search
    );


}
