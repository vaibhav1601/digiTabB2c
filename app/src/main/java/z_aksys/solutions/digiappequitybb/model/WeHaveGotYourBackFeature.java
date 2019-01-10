package z_aksys.solutions.digiappequitybb.model;

import z_aksys.solutions.digiappequitybb.Fragment.WeGotYourBackFragment;

public class WeHaveGotYourBackFeature {
    private String title;
    private int imageId;
    private int categoryId;
    private int slideIndex;

    public WeHaveGotYourBackFeature(String title, int imageId, int categoryId, int slideIndex){
        this.title= title;
        this.imageId= imageId;
        this.categoryId= categoryId;
        this.slideIndex= slideIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSlideIndex() {
        return slideIndex;
    }

    public void setSlideIndex(int slideIndex) {
        this.slideIndex = slideIndex;
    }
}
