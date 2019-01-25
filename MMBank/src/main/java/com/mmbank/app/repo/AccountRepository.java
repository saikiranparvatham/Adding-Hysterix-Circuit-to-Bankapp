package com.mmbank.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mmbank.app.accounts.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer> {

}
