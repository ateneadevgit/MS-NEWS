package com.fusm.news.controller;

import com.fusm.news.model.NewsModel;
import com.fusm.news.model.NewsRequest;
import com.fusm.news.model.NewsSearch;
import com.fusm.news.service.INewService;
import com.fusm.news.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que expone los servicios relacionados con las noticias
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.NEWS_ROUTE)
public class NewsController {

    @Autowired
    private INewService newService;


    /**
     * Obtiene las noticias
     * @param newsSearch Modelo que contiene los parámetros de búsqueda para filtrar
     * @return lista de noticias
     */
    @PostMapping("/get")
    private ResponseEntity<List<NewsModel>> getNews(
            @RequestBody NewsSearch newsSearch
            ) {
        return ResponseEntity.ok(newService.getNews(newsSearch));
    }

    /**
     * Crea una noticia
     * @param newsRequest Modelo que contiene la información encesaria para crear noticias
     * @return OK
     */
    @PostMapping
    private ResponseEntity<String> createNews(
            @RequestBody NewsRequest newsRequest
            ) {
        newService.createNew(newsRequest);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Actualzia las noticias
     * @param newsRequest Modelo que contiene la información necesaria para actualizar una noticia
     * @param newId ID de la noticia
     * @return OK
     */
    @PutMapping("/{id}")
    private ResponseEntity<String> updateNews(
            @RequestBody NewsRequest newsRequest,
            @PathVariable("id") Integer newId
    ) {
        newService.updateNews(newsRequest, newId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * Elimina una noticia
     * @param newId ID de la noticia
     * @return OK
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteNew(
            @PathVariable("id") Integer newId
    ) {
        newService.disableNews(newId);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
