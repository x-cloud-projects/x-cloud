#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao.repository;

/*-
 * ${symbol_pound}%L
 * ${artifactId}
 * %%
 * Copyright (C) 2022 xfeel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ${symbol_pound}L%
 */


import ${package}.dao.entity.ApiVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Admin
 */
@Repository
public interface ApiVendorRepository extends JpaRepository<ApiVendor, Long> {

    /**
     * 根据条件查找
     * @param name
     * @param deletedFlag
     * @return
     */
    Optional<ApiVendor> findByNameAndDeletedFlag(String name, Integer deletedFlag);

    Optional<ApiVendor> findByAppKeyAndSecretKeyAndDeletedFlag(String appKey, String secretKey, Integer deletedFlag);
}
