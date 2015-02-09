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

package com.liferay.wiki.subscription;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousMailTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portlet.subscriptions.test.BaseSubscriptionAuthorTestCase;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.wiki.util.WikiTestUtil;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author José Ángel Jiménez
 */
@Sync
public class WikiSubscriptionAuthorTest extends BaseSubscriptionAuthorTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE,
			SynchronousMailTestRule.INSTANCE);

	@Override
	protected long addBaseModel(long userId, long containerModelId)
		throws Exception {

		WikiPage page = WikiTestUtil.addPage(
			userId, group.getGroupId(), containerModelId,
			RandomTestUtil.randomString(), true);

		return page.getResourcePrimKey();
	}

	@Override
	protected long addContainerModel(long userId, long containerModelId)
		throws Exception {

		WikiNode node = WikiTestUtil.addNode(userId, group.getGroupId());

		return node.getNodeId();
	}

	@Override
	protected void addSubscription(long userId, long containerModelId)
		throws Exception {

		WikiNodeLocalServiceUtil.subscribeNode(userId, containerModelId);
	}

	@Override
	protected void updateBaseModel(long userId, long baseModelId)
		throws Exception {

		WikiPage page = WikiPageLocalServiceUtil.getPage(baseModelId, true);

		WikiTestUtil.updatePage(userId, page);
	}

}