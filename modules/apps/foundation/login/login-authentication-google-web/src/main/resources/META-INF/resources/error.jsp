<%--
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
--%>

<%@ include file="/init.jsp" %>

<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h2>
				<liferay-ui:message key="unable-to-sign-in-using-this-google-account" />
			</h2>

			<liferay-ui:error exception="<%= Exception.class %>" message="there-was-an-unknown-error" />
			<liferay-ui:error exception="<%= StrangersNotAllowedException.class %>" message="only-registered-users-are-allowed-to-sign-in-using-google" />
			<liferay-ui:error exception="<%= UserEmailAddressException.MustNotUseCompanyMx.class %>" message="this-google-account-cannot-be-used-to-register-a-new-user-because-its-email-domain-is-reserved" />

			<aui:button-row>
				<aui:button onClick="window.close();" value="close" />
			</aui:button-row>
		</div>
	</div>
</div>