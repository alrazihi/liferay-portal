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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the KaleoInstance service. Represents a row in the &quot;KaleoInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>KaleoInstanceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoInstanceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceImpl
 * @generated
 */
@ProviderType
public class KaleoInstanceModelImpl
	extends BaseModelImpl<KaleoInstance> implements KaleoInstanceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo instance model instance should use the <code>KaleoInstance</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoInstance";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"kaleoInstanceId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoDefinitionName", Types.VARCHAR},
		{"kaleoDefinitionVersion", Types.INTEGER},
		{"rootKaleoInstanceTokenId", Types.BIGINT},
		{"className", Types.VARCHAR}, {"classPK", Types.BIGINT},
		{"completed", Types.BOOLEAN}, {"completionDate", Types.TIMESTAMP},
		{"workflowContext", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersion", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("rootKaleoInstanceTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("className", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("completed", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("completionDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("workflowContext", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoInstance (mvccVersion LONG default 0 not null,kaleoInstanceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionVersionId LONG,kaleoDefinitionName VARCHAR(200) null,kaleoDefinitionVersion INTEGER,rootKaleoInstanceTokenId LONG,className VARCHAR(200) null,classPK LONG,completed BOOLEAN,completionDate DATE null,workflowContext TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table KaleoInstance";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoInstance.kaleoInstanceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoInstance.kaleoInstanceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstance"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstance"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.model.KaleoInstance"),
		true);

	public static final long CLASSNAME_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long COMPLETED_COLUMN_BITMASK = 8L;

	public static final long COMPLETIONDATE_COLUMN_BITMASK = 16L;

	public static final long KALEODEFINITIONNAME_COLUMN_BITMASK = 32L;

	public static final long KALEODEFINITIONVERSION_COLUMN_BITMASK = 64L;

	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 128L;

	public static final long USERID_COLUMN_BITMASK = 256L;

	public static final long KALEOINSTANCEID_COLUMN_BITMASK = 512L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoInstance"));

	public KaleoInstanceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoInstance.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoInstance, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoInstance, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((KaleoInstance)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoInstance, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoInstance, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoInstance)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoInstance, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoInstance, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, KaleoInstance>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			KaleoInstance.class.getClassLoader(), KaleoInstance.class,
			ModelWrapper.class);

		try {
			Constructor<KaleoInstance> constructor =
				(Constructor<KaleoInstance>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<KaleoInstance, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KaleoInstance, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KaleoInstance, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<KaleoInstance, Object>>();
		Map<String, BiConsumer<KaleoInstance, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<KaleoInstance, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", KaleoInstance::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<KaleoInstance, Long>)KaleoInstance::setMvccVersion);
		attributeGetterFunctions.put(
			"kaleoInstanceId", KaleoInstance::getKaleoInstanceId);
		attributeSetterBiConsumers.put(
			"kaleoInstanceId",
			(BiConsumer<KaleoInstance, Long>)KaleoInstance::setKaleoInstanceId);
		attributeGetterFunctions.put("groupId", KaleoInstance::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<KaleoInstance, Long>)KaleoInstance::setGroupId);
		attributeGetterFunctions.put("companyId", KaleoInstance::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<KaleoInstance, Long>)KaleoInstance::setCompanyId);
		attributeGetterFunctions.put("userId", KaleoInstance::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<KaleoInstance, Long>)KaleoInstance::setUserId);
		attributeGetterFunctions.put("userName", KaleoInstance::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<KaleoInstance, String>)KaleoInstance::setUserName);
		attributeGetterFunctions.put(
			"createDate", KaleoInstance::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<KaleoInstance, Date>)KaleoInstance::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", KaleoInstance::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<KaleoInstance, Date>)KaleoInstance::setModifiedDate);
		attributeGetterFunctions.put(
			"kaleoDefinitionVersionId",
			KaleoInstance::getKaleoDefinitionVersionId);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionVersionId",
			(BiConsumer<KaleoInstance, Long>)
				KaleoInstance::setKaleoDefinitionVersionId);
		attributeGetterFunctions.put(
			"kaleoDefinitionName", KaleoInstance::getKaleoDefinitionName);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionName",
			(BiConsumer<KaleoInstance, String>)
				KaleoInstance::setKaleoDefinitionName);
		attributeGetterFunctions.put(
			"kaleoDefinitionVersion", KaleoInstance::getKaleoDefinitionVersion);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionVersion",
			(BiConsumer<KaleoInstance, Integer>)
				KaleoInstance::setKaleoDefinitionVersion);
		attributeGetterFunctions.put(
			"rootKaleoInstanceTokenId",
			KaleoInstance::getRootKaleoInstanceTokenId);
		attributeSetterBiConsumers.put(
			"rootKaleoInstanceTokenId",
			(BiConsumer<KaleoInstance, Long>)
				KaleoInstance::setRootKaleoInstanceTokenId);
		attributeGetterFunctions.put("className", KaleoInstance::getClassName);
		attributeSetterBiConsumers.put(
			"className",
			(BiConsumer<KaleoInstance, String>)KaleoInstance::setClassName);
		attributeGetterFunctions.put("classPK", KaleoInstance::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<KaleoInstance, Long>)KaleoInstance::setClassPK);
		attributeGetterFunctions.put("completed", KaleoInstance::getCompleted);
		attributeSetterBiConsumers.put(
			"completed",
			(BiConsumer<KaleoInstance, Boolean>)KaleoInstance::setCompleted);
		attributeGetterFunctions.put(
			"completionDate", KaleoInstance::getCompletionDate);
		attributeSetterBiConsumers.put(
			"completionDate",
			(BiConsumer<KaleoInstance, Date>)KaleoInstance::setCompletionDate);
		attributeGetterFunctions.put(
			"workflowContext", KaleoInstance::getWorkflowContext);
		attributeSetterBiConsumers.put(
			"workflowContext",
			(BiConsumer<KaleoInstance, String>)
				KaleoInstance::setWorkflowContext);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_columnBitmask = -1L;

		_kaleoInstanceId = kaleoInstanceId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		_columnBitmask |= KALEODEFINITIONVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionVersionId) {
			_setOriginalKaleoDefinitionVersionId = true;

			_originalKaleoDefinitionVersionId = _kaleoDefinitionVersionId;
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	public long getOriginalKaleoDefinitionVersionId() {
		return _originalKaleoDefinitionVersionId;
	}

	@Override
	public String getKaleoDefinitionName() {
		if (_kaleoDefinitionName == null) {
			return "";
		}
		else {
			return _kaleoDefinitionName;
		}
	}

	@Override
	public void setKaleoDefinitionName(String kaleoDefinitionName) {
		_columnBitmask |= KALEODEFINITIONNAME_COLUMN_BITMASK;

		if (_originalKaleoDefinitionName == null) {
			_originalKaleoDefinitionName = _kaleoDefinitionName;
		}

		_kaleoDefinitionName = kaleoDefinitionName;
	}

	public String getOriginalKaleoDefinitionName() {
		return GetterUtil.getString(_originalKaleoDefinitionName);
	}

	@Override
	public int getKaleoDefinitionVersion() {
		return _kaleoDefinitionVersion;
	}

	@Override
	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion) {
		_columnBitmask |= KALEODEFINITIONVERSION_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionVersion) {
			_setOriginalKaleoDefinitionVersion = true;

			_originalKaleoDefinitionVersion = _kaleoDefinitionVersion;
		}

		_kaleoDefinitionVersion = kaleoDefinitionVersion;
	}

	public int getOriginalKaleoDefinitionVersion() {
		return _originalKaleoDefinitionVersion;
	}

	@Override
	public long getRootKaleoInstanceTokenId() {
		return _rootKaleoInstanceTokenId;
	}

	@Override
	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId) {
		_rootKaleoInstanceTokenId = rootKaleoInstanceTokenId;
	}

	@Override
	public String getClassName() {
		if (_className == null) {
			return "";
		}
		else {
			return _className;
		}
	}

	@Override
	public void setClassName(String className) {
		_columnBitmask |= CLASSNAME_COLUMN_BITMASK;

		if (_originalClassName == null) {
			_originalClassName = _className;
		}

		_className = className;
	}

	public String getOriginalClassName() {
		return GetterUtil.getString(_originalClassName);
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public boolean getCompleted() {
		return _completed;
	}

	@Override
	public boolean isCompleted() {
		return _completed;
	}

	@Override
	public void setCompleted(boolean completed) {
		_columnBitmask |= COMPLETED_COLUMN_BITMASK;

		if (!_setOriginalCompleted) {
			_setOriginalCompleted = true;

			_originalCompleted = _completed;
		}

		_completed = completed;
	}

	public boolean getOriginalCompleted() {
		return _originalCompleted;
	}

	@Override
	public Date getCompletionDate() {
		return _completionDate;
	}

	@Override
	public void setCompletionDate(Date completionDate) {
		_columnBitmask |= COMPLETIONDATE_COLUMN_BITMASK;

		if (_originalCompletionDate == null) {
			_originalCompletionDate = _completionDate;
		}

		_completionDate = completionDate;
	}

	public Date getOriginalCompletionDate() {
		return _originalCompletionDate;
	}

	@Override
	public String getWorkflowContext() {
		if (_workflowContext == null) {
			return "";
		}
		else {
			return _workflowContext;
		}
	}

	@Override
	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), KaleoInstance.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoInstance toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoInstanceImpl kaleoInstanceImpl = new KaleoInstanceImpl();

		kaleoInstanceImpl.setMvccVersion(getMvccVersion());
		kaleoInstanceImpl.setKaleoInstanceId(getKaleoInstanceId());
		kaleoInstanceImpl.setGroupId(getGroupId());
		kaleoInstanceImpl.setCompanyId(getCompanyId());
		kaleoInstanceImpl.setUserId(getUserId());
		kaleoInstanceImpl.setUserName(getUserName());
		kaleoInstanceImpl.setCreateDate(getCreateDate());
		kaleoInstanceImpl.setModifiedDate(getModifiedDate());
		kaleoInstanceImpl.setKaleoDefinitionVersionId(
			getKaleoDefinitionVersionId());
		kaleoInstanceImpl.setKaleoDefinitionName(getKaleoDefinitionName());
		kaleoInstanceImpl.setKaleoDefinitionVersion(
			getKaleoDefinitionVersion());
		kaleoInstanceImpl.setRootKaleoInstanceTokenId(
			getRootKaleoInstanceTokenId());
		kaleoInstanceImpl.setClassName(getClassName());
		kaleoInstanceImpl.setClassPK(getClassPK());
		kaleoInstanceImpl.setCompleted(isCompleted());
		kaleoInstanceImpl.setCompletionDate(getCompletionDate());
		kaleoInstanceImpl.setWorkflowContext(getWorkflowContext());

		kaleoInstanceImpl.resetOriginalValues();

		return kaleoInstanceImpl;
	}

	@Override
	public int compareTo(KaleoInstance kaleoInstance) {
		int value = 0;

		if (getKaleoInstanceId() < kaleoInstance.getKaleoInstanceId()) {
			value = -1;
		}
		else if (getKaleoInstanceId() > kaleoInstance.getKaleoInstanceId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoInstance)) {
			return false;
		}

		KaleoInstance kaleoInstance = (KaleoInstance)obj;

		long primaryKey = kaleoInstance.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		KaleoInstanceModelImpl kaleoInstanceModelImpl = this;

		kaleoInstanceModelImpl._originalCompanyId =
			kaleoInstanceModelImpl._companyId;

		kaleoInstanceModelImpl._setOriginalCompanyId = false;

		kaleoInstanceModelImpl._originalUserId = kaleoInstanceModelImpl._userId;

		kaleoInstanceModelImpl._setOriginalUserId = false;

		kaleoInstanceModelImpl._setModifiedDate = false;

		kaleoInstanceModelImpl._originalKaleoDefinitionVersionId =
			kaleoInstanceModelImpl._kaleoDefinitionVersionId;

		kaleoInstanceModelImpl._setOriginalKaleoDefinitionVersionId = false;

		kaleoInstanceModelImpl._originalKaleoDefinitionName =
			kaleoInstanceModelImpl._kaleoDefinitionName;

		kaleoInstanceModelImpl._originalKaleoDefinitionVersion =
			kaleoInstanceModelImpl._kaleoDefinitionVersion;

		kaleoInstanceModelImpl._setOriginalKaleoDefinitionVersion = false;

		kaleoInstanceModelImpl._originalClassName =
			kaleoInstanceModelImpl._className;

		kaleoInstanceModelImpl._originalClassPK =
			kaleoInstanceModelImpl._classPK;

		kaleoInstanceModelImpl._setOriginalClassPK = false;

		kaleoInstanceModelImpl._originalCompleted =
			kaleoInstanceModelImpl._completed;

		kaleoInstanceModelImpl._setOriginalCompleted = false;

		kaleoInstanceModelImpl._originalCompletionDate =
			kaleoInstanceModelImpl._completionDate;

		kaleoInstanceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoInstance> toCacheModel() {
		KaleoInstanceCacheModel kaleoInstanceCacheModel =
			new KaleoInstanceCacheModel();

		kaleoInstanceCacheModel.mvccVersion = getMvccVersion();

		kaleoInstanceCacheModel.kaleoInstanceId = getKaleoInstanceId();

		kaleoInstanceCacheModel.groupId = getGroupId();

		kaleoInstanceCacheModel.companyId = getCompanyId();

		kaleoInstanceCacheModel.userId = getUserId();

		kaleoInstanceCacheModel.userName = getUserName();

		String userName = kaleoInstanceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoInstanceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoInstanceCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoInstanceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoInstanceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoInstanceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoInstanceCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoInstanceCacheModel.kaleoDefinitionName = getKaleoDefinitionName();

		String kaleoDefinitionName =
			kaleoInstanceCacheModel.kaleoDefinitionName;

		if ((kaleoDefinitionName != null) &&
			(kaleoDefinitionName.length() == 0)) {

			kaleoInstanceCacheModel.kaleoDefinitionName = null;
		}

		kaleoInstanceCacheModel.kaleoDefinitionVersion =
			getKaleoDefinitionVersion();

		kaleoInstanceCacheModel.rootKaleoInstanceTokenId =
			getRootKaleoInstanceTokenId();

		kaleoInstanceCacheModel.className = getClassName();

		String className = kaleoInstanceCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			kaleoInstanceCacheModel.className = null;
		}

		kaleoInstanceCacheModel.classPK = getClassPK();

		kaleoInstanceCacheModel.completed = isCompleted();

		Date completionDate = getCompletionDate();

		if (completionDate != null) {
			kaleoInstanceCacheModel.completionDate = completionDate.getTime();
		}
		else {
			kaleoInstanceCacheModel.completionDate = Long.MIN_VALUE;
		}

		kaleoInstanceCacheModel.workflowContext = getWorkflowContext();

		String workflowContext = kaleoInstanceCacheModel.workflowContext;

		if ((workflowContext != null) && (workflowContext.length() == 0)) {
			kaleoInstanceCacheModel.workflowContext = null;
		}

		return kaleoInstanceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoInstance, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((KaleoInstance)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<KaleoInstance, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<KaleoInstance, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoInstance, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((KaleoInstance)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, KaleoInstance>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private long _mvccVersion;
	private long _kaleoInstanceId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _kaleoDefinitionVersionId;
	private long _originalKaleoDefinitionVersionId;
	private boolean _setOriginalKaleoDefinitionVersionId;
	private String _kaleoDefinitionName;
	private String _originalKaleoDefinitionName;
	private int _kaleoDefinitionVersion;
	private int _originalKaleoDefinitionVersion;
	private boolean _setOriginalKaleoDefinitionVersion;
	private long _rootKaleoInstanceTokenId;
	private String _className;
	private String _originalClassName;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private boolean _completed;
	private boolean _originalCompleted;
	private boolean _setOriginalCompleted;
	private Date _completionDate;
	private Date _originalCompletionDate;
	private String _workflowContext;
	private long _columnBitmask;
	private KaleoInstance _escapedModel;

}