#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constant;

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


import com.xfeel.support.common.constant.GlobalApiResultCode;

/**
 * @author Admin
 */
public class ApiResultCode extends GlobalApiResultCode {

    public static final String API_RET_CODE_API_VENDOR_BIZ_ERR = "C500M01N001";

    public static final String API_RET_CODE_API_VENDOR_VALID_ERR = "C500M01N001";
    public static final String API_RET_CODE_TEMPLATE_BIZ_ERR = "C500M02N001";
    public static final String API_RET_CODE_MESSAGE_BIZ_ERR = "C500M03N001";
}
