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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceCatalogModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceCatalog service. Represents a row in the &quot;CommerceCatalog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CommerceCatalogModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceCatalogImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceCatalogImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceCatalogModelImpl
	extends BaseModelImpl<CommerceCatalog> implements CommerceCatalogModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce catalog model instance should use the <code>CommerceCatalog</code> interface instead.
	 */
	public static final String TABLE_NAME = "CommerceCatalog";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"externalReferenceCode", Types.VARCHAR},
		{"commerceCatalogId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"commerceCurrencyCode", Types.VARCHAR},
		{"catalogDefaultLanguageId", Types.VARCHAR}, {"system_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceCatalogId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commerceCurrencyCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("catalogDefaultLanguageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("system_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CommerceCatalog (mvccVersion LONG default 0 not null,externalReferenceCode VARCHAR(75) null,commerceCatalogId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,commerceCurrencyCode VARCHAR(75) null,catalogDefaultLanguageId VARCHAR(75) null,system_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table CommerceCatalog";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceCatalog.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CommerceCatalog.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean ENTITY_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean FINDER_CACHE_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static final boolean COLUMN_BITMASK_ENABLED = true;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long SYSTEM_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.product.model.CommerceCatalog"));

	public CommerceCatalogModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceCatalogId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceCatalogId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceCatalogId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceCatalog.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceCatalog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceCatalog, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CommerceCatalog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceCatalog, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CommerceCatalog)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceCatalog, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceCatalog, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceCatalog)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceCatalog, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceCatalog, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CommerceCatalog>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceCatalog.class.getClassLoader(), CommerceCatalog.class,
			ModelWrapper.class);

		try {
			Constructor<CommerceCatalog> constructor =
				(Constructor<CommerceCatalog>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<CommerceCatalog, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CommerceCatalog, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceCatalog, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<CommerceCatalog, Object>>();
		Map<String, BiConsumer<CommerceCatalog, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CommerceCatalog, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", CommerceCatalog::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CommerceCatalog, Long>)CommerceCatalog::setMvccVersion);
		attributeGetterFunctions.put(
			"externalReferenceCode", CommerceCatalog::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<CommerceCatalog, String>)
				CommerceCatalog::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"commerceCatalogId", CommerceCatalog::getCommerceCatalogId);
		attributeSetterBiConsumers.put(
			"commerceCatalogId",
			(BiConsumer<CommerceCatalog, Long>)
				CommerceCatalog::setCommerceCatalogId);
		attributeGetterFunctions.put(
			"companyId", CommerceCatalog::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<CommerceCatalog, Long>)CommerceCatalog::setCompanyId);
		attributeGetterFunctions.put("userId", CommerceCatalog::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<CommerceCatalog, Long>)CommerceCatalog::setUserId);
		attributeGetterFunctions.put("userName", CommerceCatalog::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<CommerceCatalog, String>)CommerceCatalog::setUserName);
		attributeGetterFunctions.put(
			"createDate", CommerceCatalog::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CommerceCatalog, Date>)CommerceCatalog::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", CommerceCatalog::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<CommerceCatalog, Date>)
				CommerceCatalog::setModifiedDate);
		attributeGetterFunctions.put("name", CommerceCatalog::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<CommerceCatalog, String>)CommerceCatalog::setName);
		attributeGetterFunctions.put(
			"commerceCurrencyCode", CommerceCatalog::getCommerceCurrencyCode);
		attributeSetterBiConsumers.put(
			"commerceCurrencyCode",
			(BiConsumer<CommerceCatalog, String>)
				CommerceCatalog::setCommerceCurrencyCode);
		attributeGetterFunctions.put(
			"catalogDefaultLanguageId",
			CommerceCatalog::getCatalogDefaultLanguageId);
		attributeSetterBiConsumers.put(
			"catalogDefaultLanguageId",
			(BiConsumer<CommerceCatalog, String>)
				CommerceCatalog::setCatalogDefaultLanguageId);
		attributeGetterFunctions.put("system", CommerceCatalog::getSystem);
		attributeSetterBiConsumers.put(
			"system",
			(BiConsumer<CommerceCatalog, Boolean>)CommerceCatalog::setSystem);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_externalReferenceCode = externalReferenceCode;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalExternalReferenceCode() {
		return getColumnOriginalValue("externalReferenceCode");
	}

	@JSON
	@Override
	public long getCommerceCatalogId() {
		return _commerceCatalogId;
	}

	@Override
	public void setCommerceCatalogId(long commerceCatalogId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceCatalogId = commerceCatalogId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
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
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
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

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	@JSON
	@Override
	public String getCommerceCurrencyCode() {
		if (_commerceCurrencyCode == null) {
			return "";
		}
		else {
			return _commerceCurrencyCode;
		}
	}

	@Override
	public void setCommerceCurrencyCode(String commerceCurrencyCode) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_commerceCurrencyCode = commerceCurrencyCode;
	}

	@JSON
	@Override
	public String getCatalogDefaultLanguageId() {
		if (_catalogDefaultLanguageId == null) {
			return "";
		}
		else {
			return _catalogDefaultLanguageId;
		}
	}

	@Override
	public void setCatalogDefaultLanguageId(String catalogDefaultLanguageId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_catalogDefaultLanguageId = catalogDefaultLanguageId;
	}

	@JSON
	@Override
	public boolean getSystem() {
		return _system;
	}

	@JSON
	@Override
	public boolean isSystem() {
		return _system;
	}

	@Override
	public void setSystem(boolean system) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_system = system;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public boolean getOriginalSystem() {
		return GetterUtil.getBoolean(
			this.<Boolean>getColumnOriginalValue("system_"));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceCatalog.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceCatalog toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceCatalog>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceCatalogImpl commerceCatalogImpl = new CommerceCatalogImpl();

		commerceCatalogImpl.setMvccVersion(getMvccVersion());
		commerceCatalogImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		commerceCatalogImpl.setCommerceCatalogId(getCommerceCatalogId());
		commerceCatalogImpl.setCompanyId(getCompanyId());
		commerceCatalogImpl.setUserId(getUserId());
		commerceCatalogImpl.setUserName(getUserName());
		commerceCatalogImpl.setCreateDate(getCreateDate());
		commerceCatalogImpl.setModifiedDate(getModifiedDate());
		commerceCatalogImpl.setName(getName());
		commerceCatalogImpl.setCommerceCurrencyCode(getCommerceCurrencyCode());
		commerceCatalogImpl.setCatalogDefaultLanguageId(
			getCatalogDefaultLanguageId());
		commerceCatalogImpl.setSystem(isSystem());

		commerceCatalogImpl.resetOriginalValues();

		return commerceCatalogImpl;
	}

	@Override
	public CommerceCatalog cloneWithOriginalValues() {
		CommerceCatalogImpl commerceCatalogImpl = new CommerceCatalogImpl();

		commerceCatalogImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		commerceCatalogImpl.setExternalReferenceCode(
			this.<String>getColumnOriginalValue("externalReferenceCode"));
		commerceCatalogImpl.setCommerceCatalogId(
			this.<Long>getColumnOriginalValue("commerceCatalogId"));
		commerceCatalogImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		commerceCatalogImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		commerceCatalogImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		commerceCatalogImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		commerceCatalogImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		commerceCatalogImpl.setName(
			this.<String>getColumnOriginalValue("name"));
		commerceCatalogImpl.setCommerceCurrencyCode(
			this.<String>getColumnOriginalValue("commerceCurrencyCode"));
		commerceCatalogImpl.setCatalogDefaultLanguageId(
			this.<String>getColumnOriginalValue("catalogDefaultLanguageId"));
		commerceCatalogImpl.setSystem(
			this.<Boolean>getColumnOriginalValue("system_"));

		return commerceCatalogImpl;
	}

	@Override
	public int compareTo(CommerceCatalog commerceCatalog) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), commerceCatalog.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceCatalog)) {
			return false;
		}

		CommerceCatalog commerceCatalog = (CommerceCatalog)object;

		long primaryKey = commerceCatalog.getPrimaryKey();

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

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceCatalog> toCacheModel() {
		CommerceCatalogCacheModel commerceCatalogCacheModel =
			new CommerceCatalogCacheModel();

		commerceCatalogCacheModel.mvccVersion = getMvccVersion();

		commerceCatalogCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			commerceCatalogCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			commerceCatalogCacheModel.externalReferenceCode = null;
		}

		commerceCatalogCacheModel.commerceCatalogId = getCommerceCatalogId();

		commerceCatalogCacheModel.companyId = getCompanyId();

		commerceCatalogCacheModel.userId = getUserId();

		commerceCatalogCacheModel.userName = getUserName();

		String userName = commerceCatalogCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceCatalogCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceCatalogCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceCatalogCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceCatalogCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceCatalogCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		commerceCatalogCacheModel.name = getName();

		String name = commerceCatalogCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			commerceCatalogCacheModel.name = null;
		}

		commerceCatalogCacheModel.commerceCurrencyCode =
			getCommerceCurrencyCode();

		String commerceCurrencyCode =
			commerceCatalogCacheModel.commerceCurrencyCode;

		if ((commerceCurrencyCode != null) &&
			(commerceCurrencyCode.length() == 0)) {

			commerceCatalogCacheModel.commerceCurrencyCode = null;
		}

		commerceCatalogCacheModel.catalogDefaultLanguageId =
			getCatalogDefaultLanguageId();

		String catalogDefaultLanguageId =
			commerceCatalogCacheModel.catalogDefaultLanguageId;

		if ((catalogDefaultLanguageId != null) &&
			(catalogDefaultLanguageId.length() == 0)) {

			commerceCatalogCacheModel.catalogDefaultLanguageId = null;
		}

		commerceCatalogCacheModel.system = isSystem();

		return commerceCatalogCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceCatalog, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CommerceCatalog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceCatalog, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((CommerceCatalog)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

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
		Map<String, Function<CommerceCatalog, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CommerceCatalog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceCatalog, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CommerceCatalog)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CommerceCatalog>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _externalReferenceCode;
	private long _commerceCatalogId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _commerceCurrencyCode;
	private String _catalogDefaultLanguageId;
	private boolean _system;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<CommerceCatalog, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((CommerceCatalog)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put(
			"externalReferenceCode", _externalReferenceCode);
		_columnOriginalValues.put("commerceCatalogId", _commerceCatalogId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put(
			"commerceCurrencyCode", _commerceCurrencyCode);
		_columnOriginalValues.put(
			"catalogDefaultLanguageId", _catalogDefaultLanguageId);
		_columnOriginalValues.put("system_", _system);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("system_", "system");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("externalReferenceCode", 2L);

		columnBitmasks.put("commerceCatalogId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("name", 256L);

		columnBitmasks.put("commerceCurrencyCode", 512L);

		columnBitmasks.put("catalogDefaultLanguageId", 1024L);

		columnBitmasks.put("system_", 2048L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private CommerceCatalog _escapedModel;

}