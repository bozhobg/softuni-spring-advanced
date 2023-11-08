package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.dto.RouteBasicView;
import bg.softuni.pathfinder.model.dto.RouteDetailsView;
import bg.softuni.pathfinder.model.dto.RoutesWrapperView;
import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.repository.PictureRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RouteServiceImpl(
            RouteRepository routeRepository,
            PictureRepository pictureRepository,
            ModelMapper modelMapper
    ) {
        this.routeRepository = routeRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean routeExists(Long routeId) {
        return this.routeRepository.existsById(routeId);
    }

    @Override
    public RoutesWrapperView getRoutesWrapper() {
        RoutesWrapperView routesWrapper = new RoutesWrapperView();

        routesWrapper.getRoutes().addAll(
                this.routeRepository.findAll().stream()
                        .map(e -> modelMapper.map(e, RouteBasicView.class)
                                .setPictureUrl(
                                        this.pictureRepository.findFirstByRoute(e)
                                                .map(Picture::getUrl)
                                                .orElse(null)
                                ))
                        .toList());

        return routesWrapper;
    }

    @Override
    public RouteDetailsView getRouteDetailsView(Long routeId) {
        Route route = this.routeRepository.findById(routeId).orElse(null);
        if (route == null) return null;

//        TODO: sort out how to make it work with Converter / TypeMap
//        TypeMap<Route, RouteDetailsView> categoriesToEnums = modelMapper.createTypeMap(Route.class, RouteDetailsView.class);
//        Converter<Set<Category>, List<CategoryName>> entitySetToEnumList = c ->
//                c.getSource().stream()
//                        .map(Category::getName)
//                        .toList();
//        categoriesToEnums.addMappings(mapper ->
//                mapper.using(entitySetToEnumList).map(Route::getCategories, RouteDetailsView::setCategoryNames));
//

//        TypeMap<Route, RouteDetailsView> routeToDetailed = modelMapper.createTypeMap(Route.class, RouteDetailsView.class);
//        Converter<Level, Integer> levelToInt = c -> c.getSource().ordinal();
//        routeToDetailed.addMappings(mapper -> mapper.using(levelToInt).map(Route::getLevel, RouteDetailsView::setLevel));

        RouteDetailsView viewModel = modelMapper.map(route, RouteDetailsView.class);

        viewModel.setLevelOrdinal(route.getLevel().ordinal())
                .setCategoryNames(route.getCategories().stream()
                        .map(Category::getName)
                        .collect(Collectors.toSet()))
                .setImageUrls(
                        this.pictureRepository.findAllByRoute(route)
                                .stream()
                                .map(Picture::getUrl)
                                .toList());

        return viewModel;
    }
}
