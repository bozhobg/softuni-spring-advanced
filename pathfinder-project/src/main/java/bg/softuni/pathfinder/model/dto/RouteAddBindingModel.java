package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.entity.enums.Level;
import bg.softuni.pathfinder.validation.VideoUrl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteAddBindingModel {
    @Size(min = 3, message = "Name length must be more than 3 characters")
    private String name;
    @Size(min = 5, message = "Description length must be more than 5 characters")
    private String description;
//    TODO: validation
    private String gpxCoordinates;
    @NotNull
    private Level level;
    @VideoUrl
    private String videoUrl;

    private Set<Long> categoryIds;

    public RouteAddBindingModel() {
        categoryIds = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public RouteAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteAddBindingModel setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public RouteAddBindingModel setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public RouteAddBindingModel setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
        return this;
    }
}
