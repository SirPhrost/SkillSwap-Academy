package ca.humber.skillswap.model;

public enum SkillCategory {
    TECHNOLOGY("Technology"),
    CREATIVE_ARTS("Creative Arts"),
    BUSINESS("Business"),
    LANGUAGES("Languages"),
    LIFESTYLE("Lifestyle"),
    FITNESS("Fitness"),
    ACADEMICS("Academics");

    private final String displayName;

    SkillCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
