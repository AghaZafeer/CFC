import React, { Suspense, lazy } from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';
import SLUGS from 'resources/slugs';
import LoadingComponent from 'components/loading';
import ReportOnlineComponent from 'components/reportonline/reportonline';
import Loginscreen from 'components/login/Loginscreen';
const DashboardComponent = lazy(() => import('components/dashboard'));

function PrivateRoutes() {
    return (
        <Suspense fallback={<LoadingComponent loading />}>
            <Switch>
                <Route exact path={SLUGS.about} render={() => <div>About</div>} />
                <Route exact path={SLUGS.dashboard} component={DashboardComponent} />
                <Route exact path={SLUGS.reportonline} component={ReportOnlineComponent} />
                <Route exact path={SLUGS.faq} render={() => <div>FAQ</div>} />
                <Route exact path={SLUGS.contacts} render={() => <div>Contact Us</div>} />
                <Route exact path={SLUGS.vaccenter} render={() => <div>Covid 19 Vaccination Centre</div>} />
                <Route exact path={SLUGS.login} component={Loginscreen} />
                <Redirect to={SLUGS.dashboard} />
            </Switch>
        </Suspense>
    );
}

export default PrivateRoutes;
