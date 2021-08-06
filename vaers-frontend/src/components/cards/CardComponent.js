import React from 'react';
import { Column, Row } from 'simple-flexbox';
import { createUseStyles, useTheme } from 'react-jss';
import { FormControl, FormControlLabel, FormLabel, InputLabel, MenuItem, Radio, RadioGroup, Select } from '@material-ui/core';
import _ from "lodash";
import { ordinal } from 'resources/utilities';

const useStyles = createUseStyles((theme) => ({
    container: {
        backgroundColor: '#FFFFFF',
        border: `1px solid ${theme.color.lightGrayishBlue2}`,
        borderRadius: 4,
        padding: '24px 32px 0px 32px',
        height: '100%'
    },
    containerMobile: {
        padding: '12px 16px 6px 16px !important'
    },
    itemContainer: {
        marginLeft: -32,
        marginRight: -32,
        paddingLeft: 32,
        paddingRight: 32,
        paddingBottom: 18,
        paddingTop: 18,
        borderBottom: `1px solid ${theme.color.lightGrayishBlue2}`,
        '&:last-child': {
            borderBottom: 'none'
        }
    },
    itemContainerMobile: {
        marginLeft: -16,
        marginRight: -16,
        paddingLeft: 16,
        paddingRight: 16
    },
    link: {
        ...theme.typography.link
    },
    subtitle: {
        ...theme.typography.smallSubtitle,
        color: theme.color.grayishBlue2
    },
    subtitle2: {
        color: theme.color.veryDarkGrayishBlue,
        marginLeft: 2
    },
    title: {
        ...theme.typography.cardTitle,
        color: theme.color.veryDarkGrayishBlue
    }
}));

function CardComponent(props) {
    const theme = useTheme();
    const classes = useStyles({ theme });
    const { title, link, subtitle, subtitleTwo, items, containerStyles, onComplete} = props;
    const [vaccinename, setVaccinename] = React.useState();
    const [dosages, setDosages] = React.useState();
    const [radioValue, setRadioValue] = React.useState();
    const handleVaccineChange = (e) => {
        setVaccinename(e.target.value);
    }
    const handleChange = (e) => {
        setDosages(e.target.value);
        onComplete(vaccinename,e.target.value);
    }
    return (
        <Column
            flexGrow={1}
            className={[classes.container, containerStyles].join(' ')}
            breakpoints={{ 426: classes.containerMobile }}
        >
            <Row horizontal='space-between'>
                <Column>
                    <span className={classes.title}>{title}</span>
                    <Row style={{ marginTop: 8, marginBottom: 16 }}>
                        <span className={classes.subtitle}>{subtitle}</span>
                        {subtitleTwo && (
                            <span className={[classes.subtitle, classes.subtitle2].join(' ')}>
                                {subtitleTwo}
                            </span>
                        )}
                    </Row>
                </Column>
                <span className={classes.link}>{link}</span>
            </Row>
            <Column
                className={classes.itemContainer}
                breakpoints={{ 426: classes.itemContainerMobile }}
            >
                <FormControl fullWidth required margin="normal">
                <InputLabel>Name of the Vaccine</InputLabel>
                    <Select value={vaccinename} onChange={handleVaccineChange} name="vaccinename">
                    {items.map((vaccine) =>
                    <MenuItem value={vaccine.vaccineName}>{vaccine.vaccineName}</MenuItem>
                    )}
                    </Select>
                </FormControl>
                {items.map((vaccine) => {
                 if(vaccine.vaccineName === vaccinename) {
                  return (<FormControl fullWidth required margin="normal">
                  <FormLabel component="legend">Dosage:</FormLabel>
                  <RadioGroup aria-label="dosages" name="dosages" value={dosages || ""}>
                  {vaccine.vaccineDoses.map((vaccinedose) => {
                      return(
                              <FormControlLabel value={vaccinedose.vaccineDoseName} control={<Radio onClick = 
                                {(e) => {handleChange(e)}} color="primary"/>} label={vaccinedose.vaccineDoseName} />
                      ) 
                      })}
                      <FormControlLabel value="All" control={<Radio onClick = 
                            {(e) => {handleChange(e)}} color="primary"/>} label="All" />
                      </RadioGroup>
                      </FormControl> )
                 }
              })}
            </Column>
        </Column>
    );
}

export default CardComponent;
