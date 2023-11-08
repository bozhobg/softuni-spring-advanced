package bg.softuni.pathfinder.model.dto;

public class RouteBasicView {
    private Long id;
    private String name;
    private String description;
    private String pictureUrl;

    public RouteBasicView() {}

    public Long getId() {
        return id;
    }

    public RouteBasicView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteBasicView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteBasicView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RouteBasicView setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
