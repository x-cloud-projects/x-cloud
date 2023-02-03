package com.xfeel.cloud.common.constant;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 基础常量
 *
 * @author Admin
 */
public class BaseConstants {

    public static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DATE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_TIME = "HH:mm:ss";

    public static final int MAX_BATCH_SIZE = 1000;

    public static final Integer DATA_STATUS_NORMAL = 1;
    public static final Integer DATA_STATUS_DELETED = 0;

    public static final String ENTITY_FIELD_GMT_CREATE = "gmt_create";
    public static final String ENTITY_PROPERTY_GMT_CREATE = "gmtCreate";
    public static final String ENTITY_FIELD_GMT_MODIFIED = "gmt_modified";
    public static final String ENTITY_PROPERTY_GMT_MODIFIED = "gmtModified";
    public static final String ENTITY_FIELD_DELETED_FLAG = "deleted_flag";
    public static final String ENTITY_PROPERTY_DELETED_FLAG = "deletedFlag";

    public static final Random RANDOM = new SecureRandom();
    public static final ThreadLocalRandom THREAD_LOCAL_RANDOM = ThreadLocalRandom.current();
}
