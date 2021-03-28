package com.example.animeproject.Models;

public class Anime {

   String image_url;
   String title;
   String desc;
   String url;
   String score;
   String noe;

    public String getNoe() {
        return noe;
    }

    public String getURL() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public String getScore() {
        return score;
    }

    public String getImage_url() {
        return image_url;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "image_url='" + image_url + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", score='" + score + '\'' +
                ", noe='" + noe + '\'' +
                '}';
    }

    public Anime(String score, String url, String desc, String title, String image_url, String noe) {
        this.score = score;
        this.url=url;
        this.desc=desc;
        this.title=title;
        this.image_url=image_url;
        this.noe=noe;
    }



}
