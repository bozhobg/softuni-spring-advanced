package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.entity.enums.CategoryName;
import bg.softuni.pathfinder.model.entity.enums.Level;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RouteDetailsView {
    private Long id;
    private String name;
    private Integer levelOrdinal;
    private String description;
    private String gpxCoordinates;
    private String videoUrl;
    private String authorUsername;
    private Set<CategoryName> categoryNames;
    private List<String> imageUrls;

    public RouteDetailsView() {
        this.categoryNames = new LinkedHashSet<>();
        this.imageUrls = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public RouteDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLevelOrdinal() {
        return levelOrdinal;
    }

    public RouteDetailsView setLevelOrdinal(Integer levelOrdinal) {
        this.levelOrdinal = levelOrdinal;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteDetailsView setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsView setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public RouteDetailsView setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

    public Set<CategoryName> getCategoryNames() {
        return categoryNames;
    }

    public RouteDetailsView setCategoryNames(Set<CategoryName> categoryNames) {
        this.categoryNames = categoryNames;
        return this;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public RouteDetailsView setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }
}
