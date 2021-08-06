import React, { Component } from 'react';

import { AppBar, Button,IconButton,makeStyles,MuiThemeProvider,TextField, Toolbar, Typography } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';


const useStyles = makeStyles((theme) => ({
    input: {
      background: "rgb(255, 255, 255);"
      
    }
  }));
class Login extends Component {
constructor(props){
  super(props);
  this.state={
  username:'',
  password:''
  }
  
 }
 
render() {
    
    return ( 
      <div>
        <MuiThemeProvider>
          <div>
        <AppBar position="static">
            <Toolbar>
            <IconButton edge="start"  color="inherit" aria-label="menu">
                <MenuIcon />
            </IconButton>
            <Typography variant="h6" >
                VAERS Login
            </Typography>
            </Toolbar>
         </AppBar>
      <br/> <br/>
            <TextField
             label="Username"
             name="Username"
            
             placeholder="Enter your Username"
             onChange = {(event,newValue) => this.setState({username:newValue})}
             />
           <br/>
             <TextField
                label="Password"
                name="Password"
               
               type="password"
               placeholder="Enter your Password"
               onChange = {(event,newValue) => this.setState({password:newValue})}
               />
             <br/> <br/>
             <Button
            variant="contained"
            color="primary"
            onClick={(event) => this.handleClick(event)}
          >
            Submit
          </Button>
             
         </div>
         </MuiThemeProvider>
      </div>
    );
  }
}

export default Login;