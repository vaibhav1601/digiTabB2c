package z_aksys.solutions.digiappequitybb.utils;

import android.os.Environment;

public class Constants {


    public static final String VideoKEY = "AIzaSyDkYuwi6o3Hy1E2x2fNNXMnMUzLMTK-rJk";
    public static final String baseUrl = "http://leometric.in:80/";

    //mf
    /*public static final String appKey="80w4k8ogow8k4g4ks80sg08o4kcsc04scg48kks4";
    public static final String appSec="42d34bd99093094714e0257e391a810c";
*/
    //b2c
    /*public static final String appKey="ws08skwgk4c40s0s8k4c0wscwc4s0o4kk4oow048";
    public static final String appSec="517b85b30db64d0256c1b3fb049b9aed";*/

    //b2b
    public static final String appKey="o4gsgk0w0kcskoos0wsgggg48k4csccgoc8sks0o ";
    public static final String appSec="c91fbf2ce50a58765123973ebe4b14c7";

    public static final String user="test";


    public static final int IMAGE_TYPE = 0;
    public static final int YOUTUBE_TYPE = 1;

    public static final String lESSIONS = "lessions";
    public static final String QUESTIONS = "questions";

    public static final String EMPTY = "";
    public static final String DASH = "-";
    //public static DateTimeFormatter appDateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

    public static final String EXPERT = "expert";
    public static final String EXPERIENCED = "experienced";

    public static final String TILDE = "~";
    public static final String MASTER_TILDE = "MASTER~";
    public static final String Z_TILDE = "~Z~";

    public static final int LEAD_SESSION_ID_LENGTH = 20;
    public static final String FLURRY_API_KEY = "87VHRCK8CFR8SB3GD6C6";
    public static final String LOCALYTICS_API_KEY = "6fe6fdaa3679892a6121601-a98c7640-ce5c-11e5-6725-002dea3c3994";

    public static final String HOST = "";
    // public static final String VIDEO_PATH = "www/video/";
    public static final String VIDEO_PATH = Environment.getExternalStorageDirectory() + "/Video/";
    public static final String VIDEO_PATH_MISC = Environment.getExternalStorageDirectory() + "/Hi-Res/Misc/";
    public static final String VIDEO_PATH_DERIVATIVES = Environment.getExternalStorageDirectory() + "/Hi-Res/Derivatives/";
    public static final String VIDEO_PATH_EQUITY_INVST = Environment.getExternalStorageDirectory() + "/Hi-Res/Equity Investment Rules, Right Time,/";
    public static final String VIDEO_PATH_PRIMARY_MARKET = Environment.getExternalStorageDirectory() + "/Hi-Res/Primary Market,IPO,BSE,NSE/";

    public static final String VIDEO_1 = "angeleye";
    public static final String VIDEO_2 = "angelswift";
    public static final String VIDEO_3 = "speedpro";
    public static final String VIDEO_ARQ = "ARQ Simplified";
    public static final String VIDEO_ACCOUNT_TYPE = "Demat_vs_Trading_account";//"Demat account v3 Final";
    public static final String VIDEO_SAFE_INVEST = "3_quick_benefits_of_equity";//"3 quick benefits of equity v6 Final";
    public static final String VIDEO_EXCELLENT_INVEST = "Why_should_you_choose_equity";//"Why should you choose equity v4 Final";
    public static final String VIDEO_DERIVATIVES = "What_are_Derivatives";//"What are Derivatives";
    public static final String VIDEO_MARGIN_FUNDING = "Margin_Funding";//"Margin Funding v04 Final";
    public static final String VIDEO_EXPERT_MANAGEMENT = "What_are_Mutual_Funds";//"What are mutual funds v4 Final";
    public static final String VIDEO_RELATIONSHIP_MNGR = "Online_Vs_Offline";//"Online Vs Offline v7 Final";
    public static final String VIDEO_EDUCATION_1 = "Fifteen_key_stock_trading_terms_Part_1";//"Fifteen key stock trading terms part 1 v5 Final";
    public static final String VIDEO_EDUCATION_2 = "Fifteen_key_stock_trading_terms_Part_2";//"fifteen stock trading terms part2 v3 Final";
    public static final String VIDEO_EDUCATION_3 = "What_are_Call_Options";//"What are Call Options";
    public static final String VIDEO_EDUCATION_4 = "What_are_Put_Options";//"What are Put Options";
    public static final String VIDEO_EDUCATION_5 = "What_is_an_IPO";//"What is an IPO";

