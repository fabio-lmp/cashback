package br.com.fabioporto.cashback.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fabioporto.cashback.entity.CashBack;

public interface CashBackRepository extends JpaRepository<CashBack, Long> {

    @Query("select sum(c.cashBack) as soma from CashBack c where c.cliente.id = :id and c.dataUtilizacao is null and c.dataExpiracao is null")
    Map<String, BigDecimal> getSaldoQueryParam(@Param("id") Long id);    

    @Query("select c from CashBack c where c.cliente.id = :id and c.dataUtilizacao is null and c.dataExpiracao is null")
    List<CashBack> listDisponiveisQueryParam(@Param("id") Long id);    
    
}
