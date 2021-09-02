import React, { useEffect } from 'react';
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { FormControl, FormControlLabel, FormLabel, InputLabel, MenuItem, Radio, RadioGroup, Select } from '@material-ui/core';
import axios from 'axios';

// Destructuring props
const ReporterInformationComponent = ({
    handleNext,
    handleBack,
    handleChange,
    values: {relationToUser,reporterTitle , 
      reporterFirstName,reporterMiddleName,reporterLastName,reporterContactNo,reporterAltContactNo,reporterEmail},
    formErrors
  }) => {
    const [titles, setTitles] = React.useState([]);
    const isValid =
      relationToUser.length > 0 &&
       !formErrors.relationToUser;
       const [selectedValue, setSelectedValue] = React.useState(false);

React.useEffect(() => {
    if(relationToUser === 'SELF'){
      setSelectedValue(true);
    }
    axios.get('/getListOfUserTitles').then(res => {
      setTitles(res.data);
    }).catch(err => console.log(err))
  },[]);
    
  const handleAutoFill = (value) => {
       if(value === 'SELF'){
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
                    <FormControlLabel value="HEALTHCARE_PROFESSIONAL" control={<Radio onClick = 
                            {(e) => {handleAutoFill(e.target.value)}} color="primary"/>} label="Healthcare Professional" />
                    <FormControlLabel value="SELF" control={<Radio onClick = 
                            {(e) => {handleAutoFill(e.target.value)}} color="primary"/>} label="Self" />
                    <FormControlLabel value="RELATIVE" control={<Radio onClick = 
                            {(e) => {handleAutoFill(e.target.value)}} color="primary"/>} label="Relative" />
                     <FormControlLabel value="OTHERS" control={<Radio onClick = 
                            {(e) => {handleAutoFill(e.target.value)}} color="primary"/>} label="Others" />
                </RadioGroup>
            </FormControl>
        </Grid>
        {!selectedValue ? (  
        <Grid item xs={12} sm={6}>
        <FormControl fullWidth required margin="normal">
            <InputLabel>Title</InputLabel>
            <Select value={reporterTitle} onChange={handleChange} name="reporterTitle">
            {titles.map((tit) =>
            <MenuItem value={tit.userTitleName}>{tit.userTitleName}</MenuItem>
            )}
            </Select>
        </FormControl>
        </Grid> ) :null }
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
              label="MiddleName"
              name="reporterMiddleName"
              placeholder="reporterMiddleName"
              margin="normal"
              value={reporterMiddleName || ""}
              onChange={handleChange}
              error={!!formErrors.reporterMiddleName}
              helperText={formErrors.reporterMiddleName}
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
              label="Alternative ContactNo"
              name="reporterAltContactNo"
              placeholder="reporterAltContactNo"
              margin="normal"
              value={reporterAltContactNo || ""}
              onChange={handleChange}
              error={!!formErrors.reporterAltContactNo}
              helperText={formErrors.reporterAltContactNo}
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
              type="email"
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
          	style={{ background: '#07A9E0',marginLeft: 20 }}
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