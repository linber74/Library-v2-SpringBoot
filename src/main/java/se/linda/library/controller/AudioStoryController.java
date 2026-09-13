package se.linda.library.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.dto.request.AudioStoryRequest;
import se.linda.library.dto.response.AudioStoryResponse;
import se.linda.library.mapper.AudioStoryMapper;
import se.linda.library.model.entity.AudioStory;
import se.linda.library.service.AudioStoryService;

import java.util.List;

@RestController
@RequestMapping("/api/audiostories")
public class AudioStoryController {

    final AudioStoryService service;
    final AudioStoryMapper mapper;

    public AudioStoryController(AudioStoryService service, AudioStoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

   @GetMapping
   public List<AudioStoryResponse> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
   }

   @GetMapping("/{id}")
    public AudioStoryResponse getById(@PathVariable Long id) {
        AudioStory story = service.getById(id);
        return mapper.toResponse(story);
   }

   @PostMapping
   public AudioStoryResponse save(@Valid @RequestBody AudioStoryRequest request) {
        AudioStory story = mapper.toEntity(request);
        return mapper.toResponse(service.save(story));
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
   }

   @GetMapping("/search/duration")
    public List<AudioStoryResponse> searchByDuration(@RequestParam Integer min, @RequestParam Integer max) {
        return service.searchByDuration(min, max)
                .stream()
                .map(mapper::toResponse)
                .toList();
   }
}
