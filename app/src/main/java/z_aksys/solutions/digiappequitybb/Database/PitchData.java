package z_aksys.solutions.digiappequitybb.Database;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

import z_aksys.solutions.digiappequitybb.Fragment.AdvanceTradingPlatformFragment;
import z_aksys.solutions.digiappequitybb.Fragment.BestRecommendationFragment;
import z_aksys.solutions.digiappequitybb.Fragment.HighestReturnsFragment;
import z_aksys.solutions.digiappequitybb.Fragment.InvestmentCalculatorFragment;
import z_aksys.solutions.digiappequitybb.Fragment.RecommendationsForWinningFragment;
import z_aksys.solutions.digiappequitybb.Fragment.RevenueModelFragment;
import z_aksys.solutions.digiappequitybb.Fragment.WeGotYourBackFragment;
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
        slideCategories.put(0, "Why do investors choose Equity?");
        slideCategories.put(1, "Why do investors choose Angel Broking?");
        slideCategories.put(2, "What is growth oppourtunity for Channel Partners?");
        slideCategories.put(3, "Why become a Channel Partner with Angel Broking?");
        slideCategories.put(4, "What is Angle Broking's business model?");

        pitchSlides = new ArrayList<>();

        PitchSlide pitchSectionalSlide1 = new PitchSlide();
        pitchSectionalSlide1.id = 17;
        pitchSectionalSlide1.category = 0;
        pitchSectionalSlide1.slideThumbnailPath="slides/thumbnails/section1.png";
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
        pitchSlide1.slideThumbnailPath="slides/thumbnails/lower-investment-than-other-channels.png";
        pitchSlide1.path = "file:///android_asset/slides/lower-investment-than-other-channels.svg";
        pitchSlide1.title.put(LANG_ENG, "Lower investment than other channels");
        pitchSlide1.title.put(LANG_HINDI, "Lower investment than other channels");
        pitchSlide1.title.put(LANG_GUJRATI, "Lower investment than other channels");
        pitchSlide1.title.put(LANG_TAMIL, "Lower investment than other channels");
        pitchSlide1.title.put(LANG_BENGALI, "Lower investment than other channels");
        pitchSlide1.title.put(LANG_KANNADA, "Lower investment than other channels");
        pitchSlides.add(pitchSlide1);

        PitchSlide pitchSlide2 = new PitchSlide();
        pitchSlide2.id = 1;
        pitchSlide2.category = 0;

        Fragment highestReturnsFragment= new HighestReturnsFragment();
        Bundle bundle= new Bundle();
        bundle.putString("slide_url", "file:///android_asset/slides/highest-returns-compared-to-other-investments.svg");
        highestReturnsFragment.setArguments(bundle);
        pitchSlide2.nativeSlide = highestReturnsFragment;
        pitchSlide2.isNative= true;
        pitchSlide2.slideThumbnailPath="slides/thumbnails/highest-returns-compared-to-other-investments.png";
        pitchSlide2.title.put(LANG_ENG, "Highest returns compared to other investments");
        pitchSlide2.title.put(LANG_HINDI, "Highest returns compared to other investments");
        pitchSlide2.title.put(LANG_GUJRATI, "Highest returns compared to other investments");
        pitchSlide2.title.put(LANG_TAMIL, "Highest returns compared to other investments");
        pitchSlide2.title.put(LANG_BENGALI, "Highest returns compared to other investments");
        pitchSlide2.title.put(LANG_KANNADA, "Highest returns compared to other investments");
        pitchSlides.add(pitchSlide2);

        PitchSlide pitchSlide3 = new PitchSlide();
        pitchSlide3.id = 2;
        pitchSlide3.category = 0;
        pitchSlide3.slideThumbnailPath="slides/thumbnails/earn-more-than-just-compound-interest.png";
        pitchSlide3.path = "file:///android_asset/slides/earn-more-than-just-compound-interest.svg";
        pitchSlide3.title.put(LANG_ENG, "Earn more than just compound interest");
        pitchSlide3.title.put(LANG_HINDI, "Earn more than just compound interest");
        pitchSlide3.title.put(LANG_GUJRATI, "Earn more than just compound interest");
        pitchSlide3.title.put(LANG_TAMIL, "Earn more than just compound interest");
        pitchSlide3.title.put(LANG_BENGALI, "Earn more than just compound interest");
        pitchSlide3.title.put(LANG_KANNADA, "Earn more than just compound interest");
        pitchSlides.add(pitchSlide3);

        PitchSlide pitchSlide4 = new PitchSlide();
        pitchSlide4.id = 3;
        pitchSlide4.category = 0;
        pitchSlide4.slideThumbnailPath="slides/thumbnails/equity-offers-high-liquidity.png";
        pitchSlide4.path="file:///android_asset/slides/equity-offers-high-liquidity.svg";
        pitchSlide4.title.put(LANG_ENG, "Equity offers high liquidity");
        pitchSlide4.title.put(LANG_HINDI, "Equity offers high liquidity");
        pitchSlide4.title.put(LANG_GUJRATI, "Equity offers high liquidity");
        pitchSlide4.title.put(LANG_TAMIL, "Equity offers high liquidity");
        pitchSlide4.title.put(LANG_BENGALI, "Equity offers high liquidity");
        pitchSlide4.title.put(LANG_KANNADA, "Equity offers high liquidity");
        pitchSlides.add(pitchSlide4);

        PitchSlide pitchSectionalSlide2 = new PitchSlide();
        pitchSectionalSlide2.id = 18;
        pitchSectionalSlide2.category = 1;
        pitchSectionalSlide2.slideThumbnailPath="slides/thumbnails/section2.png";
        pitchSectionalSlide2.path = "file:///android_asset/slides/section-2.svg";
        pitchSectionalSlide2.title.put(LANG_ENG, "");
        pitchSectionalSlide2.title.put(LANG_HINDI, "");
        pitchSectionalSlide2.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide2.title.put(LANG_TAMIL, "");
        pitchSectionalSlide2.title.put(LANG_BENGALI, "");
        pitchSectionalSlide2.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide2);

        PitchSlide pitchSlide5 = new PitchSlide();
        pitchSlide5.id = 4;
        pitchSlide5.category = 1;
        pitchSlide5.slideThumbnailPath="slides/thumbnails/largest-stock-broking-house-in-india.png";
        pitchSlide5.path = "file:///android_asset/slides/largest-stock-broking-house-in-india.svg";
        pitchSlide5.title.put(LANG_ENG, "Largest stock broking house in India");
        pitchSlide5.title.put(LANG_HINDI, "Largest stock broking house in India");
        pitchSlide5.title.put(LANG_GUJRATI, "Largest stock broking house in India");
        pitchSlide5.title.put(LANG_TAMIL, "Largest stock broking house in India");
        pitchSlide5.title.put(LANG_BENGALI, "Largest stock broking house in India");
        pitchSlide5.title.put(LANG_KANNADA, "Largest stock broking house in India");
        pitchSlides.add(pitchSlide5);

        PitchSlide pitchSlide6 = new PitchSlide();
        pitchSlide6.id = 5;
        pitchSlide6.category = 1;
        Fragment recommendationsForWinningFragment= new RecommendationsForWinningFragment();
        Bundle bundle1= new Bundle();
        bundle1.putString("slide_url", "file:///android_asset/slides/recommendations-for-winning-portfolio.svg");
        recommendationsForWinningFragment.setArguments(bundle1);
        pitchSlide6.nativeSlide = recommendationsForWinningFragment;
        pitchSlide6.isNative= true;
        pitchSlide6.slideThumbnailPath="slides/thumbnails/recommendations-for-a-winning-portfolio.png";
        pitchSlide6.title.put(LANG_ENG, "Recommendations for a winning portfolio");
        pitchSlide6.title.put(LANG_HINDI, "Recommendations for a winning portfolio");
        pitchSlide6.title.put(LANG_GUJRATI, "Recommendations for a winning portfolio");
        pitchSlide6.title.put(LANG_TAMIL, "Recommendations for a winning portfolio");
        pitchSlide6.title.put(LANG_BENGALI, "Recommendations for a winning portfolio");
        pitchSlide6.title.put(LANG_KANNADA, "Recommendations for a winning portfolio");
        pitchSlides.add(pitchSlide6);

        PitchSlide pitchSlide7 = new PitchSlide();
        pitchSlide7.id = 6;
        pitchSlide7.category = 1;
        pitchSlide7.slideThumbnailPath="slides/thumbnails/single-account-for-investing-in-multiple-channels.png";
        pitchSlide7.path = "file:///android_asset/slides/single-account-for-investing-in-multiple-channels.svg";
        pitchSlide7.title.put(LANG_ENG, "Single account for investing in multiple channels");
        pitchSlide7.title.put(LANG_HINDI, "Single account for investing in multiple channels");
        pitchSlide7.title.put(LANG_GUJRATI, "Single account for investing in multiple channels");
        pitchSlide7.title.put(LANG_TAMIL, "Single account for investing in multiple channels");
        pitchSlide7.title.put(LANG_BENGALI, "Single account for investing in multiple channels");
        pitchSlide7.title.put(LANG_KANNADA, "Single account for investing in multiple channels");
        pitchSlides.add(pitchSlide7);

        PitchSlide pitchSlide8 = new PitchSlide();
        pitchSlide8.id = 7;
        pitchSlide8.category = 1;
        Fragment advanceTradingPlatformFragment= new AdvanceTradingPlatformFragment();
        Bundle bundle2= new Bundle();
        bundle2.putString("slide_url", "file:///android_asset/slides/advance-and-user-friendly-trading-platforms.svg");
        advanceTradingPlatformFragment.setArguments(bundle2);
        pitchSlide8.nativeSlide = advanceTradingPlatformFragment;
        pitchSlide8.isNative= true;
        pitchSlide8.slideThumbnailPath="slides/thumbnails/advanced-and-user-friendly-trading-platforms.png";
        pitchSlide8.title.put(LANG_ENG, "Advanced and user-friendly trading platforms");
        pitchSlide8.title.put(LANG_HINDI, "Advanced and user-friendly trading platforms");
        pitchSlide8.title.put(LANG_GUJRATI, "Advanced and user-friendly trading platforms");
        pitchSlide8.title.put(LANG_TAMIL, "Advanced and user-friendly trading platforms");
        pitchSlide8.title.put(LANG_BENGALI, "Advanced and user-friendly trading platforms");
        pitchSlide8.title.put(LANG_KANNADA, "Advanced and user-friendly trading platforms");
        pitchSlides.add(pitchSlide8);

        PitchSlide pitchSlide9 = new PitchSlide();
        pitchSlide9.id = 8;
        pitchSlide9.category = 1;
        pitchSlide9.slideThumbnailPath="slides/thumbnails/awards-and-recognitions.png";
        pitchSlide9.path = "file:///android_asset/slides/awards-and-recognitions.svg";
        pitchSlide9.title.put(LANG_ENG, "Awards and recognitions");
        pitchSlide9.title.put(LANG_HINDI, "Awards and recognitions");
        pitchSlide9.title.put(LANG_GUJRATI, "Awards and recognitions");
        pitchSlide9.title.put(LANG_TAMIL, "Awards and recognitions");
        pitchSlide9.title.put(LANG_BENGALI, "Awards and recognitions");
        pitchSlide9.title.put(LANG_KANNADA, "Awards and recognitions");
        pitchSlides.add(pitchSlide9);

        PitchSlide pitchSectionalSlide3 = new PitchSlide();
        pitchSectionalSlide3.id = 19;
        pitchSectionalSlide3.category = 2;
        pitchSectionalSlide3.slideThumbnailPath="slides/thumbnails/section3.png";
        pitchSectionalSlide3.path = "file:///android_asset/slides/section-3.svg";
        pitchSectionalSlide3.title.put(LANG_ENG, "");
        pitchSectionalSlide3.title.put(LANG_HINDI, "");
        pitchSectionalSlide3.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide3.title.put(LANG_TAMIL, "");
        pitchSectionalSlide3.title.put(LANG_BENGALI, "");
        pitchSectionalSlide3.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide3);

        PitchSlide pitchSlide10 = new PitchSlide();
        pitchSlide10.id = 9;
        pitchSlide10.category = 2;
        pitchSlide10.slideThumbnailPath="slides/thumbnails/per-capita-income-to-touch-5-lakh-by-2030.png";
        pitchSlide10.path = "file:///android_asset/slides/per-capita-income-to-touch-$5-lakh-by-2030.svg";
        pitchSlide10.title.put(LANG_ENG, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide10.title.put(LANG_HINDI, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide10.title.put(LANG_GUJRATI, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide10.title.put(LANG_TAMIL, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide10.title.put(LANG_BENGALI, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlide10.title.put(LANG_KANNADA, "Per capita income to touch \u20B95.0 Lakh by 2030");
        pitchSlides.add(pitchSlide10);

        PitchSlide pitchSlide11 = new PitchSlide();
        pitchSlide11.id = 10;
        pitchSlide11.category = 2;
        pitchSlide11.slideThumbnailPath="slides/thumbnails/investment-in-equity-is-on-the-rise.png";
        pitchSlide11.path = "file:///android_asset/slides/investment-on-equity-is-on-rise.svg";
        pitchSlide11.title.put(LANG_ENG, "Investment in Equity is on the rise");
        pitchSlide11.title.put(LANG_HINDI, "Investment in Equity is on the rise");
        pitchSlide11.title.put(LANG_GUJRATI, "Investment in Equity is on the rise");
        pitchSlide11.title.put(LANG_TAMIL, "Investment in Equity is on the rise");
        pitchSlide11.title.put(LANG_BENGALI, "Investment in Equity is on the rise");
        pitchSlide11.title.put(LANG_KANNADA, "Investment in Equity is on the rise");
        pitchSlides.add(pitchSlide11);

        PitchSlide pitchSlide12 = new PitchSlide();
        pitchSlide12.id = 11;
        pitchSlide12.category = 2;
        pitchSlide12.slideThumbnailPath="slides/thumbnails/growing-need-for-channel-partners.png";
        pitchSlide12.path = "file:///android_asset/slides/growing-need-for-channel-partners.svg";
        pitchSlide12.title.put(LANG_ENG, "Growing need for Channel Partners");
        pitchSlide12.title.put(LANG_HINDI, "Growing need for Channel Partners");
        pitchSlide12.title.put(LANG_GUJRATI, "Growing need for Channel Partners");
        pitchSlide12.title.put(LANG_TAMIL, "Growing need for Channel Partners");
        pitchSlide12.title.put(LANG_BENGALI, "Growing need for Channel Partners");
        pitchSlide12.title.put(LANG_KANNADA, "Growing need for Channel Partners");
        pitchSlides.add(pitchSlide12);

        PitchSlide pitchSectionalSlide4 = new PitchSlide();
        pitchSectionalSlide4.id = 20;
        pitchSectionalSlide4.category = 3;
        pitchSectionalSlide4.slideThumbnailPath="slides/thumbnails/section4.png";
        pitchSectionalSlide4.path = "file:///android_asset/slides/section-4.svg";
        pitchSectionalSlide4.title.put(LANG_ENG, "");
        pitchSectionalSlide4.title.put(LANG_HINDI, "");
        pitchSectionalSlide4.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide4.title.put(LANG_TAMIL, "");
        pitchSectionalSlide4.title.put(LANG_BENGALI, "");
        pitchSectionalSlide4.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide4);

        PitchSlide pitchSlide13 = new PitchSlide();
        pitchSlide13.id = 12;
        pitchSlide13.category = 3;
        pitchSlide13.slideThumbnailPath="slides/thumbnails/largest-channel-network-partner.png";
        pitchSlide13.path = "file:///android_asset/slides/largest-channel-partner-network.svg";
        pitchSlide13.title.put(LANG_ENG, "Largest Channel Network Partner");
        pitchSlide13.title.put(LANG_HINDI, "Largest Channel Network Partner");
        pitchSlide13.title.put(LANG_GUJRATI, "Largest Channel Network Partner");
        pitchSlide13.title.put(LANG_TAMIL, "Largest Channel Network Partner");
        pitchSlide13.title.put(LANG_BENGALI, "Largest Channel Network Partner");
        pitchSlide13.title.put(LANG_KANNADA, "Largest Channel Network Partner");
        pitchSlides.add(pitchSlide13);

        PitchSlide pitchSlide14 = new PitchSlide();
        pitchSlide14.id = 13;
        pitchSlide14.category = 3;
        pitchSlide14.isNative= true;
        pitchSlide14.nativeSlide= new WeGotYourBackFragment();
        pitchSlide14.slideThumbnailPath="slides/thumbnails/we-have-got-your-back.png";
        //pitchSlide14.path = "file:///android_asset/slides/11-END-TO-END-SOLUTIONS.svg";
        pitchSlide14.title.put(LANG_ENG, "We have got your back");
        pitchSlide14.title.put(LANG_HINDI, "We have got your back");
        pitchSlide14.title.put(LANG_GUJRATI, "We have got your back");
        pitchSlide14.title.put(LANG_TAMIL, "We have got your back");
        pitchSlide14.title.put(LANG_BENGALI, "We have got your back");
        pitchSlide14.title.put(LANG_KANNADA, "We have got your back");
        pitchSlides.add(pitchSlide14);

        PitchSlide pitchSlide15 = new PitchSlide();
        pitchSlide15.id = 14;
        pitchSlide15.category = 3;
        pitchSlide15.slideThumbnailPath="slides/thumbnails/bee-nxt-best-in-class-mf-servicing-platform-for-you.png";
        pitchSlide15.path = "file:///android_asset/slides/bee-nxt-best-in-class-mf-servicing-platform-for-you.svg";
        pitchSlide15.title.put(LANG_ENG, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide15.title.put(LANG_HINDI, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide15.title.put(LANG_GUJRATI, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide15.title.put(LANG_TAMIL, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide15.title.put(LANG_BENGALI, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlide15.title.put(LANG_KANNADA, "BEE NXT - Best in class MF servicing platform for you");
        pitchSlides.add(pitchSlide15);

        PitchSlide pitchSectionalSlide5 = new PitchSlide();
        pitchSectionalSlide5.id = 21;
        pitchSectionalSlide5.category = 4;
        pitchSectionalSlide5.slideThumbnailPath="slides/thumbnails/section5.png";
        pitchSectionalSlide5.path = "file:///android_asset/slides/section-5.svg";
        pitchSectionalSlide5.title.put(LANG_ENG, "");
        pitchSectionalSlide5.title.put(LANG_HINDI, "");
        pitchSectionalSlide5.title.put(LANG_GUJRATI, "");
        pitchSectionalSlide5.title.put(LANG_TAMIL, "");
        pitchSectionalSlide5.title.put(LANG_BENGALI, "");
        pitchSectionalSlide5.title.put(LANG_KANNADA, "");
        pitchSlides.add(pitchSectionalSlide5);

        PitchSlide pitchSlide16 = new PitchSlide();
        pitchSlide16.id = 15;
        pitchSlide16.category = 4;
        pitchSlide16.isNative= true;
        pitchSlide16.nativeSlide= new InvestmentCalculatorFragment();
        pitchSlide16.slideThumbnailPath="slides/thumbnails/one-time-registration-fees.png";
        pitchSlide16.path = "file:///android_asset/slides/13-ONE-APP-MULTIPLE-FEATURES.svg";
        pitchSlide16.title.put(LANG_ENG, "One-time registration fees");
        pitchSlide16.title.put(LANG_HINDI, "One-time registration fees");
        pitchSlide16.title.put(LANG_GUJRATI, "One-time registration fees");
        pitchSlide16.title.put(LANG_TAMIL, "One-time registration fees");
        pitchSlide16.title.put(LANG_BENGALI, "One-time registration fees");
        pitchSlide16.title.put(LANG_KANNADA, "One-time registration fees");
        pitchSlides.add(pitchSlide16);

        PitchSlide pitchSlide17 = new PitchSlide();
        pitchSlide17.id = 16;
        pitchSlide17.category = 4;
        pitchSlide17.isNative= true;
        pitchSlide17.nativeSlide= new RevenueModelFragment();
        pitchSlide17.slideThumbnailPath="slides/thumbnails/revenue-model.png";
        //pitchSlide17.path = "file:///android_asset/slides/13-ONE-APP-MULTIPLE-FEATURES.svg";
        pitchSlide17.title.put(LANG_ENG, "Revenue Model");
        pitchSlide17.title.put(LANG_HINDI, "Revenue Model");
        pitchSlide17.title.put(LANG_GUJRATI, "Revenue Model");
        pitchSlide17.title.put(LANG_TAMIL, "Revenue Model");
        pitchSlide17.title.put(LANG_BENGALI, "Revenue Model");
        pitchSlide17.title.put(LANG_KANNADA, "Revenue Model");
        pitchSlides.add(pitchSlide17);

        PitchSlide pitchSectionalSlide6 = new PitchSlide();
        pitchSectionalSlide6.id = 22;
        pitchSectionalSlide6.category = -1;
        pitchSectionalSlide6.slideThumbnailPath="slides/thumbnails/section6.png";
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
