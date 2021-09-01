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
import theme from 'resources/theme';
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
                <Route exact path={SLUGS.integrate} render={
                    () =>  <div >
                    <h1 style={{color: theme.color.brightBlue}} >Integration Channel</h1><br />
                    <b style={{color: theme.color.veryDarkGrayishBlue}}> 
                             Pharma companies, Govt authorities can use this data through Integration channels for analysis and report generation and to predict trend. This feature is yet to be developed  based on agreement with other parties.
                     </b>
                        </div>} />
                <Route exact path={SLUGS.consult} render={
                    () =>  <div >
                    <b style={{color: theme.color.veryDarkGrayishBlue}}> 
                           This page is under maintainance
                     </b>
                        </div>} />
                <Route exact path={SLUGS.login} component={Loginscreen} />
                <Redirect to={SLUGS.dashboard} component={DashboardComponent} />
            </Switch>
        </Suspense>
    );
}

export default PrivateRoutes;
