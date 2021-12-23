import {useCustomerPortal} from '../context';
import {pages} from '../utils/constants';
import ActivationKeys from './ActivationKeys';
import Home from './Home';
import Overview from './Overview';

const Pages = () => {
	const [{page, project, sessionId, userAccount}] = useCustomerPortal();

	if (userAccount && sessionId) {
		switch (page) {
			case pages.OVERVIEW:
				return <Overview project={project} userAccount={userAccount} />;
			case pages.ENTERPRISE_SEARCH:
				return (
					<ActivationKeys.EnterpriseSearch
						accountKey={project.accountKey}
						sessionId={sessionId}
					/>
				);
			case pages.COMMERCE:
				return (
					<ActivationKeys.Commerce
						accountKey={project.accountKey}
						sessionId={sessionId}
					/>
				);

			default:
				return <Home userAccount={userAccount} />;
		}
	}

	if (page === pages.HOME) {
		return <Home.Skeleton />;
	}

	return <ActivationKeys.Skeleton />;
};

export default Pages;
