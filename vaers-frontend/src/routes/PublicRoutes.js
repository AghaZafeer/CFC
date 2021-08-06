import Loginscreen from 'components/login/Loginscreen';
import SignIn from 'components/login/SignIn';
import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';
import SLUGS from 'resources/slugs';

function PublicRoutes() {
    return (
        <Switch>
            <Route exact path={SLUGS.signup} component={SignIn} />
            <Redirect to={SLUGS.login} />
        </Switch>
    );
}

export default PublicRoutes;
