#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

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


import com.xfeel.support.jpa.auditing.BaseAuditorConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author Admin
 */
@EnableJpaAuditing
@Configuration
public class AuditorConfig extends BaseAuditorConfig {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("admin");
//        return super.getCurrentAuditor();
    }
}