package z_aksys.solutions.digiappequitybb.Database;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

import z_aksys.solutions.digiappequitybb.Fragment.BestRecommendationFragment;
import z_aksys.solutions.digiappequitybb.Fragment.BetterReturnsFragment;
import z_aksys.solutions.digiappequitybb.Fragment.RevenueModelFragment;
import z_aksys.solutions.digiappequitybb.model.PitchSlide;

public class PitchData {
    public static final int LANG_HINDI = 0;
    public static final int LANG_ENG = 1;
    public static final int LANG_GUJRATI = 2;
    public static final int LANG_TAMIL = 3;
    public static final int LANG_BENGALI = 4;
    public static final int LANG_KANNADA = 5;
    private static PitchData instance;
    public ArrayList<PitchSlide> pitchSlides;
    public HashMap<Integer, String> slideCategories;

    private PitchData() {

        slideCategories = new HashMap<>();
        slideCategories.put(0, "Why do investors choose Mutual Funds?");
        slideCategories.put(1, "Why do investors choose Angel Bee?");
        slideCategories.put(2, "What is growth oppourtunity for Channel Partners?");
        slideCategories.put(3, "Why become a Channel Partner with Angel Bee?");
        slideCategories.put(4, "What is business model?");

        pitchSlides = new ArrayList<>();

        PitchSlide pitchSectionalSlide1 = new PitchSlide();
        pitchSectionalSlide1.id = 15;
        pitchSectionalSlide1.category = 0;
        pitchSectionalSlide1.path = "file:///android_asset/slides/section-1.svg";
        pitchSectionalSlide1.title.put(LANG_ENG, "");
        pitchSectionalSlide1.title.put(LANG_HINDI, "");
        pitchSectionalSlide1.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide1.title.put(LANG_TAMIL, "");
        pitchSectionalSlide1.title.put(LANG_BENGALI, "");
        pitchSectionalSlide1.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide1);

        PitchSlide pitchSlide1 = new PitchSlide();
        pitchSlide1.id = 0;
        pitchSlide1.category = 0;
        pitchSlide1.slideThumbnailPath="slides/thumbnails/mutual-funds-are-pocket-friendly-and-simple.png";
        pitchSlide1.path = "file:///android_asset/slides/1-MUTUAL-FUND-ARE-POCKET-FRIENDLY-AND-SIMPLE.svg";
        pitchSlide1.title.put(LANG_ENG, "Mutual Funds are pocket-friendly and simple");
        pitchSlide1.title.put(LANG_HINDI, "Mutual Funds are pocket-friendly and simple");
        pitchSlide1.title.put(LANG_GUJRATI, "Mutual Funds are pocket-friendly and simple");
        pitchSlide1.title.put(LANG_TAMIL, "Mutual Funds are pocket-friendly and simple");
        pitchSlide1.title.put(LANG_BENGALI, "Mutual Funds are pocket-friendly and simple");
        pitchSlide1.title.put(LANG_KANNADA, "Mutual Funds are pocket-friendly and simple");
        pitchSlides.add(pitchSlide1);

        PitchSlide pitchSlide2 = new PitchSlide();
        pitchSlide2.id = 1;
        pitchSlide2.category = 0;

        Fragment betterReturnsSlide= new BetterReturnsFragment();
        Bundle bundle= new Bundle();
        bundle.putString("slide_url", "file:///android_asset/slides/2-BETTER-RETURNS-COMPARED-TO-OTHER-INVESTMENT.svg");
        betterReturnsSlide.setArguments(bundle);
        pitchSlide2.nativeSlide = betterReturnsSlide;
        pitchSlide2.isNative= true;
        pitchSlide2.slideThumbnailPath="slides/thumbnails/better-returns-compared-to-other-investments.png";
        pitchSlide2.title.put(LANG_ENG, "Better returns compared to other investments");
        pitchSlide2.title.put(LANG_HINDI, "Better returns compared to other investments");
        pitchSlide2.title.put(LANG_GUJRATI, "Better returns compared to other investments");
        pitchSlide2.title.put(LANG_TAMIL, "Better returns compared to other investments");
        pitchSlide2.title.put(LANG_BENGALI, "Better returns compared to other investments");
        pitchSlide2.title.put(LANG_KANNADA, "Better returns compared to other investments");
        pitchSlides.add(pitchSlide2);

