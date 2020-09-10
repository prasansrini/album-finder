package com.prasanna.projectalbum.model;

/**
 * Model class holding the Album information.
 */
public class AlbumData {
    private String mTitle;
    private String mUrl;
    private String mThumbnailUrl;

    /**
     * Method to fetch the title.
     *
     * @return String title.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Method to set the title.
     *
     * @param mTitle String title.
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Method to set the URL.
     *
     * @param mUrl String URL.
     */
    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    /**
     * Method to fetch the ThumbnailURL.
     *
     * @return String thumbnailUrl.
     */
    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    /**
     * Method to set the ThumbnailUrl
     *
     * @param mThumbnailUrl String thumbnailUrl.
     */
    public void setThumbnailUrl(String mThumbnailUrl) {
        this.mThumbnailUrl = mThumbnailUrl;
    }
}
