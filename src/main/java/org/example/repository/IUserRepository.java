package org.example.repository;

import org.example.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    @Query(value = "select u from User u where upper(trim(u.nome)) like %?1%")
    List<User> buscarPorNome(String name);
}
