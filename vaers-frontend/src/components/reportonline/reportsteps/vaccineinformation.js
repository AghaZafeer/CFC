import React from 'react';
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import { FormControl, FormLabel, InputLabel, MenuItem, Radio, RadioGroup, Select, TextField, withStyles } from '@material-ui/core';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import CustomizeTable from 'resources/customizeTable';
import  { CustomizeDialog } from 'resources/customizeDialog';
import axios from 'axios';
import { checkDateValidation, eleContainsInArray } from 'resources/utilities';
import { CustomAlertDialog } from 'resources/customAlertDialog';
import moment from 'moment';

const VaccineInformationComponent = ({
    handleNext,
    handleBack,
    handleChange,
    handleState,
    values: {vaccinename,dosages,instituteName,dateVaccination,userBeneficiaryRefID, symptom},
    formErrors
  }) => {
    const [radioValue, setRadioValue] = React.useState();
    const [open, setOpen] = React.useState(false);
    const [selectedValue, setSelectedValue] = React.useState(false);
    const [alertError, setAlertError] = React.useState(false);
    const [noOfVaccines, setNoOfVaccines] = React.useState([]);
    const [isRefIdExist, setIsRefIdExist] = React.useState(false);
    const [refIdMessage, setRefIdMessage] = React.useState("");
    const [isAdverseEventExist, setIsAdverseEventExist] = React.useState(false);
    React.useEffect(() => {
      axios.get('/getVaccineList').then(res => {
        setNoOfVaccines(res.data)
        console.log("Sysmtoms", symptom);
        if(symptom.length >0) {
          for (var d =0 ;d<symptom.length;d++) {
            if(symptom[d].adverseEffectID !==undefined) {
             setState(prevState => [...prevState, {"adverseEffectID":symptom[d].adverseEffectID, "severityID" : symptom[d].severityID, 
            "adverseEffectReportingDate": symptom[d].adverseEffectReportingDate,
          }] );
        }
          setRadioValue(true);
          setSelectedValue(true);
        }
      }
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
    const validateReferenceId = (event) => {
      const form = {'beneficiaryRefID': userBeneficiaryRefID,
    'vaccineName' : vaccinename , 'vaccineDose' : dosages}
      axios.post('/validateBeneficiaryRefID', form)
      .then(response => {
        if (!response.data.isValid) {
          setIsRefIdExist(true);
          setRefIdMessage(response.data.message);
          return null;
        } else {
          handleNext();
        }
      }).catch(err => {   setIsRefIdExist(true);return null;});
    }; 

    const handleCallback =(items) => {
      console.log(items);
      if(items.length ==0 )
        setSelectedValue(false);
       if(items.length >0) {
        handleState(prevState,{items});
       }
    }
  const handleClose = (symptom,severity,dateStartedAdvEvt,isfatal,dateOfDeath,
    isrecovered, recoveryDate) => {
    if(checkDateValidation(dateVaccination,dateStartedAdvEvt)){
      if (checkDateValidation(dateStartedAdvEvt,recoveryDate)){
       if(checkDateValidation(dateStartedAdvEvt,dateOfDeath)) {
        setOpen(false);
        if (symptom !== undefined) {
            if(!eleContainsInArray(prevState,symptom)) {
            setState(prevState => [...prevState, {"adverseEffectID":symptom, "severityID" : severity, "adverseEffectReportingDate": dateStartedAdvEvt,
            "adverseEffectIsFatal" : isfatal ,"dateOfDeath" : dateOfDeath,
            "adverseEffectIsRecovered": isrecovered, "dateOfRecovery": recoveryDate}] );
            setSelectedValue(true);
            handleState(prevState,{"adverseEffectID":symptom,"severityID":severity,"adverseEffectReportingDate":dateStartedAdvEvt,
              "adverseEffectIsFatal" : isfatal ,"dateOfDeath" : dateOfDeath, "adverseEffectIsRecovered": isrecovered, "dateOfRecovery": recoveryDate});
            } else {
              setIsAdverseEventExist(true);
            }
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
    setIsRefIdExist(false);
    setIsAdverseEventExist(false);
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
                                {(e) => { setRadioValue(e.target.value);
                                }} color="primary"/>} label={vaccinedose.vaccineDoseName} />
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
            InputProps={{ inputProps: { max: moment().format("yyyy-MM-DD") } }}
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
            <Button style={{ background: '#07A9E0' }} disabled={!isValid}
              variant="contained"
              color="primary" onClick={handleClickOpen}
            >
            Add Adverse Event
          </Button>
         <CustomizeDialog open={open} onClose={handleClose} onChange={handleChange}/>
         {selectedValue ? (
           <StyledCustomizeTableApp prevState = {prevState} parentCallback = {handleCallback}/> 
          ) :null }
            </Grid>
            { alertError ? (
            <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Date Validation Failed"} message ={"1)Date of Vaccination should be greater than Adverse event start date.  2)Adverse event Started date should be greater than recovery Date or Date of Death."} />
            ) :null}
        </Grid>
        { isRefIdExist ? (
                <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Error"} message ={refIdMessage} />
        ) :null}
        { isAdverseEventExist ? (
                <CustomAlertDialog  onClose = {handleCloseDialog} title = {"Error"} message ={"Adverse Event already Added"} />
        ) :null}
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
          	style={{ background: '#07A9E0',marginLeft: 20 }}
            variant="contained"
            disabled={!selectedValue}
            color="primary"
            onClick={validateReferenceId}
          >
            Next
          </Button>
        </div>
      </>
    );
  };
  
export default VaccineInformationComponent;