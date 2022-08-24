package com.hamidur.springBootRESTfulWebservices.repos;

import com.hamidur.springBootRESTfulWebservices.models.Invoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

    @Query(value = "select * from invoice i where i.customer_id = :id", nativeQuery = true)
    List<Invoice> findAllByCustomerId (@Param("id") Integer id);

    //@Query(value = "insert into invoice i (customer_id, flight_id, value, method) "+
    //"values (:customer_id, :flight_id, :value, :method)", nativeQuery = true)
    //void save(@Param("customer_id") Integer costumerId,@Param("flight_id") Integer flightId,
    //@Param("value") Long value,@Param("methode") String methode);
}
