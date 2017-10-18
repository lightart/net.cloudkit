package net.cloudkit.phecda.domain.model.shared;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

/**
 * CityRepository
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public interface CityRepository extends Repository<City, Long> {

    /**
     * 查找所有
     *
     * @param pageable
     * @return
     */
    Page<City> findAll(Pageable pageable);

    /**
     * 根据 name 和 country 查找并且分页
     *
     * @param name
     * @param country
     * @param pageable
     * @return
     */
    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name, String country, Pageable pageable);

    /**
     * 根据 name 和 country 查找
     *
     * @param name
     * @param country
     * @return
     */
    City findByNameAndCountryAllIgnoringCase(String name, String country);

}