    public class Intent {
        public static final String BACK_STRING_ID = "back_string_id";
        public static final String PAGE_URL_ASSET = "page_url_asset";
        public static final String VIDEO_PATH = "video_path";
        public static final String VIDEO_POSITION = "video_position";
        public static final String VIDEO_TITLE = "video_title";
        public static final String CAP_NUM_PAGES = "cap_num_pages";
        public static final String CAP_TYPE = "cap_type";
        public static final String CAP_TITLE = "cap_title";
        public static final String TESTIMONIAL_TYPE = "testimonial_type";
        public static final String DOCUMENT_IMAGE = "document_image";
        public static final String DOCUMENT_TITLE = "document_title";
        public static final String DOCUMENT_PATH = "document_path";


        public static final String IS_VIDEO_PLAYING = "is_video_playing";
        public static final String GCM_REG_SERVICE = "gcm_reg_service";
        public static final String GCM_REG_COMPLETE = "gcm_reg_complete";
        //public static final String INTDATKEY_RESEND_TOKEN= "intdatkey_resend_token";
        public static final String UPDATE_NOTIFICATION = "update_notification";
        public static final String LEAD_MOBILE = "lead_mobile";
        public static final String LEAD_NAME = "lead_name";
        public static final String LEAD_EMAIL = "lead_email";
        public static final String IS_ADD_PAGE_SKIP = "is_add_page_skip";
        public static final String VERSION_INFO = "version_info";
    }

    public class PageUrl {
        public static final String ANDROID_ASSET_PREFIX = "file:///android_asset";

        public static final String FEES_URL = "/www/modules/fees/index.html";
        public static final String FEES_CHART_URL = "/www/modules/fees-chart/index.html";

        public static final String ANGEL_REASON_1 = "/www/modules/why-angel-awards/index.html";
        public static final String ANGEL_REASON_2 = "/www/modules/largest-broking-house/index.html";
        public static final String ANGEL_REASON_3 = "/www/modules/pan-india-network/index.html";
        public static final String ANGEL_REASON_4 = "/www/modules/award-winning-research-team/index.html";
        public static final String ANGEL_REASON_5 = "/www/modules/excellent-advisory/index.html";
        public static final String ANGEL_REASON_6 = "/www/modules/personalised-services/index.html";
        public static final String ANGEL_REASON_7 = "/www/modules/margin-funds/index.html";
        public static final String ANGEL_REASON_8 = "/www/modules/trade-on-call/index.html";
        //  public static final String ANGEL_REASON_9 = "/www/modules/software-products/index.html";
        public static final String ANGEL_REASON_9 = "/www/modules/angel-eye/index.html";
        public static final String ANGEL_REASON_10 = "/www/modules/speed-swift/index.html";
        public static final String ANGEL_REASON_11 = "/www/modules/speed-pro/index.html";
        public static final String ANGEL_REASON_12 = "/www/modules/why-angel-summary/index.html";
        public static final String ANGEL_REASON_13 = "/www/modules/pledging-collaterals/index.html";
        public static final String ANGEL_REASON_14 = "/www/modules/round-the-clock/index.html";

        public static final String GET_MORE_1 = "/www/modules/fund-research/index.html";
        public static final String GET_MORE_2 = "/www/modules/benefits-fund-research/index.html";

