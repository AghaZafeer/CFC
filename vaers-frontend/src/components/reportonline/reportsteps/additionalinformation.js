import React, { Fragment, useState } from "react";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Button from "@material-ui/core/Button";
import { Checkbox, FormControlLabel, Grid } from "@material-ui/core";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import axios from "axios";
import { convertArraytoJsonObject } from "resources/utilities";
import { CustomAlertDialog } from "resources/customAlertDialog";
import ClipLoader from "react-spinners/ClipLoader";
import { css } from "@emotion/react";
// Destructure props
const Confirm = ({
  handleNext,
  handleBack,
  values: { title,firstName,middleName, lastName, email ,address, contactNo,altcontactNo, aadharnumber,userBeneficiaryRefID,date,gender,ispregnant,
    relationToUser,reporterTitle,reporterFirstName,reporterMiddleName,reporterLastName,reporterContactNo,reporterAltContactNo,
    reporterAddress,reporterEmail,
    vaccinename,dosages,instituteName,dateVaccination,symptom, existingIllness,existingallergy
    }
}) => {
  const [termEvt1, setTermEvt1] = React.useState({term:false});
  const [termEvt2, setTermEvt2] = React.useState({term:false});
  const [caseNumber, setCaseNumber] = React.useState("");
  let [loading, setLoading] = React.useState(false);
  let [color, setColor] = React.useState("#ffffff");
  const override = css`display: block;margin: 0 auto;border-color: blue;`;
  const changeTerm1 = (e) => {
    setTermEvt1({ ...termEvt1,[e.target.name]: e.target.checked });
  };

  const changeTerm2 = (e) => {
    setTermEvt2({ ...termEvt2,[e.target.name]: e.target.checked });
  };
  const handleCloseDialog = () => {
    setCaseNumber("");
    handleNext();
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
    'userBeneficiaryRefID':userBeneficiaryRefID,
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
    setLoading(true);
    axios.post('/reportAdverseEffects', form)
        .then(response => {
          if (response.data.status === 'SUCCESS') {
            //alert("Case Number Generated by the System :- " + response.data.caseNumber);
            //handleNext();
            setCaseNumber(response.data.caseNumber);
            setLoading(false);
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
              <ListItemText primary="Beneficiary Reference ID" secondary={userBeneficiaryRefID} />
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
           <div style={{position: 'absolute' , display: 'flex', align :'center', justifyContent:'center', zIndex: 100}}>
             <ClipLoader color={color} loading={loading} css={override} size={300} />
           </div>
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
      { caseNumber !== '' ? (
            <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Case Number"} message ={"The Case number generated by the System:-" + caseNumber} />
      ) :null}
      <div style={{ display: "flex", marginTop: 50, justifyContent: "flex-end" }}>
        <Button variant="contained" color="default" onClick={handleBack}>
          Back
        </Button>
        
        <Button
          style={{ background: '#07A9E0',marginLeft: 20 }}
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
