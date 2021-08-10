import React, { Fragment, useState } from "react";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Button from "@material-ui/core/Button";
import { Checkbox, FormControlLabel, FormGroup, Grid } from "@material-ui/core";
import { CheckBox } from "@material-ui/icons";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import axios from "axios";
import { convertArraytoJsonObject } from "resources/utilities";
// Destructure props
const Confirm = ({
  handleNext,
  handleBack,
  values: { title,firstName,middleName, lastName, email ,address, contactNo,altcontactNo, aadharnumber,age,date,gender,ispregnant,
    relationToUser,reporterTitle,reporterFirstName,reporterMiddleName,reporterLastName,reporterContactNo,reporterAltContactNo,
    reporterAddress,reporterEmail,
    vaccinename,dosages,instituteName,dateVaccination,symptom, existingIllness,existingallergy
    }
}) => {
  const [termEvt1, setTermEvt1] = React.useState({term:false});
  const [termEvt2, setTermEvt2] = React.useState({term:false});
  const changeTerm1 = (e) => {
    setTermEvt1({ ...termEvt1,[e.target.name]: e.target.checked });
  };

  const changeTerm2 = (e) => {
    setTermEvt2({ ...termEvt2,[e.target.name]: e.target.checked });
  };
  const handleSubmit = () => {
    const form = {'userTitleID' : title,
    'userFirstName' : firstName,
    'userMiddleName' : middleName, 
    'userLastName' : lastName, 
    'userEmail':email , 
    'userAdverseEffectIns' : symptom, 
    'userAddress' :address,
    'userMobile': contactNo, 
    'userAlternateMobile': altcontactNo, 
    'userAadhaarNumber':aadharnumber,
    'userAge':age,
    'dateOfBirth':date,
    'userSex': gender,
    'userIsPregnant' : ispregnant, 
    'reporterTitleID':reporterTitle,
    'reporterFirstName':reporterFirstName,
    'reporterMiddleName':reporterMiddleName,
    'reporterLastName':reporterLastName,
    'reporterMobile': reporterContactNo,
    'reporterAlternateMobile': reporterAltContactNo,
    'reporterAddress':reporterAddress,
    'reporterEmail' : reporterEmail,
    'vaccineID': vaccinename,
    'vaccineDoseID' : dosages,
    'vaccinationCenter' : instituteName,
    'vaccinationDate':dateVaccination, 
    'reportedBy' : relationToUser, 
    "userIllnessIns" : convertArraytoJsonObject(existingIllness[existingIllness.length-1] ,"illnessID"), 
    'userAllergicConditionIns' : convertArraytoJsonObject(existingallergy[existingallergy.length - 1], "allergicConditionID")}
    console.log("form", form);
    axios.post('/reportAdverseEffects', form)
        .then(response => {
          if (response.data.status === 'SUCCESS') {
            handleNext();
          }
        }).catch(err => console.log(err));
  };
  return (
    <Fragment>
    <Grid container spacing={6}>
           <Grid item xs={12} sm={6}>
           <ListItem disablePadding>
            <ListItemText primary="First Name" secondary={firstName} />
            <ListItemText primary="Last Name" secondary={lastName} />
        </ListItem>
           </Grid>
           <Grid item xs={12} sm={6}>
           <ListItem>
           <ListItemText primary="Age" secondary={age} />
            <ListItemText primary="Date of birth" secondary={date} />
        </ListItem>
           </Grid>
           <Grid item xs={12} sm={6}>
           <ListItem>
           <ListItemText primary="Address" secondary={address} />
          <ListItemText  primary=" Email" secondary={email} />
        </ListItem>
           </Grid>
           <Grid item xs={12} sm={6}>
           <ListItem>
              <ListItemText primary="Gender" secondary={gender} />
              <ListItemText primary="Aadhaar Number" secondary={aadharnumber} />
           </ListItem>
           </Grid>
           <Grid item xs={12} sm={6}>
              <ListItem>
              <ListItemText primary="Phone Number" secondary={contactNo.length > 0 ? contactNo : "Not Provided"}/>
              {(existingIllness[existingIllness.length-1] !== null && existingIllness[existingIllness.length-1] !== undefined) ? (
              <ListItemText primary="Existing Illness" secondary={existingIllness[existingIllness.length-1].join(",")}/>
              ):null}
            </ListItem>
            </Grid>
            {(existingallergy[existingallergy.length-1] !== null && existingallergy[existingallergy.length-1] !== undefined) ? (
            <Grid item xs={12} sm={6}>
              <ListItem>
              <ListItemText primary="Existing Allergy" secondary={existingallergy[existingallergy.length-1].join(",")}/>
            </ListItem>
            </Grid>
            ):null}
           </Grid>
           <Grid container spacing={2}>
           <Grid item xs={12} sm={15}>
           <Divider/>
              <ListItem>
               <ListItemText primary="ReportedBy" secondary={relationToUser} />
            </ListItem>
            
           </Grid>
           {relationToUser !== 'SELF' ? (
             <Grid container spacing={2}>
           <Grid item xs={12} sm={6}>
           <ListItem>
           <ListItemText primary="ReporterFirstName" secondary={reporterFirstName} />
            <ListItemText primary="ReporterLastName" secondary={reporterLastName} />
           </ListItem>
           </Grid>
           <Grid item xs={12} sm={6}>
           <ListItem>
           <ListItemText primary="ReporterAddress" secondary={reporterAddress} />
           <ListItemText primary="ReporterContactNo" secondary={reporterContactNo} />
           </ListItem>
           </Grid>
           <Grid item xs={12} sm={15}>
           <ListItem>
           <ListItemText primary="ReporterEmail" secondary={reporterEmail} />   
           </ListItem>
           <Divider/>
           </Grid> 
           </Grid> ) : null }
           <Divider/>
           <Grid item xs={12} sm={6}>
           <ListItem>
           <ListItemText primary="Vaccinename" secondary={vaccinename} />
           <ListItemText primary="Dosages" secondary={dosages} />
           </ListItem>
           </Grid>
           <Grid item xs={12} sm={6}>
           <ListItem>
           <ListItemText primary="InstituteName" secondary={instituteName} />
           <ListItemText primary="DateVaccination" secondary={dateVaccination} />
           </ListItem> 
           </Grid>
           </Grid>
           <Divider/>
           
           <Grid container spacing={2}>
           <Grid item xs={12} sm={15}>
              <TableContainer component={Paper}>
                    <Table  aria-label="simple table">
                      <TableHead>
                        <TableRow>
                          <TableCell>Symptoms</TableCell>
                          <TableCell align="right">Date of Adverse Event</TableCell>
                          <TableCell align="right">Severity</TableCell>
                          <TableCell align="right">Date Of Death</TableCell>
                          <TableCell align="right">Date Of Recovery</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody> 
                        {symptom.map((row) => (
                          <TableRow key={row.adverseEffectID}>
                            <TableCell component="th" scope="row">
                              {row.adverseEffectID}
                            </TableCell>
                            <TableCell align="right">{row.adverseEffectReportingDate}</TableCell>
                            <TableCell align="right">{row.severityID}</TableCell>
                            {row.adverseEffectIsFatal?(<TableCell align="right">{row.dateOfDeath}</TableCell>):<TableCell align="right"></TableCell>}
                            {row.adverseEffectIsRecovered?(<TableCell align="right">{row.dateOfRecovery}</TableCell>):<TableCell align="right"></TableCell>}
                          </TableRow>
                        ))}
                      </TableBody>
                    </Table>
            </TableContainer>
           </Grid>
           </Grid>
           <Divider/>
      <Grid item xs={12} sm={6}>
      <FormControlLabel 
       control={<Checkbox checked ={termEvt1.term} value ={!termEvt1.term} name="term" onChange = {changeTerm1} color="primary"/>}
       label="I hereby confirm that the information provided is correct to the best of my knowledge"
       />
      </Grid>
      <Grid item xs={12} sm={6}>
      <FormControlLabel 
       control={<Checkbox checked ={termEvt2.term} value ={!termEvt1.term} name="term" onChange =  {changeTerm2} color="primary"/>}
       label="I hereby provide my consent allowing IBM to use my personal information"
       />
      </Grid>
      <div style={{ display: "flex", marginTop: 50, justifyContent: "flex-end" }}>
        <Button variant="contained" color="default" onClick={handleBack}>
          Back
        </Button>
        <Button
          style={{ marginLeft: 20 }}
          disabled={!termEvt1.term ? true : (!termEvt2.term)}
          variant="contained"
          color="primary"
          onClick={handleSubmit}
        >
          Confirm & Continue
        </Button>
      </div>
    </Fragment>
  );
};

export default Confirm;
