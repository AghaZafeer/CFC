import React from 'react';
import { Button, Checkbox, FormControl, FormControlLabel, FormLabel, InputLabel, MenuItem, Radio, RadioGroup, Select, TextField } from "@material-ui/core";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import { getSeverity, getSymptoms, getTemparatures } from './dataService';
import axios from 'axios';

export function CustomizeDialog(props) {
    const {onClose,open,onChange} = props;
    const [severity, setSeverity] = React.useState();
    const [symptom, setSymptom]= React.useState("");
    const [others, setOthers] = React.useState();
    const [dateStartedAdvEvt, setDateStartedAdvEvt] = React.useState();
    const [showFeverField, setShowFeverField] = React.useState(false);
    const [showOtherField, setShowOtherField] = React.useState(false);
    const [fatalEvt, setFatalEvt] = React.useState({isfatal: false});
    const [recoveredEvt, setRecoveredEvt] = React.useState({isrecovered: false});
    const [dateOfDeath, setDateOfDeath] = React.useState();
    const [recoveryDate, setRecoveryDate] = React.useState();
    const [showValidSeverity, setShowValidSeverity] = React.useState(false);
    const [showValidDate, setShowValidDate] = React.useState(false);
    const [noOfSymptoms, setNoOfSymptoms] = React.useState([]);
    const [noOfSeverity, setNoOfSeverity] = React.useState([]);
    const handleClose = () => {
      onClose();
    };
    const handleSave = () => {
        onClose(symptom,severity,dateStartedAdvEvt,fatalEvt.isfatal,dateOfDeath,
          recoveredEvt.isrecovered, recoveryDate);
    };
    React.useEffect(() => {
      axios.get('/getAdverseEvents').then(res => {
        setNoOfSymptoms(res.data)
      }).catch(err => console.log(err))
      axios.get('/getListOfSeverity').then(res => {
        setNoOfSeverity(res.data)
      }).catch(err => console.log(err))
     }, [])
    
    const changeSymptoms = (event) => {
        const { value } = event.target;
        setSymptom(value); 
        setSeverity();
        setDateStartedAdvEvt();
        setShowFeverField(true);
        setShowValidSeverity(false);
        setShowValidDate(false);
        if(value === "Others") {
            setShowOtherField(true);
        } else {
            setShowOtherField(false);
        }
        onChange(event);
      };
      const changeSeverity = (event) => {
        const { value } = event.target;
        setSeverity(value);
        onChange(event);
        setShowValidSeverity(true);
      };

      const changeDateStartedAdvEvt = (event) => {
        const { value } = event.target;
        setDateStartedAdvEvt(value);
        onChange(event);
        setShowValidDate(true);
      };

      const changeFatal = (event) => {
        setFatalEvt({ ...fatalEvt,[event.target.name]: event.target.checked });
        onChange(event);
      };

      const changeRecovered = (event) => {
        setRecoveredEvt({ ...recoveredEvt,[event.target.name]: event.target.checked });
        onChange(event);
      };

    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Add the Adverse Event</DialogTitle>
        <DialogContent>
            <FormControl fullWidth required margin="normal">
                    <InputLabel>Add Symptoms</InputLabel>
                        <Select value={symptom} name="symptom" onChange={(event) => changeSymptoms(event)}>
                        {noOfSymptoms.map((symp) =>
                        <MenuItem value={symp.adevName}>{symp.adevName}</MenuItem>
                        )}
                        </Select>   
            </FormControl>
            {showFeverField ? ( <FormControl fullWidth required margin="normal">
                   <TextField
                    fullWidth
                    required
                    InputLabelProps={{
                        shrink: true
                      }}
                    label="Adverse Event Started"
                    name="dateStartedAdvEvt"
                    placeholder=""
                    margin="normal"
                    type="date"
                    onChange={(event) => changeDateStartedAdvEvt(event)}
                    value={dateStartedAdvEvt || ""}
              />
                 </FormControl>):null}
            {showFeverField ? (
                 <FormControl required fullWidth  margin="normal">
                 <FormLabel component="legend">Severity:</FormLabel>
                 <RadioGroup aria-label="severity" name="severity" value={severity || ""} >
                 {noOfSeverity.map((severity) => {
                     return(
                             <FormControlLabel value={severity.sevName} control={<Radio onClick = 
                               {(e) => {changeSeverity(e)}} color="primary"/>} label={severity.sevName} />
                     ) 
                     })}
                     </RadioGroup>
                     </FormControl>
                ) : null}
              <FormControlLabel control={<Checkbox value ={!fatalEvt.isfatal} checked={fatalEvt.isfatal} name="isfatal" color="primary" onChange = 
                            {changeFatal}/>} label="is-Fatal" />
              <FormControlLabel control={<Checkbox value={!recoveredEvt.isrecovered} checked={recoveredEvt.isrecovered} name="isrecovered" color="primary" onChange = 
                            {changeRecovered}/>} label="is-Recovered" />
            {fatalEvt.isfatal ? ( 
               <FormControl fullWidth required margin="normal">
                <TextField
                fullWidth
                InputLabelProps={{
                  shrink: true
                }}
                label="Date of Death"
                name="dateOfDeath"
                type='date'
                placeholder="Date of Death"
                defaultValue={dateOfDeath || ""}
                margin="normal"
                onChange = 
                {(e) => {setDateOfDeath(e.target.value)}}
                required/> </FormControl>) : null}
             {recoveredEvt.isrecovered ? ( 
               <FormControl fullWidth required margin="normal">
                <TextField
                fullWidth
                InputLabelProps={{
                  shrink: true
                }}
                label="Date of Recovery"
                name="recoveryDate"
                type='date'
                placeholder="Date of Recovery"
                onChange = 
                {(e) => {setRecoveryDate(e.target.value)}}
                defaultValue={recoveryDate || ""}
                margin="normal"
                required/> </FormControl>) : null}
             {showOtherField ? (
                <FormControl fullWidth margin="normal">
                   <TextField
                    fullWidth
                    label="Others"
                    name="Others"
                    placeholder="Others Information"
                    margin="normal"
                    value={others || ""}
              />
                 </FormControl>
                ) : null}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button  disabled={!showValidSeverity ? true : (!showValidDate)} onClick={handleSave} color="primary">
            Save
          </Button>
        </DialogActions>
      </Dialog>
    );
  }
 
  