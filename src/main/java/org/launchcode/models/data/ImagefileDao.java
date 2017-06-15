package org.launchcode.models.data;
import org.launchcode.models.Imagefile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by shaikh on 5/15/2017.
 */

@Repository
@Transactional
public interface ImagefileDao extends  CrudRepository<Imagefile, Integer> { // primary keys in those objects are Integer.
/** curdrepository allows several methods to put the data in and out of the database. the methods include update, delete
 * and add/modify records/objects. Dao interacts with the relational database - mysql***/

}
//public interface ImageDao extends CrudRepository<ImageUpload, Integer>  JpaRepository
//public interface ImageDao

/**The central interface in Spring Data repository abstraction is Repository (probably not that much of a surprise).
 * It takes the domain class to manage as well as the id type of the domain class as type arguments.
 * This interface acts primarily as a marker interface to capture the types to work with and to help you to
 * discover interfaces that extend this one. The CrudRepository provides sophisticated CRUD functionality for the
 * entity class that is being managed.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
