#!/usr/bin/env bash

# Env Config
DATA_ROOT=/Users/dyfeelme/data

BACKUP_BASE_PATH=$DATA_ROOT/backup

SOURCE_BASE_PATH=$DATA_ROOT/install
SOURCE_JAR_NAME=job-admin-service-1.0.0.jar

TARGET_BASE_PATH=$DATA_ROOT/installed
TARGET_SERVICE_NAME=job-admin-service
TARGET_JAR_NAME=job-admin-service-1.0.0.jar

deploy () {
	echo "=============DEPLOY===================="
	echo "======================================="
	echo "#@@@ ${TARGET_SERVICE_NAME} @@@#"
	echo "======================================="
	DATE_SUB_PATH=`date +%Y%m%d`
	mkdir -p $BACKUP_BASE_PATH/$DATE_SUB_PATH/$TARGET_SERVICE_NAME
	cp $TARGET_BASE_PATH/$TARGET_SERVICE_NAME/$TARGET_JAR_NAME $BACKUP_BASE_PATH/$DATE_SUB_PATH/$TARGET_SERVICE_NAME/$TARGET_JAR_NAME.`date +%Y%m%d%H%M%S`

}

recovery () {
	echo "=============RECOVERY===================="
	echo "======================================="
	echo "#@@@ ${TARGET_SERVICE_NAME} @@@#"
	echo "======================================="
	LATEST_DIR=`ls -lt $BACKUP_BASE_PATH $ |grep -v total | head -n 1|awk '{print $9}'`
	LATEST_FILE=`ls -lt $BACKUP_BASE_PATH/$LATEST_DIR $ |grep -v total | head -n 1|awk '{print $9}'`
	BACKUP_JAR_FILE=$BACKUP_BASE_PATH/$LATEST_DIR/$LATEST_FILE
	[[ -f  $BACKUP_JAR_FILE ]] && cp $BACKUP_JAR_FILE $TARGET_BASE_PATH/$TARGET_SERVICE_NAME/$TARGET_JAR_NAME
}