        public static final String GET_MORE_EXPERT_1 = "/www/modules/time-the-market-wisely/index.html";
        public static final String GET_MORE_EXPERT_2 = "/www/modules/trade-like-pro-table/index.html";

        public static final String MUTUAL_FUNDS_1 = "/www/modules/expert-mgmt-investment/index.html";
        public static final String MUTUAL_FUNDS_2 = "/www/modules/adv-of-sys-investment/index.html";

        public static final String ACCOUNT_OPEN_1 = "/www/modules/account-types/index.html";
        public static final String ACCOUNT_OPEN_2 = "/www/modules/trading-account/index.html";
        public static final String ACCOUNT_OPEN_3 = "/www/modules/demat-account/index.html";
        public static final String ACCOUNT_OPEN_4 = "/www/modules/online-share-trading/index.html";
        public static final String ACCOUNT_OPEN_5 = "/www/modules/account-activation/index.html";
        public static final String ACCOUNT_OPEN_6 = "/www/modules/order-tracking/index.html";

        public static final String INVESTMENT_OPTION_1 = "/www/modules/investment-to-next-level/index.html";
        public static final String INVESTMENT_OPTION_2 = "/www/modules/benifits-of-derivative/index.html";
        public static final String INVESTMENT_OPTION_3 = "/www/modules/benefits-of-currency/index.html";
        public static final String INVESTMENT_OPTION_4 = "/www/modules/benefits-of-commodities/index.html";
        public static final String INVESTMENT_OPTION_5 = "/www/modules/excellent-advisory/index.html";
        public static final String INVESTMENT_OPTION_6 = "/www/modules/personalised-services/index.html";
        public static final String INVESTMENT_OPTION_7 = "/www/modules/margin-funds/index.html";
        public static final String INVESTMENT_OPTION_8 = "/www/modules/trade-on-call/index.html";

        public static final String INVESTMENT_BEYOND_BASICS_1 = "/www/modules/diversify-portfolio/index.html";
        public static final String INVESTMENT_BEYOND_BASICS_2 = "/www/modules/benefit-of-growing-trend/index.html";
        public static final String INVESTMENT_BEYOND_BASICS_3 = "/www/modules/intraday-product/index.html";

        public static final String NEW_INVESTOR_REASON_1 = "/www/modules/equity-investment-safe/index.html";
        public static final String NEW_INVESTOR_REASON_2 = "/www/modules/low-investment/index.html";
        public static final String NEW_INVESTOR_REASON_3 = "/www/modules/high-returns/index.html";
        public static final String NEW_INVESTOR_REASON_4 = "/www/modules/better-than-other-asset-classes/index.html";
        public static final String NEW_INVESTOR_REASON_5 = "/www/modules/increases-wealth/index.html";
        public static final String NEW_INVESTOR_REASON_6 = "/www/modules/beat-inflation/index.html";
        public static final String NEW_INVESTOR_REASON_7 = "/www/modules/high-liquidity/index.html";
        public static final String NEW_INVESTOR_REASON_8 = "/www/modules/excellent-investment/index.html";
        public static final String NEW_INVESTOR_REASON_9 = "/www/modules/why-equity-summary/index.html";

        public static final String EXPERIENCED_INVESTOR_REASON_1 = "/www/modules/invest-beyond-basics/index.html";
        public static final String EXPERIENCED_INVESTOR_REASON_2 = "/www/modules/benifits-of-derivative/index.html";
        public static final String EXPERIENCED_INVESTOR_REASON_3 = "/www/modules/time-the-market-wisely/index.html";
        public static final String EXPERIENCED_INVESTOR_REASON_4 = "/www/modules/benifits-of-technical/index.html";

        public static final String EXPERT_INVESTOR_REASON_1 = "/www/modules/trade-like-pro/index.html";
        public static final String EXPERT_INVESTOR_REASON_2 = "/www/modules/trade-like-pro-table/index.html";

