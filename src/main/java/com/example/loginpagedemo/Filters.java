package com.example.loginpagedemo;

public class Filters {
    private FilterController.Duration duration;
    private FilterController.Rating rating;
    private FilterController.Language language;

    public Filters(FilterController.Duration duration, FilterController.Rating rating, FilterController.Language language) {
        this.duration = duration;
        this.rating = rating;
        this.language = language;
    }

    public FilterController.Duration getDuration() {
        return duration;
    }

    public FilterController.Rating getRating() {
        return rating;
    }

    public FilterController.Language getLanguage() {
        return language;
    }
}
