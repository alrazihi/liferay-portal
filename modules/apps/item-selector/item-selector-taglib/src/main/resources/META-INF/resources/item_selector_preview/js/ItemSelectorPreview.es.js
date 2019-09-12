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

import {ClayIconSpriteContext} from '@clayui/icon';
import dom from 'metal-dom';
import PropTypes from 'prop-types';
import React, {Component} from 'react';

import Header from './Header.es';
import Carousel from './Carousel.es';
import Footer from './Footer.es';

class ItemSelectorPreview extends Component {
	static propTypes = {
		handleSelectedItem: PropTypes.func.isRequired,
		headerTitle: PropTypes.string.isRequired,
		links: PropTypes.string.isRequired,
		selector: PropTypes.string
	};

	constructor(props) {
		super(props);

		let items = Array.from(document.querySelectorAll(this.props.links));

		this.state = {
			currentItem: null,
			currentItemIndex: 0,
			items: items,
			openViewer: false
		};
	}

	componentDidMount() {
		const items = this.state.items;
		const selector = this.props.selector;

		items.forEach((item, index) => {
			let clicableItem = item;

			if (selector) {
				clicableItem = item.querySelector(selector);
			}

			if (clicableItem) {
				clicableItem.addEventListener('click', e => {
					e.preventDefault();
					e.stopPropagation();

					this.setState({
						currentItem: item,
						currentItemIndex: index,
						openViewer: true
					});
				});
			}
		});
	}

	componentDidUpdate() {
		console.log('componentDidUpdate');
		console.log(this.refs);

		const sidenavToggle = this.refs.test;

		if (sidenavToggle) {
			Liferay.SideNavigation.initialize(sidenavToggle, {
				container: '.sidenav-container',
				position: 'right',
				type: 'relative',
				typeMobile: 'fixed',
				width: '320px'
			});
		}
	}

	handleClickClose = () => {
		this.setState({openViewer: false});
	};

	handleClickDone = () => {
		this.setState({openViewer: false});

		const selectedItem = this.state.currentItem;

		this.props.handleSelectedItem(selectedItem);
	};

	handleOnItemChange = (item, index) => {
		this.setState({
			currentItem: item,
			currentItemIndex: index
		});
	};

	render() {console.log('render');
		const {openViewer, currentItemIndex, currentItem, items} = this.state;

		const spritemap =
			Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg';

		const itemName = currentItem ? currentItem.dataset.title : '';

		return (
			<>
				{openViewer && (
					<div className="fullscreen item-selector-preview">
						<ClayIconSpriteContext.Provider value={spritemap}>
							<Header
								handleClickClose={this.handleClickClose}
								handleClickDone={this.handleClickDone}
								headerTitle={this.props.headerTitle}
							/>
							<button type="button" ref="test">TEST</button>
							<Carousel
								currentItemIndex={currentItemIndex}
								items={items}
								onItemChange={this.handleOnItemChange}
							/>

							<Footer
								title={itemName}
								currentIndex={currentItemIndex}
								totalItems={items.length}
							/>
						</ClayIconSpriteContext.Provider>
					</div>
				)}
			</>
		);
	}
}

export default ItemSelectorPreview;
