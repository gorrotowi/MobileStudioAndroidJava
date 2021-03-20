package com.mobilestudio.android_105_navigation.models;

public class Mail {

    private String title;
    private String subject;

    public Mail(String title, String subject) {
        this.title = title;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
