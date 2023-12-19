#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.admin.web.config;

/*-
 * ${symbol_pound}%L
 * Message Admin Service
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


import com.xfeel.boot.autoconfigure.swagger.SwaggerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 */
@Configuration
public class SwaggerConfig {

    @Bean
    @Primary
    public List<Parameter> globalOperationParameters(){
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("access_token")
                .description("Access Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
        return parameters;
    }

    @Bean
    @Primary
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .basePackage("com.xfeel.cloud.message.admin.web.controller")
                .groupName("${parentArtifactId}")
                .title("消息管理平台")
                .description("消息管理平台(Message Management Platform)")
                .contactEmail("dyfeelme@gmail.com")
                .contactUrl("https://cloud.xfeel-coding.cn/me")
                .build();
    }
}
