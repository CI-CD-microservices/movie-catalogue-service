package com.microservices.tp.moviecatalogueservice.ressources;

import com.microservices.tp.moviecatalogueservice.models.ItemCatalog;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogRessource {

    @RequestMapping("/{userId}")
    public List<ItemCatalog> getCatalog(@PathVariable("userId") String userId) {
        return Collections.singletonList(
                new ItemCatalog("Transformers New Version 4", "Sci-fi film", 4)
        );
    }
}
