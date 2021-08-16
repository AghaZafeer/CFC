import React , { Fragment } from 'react';
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
    values: { title, firstName,middleName, lastName, email ,address, contactNo,altcontactNo, aadharnumber,age,date,gender,ispregnant,existingIllness,existingallergy},
    formErrors
  }) => {
    const [illness, setIllness] = React.useState([]);
    const [selectedIllness, setSelectedIllness] = React.useState([]);
    const [alergy, setAlergy] = React.useState([]);
    const [selectedalergy, setSelectedalergy] = React.useState([]);
    const [titles, setTitles] = React.useState([]);
    const [isValidForm, setIsValidForm] = React.useState(false);
    const [isValidOtp, setIsValidOtp] = React.useState(false);
    const [otp, setOtp] = React.useState("");
    const [errorMessage, setErrorMessage] = React.useState("");
    let [loading, setLoading] = React.useState(false);
    let [color, setColor] = React.useState("#ffffff");
    const override = css`display: block;margin: 0 auto;border-color: blue;`;

    const handleChangeMultipleIllness = (event) => {
      setSelectedIllness(event.target.value);
      existingIllness.push(event.target.value);
    };
    const handleChangeMultipleAlergy = (event) => {
      setSelectedalergy(event.target.value);
      existingallergy.push(event.target.value);
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
      setIsValidForm(false);
      setIsValidOtp(false);
      setLoading(false);
      setErrorMessage("");
    };

    const handleChange1 = (event) => {
      setOtp(event.target.value); 
    };

    const validateOTP = (event) => {
      setLoading(true);
      const form = {'otpEmailID' : email, 'otpValue' : otp
    }
    console.log(form);
      axios.post('/validateOTP', form)
      .then(response => {
        if (response.data.isValid) {
          setIsValidForm(true);
          setLoading(false);
        } else {
          setErrorMessage("Not Valid Code ");
          setLoading(false);
        }
      }).catch(err => {setErrorMessage("Not Valid Code ");setLoading(false);});
     
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
      age.length > 0 &&
      !formErrors.age &&
      date.length > 0 &&
      gender.length > 0 &&
      title.length >0;
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
                <Button variant="contained" color="primary" onClick={reset} >Reset</Button>
              </div>
              { errorMessage &&
              <h3 style={{ color: 'red' }}> {errorMessage } </h3> }
        </Grid>  ) : null }
        {(isValidForm) ? (
        <Grid container spacing={2} noValidate>
              <Grid item xs={12} sm={6}>
                  <FormControl fullWidth required margin="normal">
                      <InputLabel>Title</InputLabel>
                      <Select value={title} onChange={handleChange} name="title">
                      {titles.map((tit) =>
                      <MenuItem value={tit.userTitleName}>{tit.userTitleName}</MenuItem>
                      )}
                      </Select>
                  </FormControl>
                  </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="First Name"
                    name="firstName"
                    placeholder="Your first name"
                    margin="normal"
                    value={firstName || ""}
                    onChange={handleChange}
                    error={!!formErrors.firstName}
                    helperText={formErrors.firstName}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Middle Name"
                    name="MiddleName"
                    placeholder="Your Middle name"
                    margin="normal"
                    value={middleName || ""}
                    onChange={handleChange}
                    error={!!formErrors.middleName}
                    helperText={formErrors.middleName}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Last Name"
                    name="lastName"
                    placeholder="Your last name"
                    margin="normal"
                    value={lastName || ""}
                    onChange={handleChange}
                    error={!!formErrors.lastName}
                    helperText={formErrors.lastName}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    disabled = "true"
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
                      label="Address"
                      name="address"
                      placeholder="Your address"
                      value={address || ""}
                      onChange={handleChange}
                      margin="normal"
                      />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Contact Number"
                    name="contactNo"
                    placeholder="i.e: xxx-xxx-xxxx"
                    margin="normal"
                    value={contactNo || ""}
                    onChange={handleChange}
                    error={!!formErrors.contactNo}
                    helperText={formErrors.contactNo}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Alternative Contact Number"
                    name="altcontactNo"
                    placeholder="i.e: xxx-xxx-xxxx"
                    margin="normal"
                    value={altcontactNo || ""}
                    onChange={handleChange}
                    error={!!formErrors.altcontactNo}
                    helperText={formErrors.altcontactNo}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Aadhaar Card Number"
                    name="aadharnumber"
                    placeholder="Aadhaar Number"
                    margin="normal"
                    value={aadharnumber || ""}
                    onChange={handleChange}
                    error={!!formErrors.aadharnumber}
                    helperText={formErrors.aadharnumber}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    fullWidth
                    label="Age"
                    name="age"
                    placeholder="Enter Age"
                    margin="normal"
                    value={age || ""}
                    onChange={handleChange}
                    error={!!formErrors.age}
                    helperText={formErrors.age}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  InputLabelProps={{
                    shrink: true
                  }}
                  label="Date of birth"
                  name="date"
                  type="date"
                  defaultValue={date || ""}
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
            
              {/* <Dropdown
                      data={illness}
                      fullWidth
                      searchable
                      searchPlaceHolder='Search illness'
                      itemId='illnessName'
                      itemLabel='illnessName'
                      multiple
                      simpleValue 
                      searchByValue='illnessName'
                      itemValue='illnessName'
                      selectedValues={selectedIllness}
                      errorText='error'
                      title='Select existing Illness'
                      onItemClick={(records) => {
                        setSelectedIllness(records);
                        existingIllness.push(records);
                      }}
                      onDeleteItem={(deleted) => {
                        console.log('deleted', deleted)
                        existingIllness.slice(deleted);
                      }}
                    />*/}
                  
                </Grid>
                <Grid item xs={12} sm={6}>
                <FormControl fullWidth  margin="normal">
                <InputLabel id="demo-mutiple-checkbox-label">Select existing Alergies</InputLabel>
                  <Select
                    labelId="demo-mutiple-checkbox-label"
                    id="demo-mutiple-checkbox"
                    multiple
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
                {/*<Dropdown
                      data={alergy}
                      fullWidth
                      searchable
                      searchPlaceHolder='Search Alergies'
                      itemId='allgcondName'
                      itemLabel='allgcondName'
                      multiple
                      simpleValue 
                      searchByValue='allgcondName'
                      itemValue='allgcondName'
                      selectedValues={selectedalergy}
                      errorText='error'
                      title='Select existing Alergies'
                      onItemClick={(records) => {
                        setSelectedalergy(records);
                        existingallergy.push(records);
                      }}
                      onDeleteItem={(deleted) => {
                        console.log('deleted', deleted);
                        existingallergy.slice(deleted);
                      }}
                    /> */}

                </Grid>
            <Grid item xs={12} sm={6}>
                <FormControl fullWidth required margin="normal">
                    <InputLabel>Gender</InputLabel>
                    <Select value={gender} onChange={handleChange} name="gender">
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
              {(isValidForm) ? (
                    <div
                      style={{ display: "flex", marginTop: 50, justifyContent: "flex-end" }}
                    >
                        <Button
                          variant="contained"
                          disabled={!isValid}
                          color="primary"
                          onClick={isValid ? handleNext : null}
                        >
                          Next
                        </Button>
                    </div> ) :null }
       
      </Fragment>
    );
  };
export default PatientInformationComponent;