        public static final String BROKERAGE_CALCULATOR = "/www/modules/calculator/index.html";
        public static final String BANK_LIST = "/www/modules/bank-list/index.html";

        public static final String WHAT_ARQ = "/www/modules/what-arq/index.html";
        public static final String BENEFITS_ARQ = "/www/modules/benefits-arq/index.html";
        public static final String PERSONALIZED_ARQ = "/www/modules/personalized-arq/index.html";
        public static final String SENSEX_ARQ = "/www/modules/sensex-arq/index.html";
    }

    public class CapType {
        public static final String CAP_LARGE = "cap_large";
        public static final String CAP_MID = "cap_mid";
        public static final String CAP_MULTI = "cap_multi";
        public static final String FUND_CALLS = "fund_calls";

    }

    public class ClassTypeName {
        public static final String LOGIN_FORM = "login-form";
        public static final String START = "start";
        public static final String WELCOME = "welcome";
        public static final String USER_FORM = "user-form";
        public static final String SYNCHRONISE = "synchronise";
        public static final String CALCULATOR = "calculator";
        public static final String SETTINGS = "settings";
        public static final String SELECT_INVESTOR_TYPE = "select-investor-type";
        //  public static final String NEW_INVESTOR_TOC = "new-invenstor";
        public static final String BENIFITS_OF_DERIVATIVE = "benifits-of-derivative";
        public static final String TIME_THE_MARKET_WISELY = "time-the-market-wisely";
        public static final String BENIFITS_OF_TECHNICAL = "benifits-of-technical";
        public static final String TRADE_LIKE_PRO = "trade-like-pro";
        public static final String TRADE_LIKE_PRO_TABLE = "trade-like-pro-table";
        public static final String WHY_EQUITY_TOC = "why-should-i-invest-in-equity";
        public static final String EQUITY_INVESTMENT_SAFE = "equity-investment-safe";
        public static final String WHY_EQUITY_SUMMARY = "why-equity-summary";

        public static final String CLOSURE_THANK_YOU = "closure-thank-you";
        public static final String CLOSURE_FORM = "closure-form";

        //LATER MODIFIED BY PRAMOD
        public static final String EXPERT_INVESTOR_TOC = "expert-investor-toc";
        public static final String EXPERIENCED_INVESTOR_TOC = "experienced-investor-toc";
        public static final String NEW_INVESTOR_TOC = "new-investor-toc";

        public static final String CONCERN_ON_MIND = "consern-on-my-mind";
        public static final String LOW_INVESTMENT = "requried-low-investment";
        public static final String HIGH_RETURNS = "offers-high-returns";
        public static final String BETTER_THAN_OTHER_ASSET_CLASSES = "better-then-other-asset-classess";
        public static final String INCREASES_WEALTH = "increases-wealth";
        public static final String BEAT_INFLATION = "beats-inflation";
        public static final String HIGH_LIQUIDITY = "can-be-liquidated-easily-";
        public static final String EXCELLENT_INVESTMENT = "an-excellent-investment";

        public static final String MUTUAL_FUNDS_AND_BENEFITS = "what-are-mutual-funds-and-its-benefits";
        public static final String EXPERT_MANAGEMENT_OF_INVESTMENT = "expert-management-of-investment";
        public static final String ADVANTAGE_OF_SYS_INVESTMENT = "advantages-of-systematic-investment";

        public static final String WHY_ANGEL_TOC = "how-is-ab-better-choice";
        public static final String WHY_ANGEL_AWARDS = "most-awarded";
        public static final String LARGEST_BROKING_HOUSE = "fastest-growing";
        public static final String PAN_INDIA_NETWORK = "pan-india-presence";
        public static final String AWARD_WINNING_RESEARCH_TEAM = "expert-research-team";
        public static final String EXCELLENT_ADVISORY = "excellent-advisory";
        public static final String PERSONALISED_SERVICES = "personalized-service";
        public static final String MARGIN_FUNDS = "margin-funds";
        public static final String TRADE_ON_CALL = "trade-on-call";
        public static final String ANGEL_EYE = "angel-eye";
        public static final String ANGEL_SWIFT = "angel-swift";
        public static final String ANGEL_SPEED = "angel-speed";
        public static final String PLEDGING_SERVICES = "facility-for-pledging-collateral";
        public static final String ROUND_THE_CLOCK = "round-the-clock-trading";
        public static final String TESTIMONIALS = "1-million-satisfied-customers";

