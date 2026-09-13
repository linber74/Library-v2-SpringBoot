package se.linda.library.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.linda.library.dto.request.ShortDramaRequest;
import se.linda.library.dto.response.ShortDramaResponse;
import se.linda.library.mapper.ShortDramaMapper;
import se.linda.library.service.ShortDramaService;

import java.util.List;

@RestController
@RequestMapping("/api/shortdramas")
public class ShortDramaController {

    final ShortDramaService service;
    final ShortDramaMapper mapper;

    public ShortDramaController(ShortDramaService service, ShortDramaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ShortDramaResponse> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ShortDramaResponse getById(@PathVariable Long id) {
        return mapper.toResponse(service.getById(id));
    }

    @PostMapping
    public ShortDramaResponse save(@Valid @RequestBody ShortDramaRequest request) {
        return mapper.toResponse(service.save(mapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
