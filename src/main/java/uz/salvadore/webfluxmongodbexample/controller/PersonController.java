package uz.salvadore.webfluxmongodbexample.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import uz.salvadore.webfluxmongodbexample.model.Person;
import uz.salvadore.webfluxmongodbexample.model.Response;
import uz.salvadore.webfluxmongodbexample.model.Search;
import uz.salvadore.webfluxmongodbexample.repository.PersonRepository;
import uz.salvadore.webfluxmongodbexample.utils.RestUtils;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class PersonController {

    private PersonRepository personRepository;
    private RestUtils utils;

    @PostMapping("person")
    public Mono<ResponseEntity<Response>> save(@RequestBody Person person) {
        return personRepository
                .save(person)
                .map(p -> ResponseEntity.ok(utils.buildOk("Successfully save person", p)))
                .onErrorReturn(ResponseEntity.ok(utils.buildError("Error when save person")));
    }

    @GetMapping("person/{id}")
    public Mono<ResponseEntity<Response>> delete(@PathVariable("id") String id) {
        return personRepository
                .deleteById(id)
                .map((v) -> ResponseEntity.ok(utils.buildOk("Successfully delete person", null)))
                .onErrorReturn(ResponseEntity.ok(utils.buildError("Error when save person")));
    }

    @GetMapping("person")
    public Mono<ResponseEntity<Response>> findAll() {
        return personRepository
                .findAll()
                .collectList()
                .map((l) -> ResponseEntity.ok(utils.buildOk("Success getting list of persons", l)))
                .onErrorReturn(ResponseEntity.ok(utils.buildError("Error getting person list")));
    }


    @PostMapping("person/search")
    public Mono<ResponseEntity<Response>> findByName(@RequestBody Search search) {
        return personRepository
                .findPersonByName(search.getValue())
                .collectList()
                .map((v) -> ResponseEntity.ok(utils.buildOk("Successfully get person", v)))
                .onErrorReturn(ResponseEntity.ok(utils.buildError("Error when get person")));
    }

}
