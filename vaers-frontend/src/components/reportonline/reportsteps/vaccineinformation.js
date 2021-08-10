import React from 'react';
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import { FormControl, FormLabel, InputLabel, MenuItem, Radio, RadioGroup, Select, TextField, withStyles } from '@material-ui/core';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import CustomizeTable from 'resources/customizeTable';
import  { CustomizeDialog } from 'resources/customizeDialog';
import axios from 'axios';
import { checkDateValidation } from 'resources/utilities';
import { CustomAlertDialog } from 'resources/customAlertDialog';

const VaccineInformationComponent = ({
    handleNext,
    handleBack,
    handleChange,
    handleState,
    values: {vaccinename,dosages,instituteName,dateVaccination},
    formErrors
  }) => {
    const [radioValue, setRadioValue] = React.useState();
    const [open, setOpen] = React.useState(false);
    const [selectedValue, setSelectedValue] = React.useState(false);
    const [alertError, setAlertError] = React.useState(false);
    const [noOfVaccines, setNoOfVaccines] = React.useState([]);
    React.useEffect(() => {
      axios.get('/getVaccineList').then(res => {
        setNoOfVaccines(res.data)
      }).catch(err => console.log(err))
     }, [])

   const isValid = 
    vaccinename.toString().length > 0 &&
      !formErrors.vaccinename && 
    dosages.toString().length > 0 &&
      !formErrors.dosages &&
    instituteName.length > 0 &&
      !formErrors.instituteName &&
    dateVaccination.length > 0 &&
      !formErrors.dateVaccination;
    const styles = theme => ({
      root: {
        width: "100%",
        marginTop: theme.spacing.unit * 2,
        overflowX: "auto"
      },
      table: {}
    });
    const StyledCustomizeTableApp = withStyles(styles)(CustomizeTable);
    const [prevState, setState] = React.useState([]);
    const handleClickOpen = () => {
      setOpen(true);
    }; 

  const handleClose = (symptom,severity,dateStartedAdvEvt,isfatal,dateOfDeath,
    isrecovered, recoveryDate) => {
    if(checkDateValidation(dateVaccination,dateStartedAdvEvt)){
      if (checkDateValidation(dateStartedAdvEvt,recoveryDate)){
       if(checkDateValidation(dateStartedAdvEvt,dateOfDeath)) {
        setOpen(false);
        if (symptom !== undefined) {
            setState(prevState => [...prevState, {"adverseEffectID":symptom, "severityID" : severity, "adverseEffectReportingDate": dateStartedAdvEvt,
            "adverseEffectIsFatal" : isfatal ,"dateOfDeath" : dateOfDeath,
            "adverseEffectIsRecovered": isrecovered, "dateOfRecovery": recoveryDate}] );
            setSelectedValue(true);
            handleState(prevState,{"adverseEffectID":symptom,"severityID":severity,"adverseEffectReportingDate":dateStartedAdvEvt,
              "adverseEffectIsFatal" : isfatal ,"dateOfDeath" : dateOfDeath, "adverseEffectIsRecovered": isrecovered, "dateOfRecovery": recoveryDate});
        }
    } else {
      setAlertError(true);
    } }else {
      setAlertError(true);
    } }else {
      setAlertError(true);
    }
  };

 const handleCloseDialog = () => {
    setAlertError(false);
   };

    return (
      <>
        <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
            <FormControl fullWidth required margin="normal">
                <InputLabel>Name of the Vaccine</InputLabel>
                <Select value={vaccinename} onChange={handleChange} name="vaccinename">
                {noOfVaccines.map((vaccine) =>
                <MenuItem value={vaccine.vaccineName}>{vaccine.vaccineName}</MenuItem>
                )}
                </Select>
            </FormControl>
            </Grid>
            {noOfVaccines.map((vaccine) => {
                 if(vaccine.vaccineName === vaccinename) {
                  return (<FormControl fullWidth required margin="normal">
                  <FormLabel component="legend">Dosage:</FormLabel>
                  <RadioGroup aria-label="dosages" name="dosages" value={dosages || ""}  onChange={handleChange}>
                  {vaccine.vaccineDoses.map((vaccinedose) => {
                      return(
                              <FormControlLabel value={vaccinedose.vaccineDoseName.toString()} control={<Radio onClick = 
                                {(e) => {setRadioValue(e.target.value)}} color="primary"/>} label={vaccinedose.vaccineDoseName} />
                      ) 
                      })}
                      </RadioGroup>
                      </FormControl> )
                 }
              })}
        </Grid>
        <Grid container spacing={2} style={ radioValue ? {} : { display: 'none' }} >
          <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                label="Vaccination Center"
                name="instituteName"
                placeholder="Where you have taken the Vaccine"
                margin="normal"
                value={instituteName || ""}
                onChange={handleChange}
                error={!!formErrors.instituteName}
                helperText={formErrors.instituteName}
                required
              />
            </Grid>
            <Grid item xs={12} sm={6}>
            <TextField
            fullWidth
            InputLabelProps={{
              shrink: true
            }}
            label="Date of Vaccination"
            name="dateVaccination"
            type="date"
            defaultValue={dateVaccination || ""}
            onChange={handleChange}
            margin="normal"
            required
          />
            </Grid>
            <Grid item xs={12}>
            <Button disabled={!isValid}
              variant="contained"
              color="primary" onClick={handleClickOpen}
            >
            Add Adverse Event
          </Button>
         <CustomizeDialog open={open} onClose={handleClose} onChange={handleChange}/>
         {selectedValue ? (
           <StyledCustomizeTableApp prevState = {prevState}/> 
          ) :null }
            </Grid>
            { alertError ? (
            <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Date Validation Failed"} message ={"1)Date of Vaccination should be greater than Adverse event start date.  2)Adverse event Started date should be greater than recovery Date or Date of Death."} />
            ) :null}
        </Grid>
        <div
          style={{ display: "flex", marginTop: 50, justifyContent: "flex-end" }}
        >
          <Button
            variant="contained"
            color="default"
            onClick={handleBack}
            style={{ marginRight: 10 }}
          >
            Back
          </Button>
          <Button
            variant="contained"
            disabled={!isValid}
            color="primary"
            onClick={isValid ? handleNext : null}
          >
            Next
          </Button>
        </div>
      </>
    );
  };
  
export default VaccineInformationComponent;