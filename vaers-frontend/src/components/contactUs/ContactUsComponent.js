
import React, { Component } from 'react';
import IMAGES from 'assets/images';
import theme from 'resources/theme';




function ContactUsComponent() {
    
    return (
        <div >
               <h1 style={{color: theme.color.brightBlue}} >Contact Us At :</h1><br />
               <p  align="center"> 
                    <a href="https://www.facebook.com/CFC-VAERS-105747035172073"><img src={IMAGES.facebook}   height={200} width={300}/></a>
                    <a href="mailto:cfc_vaers1@gmail.com"><img src={IMAGES.gmail}   height={200} width={300}/></a><br/>
                    <a href="https://twitter.com/CFC_Vaers"><img src={IMAGES.twitter}   height={150} width={160}/></a><br/>
                    <a href="https://www.instagram.com/cfc_vaers/"><img src={IMAGES.instagram}  height={150} width={150}/></a><br/>
                   
                </p>
   
        </div>
    );

    

}
export default ContactUsComponent;

