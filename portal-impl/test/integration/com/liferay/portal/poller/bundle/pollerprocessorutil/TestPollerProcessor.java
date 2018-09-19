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

package com.liferay.portal.poller.bundle.pollerprocessorutil;

import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Philip Jones
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=PollerProcessorUtilTest",
		"service.ranking:Integer=" + Integer.MAX_VALUE
	},
	service = PollerProcessor.class
)
public class TestPollerProcessor implements PollerProcessor {

	@Override
	public PollerResponse receive(PollerRequest pollerRequest) {
		return null;
	}

	@Override
	public void send(PollerRequest pollerRequest) {
		return;
	}

}