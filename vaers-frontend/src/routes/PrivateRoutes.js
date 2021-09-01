import React, { Suspense, lazy } from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';
import SLUGS from 'resources/slugs';
import LoadingComponent from 'components/loading';
import ReportOnlineComponent from 'components/reportonline/reportonline';
import Loginscreen from 'components/login/Loginscreen';
import FaqComponent from 'components/Faq/FaqComponent';
import ContactUsComponent from 'components/contactUs/ContactUsComponent';
import SearchComponent from 'components/search/SearchComponent';
import AboutComponent from 'components/about';
const DashboardComponent = lazy(() => import('components/dashboard'));

function PrivateRoutes() {
    return (
        <Suspense fallback={<LoadingComponent loading />}>
            <Switch>
                <Route exact path={SLUGS.about} component={AboutComponent}/>
                <Route exact path={SLUGS.search} component={SearchComponent}  />
                <Route exact path={SLUGS.dashboard} component={DashboardComponent} />
                <Route exact path={SLUGS.reportonline} component={ReportOnlineComponent} />
                <Route exact path={SLUGS.faq} component={FaqComponent} />
                <Route exact path={SLUGS.contacts} component={ContactUsComponent} />
                {/* <Route exact path={SLUGS.vaccenter} render={() => <div>Covid 19 Vaccination Centre</div>} /> */}
                <Route exact path={SLUGS.login} component={Loginscreen} />
                <Redirect to={SLUGS.dashboard} component={DashboardComponent} />
            </Switch>
        </Suspense>
    );
}

export default PrivateRoutes;
