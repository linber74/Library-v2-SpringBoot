package se.linda.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.model.entity.Season;
import se.linda.library.model.entity.TVSeries;
import se.linda.library.service.TVSeriesService;

import java.util.List;

@RestController
@RequestMapping("/api/tvseries")
public class TVSeriesController {

    final TVSeriesService tvSeriesService;

    public TVSeriesController(TVSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @GetMapping
    public List<TVSeries> getAll(){
        return tvSeriesService.getAll();
    }

    @GetMapping("/{id}")
    public TVSeries getById (@PathVariable Long id){
        return tvSeriesService.getById(id);
    }

    @PostMapping
    public TVSeries save (@RequestBody TVSeries series){
        return tvSeriesService.save(series);
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
