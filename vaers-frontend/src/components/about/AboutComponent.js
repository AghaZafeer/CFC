import React, { Component } from 'react';
import theme from 'resources/theme';

function AboutComponent() {
    
    return (
        <div >
               <h1 style={{color: theme.color.brightBlue}} >About</h1><br />
               <b style={{color: theme.color.veryDarkGrayishBlue}}> 
                    
                    COVID Vaccine Adverse Event Reporting and Analysis (CVAERA) is a unified portal available in public domain and accessible by receivers of COVID vaccines/healthcare professionals who want to report any adverse event or side effects experienced post vaccination. CVAERA accepts information about symptoms of illness and generates reports for further analysis by vaccine providers, healthcare organizations or government health departments.<br/>
                    CVAERA is a passive reporting system, meaning it relies on individuals to send in reports of their experiences. It is not designed to determine if a vaccine caused a health problem, but is especially useful for detecting unusual or unexpected patterns of adverse event reporting that might indicate a possible safety problem with a vaccine. <br/><br/>
                    {/* <b style={{color: theme.color.paleBlue}} >Objectives of CVAERA </b><br/> */}
                    <b>The primary objectives of CVAERA are to:</b><br/>
                    Detect new, unusual, or rare vaccine adverse events;<br/>
                    Monitor increases in known adverse events;<br/>
                    Determine and address possible reporting clusters;<br/>
                    Recognize persistent safe-use problems and administration errors;<br/>
                    Provide information to safety monitoring system that extends to the entire general population for response to public health emergencies, such as a large-scale pandemic influenza vaccination program.
                   
                </b>
   
        </div>
    );

    

}
export default AboutComponent;