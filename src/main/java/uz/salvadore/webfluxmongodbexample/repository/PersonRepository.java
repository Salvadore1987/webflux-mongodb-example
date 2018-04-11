package uz.salvadore.webfluxmongodbexample.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.salvadore.webfluxmongodbexample.model.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    @Override
    <S extends Person> Mono<S> save(S s);

    @Override
    Mono<Void> deleteById(String s);

    @Override
    Mono<Long> count();

    @Override
    Mono<Person> findById(String s);

    @Override
    Flux<Person> findAll();

    Flux<Person> findPersonByName(String name);

    Flux<Person> findPersonBySurname(String surname);

}
