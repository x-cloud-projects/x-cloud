#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utility;

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


import ${groupId}.common.util.JsonUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.Map;

/**
 * 模板处理工具类
 *
 * @author Admin
 */
public class TemplateUtils {

    /**
     * 渲染
     *
     * @param template 模板内容
     * @param jsonData JSON数据
     * @return
     */
    public static String render(String template, String jsonData) {
        Map<String, String> data = JsonUtils.parseJson(jsonData, Map.class);
        StrSubstitutor strSubstitutor = new StrSubstitutor(data);
        return strSubstitutor.replace(template);
    }
}
