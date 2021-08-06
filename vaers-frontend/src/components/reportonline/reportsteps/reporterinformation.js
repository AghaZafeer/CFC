import React, { useEffect } from 'react';
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { FormControl, FormControlLabel, FormLabel, Radio, RadioGroup } from '@material-ui/core';

// Destructuring props
const ReporterInformationComponent = ({
    handleNext,
    handleBack,
    handleChange,
    values: {relationToUser,reporterFirstName,reporterLastName,reporterContactNo,reporterAddress,reporterEmail},
    formErrors
  }) => {
    const isValid =
      relationToUser.length > 0 &&
       !formErrors.relationToUser;
       const [selectedValue, setSelectedValue] = React.useState(false);

  useEffect(() => {
    if(relationToUser === 'self'){
      setSelectedValue(true);
    }
  });
    
  const handleAutoFill = (value) => {
       if(value === 'user'){
        setSelectedValue(true);
       } else {
        setSelectedValue(false);
       }
      };
    return (
      <>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <FormControl component="fieldset" fullWidth required margin="normal">
                <FormLabel component="legend">Relation to User:</FormLabel>
                <RadioGroup aria-label="relationToUser" name="relationToUser" value={relationToUser || ""} onChange={handleChange}>
                    <FormControlLabel value="Heathcare Professional/Staff" control={<Radio onClick = 
                            {(e) => {handleAutoFill(e.target.value)}} color="primary"/>} label="Heathcare Professional/Staff" />
                    <FormControlLabel value="self" control={<Radio onClick = 
                            {(e) => {handleAutoFill(e.target.value)}} color="primary"/>} label="Self" />
                    <FormControlLabel value="Other" control={<Radio onClick = 
                            {(e) => {handleAutoFill(e.target.value)}} color="primary"/>} label="Parent/Guardian/Relative/Caregiver" />
                </RadioGroup>
            </FormControl>
        </Grid>
        {!selectedValue ? (  
        <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="FirstName"
              name="reporterFirstName"
              placeholder="reporterFirstName"
              margin="normal"
              value={reporterFirstName || ""}
              onChange={handleChange}
              error={!!formErrors.reporterFirstName}
              helperText={formErrors.reporterFirstName}
              required
            />
          </Grid> ) :null }
          {!selectedValue ? ( 
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="LastName"
              name="reporterLastName"
              placeholder="reporterLastName"
              margin="normal"
              value={reporterLastName || ""}
              onChange={handleChange}
              error={!!formErrors.reporterLastName}
              helperText={formErrors.reporterLastName}
              required
            />
           </Grid> ) :null }
           {!selectedValue ? ( 
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="ContactNo"
              name="reporterContactNo"
              placeholder="reporterContactNo"
              margin="normal"
              value={reporterContactNo || ""}
              onChange={handleChange}
              error={!!formErrors.reporterContactNo}
              helperText={formErrors.reporterContactNo}
            />
         </Grid> ) :null }
         {!selectedValue ? ( 
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="Address"
              name="reporterAddress"
              placeholder="reporterAddress"
              margin="normal"
              value={reporterAddress || ""}
              onChange={handleChange}
              error={!!formErrors.reporterAddress}
              helperText={formErrors.reporterAddress}
            />
           </Grid> ) :null }
          {!selectedValue ? (
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="Email"
              name="reporterEmail"
              placeholder="reporterEmail"
              margin="normal"
              value={reporterEmail || ""}
              onChange={handleChange}
              error={!!formErrors.reporterEmail}
              helperText={formErrors.reporterEmail}
              required
            />
           </Grid> ) :null }
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
export default ReporterInformationComponent;