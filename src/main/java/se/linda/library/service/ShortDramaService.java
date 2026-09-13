package se.linda.library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.ShortDrama;
import se.linda.library.repository.ShortDramaRepository;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ShortDramaService {

    final ShortDramaRepository shortDramaRepository;

    public ShortDramaService(ShortDramaRepository shortDramaRepository) {
        this.shortDramaRepository = shortDramaRepository;
    }

    public ShortDrama save(ShortDrama shortDrama) {
        if (shortDrama == null) {
            log.warn("Attempted to save a null Short drama");
            throw new IllegalArgumentException("Short drama can't be null");
        }

        log.info("Saving Short Drama {}", shortDrama.getTitle());
        return shortDramaRepository.save(shortDrama);
    }

    public List<ShortDrama> getAll() {
        return shortDramaRepository.findAll();
    }

    public ShortDrama getById(Long id) {
        return shortDramaRepository.findById(id).orElseThrow(()
                -> {
            log.warn("Short Drama not found with id {}", id);
            throw new ItemNotFoundException("Short drama with id " + id + " not found");
        });
    }

    public void deleteById(Long id) {
        if (!shortDramaRepository.existsById(id)) {
            log.warn("Short Drama not found with id {}", id);
            throw new ItemNotFoundException("Short drama with id " + id + " not found");
        }
        log.info("Deleting Short Drama {}", id);
        shortDramaRepository.deleteById(id);
    }

    public List<ShortDrama> searchByDuration(Integer min, Integer max) {

        if(min == null || max == null) {
            log.warn("Min and max duration can't be null");
            throw new IllegalArgumentException("Min and max duration can't be null");
        }

        if(min < 0 || max < 0) {
            log.warn("Duration values can't be negative");
            throw new IllegalArgumentException("Duration values can't be negative");
        }

        if(min > max) {
            log.warn("Min and max duration can't be greater than max");
            throw new IllegalArgumentException("Min and max duration can't be greater than max");
        }

        log.info("Found by duration between: {} and {}", min, max);
        return shortDramaRepository.findByDurationSecondsBetween(min, max);
    }
}
