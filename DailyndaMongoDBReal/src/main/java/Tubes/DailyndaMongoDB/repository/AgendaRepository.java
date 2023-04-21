package Tubes.DailyndaMongoDB.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import Tubes.DailyndaMongoDB.model.Agenda;

@Repository
public interface AgendaRepository extends ReactiveMongoRepository<Agenda, Long>{
}
