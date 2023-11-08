package bg.softuni.pathfinder.model.dto;

import java.util.ArrayList;
import java.util.List;

public class RoutesWrapperView {
    private List<RouteBasicView> routes;

    public RoutesWrapperView() {
        this.routes = new ArrayList<>();
    }

    public List<RouteBasicView> getRoutes() {
        return routes;
    }

    public RoutesWrapperView setRoutes(List<RouteBasicView> routes) {
        this.routes = routes;
        return this;
    }
}
