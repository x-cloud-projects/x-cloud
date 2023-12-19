#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

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
import com.xfeel.support.mapstruct.base.GenericMapperConfig;
import ${package}.domain.ApiVendorDetailResult;
import ${package}.domain.ApiVendorResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Admin
 */
@Mapper(config = GenericMapperConfig.class)
public interface ApiVendorServiceBeanMapper {

    /**
     * 转换ApiVendorResult
     * @param apiVendor
     * @return
     */
    @Mapping(target = "id",source = "id")
    ApiVendorResult toApiVendorResult(ApiVendor apiVendor);

    ApiVendorDetailResult toApiVendorDetailResult(ApiVendor apiVendor);
}
