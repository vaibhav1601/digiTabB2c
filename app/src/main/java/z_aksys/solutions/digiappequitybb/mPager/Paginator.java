package z_aksys.solutions.digiappequitybb.mPager;

import java.util.ArrayList;

public class Paginator {


    public static final int ITEMS_PER_PAGE = 10;
    public static int TOTAL_NUM_ITEMS = 0;
    public static int ITEMS_REMAINING = 0;
    public static int LAST_PAGE = 0;

    public Paginator(int size) {
        this.TOTAL_NUM_ITEMS = size;
    }

    public ArrayList<String> generatePage(int currentPage) {

        ITEMS_REMAINING = TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;

        LAST_PAGE = TOTAL_NUM_ITEMS / ITEMS_PER_PAGE;

        int startItem = currentPage * ITEMS_PER_PAGE + 1;
        int numOfData = ITEMS_PER_PAGE;
        ArrayList<String> pageData = new ArrayList<>();

        if (currentPage == LAST_PAGE && ITEMS_REMAINING > 0) {
            for (int i = startItem; i < startItem + ITEMS_REMAINING; i++) {
                pageData.add("Number " + i);
            }
        } else {
            for (int i = startItem; i < startItem + numOfData; i++) {
                pageData.add("Number " + i);
            }
        }
        return pageData;
    }
}
