import React , { Fragment } from 'react';
import ReactDOM from 'react-dom';
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import {FormControlLabel, FormLabel, Radio, RadioGroup } from '@material-ui/core';
import axios from 'axios';
import Input from '@material-ui/core/Input';
import Checkbox from '@material-ui/core/Checkbox';
import ListItemText from '@material-ui/core/ListItemText';
import ClipLoader from "react-spinners/ClipLoader";
import { css } from "@emotion/react";
import { CustomAlertDialog } from "resources/customAlertDialog";
const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};
const PatientInformationComponent = ({
    handleNext,
    handleChange,
    handleUserChange,
    values: {title, firstName,middleName, lastName, email ,address, userBeneficiaryRefID,
       contactNo,altcontactNo, aadharnumber,age,date,gender,ispregnant,existingIllness,existingallergy,isValidForm, isdefaultvalue},
    formErrors
  }) => {
    const [illness, setIllness] = React.useState([]);
    const [selectedIllness, setSelectedIllness] = React.useState([]);
    const [alergy, setAlergy] = React.useState([]);
    const [selectedalergy, setSelectedalergy] = React.useState([]);
    const [titles, setTitles] = React.useState([]);
    const [isValidOtp, setIsValidOtp] = React.useState(false);
    const [otp, setOtp] = React.useState("");
    const [errorMessage, setErrorMessage] = React.useState("");
    let [loading, setLoading] = React.useState(false);
    let [color, setColor] = React.useState("#ffffff");
    const override = css`display: block;margin: 0 auto;border-color: blue;`;
    const [defaultFirstName, setDefaultFirstName] = React.useState("");
    const [defaultMiddleName, setDefaultMiddleName] = React.useState("");
    const [defaultContactNumber, setDefaultContactNumber] = React.useState("");
    const [defaultAlternateContactNumber, setDefaultAlternateContactNumber] = React.useState("");
    const [defaultUserTitle, setDefaultUserTitle] = React.useState("");
    const [defaultLastName, setDefaultLastName] = React.useState("");
    const [defaultAadhaarNumber, setDefaultAadhaarNumber] = React.useState("");
    const [defaultAddress, setDefaultAddress] = React.useState("");
    const [defaultAge, setDefaultAge] = React.useState("");
    const [defaultBeneficiaryReferenceID, setDefaultBeneficiaryReferenceID] = React.useState("");
    const [defaultGender, setDefaultGender] = React.useState("");
    const [defaultDob, setDefaultDob] = React.useState("");
    const [isAadharExist, setIsAadharExist] = React.useState(false);
    const handleChangeMultipleIllness = (event) => {
      console.log(event.target.value);
      setSelectedIllness(event.target.value);
      existingIllness.push(event.target.value);
    };
    const handleChangeMultipleAlergy = (event) => {
      setSelectedalergy(event.target.value);
      existingallergy.push(event.target.value);
    };

    const handleCloseDialog = () => {
      setIsAadharExist(false);
     };

    const sendOTP = (event) => {
      setLoading(true);
      
      const form = {'emailID' : email,
      }
      axios.post('/generateOTP', form)
      .then(response => {
        if (response.data.otpGenerationSuccessful) {
          setIsValidOtp(true);
          setLoading(false);
        }
      }).catch(err => {setLoading(false);});
    };

    const reset = (event) => {
      handleUserChange('isValidForm', false);
      setIsValidOtp(false);
      setLoading(false);
      setErrorMessage("");
    };

    const handleChange1 = (event) => {
      setOtp(event.target.value); 
    };

    const validateAadhar = () => {
      const form = {'aadhaarNumber': aadharnumber}
      axios.post('/validateAadhaar', form)
      .then(response => {
        if (!response.data.isValid) {
          setIsAadharExist(true);
          return null;
        } else {
          handleNext();
        }
      }).catch(err => {setIsAadharExist(true); return null;});
      
    };

    const validateOTP = (event) => {
      setLoading(true);
      const form = {'otpEmailID' : email, 'otpValue' : otp
    }
      axios.post('/validateOTP', form)
      .then(response => {
        let resp = response.data;
        if (response.data.isValid) {
          if(response.data.userMasterOut !== null) {
            setDefaultFirstName(response.data.userMasterOut.firstName);
            handleUserChange("firstName",response.data.userMasterOut.firstName);
            setDefaultLastName(response.data.userMasterOut.lastName);
            handleUserChange("lastName",response.data.userMasterOut.lastName);
            setDefaultAadhaarNumber(response.data.userMasterOut.aadhaarNumber);
            handleUserChange("aadharnumber",response.data.userMasterOut.aadhaarNumber);
            setDefaultAddress(response.data.userMasterOut.address);
            handleUserChange("address",response.data.userMasterOut.address);
            setDefaultAge(response.data.userMasterOut.age);
            handleUserChange("age",response.data.userMasterOut.age);
            setDefaultBeneficiaryReferenceID(response.data.userMasterOut.beneficiaryReferenceID);
            handleUserChange("userBeneficiaryRefID",response.data.userMasterOut.beneficiaryReferenceID);
            setDefaultMiddleName(response.data.userMasterOut.middleName);
            handleUserChange("middleName",response.data.userMasterOut.middleName);
            setDefaultContactNumber(response.data.userMasterOut.contactNumber);
            handleUserChange("contactNo",response.data.userMasterOut.contactNumber);
            setDefaultAlternateContactNumber(response.data.userMasterOut.alternateContactNumber);
            handleUserChange("altcontactNo",response.data.userMasterOut.alternateContactNumber);
            setDefaultUserTitle(response.data.userMasterOut.userTitle);
            handleUserChange("title",response.data.userMasterOut.userTitle);
            setDefaultGender(response.data.userMasterOut.gender);
            handleUserChange("gender",response.data.userMasterOut.gender);
            setDefaultDob(response.data.userMasterOut.dateOfBirth);
            handleUserChange("date",response.data.userMasterOut.dateOfBirth);
            var illness="";
            var alergy = "";
            if(response.data.userMasterOut.userIllnessOuts.length >0) {
                  for (var d =0 ;d<response.data.userMasterOut.userIllnessOuts.length;d++) {
                    var z = response.data.userMasterOut.userIllnessOuts[d];
                    if(illness ==""){
                      illness = illness + z.illnessName;
                    }else 
                    illness = illness + "," + z.illnessName;
                    existingIllness.push([z.illnessName]);
                  }
                  setSelectedIllness([illness]);
              }
              if(response.data.userMasterOut.userAllergiesOut.length>0) {
                  for (var d =0 ;d<response.data.userMasterOut.userAllergiesOut.length;d++) {
                    var z = response.data.userMasterOut.userAllergiesOut[d];
                    if(alergy ==""){
                      alergy = alergy + z.allergyName;
                    }else 
                    alergy = alergy + "," + z.allergyName;
                    existingallergy.push([z.allergyName]);
                  }
                  setSelectedalergy([alergy]);
                }
            handleUserChange('isdefaultvalue', true);
          } else {
            setDefaultFirstName("");
            setDefaultLastName("");
            setDefaultAadhaarNumber("");
            setDefaultAddress("");
            setDefaultAge("");
            setDefaultBeneficiaryReferenceID("");
            setDefaultMiddleName("");
            setDefaultContactNumber("");
            setDefaultAlternateContactNumber("");
            setDefaultUserTitle("");
            setDefaultGender("");
            setDefaultDob("");
            handleUserChange('isdefaultvalue', false);
          }
          handleUserChange('isValidForm', true);
          setLoading(false);
        } else {
          setErrorMessage("Not Valid Code");
          setLoading(false);
        }
      }).catch(err => {setErrorMessage("Not Valid Code");setLoading(false);});
     
    };

    React.useEffect(() => {
      axios.get('/getIllnessList').then(res => {
        setIllness(res.data);
      }).catch(err => console.log(err))
      axios.get('/getAllergicConditionsList').then(res => {
        setAlergy(res.data);
      }).catch(err => console.log(err))
      axios.get('/getListOfUserTitles').then(res => {
        setTitles(res.data);
      }).catch(err => console.log(err))
     },[])
    const isValid =
    firstName.length > 0 &&
    !formErrors.firstName &&
    lastName.length > 0 &&
    !formErrors.lastName &&
    email.length > 0 &&
    !formErrors.email  && 
    contactNo.length > 0 &&
    !formErrors.contactNo && 
    aadharnumber.length > 0 &&
    !formErrors.aadharnumber &&
    address.length > 0 &&
    !formErrors.address && 
    age.length > 0 &&
    !formErrors.age &&
    date.length > 0 &&
    gender.length > 0 &&
    title.length >0 &&
    userBeneficiaryRefID.length > 0 &&
    !formErrors.userBeneficiaryRefID;

    return (
      <Fragment>
        {(!isValidForm) ? (
        <Grid container direction="column"
        alignItems="center"
        justify="center"
        spacing={2} noValidate>
            <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  disabled = {isValidOtp}
                  label="Please validate your email"
                  name="email"
                  placeholder="Your email address"
                  type="email"
                  value={email || ""}
                  onChange={handleChange}
                  margin="normal"
                  error={!!formErrors.email}
                  helperText={formErrors.email}
                  required
                />
                {(isValidOtp) ? (
                <TextField
                  fullWidth
                  label="Enter OTP"
                  name="otp"
                  placeholder="Enter OTP"
                  onChange={handleChange1}
                  value={otp || ""}
                  margin="normal"
                /> ) : null }
              </Grid>
              <ClipLoader color={color} loading={loading} css={override} size={150} />
              <div style={{ display: "flex", marginTop:10, justifyContent: "flex-end" }}>
                <Button  style={{marginRight:15 }} disabled = {isValidOtp} variant="contained" color="primary" onClick={sendOTP} >Send OTP</Button>
                <Button  style={{marginRight:15 }} disabled = {!isValidOtp} variant="contained" color="primary" onClick={validateOTP} >Validate OTP</Button>
                <Button variant="contained" color="primary" onClick={reset} >Resend</Button>
              </div>
              { errorMessage &&
              <h3 style={{ color: 'red' }}> {errorMessage } </h3> }
        </Grid>  ) : null }
        {(isValidForm) ? (
        <Grid container spacing={2} noValidate>
              <Grid item xs={12} sm={6}>
                  <FormControl fullWidth required margin="normal">
                      <InputLabel>Title</InputLabel>
                      <Select  disabled={isdefaultvalue} value={title || (title = isdefaultvalue ? defaultUserTitle :"")} onChange={handleChange} name="title">
                      {titles.map((tit) =>
                      <MenuItem value={tit.userTitleName}>{tit.userTitleName}</MenuItem>
                      )}
                      </Select>
                  </FormControl>
                  </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="First Name"
                    name="firstName"
                    placeholder="Your first name"
                    margin="normal"
                    value={ firstName || (firstName = isdefaultvalue ? defaultFirstName :"")}
                    onChange={handleChange}
                    error={!!formErrors.firstName}
                    helperText={formErrors.firstName}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="Middle Name"
                    name="MiddleName"
                    placeholder="Your Middle name"
                    margin="normal"
                    value={middleName || (middleName = isdefaultvalue ? defaultMiddleName :"")}
                    onChange={handleChange}
                    error={!!formErrors.middleName}
                    helperText={formErrors.middleName}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="Last Name"
                    name="lastName"
                    placeholder="Your last name"
                    margin="normal"
                    value={lastName || (lastName = isdefaultvalue ? defaultLastName :"")}
                    onChange={handleChange}
                    error={!!formErrors.lastName}
                    helperText={formErrors.lastName}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    disabled={isdefaultvalue}
                    fullWidth 
                    label="Email"
                    name="Email"
                    placeholder="Email"
                    margin="normal"
                    value={email || ""}
                    onChange={handleChange}
                    error={!!formErrors.lastName}
                    helperText={formErrors.lastName}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                      fullWidth
                      disabled={isdefaultvalue}
                      label="Address"
                      name="address"
                      placeholder="Your address"
                      value={address || (address = isdefaultvalue ? defaultAddress :"")}
                      onChange={handleChange}
                      margin="normal"
                      required
                      />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="Contact Number"
                    name="contactNo"
                    placeholder="i.e: xxx-xxx-xxxx"
                    margin="normal"
                    value={contactNo || (contactNo = isdefaultvalue ? defaultContactNumber :"")}
                    onChange={handleChange}
                    error={!!formErrors.contactNo}
                    helperText={formErrors.contactNo}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="Alternative Contact Number"
                    name="altcontactNo"
                    placeholder="i.e: xxx-xxx-xxxx"
                    margin="normal"
                    value={altcontactNo || (altcontactNo = isdefaultvalue ? defaultAlternateContactNumber :"")}
                    onChange={handleChange}
                    error={!!formErrors.altcontactNo}
                    helperText={formErrors.altcontactNo}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="Aadhaar Card Number"
                    name="aadharnumber"
                    placeholder="Aadhaar Number"
                    margin="normal"
                    value={aadharnumber || (aadharnumber = isdefaultvalue ? defaultAadhaarNumber :"")}
                    onChange={handleChange}
                    error={!!formErrors.aadharnumber}
                    helperText={formErrors.aadharnumber}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="Beneficiary Reference ID"
                    name="userBeneficiaryRefID"
                    placeholder="Reference ID"
                    margin="normal"
                    value={userBeneficiaryRefID || (userBeneficiaryRefID = isdefaultvalue ? defaultBeneficiaryReferenceID :"")}
                    onChange={handleChange}
                    error={!!formErrors.userBeneficiaryRefID}
                    helperText={formErrors.userBeneficiaryRefID}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    disabled={isdefaultvalue}
                    label="Age"
                    name="age"
                    placeholder="Enter Age"
                    margin="normal"
                    value={age || (age  = isdefaultvalue ? defaultAge :"")}
                    onChange={handleChange}
                    error={!!formErrors.age}
                    helperText={formErrors.age}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  disabled={isdefaultvalue}
                  InputLabelProps={{
                    shrink: true
                  }}
                  label="Date of birth"
                  name="date"
                  type="date"
                  defaultValue={date || (date = isdefaultvalue ? defaultDob :"")}
                  onChange={handleChange}
                  margin="normal"
                  required
                />
                </Grid>
                <Grid item xs={12} sm={6}>
                <FormControl fullWidth  margin="normal">
                <InputLabel id="demo-mutiple-checkbox-label">Select Existing Illness</InputLabel>
                  <Select
                    labelId="demo-mutiple-checkbox-label"
                    id="demo-mutiple-checkbox"
                    multiple
                    value={selectedIllness}
                    disabled={isdefaultvalue}
                    onChange={handleChangeMultipleIllness}
                    input={<Input />}
                    renderValue={(selected) => selected.join(', ')}
                    MenuProps={MenuProps}
                  >
                    {illness.map((name) => (
                      <MenuItem key={name.illnessName} value={name.illnessName}>
                        <Checkbox color="primary" checked={selectedIllness.indexOf(name.illnessName) > -1} />
                        <ListItemText primary={name.illnessName} />
                      </MenuItem>
                    ))}
                  </Select>
                  </FormControl>
                </Grid>
                <Grid item xs={12} sm={6}>
                <FormControl fullWidth  margin="normal">
                <InputLabel id="demo-mutiple-checkbox-label">Select existing Alergies</InputLabel>
                  <Select
                    labelId="demo-mutiple-checkbox-label"
                    id="demo-mutiple-checkbox"
                    multiple
                    disabled={isdefaultvalue}
                    value={selectedalergy}
                    onChange={handleChangeMultipleAlergy}
                    input={<Input />}
                    renderValue={(selected) => selected.join(', ')}
                    MenuProps={MenuProps}
                  >
                    {alergy.map((name) => (
                      <MenuItem key={name.allgcondName} value={name.allgcondName}>
                        <Checkbox color="primary" checked={selectedalergy.indexOf(name.allgcondName) > -1} />
                        <ListItemText primary={name.allgcondName} />
                      </MenuItem>
                    ))}
                  </Select>
                  </FormControl>
              

                </Grid>
            <Grid item xs={12} sm={6}>
                <FormControl fullWidth required margin="normal">
                    <InputLabel>Gender</InputLabel>
                    <Select  disabled={isdefaultvalue} value={gender || (gender = isdefaultvalue ? defaultGender :"")} onChange={handleChange} name="gender">
                      <MenuItem value={"MALE"}>Male</MenuItem>
                      <MenuItem value={"FEMALE"}>Female</MenuItem>
                      <MenuItem value={"UNKNOWN"}>Unknown</MenuItem>
                    </Select>
                </FormControl>
            </Grid>
            {(gender === 'FEMALE') ? (
              <Grid item xs={12} sm={6}>
                  <FormControl component="fieldset" fullWidth  margin="normal">
                      <FormLabel component="legend">Are you pregnant ?</FormLabel>
                      <RadioGroup aria-label="ispregnant" name="ispregnant" value={ispregnant || ""} onChange={handleChange}>
                          <FormControlLabel value="Non Pregnant" control={<Radio color="primary"/>} label="No" />
                          <FormControlLabel value="Pregnant" control={<Radio color="primary"/>} label="Yes" />
                      </RadioGroup>
                  </FormControl>
              </Grid>) :null}
              </Grid> ) : null}
              { isdefaultvalue ? (
                <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Note"} message ={"The record already exist for Aadhar Number :- "+ defaultAadhaarNumber} />
              ) :null}
              { isAadharExist ? (
                <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Alert"} message ={"Aadhaar card is associated with another email id in our records"} />
              ) :null}
              
              {(isValidForm) ? (
                    <div
                      style={{ display: "flex", marginTop: 50, justifyContent: "flex-end" }}
                    >
                      { !isdefaultvalue ? (
                        <Button
                          variant="contained"
                          disabled={!isValid}
                          color="primary"
                          onClick={validateAadhar}
                        >
                          Next
                        </Button> ): (<Button
                          variant="contained"
                          color="primary"
                          onClick={handleNext}
                            >
                          Next
                        </Button> ) }
                    </div> ) :null }
       
      </Fragment>
    );
  };
export default PatientInformationComponent;