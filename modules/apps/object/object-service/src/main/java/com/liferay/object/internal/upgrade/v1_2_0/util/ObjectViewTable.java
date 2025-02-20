/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.object.internal.upgrade.v1_2_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class ObjectViewTable {

	public static final String TABLE_NAME = "ObjectView";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"objectViewId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"objectDefinitionId", Types.BIGINT},
		{"defaultObjectView", Types.BOOLEAN}, {"name", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);

TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("objectViewId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("objectDefinitionId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("defaultObjectView", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE =
"create table ObjectView (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,objectViewId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,objectDefinitionId LONG,defaultObjectView BOOLEAN,name STRING null)";

	public static final String TABLE_SQL_DROP = "drop table ObjectView";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_6AF6C9EA on ObjectView (objectDefinitionId, defaultObjectView)",
		"create index IX_C771CDE on ObjectView (uuid_[$COLUMN_LENGTH:75$], companyId)"
	};

}