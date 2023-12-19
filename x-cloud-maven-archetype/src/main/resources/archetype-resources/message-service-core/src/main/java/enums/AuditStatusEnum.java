#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.enums;

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


/**
 * @author Admin
 */

public enum AuditStatusEnum {

    /**
     * 待审核
     */
    AUDITING(0, "待审核"),
    /**
     * 通过
     */
    PASS(1, "通过"),
    /**
     * 审核未通过
     */
    REJECT(2, "审核未通过");


    private final int value;

    private final String phrase;

    AuditStatusEnum(int value, String phrase) {
        this.value = value;
        this.phrase = phrase;
    }

    public int value() {
        return this.value;
    }

    public String getPhrase() {
        return this.phrase;
    }

    @Override
    public String toString() {
        return this.value + " " + this.phrase;
    }

    public static AuditStatusEnum valueOf(int statusCode) {
        AuditStatusEnum status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        }
        return status;
    }

    public static AuditStatusEnum resolve(int code) {
        for (AuditStatusEnum status : values()) {
            if (status.value == code) {
                return status;
            }
        }

        return null;
    }
}