        public static final String WHY_ANGEL_SUMMARY = "why-angel-summary";
        public static final String ACCOUNT_OPENING_TOC = "account-opening-toc";
        public static final String ACCOUNT_TYPES = "account-types";
        public static final String TRADING_ACCOUNT = "trading-account";
        public static final String DEMAT_ACCOUNT = "demat-account";
        public static final String ONLINE_SHARE_TRADING = "online-share-trading";
        public static final String DOCUMENTS = "documents";
        public static final String ACCOUNT_ACTIVATION = "account-activation";
        public static final String ORDER_TRACKING = "order-tracking";

        public static final String FEES_AND_BROKERAGE_TOC = "fees-and-brokerage-toc";
        public static final String FEES = "fees";
        public static final String FEES_CHART = "calculator-fees"; //"fees-chart";

        public static final String GET_MORE_ADVANTAGE = "how-to-get-more-than-14-5-returns";
        public static final String FUNDAMENTAL_RESEARCH = "experienced-what-is-fundamental-research-analysis";
        public static final String BENEFIT_OF_FUND_RESEARCH = "experienced-how-can-fundamental-research-benefit-me";

        public static final String INVESTMENT_BEYOND_BASICS = "how-do-i-invest-beyond-the-basics";
        public static final String DIVERSIFY_PORTFOLIO = "diversify-your-portfolio";
        public static final String BENEFIT_GROWING_TREND = "benefit-from-the-growing-trend";
        public static final String INTRADAY_PRODUCT = "IntradayProduct";

        public static final String INVESTMENT_OPTIONS = "different-investment-options";
        public static final String BENEFITS_OF_DERIVATIVES = "what-are-the-benefits-of-derivatives";
        public static final String BENEFITS_OF_CURRENCIES = "what-are-the-benefits-of-currency-trading";
        public static final String BENEFITS_OF_COMMODITIES = "what-are-the-benefits-of-commodity-trading";
        public static final String MAXIMIZE_INVEST_REDUCE_RISK = "how-can-i-maximize-investments-yet-reduced-risk";

        public static final String EXPERT_GET_MORE_ADVANTAGE = "expert-investor-how-to-get-more-than-14-5-returns";
        public static final String TIME_MARKET_WISELY = "expert-investor-time-the-market-wisely";
        public static final String LOOK_TREND = "expert-investor-look-at-the-trend-and-grab-the-opportunity";

        public static final String CHOOSE_WISELY = "Choosewisely";
        public static final String ALL_NEED_TO_KNOW = "Allthatyouneedtoknow";

        public static final String ARQ1 = "what-is-arq";
        public static final String ARQ2 = "benefits-of-arq";
        public static final String ARQ3 = "arq-offers-personalized-recommendations";
        public static final String ARQ4 = "arq-bet-senesex";
    }

    public class PrefName {
        public static final String SENT_TOKEN_TO_SERVER = "sent_token_to_server";
        public static final String GCM_TOKEN = "gcm_token";
        public static final String AGENT_ID = "agent_id";
        public static final String FIRST_SYNC_DONE = "first_sync_done";
        public static final String FIRST_SYNC_DONE_FOR_FAQ = "first_sync_done_for_faq";
        public static final String NOTIFICATION_AVOIDED_ATTEMPTS = "notification_avoided_attempts";
        //public static final String AGENTID = "notification_avoided_attempts";
    }
}
