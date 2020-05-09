package co.lq.modules.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.lq.modules.monitor.domain.Server;

/**
 * @author Zhang houying
 * @date 2019-11-03
 */
public interface ServerRepository extends JpaRepository<Server, Integer>, JpaSpecificationExecutor<Server> {
}
