import React from 'react';
import { createUseStyles, useTheme } from 'react-jss';
import { useHistory } from 'react-router-dom';
import SLUGS from 'resources/slugs';
import {
    IconAgents,
    IconArticles,
    IconContacts,
    IconSubscription,
    IconTickets
} from 'assets/icons';
import { convertSlugToUrl } from 'resources/utilities';
import LogoComponent from '../custom/LogoComponent';
import Menu from '../custom/MenuComponent';
import MenuItem from '../custom/MenuItemComponent';

const useStyles = createUseStyles({
    separator: {
        borderTop: ({ theme }) => `1px solid ${theme.color.lightGrayishBlue}`,
        marginTop: 16,
        marginBottom: 16,
        opacity: 0.06
    }
});

function SidebarComponent() {
    const { push } = useHistory();
    const theme = useTheme();
    const classes = useStyles({ theme });
    const isMobile = window.innerWidth <= 1080;

    async function logout() {
        push(SLUGS.login);
    }

    function onClick(slug, parameters = {}) {
        push(convertSlugToUrl(slug, parameters));
    }

    return (
        <Menu isMobile={isMobile}>
        <div style={{ paddingTop: 10, paddingBottom: 10 }}>
        	<LogoComponent />
        </div>
            <MenuItem
                id={SLUGS.dashboard}
                title='Dashboard'
                icon={IconSubscription}
                onClick={() => onClick(SLUGS.dashboard)}
            />
            <MenuItem
                id={SLUGS.reportonline}
                title='Report Online'
                icon={IconTickets}
                onClick={() => onClick(SLUGS.reportonline)}
            />
            {/* <MenuItem
                id={SLUGS.vaccenter}
                title='Covid-19 Vaccination'
                icon={IconArticles}
                onClick={() => onClick(SLUGS.vaccenter)}
            /> */}
             <MenuItem
                id={SLUGS.about}
                title='About'
                icon={IconSubscription}
                onClick={() => onClick(SLUGS.about)}
            />
            <MenuItem
                id={SLUGS.faq}
                title='FAQ'
                icon={IconAgents}
                onClick={() => onClick(SLUGS.faq)}
            />
            <MenuItem
                id={SLUGS.contacts}
                title='Contact Us'
                icon={IconArticles}
                onClick={() => onClick(SLUGS.contacts)}
            />
            <MenuItem
                id={SLUGS.search}
                title='Search'
                icon={IconArticles}
                onClick={() => onClick(SLUGS.search)}
            />
            <div className={classes.separator}></div>
        </Menu>
    );
}
export default SidebarComponent;
