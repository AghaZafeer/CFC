import React, { useEffect } from 'react';
import { Column, Row } from 'simple-flexbox';
import { createUseStyles, useTheme } from 'react-jss';
import {Doughnut, Bar} from 'react-chartjs-2';
import {  exportGraphCriteria } from 'resources/dataService';
import { FormControl, MenuItem, Select, InputLabel, ListItem, ListItemText, Divider, List, TableCell, TableBody, TableHead, Table, TableContainer, Paper, Grid, TableRow } from '@material-ui/core';
import { Fragment } from 'react';
const data = [];

for (let x = 1; x <= 24; x++) {
    data.push({ x: x, y: Math.floor(Math.random() * 100) });
}

const useStyles = createUseStyles((theme) => ({
    container: {
        backgroundColor: '#FFFFFF',
        border: `1px solid ${theme.color.lightGrayishBlue2}`,
        borderRadius: 4,
        cursor: 'pointer'
    },
    graphContainer: {
        marginTop: 24,
        marginLeft: 0,
        marginRight: 0,
        width: '100%'
    },
    graphSection: {
        padding: 24
    },
    graphSubtitle: {
        ...theme.typography.smallSubtitle,
        color: theme.color.grayishBlue2,
        marginTop: 4,
        marginRight: 8
    },
    graphTitle: {
        ...theme.typography.cardTitle,
        color: theme.color.veryDarkGrayishBlue
    },
    legendTitle: {
        ...theme.typography.smallSubtitle,
        fontWeight: '600',
        color: theme.color.grayishBlue2,
        marginLeft: 8
    },
    separator: {
        backgroundColor: theme.color.lightGrayishBlue2,
        width: 1,
        minWidth: 1
    },
    statContainer: {
        borderBottom: `1px solid ${theme.color.lightGrayishBlue2}`,
        padding: '24px 32px 24px 32px',
        height: 'calc(114px - 48px)',
        '&:last-child': {
            border: 'none'
        }
    },
    stats: {
        borderTop: `1px solid ${theme.color.lightGrayishBlue2}`,
        width: '100%'
    },
    statTitle: {
        fontWeight: '600',
        fontSize: 20,
        lineHeight: '22px',
        letterSpacing: '0.3px',
        textAlign: 'center',
        color: theme.color.grayishBlue2,
        whiteSpace: 'nowrap',
        marginBottom: 6
    },
    statValue: {
        ...theme.typography.title,
        textAlign: 'center',
        color: theme.color.veryDarkGrayishBlue
    },
    statadverse: {
        ...theme.typography.title,
        textAlign: 'center',
        fontSize: 20,
        color: theme.color.veryDarkGrayishBlue
    }
    ,
    statSeverity: {
        ...theme.typography.title,
        textAlign: 'bottom',
        color: theme.color.blue,
        fontSize: 10,
        whiteSpace: 'nowrap'
    }
}));

