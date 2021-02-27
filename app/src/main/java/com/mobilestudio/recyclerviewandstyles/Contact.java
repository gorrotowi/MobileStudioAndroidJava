package com.mobilestudio.recyclerviewandstyles;

import androidx.annotation.DrawableRes;

public class Contact {
    private int image;
    private String name;

    public Contact(@DrawableRes int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
