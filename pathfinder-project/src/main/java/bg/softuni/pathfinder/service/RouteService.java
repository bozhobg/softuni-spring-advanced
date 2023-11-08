package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.RouteDetailsView;
import bg.softuni.pathfinder.model.dto.RoutesWrapperView;

import java.util.Optional;

public interface RouteService {

    boolean routeExists(Long routeId);

    RoutesWrapperView getRoutesWrapper();

    RouteDetailsView getRouteDetailsView(Long routeId);
}
