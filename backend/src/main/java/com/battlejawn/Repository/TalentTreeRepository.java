package com.battlejawn.Repository;

import com.battlejawn.Entities.TalentTree.TalentTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentTreeRepository extends JpaRepository<TalentTree, Long> {

}
