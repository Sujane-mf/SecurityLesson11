package ru.geekbrains.SecurityLesson11.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.SecurityLesson11.entities.Privilege;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
}
