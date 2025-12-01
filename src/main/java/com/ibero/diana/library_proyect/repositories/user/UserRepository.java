package com.ibero.diana.library_proyect.repositories.user;

import com.ibero.diana.library_proyect.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Page<User> getUsersByStateIs(boolean state, Pageable pageable);
}