function TodayTrendsComponent(props) {
    const theme = useTheme();
    const classes = useStyles({ theme });
    const {items,selectedVaccine,selectedDosage } = props;
    const [totalUserVaccineWise, setTotalUserVaccineWise] = React.useState();
    const [totalSymptoms, setTotalSymptoms] = React.useState([]);
    const [graphcriteria, setGraphCriteria] = React.useState("By Doses");
    const [adverseeventcriteria, setAdverseEventCriteria] = React.useState("");
    const [todayDate] = React.useState({date: new Date()});
    const graphCriteria = exportGraphCriteria();
    
    const handleChange = (e) => {
        setGraphCriteria(e.target.value);
    }

    const handleAdverseEventChange = (e) => {
        if(e.target.value === 'UnSelect') {
           setAdverseEventCriteria("");
        } else {
           setAdverseEventCriteria(e.target.value);
        }
    }

    const options = {
        showLines: true,
        tooltips: {
            callbacks: {
              label: function(tooltipItem, data) {
                return data['labels'][tooltipItem['index']] + ': ' + data['datasets'][0]['data'][tooltipItem['index']] + '%';
              }
            }},
        scales: {
            xAxes: [{
                barPercentage: 0.6
            }],
          yAxes: [
            {
              ticks: {
                min: 0,
                max: 100,
                stepSize: 10,
                callback: function (value) {
                  return (value / this.max * 100).toFixed(0) + '%';
                },
              },
              type: "linear",
              display: true,
              position: "right",
              id: "y-axis-1",
              gridLines: {
                drawOnArea: false,
              },
            },
          ],
        }
    }
      const severitydata = {
        labels: renderingLabels(),
        datasets: [
            {
              label: "Mile",
              backgroundColor: '#B21F00',
              borderColor: "rgba(255,99,132,1)",
              borderWidth: 1,
              stack:1,
              hoverBackgroundColor: "rgba(255,99,132,0.4)",
              hoverBorderColor: "rgba(255,99,132,1)",
              data: renderingMileData()
            },
             {
              label: "Moderate",
              backgroundColor: '#C9DE00',
              borderColor: "rgba(255,99,132,1)",
              stack:1,
              borderWidth: 1,
              hoverBackgroundColor: "rgba(255,99,132,0.4)",
              hoverBorderColor: "rgba(255,99,132,1)",
              data: renderingModerateData()
            }
            , 
            {
                label: "Severe",
                backgroundColor: '#2FDE00',
                borderColor: "rgba(255,99,132,1)",
                borderWidth: 1,
                 stack:1,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                data: renderingSevereData()
              }
          ]
      };

      const agedata = {
        labels: renderingLabels(),
        datasets: [
            {
              label: "18-40",
              backgroundColor: '#B21F00',
              borderColor: "rgba(255,99,132,1)",
              borderWidth: 1,
              stack:1,
              hoverBackgroundColor: "rgba(255,99,132,0.4)",
              hoverBorderColor: "rgba(255,99,132,1)",
              data: renderingAgeGroup1Data()
            },
             {
              label: "40-60",
              backgroundColor: '#C9DE00',
              borderColor: "rgba(255,99,132,1)",
              stack:1,
              borderWidth: 1,
              hoverBackgroundColor: "rgba(255,99,132,0.4)",
              hoverBorderColor: "rgba(255,99,132,1)",
              data: renderingAgeGroup2Data()
            }
            , 
            {
                label: ">60",
                backgroundColor: '#2FDE00',
                borderColor: "rgba(255,99,132,1)",
                borderWidth: 1,
                 stack:1,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                data: renderingAgeGroup3Data()
              }
          ]
      };

      const adverseEventGraphData = {
        labels: ["18-40","40-60",">60"],
        datasets: [
            {
              label: adverseeventcriteria,
              backgroundColor: '#B21F00',
              borderColor: "rgba(255,99,132,1)",
              borderWidth: 1,
              stack:1,
              hoverBackgroundColor: "rgba(255,99,132,0.4)",
              hoverBorderColor: "rgba(255,99,132,1)",
              data: renderingAdverseEventGraphData()
            }
          ]
      };

    var dosesdata = {
        labels:renderingLabels()
        ,
        datasets: [
          {
            label: 'Adverse Event',
            backgroundColor: [
              '#B21F00',
              '#C9DE00',
              '#2FDE00',
              '#00A6B4',
              '#6800B4'
            ],
            hoverBackgroundColor: [
            '#501800',
            '#4B5000',
            '#175000',
            '#003350',
            '#35014F'
            ],
            data: renderingPieData()
          }
        ]
      } 
  function renderingLabels(){
    const r =  totalSymptoms.map((item) =>  {return (item.name.toString())})
     return r;
    }

    function renderingPieData(){
        const r =  totalSymptoms.map((item) => {return (item.totalusercount.toString())})
        console.log("r1" , r);
        return r;
        }
    function renderingMileData(){
            const r =  totalSymptoms.map((item) => {
                 var milepercent =  ((item.mileusercount/item.totalusercount) * 100).toFixed(3);
                 return (milepercent.toString())
                })
                
               return r;
    }

    function renderingModerateData(){
        const r =  totalSymptoms.map((item) => {
            var moderatepercent =  ((item.moderateusercount/item.totalusercount) * 100).toFixed(3);
             return (moderatepercent.toString())
            })
            return r;
     }

   function renderingSevereData(){
            const r =  totalSymptoms.map((item) => {
                var severepercent =  ((item.severeusercount/item.totalusercount) * 100).toFixed(3);
                return (severepercent.toString())
              })
               return r;
     }
    
     function renderingAgeGroup1Data(){
        const r =  totalSymptoms.map((item) => {
            var severepercent =  ((item.agegroup1/item.totalusercount) * 100).toFixed(3);
            return (severepercent.toString())
          })
           return r;
     }
     
     function renderingAgeGroup2Data(){
        const r =  totalSymptoms.map((item) => {
            var severepercent =  ((item.agegroup2/item.totalusercount) * 100).toFixed(3);
            return (severepercent.toString())
          })
           return r;
     }

     function renderingAgeGroup3Data(){
        const r =  totalSymptoms.map((item) => {
            var severepercent =  ((item.agegroup3/item.totalusercount) * 100).toFixed(3);
            return (severepercent.toString())
          })
           return r;
     }

     function renderingAdverseEventGraphData() {
        const r =  totalSymptoms.map((item) => {
            if(item.name === adverseeventcriteria){
                var agegroup1 =  ((item.agegroup1/item.totalusercount) * 100).toFixed(3);
                var agegroup2 =  ((item.agegroup2/item.totalusercount) * 100).toFixed(3);
                var agegroup3 =  ((item.agegroup3/item.totalusercount) * 100).toFixed(3);
             const arr= [agegroup1,agegroup2,agegroup3];
             return arr;
            }
          })
          for (var i =0 ;i<r.length;i++) 
          {
              var y = r[i];
              if(y !== undefined) {
                return y
              }
          }
     }
useEffect(() => {
       var datas = []; 
       var optiondatas = [];
       totalSymptoms.splice(0,totalSymptoms.length);
        items.map((item) => { 
            if(selectedVaccine === item.vaccineName) {
              setTotalUserVaccineWise(item.totaluser);
              item.listofadverseevent.map((lstItem) => {
                lstItem.symptoms.map((it) => {
                    if(!datas.includes(it.value))
                    datas.push({ "name":it.value,"totalusercount":it.totaluser,"mileusercount":it.milduser, 
                    "moderateusercount":it.moderateuser , "severeusercount":it.severeuser , "agegroup1":it.agegroup1,"agegroup2":it.agegroup2, "agegroup3":it.agegroup3  });
                })
              })
              var result = datas.reduce(function(acc, val){
                var o = acc.filter(function(obj){
                    return obj.name===val.name;
                }).pop() || {name:val.name,totalusercount:0,mileusercount:0,moderateusercount:0,severeusercount:0,agegroup1:0 , agegroup2:0 , agegroup3:0};
                o.totalusercount += val.totalusercount;
                o.mileusercount += val.mileusercount;
                o.moderateusercount += val.moderateusercount;
                o.severeusercount += val.severeusercount;
                o.agegroup1 += val.agegroup1;
                o.agegroup2 += val.agegroup2;
                o.agegroup3 += val.agegroup3;
                acc.push(o);
                return acc;
            },[]);
            datas = result.filter(function(elem, pos) {
                return result.indexOf(elem) === pos;
               });
              item.listofadverseevent.map((lstItem) => {
                  if(selectedDosage === lstItem.vaccineDoseName) {
                    optiondatas.splice(0,datas.length);
                    console.log(lstItem.vaccineDoseName);
                      setTotalUserVaccineWise(lstItem.totaluser);
                      lstItem.symptoms.map((lstSym) => {
                        optiondatas.push({"name":lstSym.value,"totalusercount":lstSym.totaluser, "mileusercount" :lstSym.milduser,
                           "moderateusercount":lstSym.moderateuser,"severeusercount":lstSym.severeuser,"agegroup1":lstSym.agegroup1,"agegroup2":lstSym.agegroup2, "agegroup3":lstSym.agegroup3});
                      })
                  }
              })
              if (optiondatas.length >0) {
                setTotalSymptoms(optiondatas);
              } else {
                setTotalSymptoms(datas);
              }
            }
        })
        }, [selectedVaccine,selectedDosage]);
    function renderLegend(color, title) {
        return (
            <Row vertical='center'>
                <FormControl fullWidth required margin="normal">
                <Select  onChange={handleChange} name="graphcriteria">
                {graphCriteria.map((g) =>
                <MenuItem value={g.value}>{g.value}</MenuItem>
                )}
                </Select>
            </FormControl>
            </Row>
        );
    }

    function renderAdverseEvent(color, title) {
        return (
                <FormControl fullWidth  margin="normal">
                <InputLabel>Select Adverse Event</InputLabel>
                <Select  onChange={handleAdverseEventChange} name="selectedAdverseevent">
                <MenuItem value="UnSelect">UnSelect</MenuItem>
                {totalSymptoms.map((itm) => {
                return (<MenuItem value={itm.name}>{itm.name}</MenuItem>)
                }
                )
                }
                </Select>
            </FormControl> 
        );
    }

    function renderStat(title, value) {
        return (
            <Column
                flexGrow={1}
                className={classes.statContainer}
                vertical='center'
                horizontal='center'
            >
                <span className={classes.statTitle}>{title}</span>
                <span className={classes.statValue}>{value}</span>
            </Column>
        );
    }

    return (
        <Row
            flexGrow={1}
            className={classes.container}
            horizontal='center'
            breakpoints={{ 1024: 'column' }}
        >
            <Column
                wrap
                flexGrow={3}
                flexBasis='200px'
                className={classes.graphSection}
                breakpoints={{ 1024: {width: 'calc(100% - 80px)', flexBasis: 'auto' } }}
            >
                <Row wrap horizontal='space-between'>
                    <Column>
                        <span className={classes.graphTitle}> Adverse Event for Vaccine :- {selectedVaccine}, {selectedDosage}</span>
                        <span className={classes.graphSubtitle}>as of {todayDate.date.toString()}</span>
                    </Column>
                    {renderLegend(theme.color.lightBlue, 'Today')}
                    {(graphcriteria === 'By Ages') ? 
                    (  renderAdverseEvent(theme.color.lightBlue, 'Today')
                    ) : null
                 }
                </Row>
                <div className={classes.graphContainer}>
                {(graphcriteria === 'By Doses') ? 
                    ( <Doughnut
                                data={dosesdata}
                                options={{
                                    title:{
                                    display:true,
                                    text:'Adverse Event',
                                    fontSize:20
                                    },
                                    legend:{
                                    display:true,
                                    position:'right'
                                    }
                                }}
                        />) : null 
                    } 
                 {(graphcriteria === 'By Severity') ? 
                    (  <Bar 
                        data = { severitydata }  
                        options = {options}
                        height="200px"
                      />) : null
                 }
                 {(graphcriteria === 'By Ages' && adverseeventcriteria === '') ? 
                    (  
                    <Bar 
                        data = { agedata }  
                        options = {options}
                        height="200px"
                      /> ) : null
                 }
                   {(graphcriteria === 'By Ages' && adverseeventcriteria !== '') ? 
                    (  
                    <Bar 
                        data = { adverseEventGraphData }  
                        options = {options}
                        height="200px"
                      /> ) : null
                 }

                </div>
            </Column>
            <Column className={classes.separator} breakpoints={{ 1024: { display: 'none' } }}>
                <div />
            </Column>
            <Column flexGrow={3} flexBasis='200px' breakpoints={{ 512: classes.stats }}>
                    {renderStat( "Total"+ " " + JSON.stringify(selectedVaccine) + " "+'User',totalUserVaccineWise)}
          {(graphcriteria === 'By Doses') ? (
                      <Grid item xs={12} sm={15}>
                          <TableContainer component={Paper}>
                                <Table  aria-label="simple table">
                                  <TableHead>
                                    <TableRow>
                                      <TableCell>Adverse Event</TableCell>
                                      <TableCell>Total User</TableCell>
                                    </TableRow>
                                  </TableHead>
                                  <TableBody> 
                                    {totalSymptoms.map((row) => (
                                      <TableRow key={row.name}>
                                        <TableCell component="th" scope="row">
                                          {row.name}
                                        </TableCell>
                                        <TableCell>{row.totalusercount}</TableCell>
                                      </TableRow>
                                    ))}
                                  </TableBody>
                                </Table>
                        </TableContainer>
                   </Grid> 
                      ):null }
          {(graphcriteria === 'By Severity') ? (
            <Grid item xs={12} sm={15}>
                    <TableContainer component={Paper}>
                          <Table  aria-label="simple table">
                            <TableHead>
                              <TableRow>
                                <TableCell>Adverse Event</TableCell>
                                <TableCell align="right">Mile</TableCell>
                                <TableCell align="right">Moderate</TableCell>
                                <TableCell align="right">Severe</TableCell>
                                <TableCell align="right">Total User</TableCell>
                              </TableRow>
                            </TableHead>
                            <TableBody> 
                              {totalSymptoms.map((row) => (
                                <TableRow key={row.name}>
                                  <TableCell component="th" scope="row">
                                    {row.name}
                                  </TableCell>
                                  <TableCell align="right">{row.mileusercount}</TableCell>
                                  <TableCell align="right">{row.moderateusercount}</TableCell>
                                  <TableCell align="right">{row.severeusercount}</TableCell>
                                  <TableCell align="right">{row.totalusercount}</TableCell>
                                </TableRow>
                              ))}
                            </TableBody>
                          </Table>
                  </TableContainer>
              </Grid> 
           ) : null}
           {(graphcriteria === 'By Ages') ? (
            <Grid item xs={12} sm={15}>
                    <TableContainer component={Paper}>
                          <Table  aria-label="simple table">
                            <TableHead>
                              <TableRow>
                                <TableCell>Adverse Event</TableCell>
                                <TableCell align="right">18-40</TableCell>
                                <TableCell align="right">40-60</TableCell>
                                <TableCell align="right">Above 60</TableCell>
                                <TableCell align="right">Total User</TableCell>
                              </TableRow>
                            </TableHead>
                            <TableBody> 
                              {totalSymptoms.map((row) => (
                                <TableRow key={row.name}>
                                  <TableCell component="th" scope="row">
                                    {row.name}
                                  </TableCell>
                                  <TableCell align="right">{row.agegroup1}</TableCell>
                                  <TableCell align="right">{row.agegroup2}</TableCell>
                                  <TableCell align="right">{row.agegroup3}</TableCell>
                                  <TableCell align="right">{row.totalusercount}</TableCell>
                                </TableRow>
                              ))}
                            </TableBody>
                          </Table>
                  </TableContainer>
              </Grid> 
           ) : null}
                    </Column>
        </Row>
    );
}

export default TodayTrendsComponent;
