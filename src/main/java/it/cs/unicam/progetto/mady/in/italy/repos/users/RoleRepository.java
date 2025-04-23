package it.cs.unicam.progetto.mady.in.italy.repos.users;

import it.cs.unicam.progetto.mady.in.italy.model.user.Authorization;
import it.cs.unicam.progetto.mady.in.italy.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.authorization IN :authorizations")
    List<Role> findByAuthorizationIn(@Param("authorizations") List<Authorization> authorizations);

}
