package se.linda.library.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.dto.request.TVSeriesRequest;
import se.linda.library.dto.response.TVSeriesResponse;
import se.linda.library.mapper.TVSeriesMapper;
import se.linda.library.model.entity.Season;
import se.linda.library.model.entity.TVSeries;
import se.linda.library.service.TVSeriesService;

import java.util.List;

@RestController
@RequestMapping("/api/tvseries")
public class TVSeriesController {

    final TVSeriesService tvSeriesService;
    final TVSeriesMapper tvSeriesMapper;

    public TVSeriesController(TVSeriesService tvSeriesService, TVSeriesMapper tvSeriesMapper) {
        this.tvSeriesService = tvSeriesService;
        this.tvSeriesMapper = tvSeriesMapper;
    }

    @GetMapping
    public List<TVSeriesResponse> getAll() {
        return tvSeriesService.getAll()
                .stream()
                .map(tvSeriesMapper :: toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public TVSeriesResponse getById (@PathVariable Long id){
        TVSeries tvSeries = tvSeriesService.getById(id);
        return tvSeriesMapper.toResponse(tvSeries);
    }

    @PostMapping
    public TVSeriesResponse save (@Valid @RequestBody TVSeriesRequest request){
        TVSeries tvSeries = tvSeriesMapper.toEntity(request);
        TVSeries saved = tvSeriesService.save(tvSeries);
        return tvSeriesMapper.toResponse(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        tvSeriesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/season")
    public Season addSeason (@PathVariable Long id, @RequestBody Season season){
        return tvSeriesService.addSeason(id, season);
    }

    @GetMapping ("/search/season")
    public List<Season> searchBySeasonNumber(@RequestParam int nr){
        return tvSeriesService.searchBySeasonNumber(nr);
    }

    @GetMapping ("/{id}/season")
    public List<Season> searchBySeason (@PathVariable Long id){
        TVSeries tvSeries = tvSeriesService.getById(id);
        return tvSeriesService.searchByTvSeries(tvSeries);
    }

}
