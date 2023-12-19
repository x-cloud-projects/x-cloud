#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

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


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiVendorCreateParam {

    @NotBlank(message = "{valid.apivendor.name.not.empty}")
    @Length(max = 50, message = "{valid.apivendor.name.lengh.too.long}")
    private String name;

    @NotBlank(message = "{valid.apivendor.app_key.not.empty}")
    @Length(max = 100, message = "{valid.apivendor.app_key.too.long}")
    private String appKey;

    @NotBlank(message = "{valid.apivendor.secret_key.not.empty}")
    @Length(max = 200, message = "valid.apivendor.secret_key.too.long")
    private String secretKey;

}
