package com.yunus.fakebank.Repository;

import com.yunus.fakebank.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findCardsByAccountId(Long id);

    @Transactional
    void deleteCardsByAccountId(Long accountId);
}