        PitchSlide pitchSectionalSlide2 = new PitchSlide();
        pitchSectionalSlide2.id = 16;
        pitchSectionalSlide2.category = 1;
        pitchSectionalSlide2.path = "file:///android_asset/slides/section-2.svg";
        pitchSectionalSlide2.title.put(LANG_ENG, "");
        pitchSectionalSlide2.title.put(LANG_HINDI, "");
        pitchSectionalSlide2.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide2.title.put(LANG_TAMIL, "");
        pitchSectionalSlide2.title.put(LANG_BENGALI, "");
        pitchSectionalSlide2.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide2);

        PitchSlide pitchSlide3 = new PitchSlide();
        pitchSlide3.id = 2;
        pitchSlide3.category = 1;
        pitchSlide3.slideThumbnailPath="slides/thumbnails/angel-the-right-choice.png";
        pitchSlide3.path = "file:///android_asset/slides/3-ANGLE-THE-RIGHT-CHOICE.svg";
        pitchSlide3.title.put(LANG_ENG, "Angel — the right choice");
        pitchSlide3.title.put(LANG_HINDI, "Angel — the right choice");
        pitchSlide3.title.put(LANG_GUJRATI, "Angel — the right choice");
        pitchSlide3.title.put(LANG_TAMIL, "Angel — the right choice");
        pitchSlide3.title.put(LANG_BENGALI, "Angel — the right choice");
        pitchSlide3.title.put(LANG_KANNADA, "Angel — the right choice");
        pitchSlides.add(pitchSlide3);

        PitchSlide pitchSlide4 = new PitchSlide();
        pitchSlide4.id = 3;
        pitchSlide4.category = 1;
        pitchSlide4.isNative= true;
        pitchSlide4.slideThumbnailPath="slides/thumbnails/best-in-industry-recommendations.png";
        pitchSlide4.nativeSlide= new BestRecommendationFragment();
        pitchSlide4.title.put(LANG_ENG, "Best-in-industry recommendations");
        pitchSlide4.title.put(LANG_HINDI, "Best-in-industry recommendations");
        pitchSlide4.title.put(LANG_GUJRATI, "Best-in-industry recommendations");
        pitchSlide4.title.put(LANG_TAMIL, "Best-in-industry recommendations");
        pitchSlide4.title.put(LANG_BENGALI, "Best-in-industry recommendations");
        pitchSlide4.title.put(LANG_KANNADA, "Best-in-industry recommendations");
        pitchSlides.add(pitchSlide4);

        PitchSlide pitchSlide5 = new PitchSlide();
        pitchSlide5.id = 4;
        pitchSlide5.category = 1;
        pitchSlide5.slideThumbnailPath="slides/thumbnails/best-in-class-investing-platforms.png";
        pitchSlide5.path = "file:///android_asset/slides/5-BEST-IN-CLASS-INVESTING-PLATFORMS.svg";
        pitchSlide5.title.put(LANG_ENG, "Best-in-class investing platforms");
        pitchSlide5.title.put(LANG_HINDI, "Best-in-class investing platforms");
        pitchSlide5.title.put(LANG_GUJRATI, "Best-in-class investing platforms");
        pitchSlide5.title.put(LANG_TAMIL, "Best-in-class investing platforms");
        pitchSlide5.title.put(LANG_BENGALI, "Best-in-class investing platforms");
        pitchSlide5.title.put(LANG_KANNADA, "Best-in-class investing platforms");
        pitchSlides.add(pitchSlide5);

        PitchSlide pitchSectionalSlide3 = new PitchSlide();
        pitchSectionalSlide3.id = 17;
        pitchSectionalSlide3.category = 2;
        pitchSectionalSlide3.path = "file:///android_asset/slides/section-3.svg";
        pitchSectionalSlide3.title.put(LANG_ENG, "");
        pitchSectionalSlide3.title.put(LANG_HINDI, "");
        pitchSectionalSlide3.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide3.title.put(LANG_TAMIL, "");
        pitchSectionalSlide3.title.put(LANG_BENGALI, "");
        pitchSectionalSlide3.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide3);

        PitchSlide pitchSlide6 = new PitchSlide();
        pitchSlide6.id = 5;
        pitchSlide6.category = 2;
        pitchSlide6.slideThumbnailPath="slides/thumbnails/per-capita-income-to-touch-5-lakh-by-2030.png";
        pitchSlide6.path = "file:///android_asset/slides/6-PER-CAPITA-INCOME-TO-TOUCH-5-LAKH-BY-2030.svg";
        pitchSlide6.title.put(LANG_ENG, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide6.title.put(LANG_HINDI, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide6.title.put(LANG_GUJRATI, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide6.title.put(LANG_TAMIL, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide6.title.put(LANG_BENGALI, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide6.title.put(LANG_KANNADA, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlides.add(pitchSlide6);
        
        

        PitchSlide pitchSlide7 = new PitchSlide();
        pitchSlide7.id = 6;
        pitchSlide7.category = 2;
        pitchSlide7.slideThumbnailPath="slides/thumbnails/investment-in-mutual-funds-is-on-the-rise.png";
        pitchSlide7.path = "file:///android_asset/slides/7-INVESTMENT-IN-MUTUAL-FUND-IS-ON-RISE.svg";
        pitchSlide7.title.put(LANG_ENG, "Investment in Mutual Funds is on the rise");
        pitchSlide7.title.put(LANG_HINDI, "Investment in Mutual Funds is on the rise");
        pitchSlide7.title.put(LANG_GUJRATI, "Investment in Mutual Funds is on the rise");
        pitchSlide7.title.put(LANG_TAMIL, "Investment in Mutual Funds is on the rise");
        pitchSlide7.title.put(LANG_BENGALI, "Investment in Mutual Funds is on the rise");
        pitchSlide7.title.put(LANG_KANNADA, "Investment in Mutual Funds is on the rise");
        pitchSlides.add(pitchSlide7);

        PitchSlide pitchSlide8 = new PitchSlide();
        pitchSlide8.id = 7;
        pitchSlide8.category = 2;
        pitchSlide8.slideThumbnailPath="slides/thumbnails/increased-opportunities-for-channel-partners.png";
        pitchSlide8.path = "file:///android_asset/slides/8-INCREASED-OPPOURTUNITIES-FOR-CHANNEL-PARTNERS.svg";
        pitchSlide8.title.put(LANG_ENG, "Increased opportunities for Channel Partners");
        pitchSlide8.title.put(LANG_HINDI, "Increased opportunities for Channel Partners");
        pitchSlide8.title.put(LANG_GUJRATI, "Increased opportunities for Channel Partners");
        pitchSlide8.title.put(LANG_TAMIL, "Increased opportunities for Channel Partners");
        pitchSlide8.title.put(LANG_BENGALI, "Increased opportunities for Channel Partners");
        pitchSlide8.title.put(LANG_KANNADA, "Increased opportunities for Channel Partners");
        pitchSlides.add(pitchSlide8);

        PitchSlide pitchSectionalSlide4 = new PitchSlide();
        pitchSectionalSlide4.id = 18;
        pitchSectionalSlide4.category = 3;
        pitchSectionalSlide4.path = "file:///android_asset/slides/section-4.svg";
        pitchSectionalSlide4.title.put(LANG_ENG, "");
        pitchSectionalSlide4.title.put(LANG_HINDI, "");
        pitchSectionalSlide4.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide4.title.put(LANG_TAMIL, "");
        pitchSectionalSlide4.title.put(LANG_BENGALI, "");
        pitchSectionalSlide4.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide4);

        PitchSlide pitchSlide9 = new PitchSlide();
        pitchSlide9.id = 8;
        pitchSlide9.category = 3;
        pitchSlide9.slideThumbnailPath="slides/thumbnails/largest-channel-partner-network.png";
        pitchSlide9.path = "file:///android_asset/slides/9-LARGEST-CHANNEL-PARTNER-NETWORK.svg";
        pitchSlide9.title.put(LANG_ENG, "Largest Channel Partner Network");
        pitchSlide9.title.put(LANG_HINDI, "Largest Channel Partner Network");
        pitchSlide9.title.put(LANG_GUJRATI, "Largest Channel Partner Network");
        pitchSlide9.title.put(LANG_TAMIL, "Largest Channel Partner Network");
        pitchSlide9.title.put(LANG_BENGALI, "Largest Channel Partner Network");
        pitchSlide9.title.put(LANG_KANNADA, "Largest Channel Partner Network");
        pitchSlides.add(pitchSlide9);

        PitchSlide pitchSlide10 = new PitchSlide();
        pitchSlide10.id = 9;
        pitchSlide10.category = 3;
        pitchSlide10.slideThumbnailPath="slides/thumbnails/work-with-the-market-leaders.png";
        pitchSlide10.path = "file:///android_asset/slides/10-WORK-WITH-THE-MARKET-LEADERS.svg";
        pitchSlide10.title.put(LANG_ENG, "Work with the market leaders");
        pitchSlide10.title.put(LANG_HINDI, "Work with the market leaders");
        pitchSlide10.title.put(LANG_GUJRATI, "Work with the market leaders");
        pitchSlide10.title.put(LANG_TAMIL, "Work with the market leaders");
        pitchSlide10.title.put(LANG_BENGALI, "Work with the market leaders");
        pitchSlide10.title.put(LANG_KANNADA, "Work with the market leaders");
        pitchSlides.add(pitchSlide10);

        PitchSlide pitchSlide11 = new PitchSlide();
        pitchSlide11.id = 10;
        pitchSlide11.category = 3;
        pitchSlide11.slideThumbnailPath="slides/thumbnails/end-to-end-solutions.png";
        pitchSlide11.path = "file:///android_asset/slides/11-END-TO-END-SOLUTIONS.svg";
        pitchSlide11.title.put(LANG_ENG, "End-to-end Solutions");
        pitchSlide11.title.put(LANG_HINDI, "End-to-end Solutions");
        pitchSlide11.title.put(LANG_GUJRATI, "End-to-end Solutions");
        pitchSlide11.title.put(LANG_TAMIL, "End-to-end Solutions");
        pitchSlide11.title.put(LANG_BENGALI, "End-to-end Solutions");
        pitchSlide11.title.put(LANG_KANNADA, "End-to-end Solutions");
        pitchSlides.add(pitchSlide11);

        PitchSlide pitchSlide12 = new PitchSlide();
        pitchSlide12.id = 11;
        pitchSlide12.category = 3;
        pitchSlide12.slideThumbnailPath="slides/thumbnails/bee-nxt-best-in-class-mf-servicing-platform-for-you.png";
        pitchSlide12.path = "file:///android_asset/slides/12-BEE-NXT-BEST-IN-CLASS-MF-SERVICING-PLATFORM-FOR-YOU.svg";
        pitchSlide12.title.put(LANG_ENG, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide12.title.put(LANG_HINDI, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide12.title.put(LANG_GUJRATI, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide12.title.put(LANG_TAMIL, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide12.title.put(LANG_BENGALI, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide12.title.put(LANG_KANNADA, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlides.add(pitchSlide12);

        PitchSlide pitchSlide13 = new PitchSlide();
        pitchSlide13.id = 12;
        pitchSlide13.category = 3;
        pitchSlide13.slideThumbnailPath="slides/thumbnails/one-app-multiple-features.png";
        pitchSlide13.path = "file:///android_asset/slides/13-ONE-APP-MULTIPLE-FEATURES.svg";
        pitchSlide13.title.put(LANG_ENG, "One app, multiple features");
        pitchSlide13.title.put(LANG_HINDI, "One app, multiple features");
        pitchSlide13.title.put(LANG_GUJRATI, "One app, multiple features");
        pitchSlide13.title.put(LANG_TAMIL, "One app, multiple features");
        pitchSlide13.title.put(LANG_BENGALI, "One app, multiple features");
        pitchSlide13.title.put(LANG_KANNADA, "One app, multiple features");
        pitchSlides.add(pitchSlide13);

        PitchSlide pitchSlide14 = new PitchSlide();
        pitchSlide14.id = 13;
        pitchSlide14.category = 3;
        pitchSlide14.slideThumbnailPath="slides/thumbnails/most-awarded-indian-stock-broking-house.png";
        pitchSlide14.path = "file:///android_asset/slides/14-MOST-AWARDED-INDIAN-STOCK-BROKING-HOUSE.svg";
        pitchSlide14.title.put(LANG_ENG, "Most Awarded Indian Stock Broking House");
        pitchSlide14.title.put(LANG_HINDI, "Most Awarded Indian Stock Broking House");
        pitchSlide14.title.put(LANG_GUJRATI, "Most Awarded Indian Stock Broking House");
        pitchSlide14.title.put(LANG_TAMIL, "Most Awarded Indian Stock Broking House");
        pitchSlide14.title.put(LANG_BENGALI, "Most Awarded Indian Stock Broking House");
        pitchSlide14.title.put(LANG_KANNADA, "Most Awarded Indian Stock Broking House");
        pitchSlides.add(pitchSlide14);

        PitchSlide pitchSectionalSlide5 = new PitchSlide();
        pitchSectionalSlide5.id = 19;
        pitchSectionalSlide5.category = 4;
        pitchSectionalSlide5.path = "file:///android_asset/slides/section-5.svg";
        pitchSectionalSlide5.title.put(LANG_ENG, "");
        pitchSectionalSlide5.title.put(LANG_HINDI, "");
        pitchSectionalSlide5.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide5.title.put(LANG_TAMIL, "");
        pitchSectionalSlide5.title.put(LANG_BENGALI, "");
        pitchSectionalSlide5.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide5);
        
        PitchSlide pitchSlide15 = new PitchSlide();
        pitchSlide15.id = 14;
        pitchSlide15.category = 4;
        pitchSlide15.isNative= true;
        pitchSlide15.slideThumbnailPath="slides/thumbnails/revenue-model.png";
        pitchSlide15.nativeSlide= new RevenueModelFragment();
        pitchSlide15.title.put(LANG_ENG, "Revenue Model");
        pitchSlide15.title.put(LANG_HINDI, "Revenue Model");
        pitchSlide15.title.put(LANG_GUJRATI, "Revenue Model");
        pitchSlide15.title.put(LANG_TAMIL, "Revenue Model");
        pitchSlide15.title.put(LANG_BENGALI, "Revenue Model");
        pitchSlide15.title.put(LANG_KANNADA, "Revenue Model");
        pitchSlides.add(pitchSlide15);

        PitchSlide pitchSectionalSlide6 = new PitchSlide();
        pitchSectionalSlide6.id = 20;
        pitchSectionalSlide6.category = -1;
        pitchSectionalSlide6.path = "file:///android_asset/slides/section-6.svg";
        pitchSectionalSlide6.title.put(LANG_ENG, "");
        pitchSectionalSlide6.title.put(LANG_HINDI, "");
        pitchSectionalSlide6.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide6.title.put(LANG_TAMIL, "");
        pitchSectionalSlide6.title.put(LANG_BENGALI, "");
        pitchSectionalSlide6.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide6);
    }

    public static PitchData getInstance() {
        if (instance == null) {
            instance = new PitchData();
        }

        return instance;
    }
}
