package biz.eurosib.lkdkp.h2;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskResultRepository extends JpaRepository<TaskResult, Long> {
    TaskResult findDistinctTopByGuid(UUID guid);
}
