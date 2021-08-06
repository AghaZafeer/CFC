import React , { Fragment } from 'react';
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import { FormControlLabel, FormLabel, Radio, RadioGroup } from '@material-ui/core';
import Dropdown from 'react-mui-multiselect-dropdown'
import axios from 'axios';
import Multiselect from 'multiselect-react-dropdown';

// Destructuring props
const PatientInformationComponent = ({
    handleNext,
    handleChange,
    values: { firstName, lastName, email ,address, contactNo, aadharnumber,age,date,gender,ispregnant,existingIllness,existingallergy},
    formErrors
  }) => {
    const [illness, setIllness] = React.useState([]);
    const [selectedIllness, setSelectedIllness] = React.useState([]);
    const [alergy, setAlergy] = React.useState([]);
    const [selectedalergy, setSelectedalergy] = React.useState([]);

    React.useEffect(() => {
      axios.get('/getIllnessList').then(res => {
        setIllness(res.data);
      }).catch(err => console.log(err))
      axios.get('/getAllergicConditionsList').then(res => {
        setAlergy(res.data);
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
      gender.length > 0;

    return (
      <Fragment>
        <Grid container spacing={2} noValidate>
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
              fullWidth
              label="Email"
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
          <FormControl fullWidth required margin="normal">
          <Dropdown
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
              />
              </FormControl>
          </Grid>
          <Grid item xs={12} sm={6}>
          <FormControl fullWidth required margin="normal">
          <Dropdown
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
              />
              </FormControl>
          </Grid>
          </Grid>
          <Grid container spacing={2} noValidate>
        <Grid item xs={12} sm={6}>
            <FormControl fullWidth required margin="normal">
                <InputLabel>Gender</InputLabel>
                <Select value={gender} onChange={handleChange} name="gender">
                  <MenuItem value={"Male"}>Male</MenuItem>
                  <MenuItem value={"Female"}>Female</MenuItem>
                </Select>
            </FormControl>
        </Grid>
        {(gender === 'Female') ? (
          <Grid item xs={12} sm={6}>
              <FormControl component="fieldset" fullWidth  margin="normal">
                  <FormLabel component="legend">Are you pregnant ?</FormLabel>
                  <RadioGroup aria-label="ispregnant" name="ispregnant" value={ispregnant || ""} onChange={handleChange}>
                      <FormControlLabel value="Non Pregnant" control={<Radio color="primary"/>} label="No" />
                      <FormControlLabel value="Pregnant" control={<Radio color="primary"/>} label="Yes" />
                  </RadioGroup>
              </FormControl>
          </Grid>) :null}
         
        </Grid>
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
        </div>
      </Fragment>
    );
  };
export default PatientInformationComponent;