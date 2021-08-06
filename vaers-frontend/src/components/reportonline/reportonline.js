import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import PatientinformationComponent from './reportsteps/patientinformation';
import ReporterInformationComponent from './reportsteps/reporterinformation';
import VaccineInformationComponent from './reportsteps/vaccineinformation';
import AdditionalInformationComponent from './reportsteps/additionalinformation';
import formValidation from 'validator/formValidation';
const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
  },
  button: {
    marginTop: theme.spacing(1),
    marginRight: theme.spacing(1),
  },
  actionsContainer: {
    marginBottom: theme.spacing(2),
  },
  resetContainer: {
    padding: theme.spacing(3),
  },
}));

function getSteps() {
  return ['Vaccinated User Information','Reporter Information', 'Vaccine & Adverse Event', 'Summary'];
}

const initialValues = {
  firstName: "",
  lastName: "",
  email: "",
  gender: "",
  date: "",
  city: "",
  phone: "",
  aadharnumber:"",
  age:"",
  date:"",
  gender:"",
  ispregnant:"",
  address:"",
  contactNo:"",
  relationToUser:"",
  reporterFirstName:"",
  reporterLastName :"",
  reporterContactNo :"",
  reporterAddress :"",
  reporterEmail :"",
  vaccinename:"",
  dosages:"",
  instituteName:"",
  dateVaccination:"",
  symptom:[{}],
  existingIllness:[],
  existingallergy:[],
  severity:"",
  dateStartedAdvEvt:"",
  isfatal:"",
  isrecovered:"",
  recoveryDate:"",
  dateOfDeath:""
};

const fieldsValidation = {
  firstName: {
    error: "",
    validate: "text",
    minLength: 2,
    maxLength: 20
  },
  lastName: {
    error: "",
    validate: "text",
    minLength: 2,
    maxLength: 20
  },
  email: {
    error: "",
    validate: "email"
  },
  relationToUser: {},
  date: {},
  city: {
    error: "",
    validate: "text",
    minLength: 3,
    maxLength: 20
  },
  phone: {
    error: "",
    validate: "phone",
    maxLength: 15
  },
  address: {},

  aadharnumber: {
    error: "",
    validate: "aadharnumber",
    minLength: 12,
    maxLength: 12
},
  contactNo: {
    error: "",
    validate: "contactNo",
    maxLength: 15
  },
  reporterFirstName:{},
  reporterLastName :{},
  reporterContactNo :{},
  reporterAddress :{},
  reporterEmail :{},
  vaccinename :{},
  dosages :{},
  instituteName :{},
  dateVaccination :{},
  age:{},
  date:{},
  gender:{},
  ispregnant:{},
  symptom:[{}],
  severity:{},
  dateStartedAdvEvt:{},
  isfatal:{},
  isrecovered:{},
  recoveryDate:{},
  dateOfDeath:{},
  
};

export default function ReportOnlineComponent() {
  const classes = useStyles();
  const [activeStep, setActiveStep] = React.useState(0);
  const steps = getSteps();
  const [formValues, setFormValues] = React.useState(initialValues);
  const [prevState, setState] = React.useState([]);
  const [formErrors, setFormErrors] = React.useState({});
  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleState = (prevState,nextState) => {
    setFormValues((prev) => ({
      ...prev,
      ["symptom"]: [...prevState, {name:nextState.name,severity:nextState.severity,
        datestartedadvevt:nextState.datestartedadvevt, 
        isfatal : nextState.isfatal ,
        dateOfDeath : nextState.dateOfDeath,
        isrecovered: nextState.isrecovered, 
        recoveryDate: nextState.recoveryDate }]
    }));
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if(name !== 'symptom') {
      setFormValues((prev) => ({
        ...prev,
        [name]: value
      }));
      if(value === 'user') {
        setFormValues((prev) => ({
          ...prev,
          ['reporterFirstName']: ""
        }));
        setFormValues((prev) => ({
          ...prev,
          ['reporterLastName']: ""
        }));
        setFormValues((prev) => ({
          ...prev,
          ['reporterContactNo']: ""
        }));
        setFormValues((prev) => ({
          ...prev,
          ['reporterAddress']: ""
        }));
        setFormValues((prev) => ({
          ...prev,
          ['reporterEmail']: ""
        }));
      }
    } 
    const error = formValidation(name, value, fieldsValidation) || "";
    setFormErrors({
      [name]: error
    });
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  const handleSteps = (step) => {
    switch (step) {
      case 0:
        return (
          <PatientinformationComponent
            handleNext={handleNext}
            handleChange={handleChange}
            values={formValues}
            formErrors={formErrors}
          />
        );
      case 1:
        return (
          <ReporterInformationComponent
            handleNext={handleNext}
            handleBack={handleBack}
            handleChange={handleChange}
            values={formValues}
            formErrors={formErrors}
          />
        );
      case 2:
          return (
          <VaccineInformationComponent  
          handleNext={handleNext} 
          handleChange={handleChange}
          handleBack={handleBack} 
          handleState={handleState}
          values={formValues}
          formErrors={formErrors}/>);  
      case 3:
          return (
          <AdditionalInformationComponent  
          handleNext={handleNext} 
          handleBack={handleBack} 
          values={formValues}
          formErrors={formErrors}/>);   
      default:
        break;
    }
  };
  return (
    <>
    {activeStep === steps.length ? (
     <Paper square elevation={0} className={classes.resetContainer}>
     <Typography>Thank you for your participation . Appreciate for your valuable information</Typography>
     </Paper>
    ) : ( <>
      <div className={classes.root}>
        <Stepper activeStep={activeStep}>
          {steps.map((label, index) => (
            <Step key={label}>
              <StepLabel>{label}</StepLabel>
            </Step>
          ))}
        </Stepper>
      </div>
      {handleSteps(activeStep)}
     </> 
     )} 
     </>
  );
}