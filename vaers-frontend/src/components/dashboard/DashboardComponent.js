import React from 'react';
import { Column, Row } from 'simple-flexbox';
import { createUseStyles } from 'react-jss';
import MiniCardComponent from 'components/cards/MiniCardComponent';
import TodayTrendsComponent from './TodayTrendsComponent';
import CardComponent from 'components/cards/CardComponent';
import axios from 'axios';

const useStyles = createUseStyles({
    cardsContainer: {
        marginRight: -30,
        marginTop: -30
    },
    cardRow: {
        marginTop: 30,
        '@media (max-width: 768px)': {
            marginTop: 0
        }
    },
    miniCardContainer: {
        flexGrow: 1,
        marginRight: 30,
        '@media (max-width: 768px)': {
            marginTop: 30,
            maxWidth: 'none'
        }
    },
    todayTrends: {
        marginTop: 30
    },
    lastRow: {
        marginTop: 30
    },
    unresolvedTickets: {
        marginRight: 30,
        '@media (max-width: 1024px)': {
            marginRight: 0
        }
    },
    tasks: {
        marginTop: 0,
        '@media (max-width: 1024px)': {
            marginTop: 30
        }
    }
});

function DashboardComponent() {
    const classes = useStyles();
    const [vaccinename, setVaccinename] = React.useState(""); // Default Values
    const [dosages, setDosages] = React.useState(""); // Default Values
    const [graphrecord, setGraphRecord]= React.useState({totalUser:"", data: []});
    const onComplete = (vaccinename, dosages) => {
        setVaccinename(vaccinename);
        setDosages(dosages);
    }
    const [noOfVaccines, setNoOfVaccines] = React.useState([]);
    React.useEffect(() => {
      axios.get('/getVaccineList').then(res => {
        setNoOfVaccines(res.data)
      }).catch(err => console.log(err))
      axios.get('/getReportAndGraphData').then(res => {
          console.log(res.data);
        setGraphRecord({totalUser:res.data.totalNoOfUsers,data:res.data.listOfVaccineDataOuts});
      }).catch(err => console.log(err))
     }, [])
    return (
        <Column>
            <Row
                className={classes.cardsContainer}
                wrap
                flexGrow={1}
                horizontal='space-between'
                breakpoints={{ 768: 'column' }}
            >
                <Row
                    className={classes.cardRow}
                    wrap
                    flexGrow={1}
                    horizontal='space-between'
                    breakpoints={{ 384: 'column' }}
                >
                    <MiniCardComponent
                        className={classes.miniCardContainer}
                        title='Number of Registered Users'
                        value={graphrecord.totalUser}
                    />
                    <CardComponent onComplete = {onComplete} title="Use Filter for Graph generation" subtitle ="" link ="" items={noOfVaccines} > </CardComponent>
               
                </Row>
                <Row
                    className={classes.cardRow}
                    wrap
                    flexGrow={1}
                    horizontal='space-between'
                    breakpoints={{ 384: 'column' }}
                >  
                </Row>
            </Row>
            <div className={classes.todayTrends}>
                <TodayTrendsComponent items={graphrecord.data} selectedVaccine ={vaccinename} selectedDosage= {dosages} />
            </div>
        </Column>
    );
}

export default DashboardComponent;
