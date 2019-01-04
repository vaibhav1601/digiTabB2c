package z_aksys.solutions.digiappequitybb.utils;

import android.content.Context;

import java.util.HashMap;

public class CTEventLog {

    private static CTEventLog instance;
    private Context mContext;

    private final String EVENTNAME_NEWS_VIEWED="News Viewed";
    private final String EVENTNAME_FAQ_VIEWED="FAQ Viewed";

    private final String EVENTPROPERTY_NEWS_VIEWED_NEWS_ID="newsId";
    private final String EVENTPROPERTY_NEWS_VIEWED_VIDEO_ID="videoId";
    private final String EVENTPROPERTY_NEWS_VIEWED_NEWS_URL="url";
    private final String EVENTPROPERTY_FAQ_VIEWED_FAQ_ID="faqId";
    private final String EVENTPROPERTY_FAQ_VIEWED_CATEGORY_ID="categoryId";

    private CTEventLog(Context context) {
        this.mContext= context;
    }

    public static CTEventLog getInstance(Context context) {
        if (instance == null) {
            instance = new CTEventLog(context);
        }

        return instance;
    }

    /**
     * Log custom events using clever tap utils class
     * Add user details to event before logging
     * @param eventName
     * @param eventProperties
     */
    private void logEvent(String eventName, HashMap<String, Object> eventProperties){
        eventProperties.put("employeeId", "sc804516");
        CleverTapUtils.getInstance(mContext).logCustomEvent("News Viewed", eventProperties);
    }

    /**
     * Log news viewed event
     * @param newsId
     * @param newsUrl
     */
    public void logNewsViewed(String newsId, String newsUrl){
        HashMap<String, Object> newsViewedEventProperties = new HashMap<>();
        newsViewedEventProperties.put(EVENTPROPERTY_NEWS_VIEWED_NEWS_ID, newsUrl);
        newsViewedEventProperties.put(EVENTPROPERTY_NEWS_VIEWED_NEWS_URL, newsId);
        logEvent(EVENTNAME_NEWS_VIEWED, newsViewedEventProperties);
    }

    /**
     * Log youtube news viewed event
     * @param newsId
     * @param videoId
     */
    public void logYoutubeNewsViewed(String newsId, String videoId){
        HashMap<String, Object> newsViewedEventProperties = new HashMap<>();
        newsViewedEventProperties.put(EVENTPROPERTY_NEWS_VIEWED_NEWS_ID, newsId);
        newsViewedEventProperties.put(EVENTPROPERTY_NEWS_VIEWED_VIDEO_ID, videoId);
        logEvent(EVENTNAME_NEWS_VIEWED, newsViewedEventProperties);
    }

    /**
     * Log news viewed event
     * @param faqId
     * @param faqCategoryId
     */
    public void logFaqViewed(String faqId, String faqCategoryId){
        HashMap<String, Object> newsViewedEventProperties = new HashMap<>();
        newsViewedEventProperties.put(EVENTPROPERTY_FAQ_VIEWED_FAQ_ID, faqId);
        newsViewedEventProperties.put(EVENTPROPERTY_FAQ_VIEWED_CATEGORY_ID, faqCategoryId);
        logEvent(EVENTNAME_NEWS_VIEWED, newsViewedEventProperties);
    }
}
