package org.launchcode.models.data;
import org.launchcode.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by shaikh on 4/11/2017.
 */

@Repository
@Transactional
public interface MenuDao extends CrudRepository<Menu, Integer>{
}

/**The central interface in Spring Data repository abstraction is Repository (probably not that much of a surprise).
 * It takes the domain class to manage as well as the id type of the domain class as type arguments.
 * This interface acts primarily as a marker interface to capture the types to work with and to help you to
 * discover interfaces that extend this one. The CrudRepository provides sophisticated CRUD functionality for the
 * entity class that is being managed.
 *
 */
