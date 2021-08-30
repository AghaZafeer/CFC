import React , { Fragment } from 'react';
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import {Divider} from '@material-ui/core';
import axios from 'axios';
import Modal from '@material-ui/core/Modal';
import ListItemText from '@material-ui/core/ListItemText';
import ClipLoader from "react-spinners/ClipLoader";
import { css } from "@emotion/react";
import { CustomAlertDialog } from "resources/customAlertDialog";
import ListItem from "@material-ui/core/ListItem";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';

function rand() {
    return Math.round(Math.random() * 20) - 10;
} 
function getModalStyle() {
    const top = 50 + rand();
    const left = 50 + rand();
    return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
    };
}

const useStyles = makeStyles(theme => ({
    modal: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    paper: {
        position: 'absolute',
        width: 900,
        height:700,
        backgroundColor: theme.palette.background.paper,
        boxShadow: theme.shadows[5],
        padding: theme.spacing(2, 4, 3),
    },
}));

export default function SearchComponent() {
    const [isCheckOtp, setIsCheckOtp] = React.useState(false);
    const [otp, setOtp] = React.useState("");
    const [aadhar, setAadhar] = React.useState("");
    const [errorMessage, setErrorMessage] = React.useState("");
    const [showingMessage, setShowingMessage] = React.useState("");
    let [loading, setLoading] = React.useState(false);
    let [color, setColor] = React.useState("#ffffff");
    const [isValidOtp, setIsValidOtp] = React.useState(false);
    const [firstName, setFirstName] = React.useState("");
    const [middleName, setMiddleName] = React.useState("");
    const [lastName, setLastName] = React.useState("");
    const [contactNumber, setContactNumber] = React.useState("");
    const [alternateContactNumber, setAlternateContactNumber] = React.useState("");
    const [userTitle, setUserTitle] = React.useState("");
    const [emailId, setEmailId] = React.useState("");
    const [aadhaarNumber, setAadhaarNumber] = React.useState("");
    const [address, setAddress] = React.useState("");
    const [beneficiaryReferenceID, setBeneficiaryReferenceID] = React.useState("");
    const [gender, setGender] = React.useState("");
    const [dob, setDob] = React.useState("");
    const [pregnant, setPregnant] = React.useState("");
    const [illness, setIllness] = React.useState("");
    const [allegy, setAllergy] = React.useState("");
    const [userVaccineReporterAdverseEffectRecord , setUserVaccineReporterAdverseEffectRecord] = React.useState([]);
    const [reportedAdverseEffectRecordOuts , setReportedAdverseEffectRecordOuts] = React.useState([]);
    const override = css`display: block;margin: 0 auto;border-color: blue;`;
    const classes = useStyles();
    const [modalStyle] = React.useState(getModalStyle);
    const [open, setOpen] = React.useState(false);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };
    const search = (event) => {
        setLoading(true);
        setIsValidOtp(false);
        const form = {'aadhaarNumber' : aadhar,
        }
        axios.post('/searchByAadhaar', form)
        .then(response => {
          if (response.data.isValid) {
            setIsCheckOtp(true);  
            setShowingMessage(response.data.message);
            setLoading(false);
          }else {
            setIsCheckOtp(false);
            setErrorMessage(response.data.message);  
            setLoading(false);
          }
        }).catch(err => {setLoading(false);});
       
      };
      const validateOTP = (event) => {
        setLoading(true);
        const form = {'aadhaarNumber' : aadhar, 'otpCode' : otp}
        axios.post('/validateOTPInSearch', form)
        .then(response => {
            if (response.data.isValid) {
                setFirstName(response.data.searchUserRecordOut.firstName);
                setLastName(response.data.searchUserRecordOut.lastName);
                setMiddleName(response.data.searchUserRecordOut.middleName);
                setContactNumber(response.data.searchUserRecordOut.contactNumber);
                setAddress(response.data.searchUserRecordOut.address);
                setAlternateContactNumber(response.data.searchUserRecordOut.alternateContactNumber);
                setUserTitle(response.data.searchUserRecordOut.userTitle);
                setAadhaarNumber(response.data.searchUserRecordOut.aadhaarNumber);
                setBeneficiaryReferenceID(response.data.searchUserRecordOut.beneficiaryReferenceID);
                setGender(response.data.searchUserRecordOut.gender);
                setPregnant(response.data.searchUserRecordOut.isPregnant);
                setDob(response.data.searchUserRecordOut.dateOfBirth);
                setEmailId(response.data.searchUserRecordOut.emailID);
                setUserVaccineReporterAdverseEffectRecord(response.data.searchUserRecordOut.userVaccineReporterAdverseEffectRecordOuts);
                if(response.data.searchUserRecordOut.userVaccineReporterAdverseEffectRecordOuts.length >0) {
                    for (var d =0 ;d<response.data.searchUserRecordOut.userVaccineReporterAdverseEffectRecordOuts.length;d++) {
                        var z = response.data.searchUserRecordOut.userVaccineReporterAdverseEffectRecordOuts[d];
                        for (var i =0 ;i<z.reportedAdverseEffectRecordOuts.length;i++){
                            reportedAdverseEffectRecordOuts.push(z.reportedAdverseEffectRecordOuts[i]);
                        }
                    }
                }
                setIsValidOtp(true);
                setIsCheckOtp(false);
                var illness="";
                var alergy = "";
                    if(response.data.searchUserRecordOut.userIllnessOuts.length >0) {
                        for (var d =0 ;d<response.data.searchUserRecordOut.userIllnessOuts.length;d++) {
                            var z = response.data.searchUserRecordOut.userIllnessOuts[d];
                            if(illness ==""){
                            illness = illness + z.illnessName;
                            }else 
                            illness = illness + "," + z.illnessName;
                        }
                        setIllness(illness);
                    }
                    if(response.data.searchUserRecordOut.userAllergiesOut.length>0) {
                        for (var d =0 ;d<response.data.searchUserRecordOut.userAllergiesOut.length;d++) {
                            var z = response.data.searchUserRecordOut.userAllergiesOut[d];
                            if(alergy ==""){
                            alergy = alergy + z.allergyName;
                            }else 
                            alergy = alergy + "," + z.allergyName;
                        }
                        setAllergy(alergy);
                        }
                setLoading(false);
            } else {
                setErrorMessage("OTP cannot be matching/active");  
                setLoading(false);
            }
       }).catch(err => {});
      };
      const handleCloseDialog = () => {
       setShowingMessage("");
       setErrorMessage("");
       };
     const handleAadharChange = (event) => {
        setAadhar(event.target.value); 
      };
      const handleOtpChange = (event) => {
        setOtp(event.target.value); 
      };
    return (
        <Fragment>
                <Grid container direction="column"
                alignItems="center"
                justify="center"
                spacing={2} noValidate>
                    <Grid item xs={12} sm={6}>
                        <TextField
                        fullWidth
                        label="Search With Aadhaar"
                        name="aadhar"
                        placeholder="Aadhaar card number"
                        value={aadhar || ""}
                        onChange={handleAadharChange}
                        margin="normal"
                        required
                        />
                        { (isCheckOtp) ? (
                        <TextField
                        fullWidth
                        label="Enter OTP"
                        name="otp"
                        placeholder="Enter OTP"
                        value={otp || ""}
                        onChange={handleOtpChange}
                        margin="normal"
                        /> ) : null  }
                    </Grid>
                    <ClipLoader color={color} loading={loading} css={override} size={150} />
                    <div style={{ display: "flex", marginTop:10, justifyContent: "flex-end" }}>
                        <Button  style={{background: '#07A9E0', marginRight:15 }} disabled = {isCheckOtp} variant="contained" color="primary" onClick={search} >Search</Button>
                        <Button  style={{background: '#07A9E0', marginRight:15 }} disabled = {!isCheckOtp} variant="contained" color="primary" onClick={validateOTP} >Validate OTP</Button>
                    </div>
                    { errorMessage &&
                    <h3 style={{ color: 'red' }}> {errorMessage } </h3> }
                    { (showingMessage !=="") ? (
                        <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Note"} message ={"The OTP has been send to the registered email ID"} />
                    ) :null}
                </Grid>
               
                
                { (isValidOtp) ? (
                <Grid container spacing={3} alignItems="center"
                justify="center">
                    <Grid item xs={12} sm={8}>
                        <ListItem>
                            <ListItemText primary="Title" secondary={userTitle} />
                            <ListItemText primary="First Name" secondary={firstName} />
                            <ListItemText primary="Middle Name" secondary={middleName} />
                            <ListItemText primary="Last Name" secondary={lastName} />
                        </ListItem>
                    </Grid>
                    <Divider/>
                    <Grid item xs={12} sm={8}>
                        <ListItem>
                        <ListItemText primary="Address" secondary={address} />
                            <ListItemText  primary="Email" secondary={emailId} />
                            <ListItemText primary="Date of Birth" secondary={dob} />
                            <ListItemText primary="Gender" secondary={gender} /> 
                        </ListItem>
                    </Grid>
                    <Grid item xs={12} sm={8}>
                        <ListItem>
                        <ListItemText primary="Aadhaar Number" secondary={aadhaarNumber} />
                        <ListItemText primary="Beneficiary Reference ID" secondary={beneficiaryReferenceID} />
                        <ListItemText primary="Phone Number" secondary={contactNumber.length > 0 ? contactNumber : "Not Provided"}/>
                        <ListItemText primary="Alt Phone Number" secondary={alternateContactNumber.length > 0 ? alternateContactNumber : "Not Provided"}/> 
                        </ListItem>
                    </Grid>
                    <Grid item xs={12} sm={8}>
                    <ListItem>
                        <ListItemText primary="Existing Illness" secondary={illness}/>
                        <ListItemText primary="Existing Allergy" secondary={allegy}/>
                    </ListItem>
                    </Grid>
                </Grid>
                 ):null}
                 { (isValidOtp) ? (
                 <Grid container spacing={2}>
                    <Grid item xs={12} sm={15}>
                        <TableContainer component={Paper}>
                                <Table  aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                    <TableCell>Case ID</TableCell>
                                    <TableCell align="right">Vaccine Name</TableCell>
                                    <TableCell align="right">Doses</TableCell>
                                    <TableCell align="right">Vaccinnation-Date</TableCell> 
                                    <TableCell align="right">Vaccination-Center</TableCell>
                                    <TableCell align="center">Adverse Event</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody> 
                                    {userVaccineReporterAdverseEffectRecord.map((row) => (
                                    <TableRow key={row.caseID}>
                                        <TableCell component="th" scope="row">
                                        {row.caseID}
                                        </TableCell>
                                        <TableCell align="right">{row.vaccineName}</TableCell>
                                        <TableCell align="right">{row.vaccineDoseName}</TableCell>
                                        <TableCell align="right">{row.vaccinnationDate}</TableCell>
                                        <TableCell align="right">{row.vaccinationCenter}</TableCell>
                                        <TableCell align="center">
                                        <Button style={{background: '#07A9E0', marginRight:15 }} variant="contained" color="primary" onClick={handleOpen}>
                                            Show Adverse event
                                        </Button>
                                        </TableCell>
                                    </TableRow>
                                    ))}
                                </TableBody>
                                </Table>
                        </TableContainer>
                    </Grid>
           </Grid>
           ):null}
           <Modal
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
                open={open}
                onClose={handleClose}
            >
                  <div style={modalStyle} className={classes.paper}>
                        <TableContainer component={Paper}>
                                <Table  aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                    <TableCell>AdverseEffectName</TableCell>
                                    <TableCell align="right">SeverityName</TableCell>
                                    <TableCell align="right">StartDate</TableCell>
                                    <TableCell align="right">Date-Of-Death</TableCell> 
                                    <TableCell align="right">Date-Of-Recovery</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody> 
                                    {reportedAdverseEffectRecordOuts.map((row) => (
                                    <TableRow key={row.adverseEffectName}>
                                        <TableCell component="th" scope="row">
                                        {row.adverseEffectName}
                                        </TableCell>
                                        <TableCell align="right">{row.severityName}</TableCell>
                                        <TableCell align="right">{row.startDate}</TableCell>
                                        <TableCell align="right">{row.dateOfDeath}</TableCell>
                                        <TableCell align="right">{row.recoveryDate}</TableCell>
                                    </TableRow>
                                    ))}
                                </TableBody>
                                </Table>
                        </TableContainer>
                   </div>
            </Modal>
        </Fragment>
    );
}