package com.agora.service;

import com.agora.models.Tag;
import com.agora.repositories.TagRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag findOrCreateTag(String name) {
        return tagRepository.findByName(name)
                .orElseGet(() -> tagRepository.save(new Tag(null, name)));
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}

